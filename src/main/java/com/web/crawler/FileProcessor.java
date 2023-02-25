package com.web.crawler;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Logger;

public class FileProcessor {
    private static Logger LOG = Logger.getLogger(FileProcessor.class.getName());

    private static final String JSON_FILE_SUFFIX = ".json";
    private static final String FILE_PATH_DELIMITER = System.getProperty("file.separator");

    public static File createJsonFile(String jsonFileName, Path parentPath) {
        String filePath;
        String fileName = UtilFuncs.specialCharacterProcessor(jsonFileName);
        StringBuilder sb = new StringBuilder(parentPath.toString());
        sb.append(FILE_PATH_DELIMITER)
                .append(fileName);
        if(!fileName.contains(JSON_FILE_SUFFIX)){
            sb.append(JSON_FILE_SUFFIX);
        }
        filePath = sb.toString();
        File jsonFile = new File(filePath);
        try{
            if (jsonFile.exists()) {
                LOG.info("File already exists");
            } else {
                LOG.info("New File Created");
                jsonFile.createNewFile();
            }
            return jsonFile;
        } catch (IOException e) {
            LOG.throwing(FileProcessor.class.getName(), "createJsonFile", e.getCause());
        }
        return null;
    }

    public static File createFolder(String dirName, Path parentPath) {
        StringJoiner sj = new StringJoiner(FILE_PATH_DELIMITER);
        sj.add(parentPath.toString()).add(dirName);
        File dir = new File(sj.toString());
        if(dir.exists()) {
            LOG.info("The directory already exists");
        } else {
            LOG.info("New Directory Created");
            dir.mkdirs();
        }
        return dir;
    }

    public static void writeInJsonObject(File jsonFile, JSONObject jo) {
        if(jo!=null) {
            try{
                if(!jsonFile.exists()) {
                    createJsonFile(jsonFile.getName(), jsonFile.getParentFile().toPath());
                }
                FileUtils.writeStringToFile(jsonFile, jo.toString(4), StandardCharsets.UTF_8, true);
            } catch (IOException e) {
                LOG.throwing(FileProcessor.class.getName(), "writeInJsonObject", e.getCause());
            }
        }
    }

    public static void renameFiles(File dir) {
        if(dir.isDirectory()) {
            List<File> dirs = Arrays.asList(dir.listFiles());
            if(dirs.size()>0) {
                dirs.stream().forEach(d->{
                    if(d.isDirectory()) {
                        Arrays.asList(d.listFiles()).stream().forEach(f->{
                            if(f.isFile()) {
                                renameFile(f);
                            }
                        });
                    } else {
                        renameFile(d);
                    }
                });
            }

        }
    }

    private static void renameFile(File file) {
        if(file.exists()) {
            synchronized (file) {
                File parent = file.getParentFile();
                StringBuilder path = new StringBuilder(parent.getAbsolutePath());
                path.append(FILE_PATH_DELIMITER)
                        .append(UtilFuncs.specialCharacterProcessor(file.getName()))
                        .append(JSON_FILE_SUFFIX);
                File newFile = new File(path.toString());
                if(!newFile.exists()) {
                    if(file.renameTo(newFile)) {
                        LOG.info(file.getName() + " has been renamed to "+newFile.getName());
                    }
                }
            }
        }
    }
}
