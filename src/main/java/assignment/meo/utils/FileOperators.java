package assignment.meo.utils;

import assignment.meo.entity.Point;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class FileOperators {

    public static File getFile(String... path) {
        if (path == null) {
            throw new NullPointerException("Path cannot be null");
        } else {
            File file = null;
            String[] var2 = path;
            int var3 = path.length;
            for(int var4 = 0; var4 < var3; ++var4) {
                String name = var2[var4];
                if (file == null) {
                    file = new File(name);
                } else {
                    file = new File(file, name);
                }
            }
            return file;
        }
    }

    public static List<String> readFrom (String path) {
        if(path!=null && path.length()>0) {
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                List<String> lines = br.lines().collect(Collectors.toList());
                return lines;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void updateTreeFile(File file, Point point) {
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(point.getTrees().size()>0) {
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<point.getTrees().size();j++) {
                if(j<4) {
                    sb.append(point.getTrees().get(j).getNum());
                    sb.append(",");
                } else {
                    sb.append(point.getTrees().get(j).getNum());
                }
            }
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                bw.write(sb.toString());
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
