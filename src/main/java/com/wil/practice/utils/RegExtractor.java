package com.wil.practice.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExtractor {

    private Pattern p;
    private String deleteChar;
    private static Field[] key;
    private int beginIndex = -1;
    private int length = -1;

    private static boolean isEmptyString(String s) {
        return (s == null || "".equals(s.trim()));
    }

    private String subString(String result) {
        if(StringUtils.isEmpty(result)) {
            return null;
        }

        if(beginIndex > -1) {
            if(length > 0 && length + beginIndex <= result.length()) {
                result = result.substring(beginIndex, beginIndex + length);
            } else {
                result = StringUtils.substring(result, beginIndex);
            }
        }

        if(!isEmptyString(deleteChar)) {
            result = result.replaceAll(deleteChar, "");
        }
        if(length == -1) {
            return result.trim();
        } else {
            if(result.length() > length) {
                result = result.substring(0, length);
            } else if (result.length()<length) {
                result = StringUtils.rightPad(result, length);
            }
            // When define the length, the space should be kept
            return result;
        }
    }

    public String regex(String text, String reg) {
        String res;
        String temp;
        StringBuilder result = null;
        p = Pattern.compile(reg, Pattern.DOTALL);
        Matcher m = p.matcher(text);
        if(m.find()) {
            temp = m.group();
            m = null;
            if(StringUtils.isNotEmpty(temp)) {
                result = new StringBuilder(subString(temp));
            }
        }
        if (result.length() == 0 || result == null) {
            return null;
        }
        res = result.toString();
        p = null;
        return res;
    }

    private static boolean hasValue(Object o) {

        if(o == null ) {
            return false;
        }
        if(o.getClass().isArray()) {
            if(Array.getLength(o) == 0) {
                return false;
            }
            String s = String.valueOf(Array.get(o,0));
            if(s.equals("null") || s.trim().equals("")) {
                return false;
            }
            return true;
        }

        if(o instanceof List) {
            List<?> list = (List<?>) o;
            if(list.size() > 0) {
                return true;
            } else {
                return false;
            }
        }
        String s = o.toString();
        if(isEmptyString(s)) {
            return false;
        }
        return true;
    }

}
