package com.wil.practice.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class EmailFormatter {

    public String evaluate(Map<String, Object> context) {
        List<UserEmail> emailList = Contexts.get(context, "email_object");
        if(emailList !=null && emailList.size()>0) {
            String emailStr = emailList.get(0).getEmailAddress();
            if(null!=emailStr) {
                emailStr = emailStr.toLowerCase();
                if(!emailStr.contains("@")) {
                    return emailStr;
                }
                String[] splits = emailStr.split("@", 2);
                String emailName = splits[0];
                String domain = splits[1];
                if(domain.contains("gmail") || domain.contains("googlemail")) {
                    emailName = emailName.replace(".", "");
                    emailName = emailName.split("\\+", 2)[0];
                }
                String graphEmail = new StringJoiner("@").add(emailName).add(domain).toString();
                return graphEmail;
            }
        }
        return null;
    }

    private class UserEmail{
        private String emailAddress;

        public UserEmail(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public String getEmailAddress() {
            return emailAddress;
        }
    }
}


class Contexts{

    public static <T> T get(Map<String, Object> context, String varName) {
        Object varValue = context.get(varName);
        return cast(varValue);
    }

    public static <T> T cast(Object varValue) {
        if(varValue == null || varValue.equals(NullValue.NULL)) {
            return null;
        } else {
            return (T) varValue;
        }
    }
}

final class NullValue {
    public static final NullValue NULL = new NullValue();

    private NullValue() {
    }
    @Override
    public String toString(){return "NULL";}

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else {
            return obj instanceof NullValue;
        }
    }

    @Override
    public int hashCode() {return 0;}
}