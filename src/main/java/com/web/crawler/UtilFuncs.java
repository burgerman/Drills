package com.web.crawler;

public class UtilFuncs {
    public static String specialCharacterProcessor(String str) {
        return str.replaceAll("/", "or")
                .replaceAll("\\\\","")
                .replaceAll("\"|\\'", "")
                .replaceAll("\\\"", "")
                .replaceAll("\\{|\\}|\\(|\\)", "")
                .replaceAll("\r|\n", "")
                .replaceAll(">|<|!|@|&|%|\\$|#|\\+|\\-|\\*", "")
                .replaceAll(";|:|=|~|json", "");
    }
}
