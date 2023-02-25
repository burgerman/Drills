package com.web.crawler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.logging.Logger;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Crawler {
    private static Logger LOG = Logger.getLogger(Crawler.class.getName());

    private static final String suffix = "?view=embed";
    private static final String pageDelimiter = "/page/";
    private static final int answerThresholdNum = 200;
    private static final int pageThreshold = 700;
    // posts with replies
    private Map<String, JSONObject> posts;
    // posts with no reply
    private Map<String, JSONObject> posts2;

    public Crawler(Map<String, JSONObject> posts, Map<String, JSONObject> posts2) {
        this.posts = posts;
        this.posts2 = posts2;
    }

    public Map<String, JSONObject> getPosts() {
        return posts;
    }

    public Map<String, JSONObject> getPosts2() {
        return posts2;
    }

    public void postUriCrawler(String baseUrl) {
        String url = baseUrl+suffix;
        try{
            Document document = Jsoup.connect(url).get();
            int countTotalPageNum = getTotalPageNum(document);
            int totalPageNum = countTotalPageNum>pageThreshold?pageThreshold:countTotalPageNum;
            document = null;
            List<String> pagesUrl = getAllPagesUrl(baseUrl, totalPageNum);
            getAllPostsUri(pagesUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> getAllPagesUrl(String baseUrl, int totalPageNum) {
        StringBuilder subPageUrl = null;
        LinkedList<String> pagesUrl = new LinkedList<>();
        pagesUrl.offer(baseUrl);
        if(totalPageNum>1) {
            for (int i = 2; i<=totalPageNum; i++) {
                subPageUrl = new StringBuilder(baseUrl);
                subPageUrl.append(pageDelimiter);
                if(i<=totalPageNum) {
                    subPageUrl.append(i);
                    pagesUrl.offer(subPageUrl.toString());
                }
            }
            subPageUrl=null;
        }
        return pagesUrl;
    }

    private void getAllPostsUri(List<String> pagesUrl) {
        String url = null;
        Document doc = null;
        for (String pageUri : pagesUrl) {
            url = new StringBuilder(pageUri).append(suffix).toString();
            try{
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                LOG.throwing(Crawler.class.getName(), "getAllPostsUri", e.getCause());
            }
            if(doc!=null) {
                List<Element> repliesEntities = doc.select(".lia-message-stats-count");
                //Ignore the num of views, count the num of replies only
                repliesEntities.removeIf(e->e.parent().text().toLowerCase().contains("views"));
                for(Element replyEntity : repliesEntities) {
                    int replyCount = Integer.valueOf(replyEntity.text().replaceAll(",", ""));
                    List<Element> siblingElements = replyEntity.parent().parent().siblingElements();
                    JSONObject postInstance;
                    for(Element siblingElement : siblingElements) {
                        if(siblingElement.attr("class").toLowerCase().contains("lia-data-cell-text")) {
                            List<Element> postUrlElements = siblingElement.select(".lia-message-unread > a");
                            for(Element postUrlElement : postUrlElements) {
                                String postUri = postUrlElement.attr("abs:href");
                                String postTitle = UtilFuncs.specialCharacterProcessor(postUrlElement.text());
                                postInstance = new JSONObject();
                                postInstance.put("post_title", postTitle);
                                postInstance.put("post_uri", postUri);
                                if (replyCount>0) {
                                    posts.put(postTitle, postInstance);
                                } else {
                                    posts2.put(postTitle, postInstance);
                                }
                            }
                        }

                    }
                }
            } else {
                LOG.info("URL: "+url+" failed to connect");
            }
        }
    }

    public void repliesFinder (String outputPath) {
        List<String> pagesUriList = null;
        JSONObject post = null;
        JSONObject question = null;
        JSONObject solution = null;
        JSONArray answers = null;
        String baseUri = null;
        String title = null;
        for(Map.Entry<String, JSONObject> entry : posts.entrySet()) {
            post = entry.getValue();
//            pagesUriList = new LinkedList<>();
            baseUri = (String) post.get("post_uri");
            title = (String) post.get("post_title");
            pagesUriList.add(baseUri);
            try {
                Document doc = Jsoup.connect(new StringBuilder(baseUri).append(suffix).toString()).get();
                int countTotalPageNum = getTotalPageNum(doc);
                int totalPageNum = countTotalPageNum>answerThresholdNum?answerThresholdNum:countTotalPageNum;
                //Get question
                question = questionFinder(doc);
                question.put("title", title);
                question.put("question", question);
                //Get solution
                solution = solutionFinder(doc);
                if(solution!=null) {
                    post.put("solution", solution);
                }
                //Get answers and respective kudos
                answers = new JSONArray();
                pagesUriList = getAllPagesUrl(baseUri, totalPageNum);
                for(String pageUri : pagesUriList) {
                    answers = answersFinder(pageUri, answers);
                }
                if(answers.length()>0) {
                    post.put("answers", answers);
                }
                File file = FileProcessor.createJsonFile(entry.getKey(), Paths.get(outputPath));
                FileProcessor.writeInJsonObject(file, post);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void zeroReplyFinder(String outputPath) {
        for(Map.Entry<String, JSONObject> entry : posts2.entrySet()) {
            JSONObject post = entry.getValue();
            String baseUri = (String) post.get("post_uri");
            String title = (String) post.get("post_title");
            try{
                Document doc = Jsoup.connect(baseUri+suffix).get();
                //Get question
                JSONObject question = ppEmployeeQuestionFinder(doc);
                if(question.get("content").toString()!=null){
                    question.put("title", title);
                    question.put("question", question);
                    File file = FileProcessor.createJsonFile(entry.getKey(), Paths.get(outputPath));
                    FileProcessor.writeInJsonObject(file, post);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static JSONArray answersFinder(String pageUri, JSONArray answers) {
        try{
            Document document = Jsoup.connect(pageUri+suffix).get();
            List<Element> paragraphsEntityList = document.select(".lia-message-body-content");
            if(paragraphsEntityList!=null) {
                for(Element element : paragraphsEntityList) {
                    JSONObject answer = new JSONObject();
                    String subject = answerSubjectFinder(element);
                    answer.put("subject", subject);
                    //Get the content of answer
                    String content = answerContentFinder(element);
                    answer.put("content", content);
                    //Get the num of kudo count
                    int kudo = kudoContentFinder(element);
                    answer.put("kudo", kudo);
                    answers.put(answer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private static int kudoContentFinder(Element element) {
        Element preElement = getParentElement(element, 5);
        List<Element> aTagElements = preElement.getElementsByTag("a");
        Element kudoEntityElement = null;
        if(aTagElements != null) {
            for(Element aTagElement : aTagElements) {
                if(aTagElement.attr("class").contains("kudos-count-link")) {
                    kudoEntityElement = aTagElement;
                }
            }
        }
        if(kudoEntityElement != null) {
            Element kudoElement = kudoEntityElement.child(0);
            if(kudoElement!=null) {
                int kudo = Integer.valueOf(kudoElement.text().replaceAll(",", ""))>0?Integer.valueOf(kudoElement.text().replaceAll(",", "")):0;
                return kudo;
            }
        }
        return 0;
    }

    private static Element getParentElement(Element current, int depth) {
        if(depth>0) {
            Element parent = current;
            for (int k = 0; k < depth; k++) {
                if(parent.parent()!=null) {
                    parent = parent.parent();
                } else {
                    return parent;
                }
            }
            return parent;
        } else {
            return current;
        }
    }

    private static String answerContentFinder(Element element) {
        List<Element> rows = element.getAllElements();
        StringJoiner sj = new StringJoiner(" ");
        if(rows!=null) {
            for(Element row : rows) {
                sj.add(row.text());
            }
            return sj.toString();
        }
        sj = null;
        return "";
    }

    private static String answerSubjectFinder(Element element) {
        Element parent = element.parent();
        Element subLayer1 = parent.previousElementSibling();
        if(subLayer1!=null) {
            List<Element> h2Elements = subLayer1.getElementsByTag("h2");
            Element subLayer2 = null;
            if(h2Elements!=null && h2Elements.size()>0) {
                subLayer2 = h2Elements.get(0);
            }
            if(subLayer2!=null) {
                Element subLayer3 = subLayer2.child(0);
                if(subLayer3!=null) {
                    Element subjectEntity = subLayer3.child(0);
                    if(subjectEntity!=null) {
                        return subjectEntity.text()!=null?subjectEntity.text():"";
                    }
                }
            }
        }
        return "";
    }

    private static JSONObject questionFinder(Document doc) {
        JSONObject question = new JSONObject();
        Element topicEntity = doc.selectFirst(".lia-component-topic-message");
        if(topicEntity!=null) {
            List<Element> contentEntity = topicEntity.select(".lia-message-body-content > p");
            if(contentEntity!=null) {
                StringJoiner sj = new StringJoiner(" ");
                contentEntity.forEach(e->sj.add(e.text()));
                question.put("question", sj.toString());
            } else {
                question.put("question", "");
            }
        } else {
            LOG.info("question body not found");
            question.put("content", "");
        }
        return question;
    }

    private static JSONObject solutionFinder(Document document) {
        Element topicEntity = document.selectFirst(".lia-component-topic-message");
        Element bodyEntity = null;
        if(topicEntity!=null) {
            bodyEntity = topicEntity.selectFirst(".lia-message-body-content");
        }
        Element uriEntity = null;
        if(bodyEntity!=null) {
            uriEntity = bodyEntity.selectFirst(".lia-panel-feedback-banner-safe > p");
        }
        Element solutionEntity = null;
        if(uriEntity!=null) {
            solutionEntity = uriEntity.getElementsByTag("a").size()>0? uriEntity.getElementsByTag("a").first():null;
        }
        if(solutionEntity!=null) {
            String solutionLink = solutionEntity.attr("abs:href");
            Element soluParentEntity = document.selectFirst(".lia-component-solution-list");
            if(soluParentEntity!=null) {
                StringJoiner sj = new StringJoiner("");
                List<Element> soluEntity = soluParentEntity.select(".lia-message-body-content > p");
                soluEntity.forEach(s->sj.add(s.text()));
                if(sj.length()>0) {
                    JSONObject solution = new JSONObject();
                    solution.put("content", sj.toString());
                    solution.put("solution_link", solutionLink);
                    return solution;
                }
            } else {
                LOG.info("solution parent entity not found");
            }
        } else {
            LOG.info("solution entity not found");
        }
        return null;
     }

    private static JSONObject ppEmployeeQuestionFinder(Document document) {
        JSONObject question = new JSONObject();
        Element topicEntity = document.selectFirst(".lia-component-topic-message");
        if(topicEntity!=null) {
            List<Element> contentEntity = topicEntity.select(".lia-message-body-content > p");
            List<Element> entities = topicEntity.getElementsByTag("div");
            if(contentEntity!=null) {
                StringJoiner sj = new StringJoiner(" ");
                entities.forEach(e-> {
                    if(e.attr("class").contains("author-rank")) {
                        if(e.text().contains("PayPal Employee")) {
                            int kudo = questionKudoFinder(topicEntity);
                            question.put("kudo", kudo);
                            contentEntity.forEach(c -> sj.add(c.text()));
                        }
                    }
                });
                question.put("content", sj.toString());
            } else {
                question.put("content", "");
            }
        } else {
            LOG.info("question body not found");
            question.put("content", "");
        }
        return question;
    }

    private static int questionKudoFinder(Element element) {
        Element divTagElement = element.getElementsByAttributeValue("class", "lia-button-image-kudos-count").first();
        int kudo = 0;
        if(divTagElement!=null) {
            Element kudoElement = divTagElement.getElementsByTag("span").first();
            if(kudoElement!=null) {
                int count = Integer.parseInt(kudoElement.text().replaceAll(",", "").replaceAll(" kudos", ""));
                kudo = count>kudo?count:kudo;
            }
        }
        return kudo;
    }


    public static List<String> urlCrawler(String baseUrl) {
        List<String> urls = new LinkedList<>();
        String url = baseUrl+suffix;
        try{
            Document document = Jsoup.connect(url).get();
            List<Element> links = document.select(".board-title > a");
            for (Element e : links) {
                String link = e.attr("abs:href");
                String title = e.text();
                if(!title.toLowerCase().contains("archives")) {
                    urls.add(link);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }

    private static int getTotalPageNum(Document document) {
        List<Element> pages = document.getElementsByClass("lia-paging-full-pages");
        if(pages.size()>0) {
            Element e = pages.get(0);
            List<Element> pageNum = e.getElementsByTag("li");
            String className = null;
            for (Element element: pageNum) {
                className = element.attr("class");
                if(className.toLowerCase().contains("page-last")) {
                    return Integer.valueOf(element.children().first().text().replaceAll(",", ""));
                }
            }
        }
        return 1;
    }
}
