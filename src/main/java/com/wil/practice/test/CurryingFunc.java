package com.wil.practice.test;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CurryingFunc {

    public static void main(String[] args) {
        Letter letter = Letter.builder()
                .withReturnAddress("test@gmail.com")
                .withClosing("closing")
                .withDateOfLetter(FastDateFormat.getInstance("yyyy-MM-dd", TimeZone.getTimeZone("GMT-8:00")).format(new Date()))
                .withInsideAddress("inside address")
                .withSalutation("best regards")
                .withBody("email letter content");
        System.out.println(letter);
        System.out.println(DateFormatUtils.formatUTC(new Date(), "yyyy-MM-dd", Locale.CHINA));
    }

}




class Letter {
    private String returningAddress;
    private String insideAddress;
    private String dateOfLetter;
    private String salutation;
    private String body;
    private String closing;

    Letter(String returningAddress, String insideAddress, String dateOfLetter,
           String salutation, String body, String closing) {
        this.returningAddress = returningAddress;
        this.insideAddress = insideAddress;
        this.dateOfLetter = dateOfLetter;
        this.salutation = salutation;
        this.body = body;
        this.closing = closing;
    }

    @Override
    public String toString() {
        return "Letter[" +
                "returningAddress='" + returningAddress + '\'' +
                ", insideAddress='" + insideAddress + '\'' +
                ", dateOfLetter='" + dateOfLetter + '\'' +
                ", salutation='" + salutation + '\'' +
                ", body='" + body + '\'' +
                ", closing='" + closing + '\'' +
                ']';
    }

    @FunctionalInterface
    interface AddReturnAddress {
        Letter.AddClosing withReturnAddress(String returnAddress);
    }

    @FunctionalInterface
    interface AddClosing {
        Letter.AddDateOfLetter withClosing(String closing);
    }

    @FunctionalInterface
    interface AddDateOfLetter {
        Letter.AddInsideAddress withDateOfLetter(String dateOfLetter);
    }

    @FunctionalInterface
    interface AddInsideAddress {
        Letter.AddSalutation withInsideAddress(String insideAddress);
    }

    @FunctionalInterface
    interface AddSalutation {
        Letter.AddBody withSalutation(String salutation);
    }

    @FunctionalInterface
    interface AddBody {
        Letter withBody(String body);
    }

    static AddReturnAddress builder(){
        return returnAddress
                -> closing
                -> dateOfLetter
                -> insideAddress
                -> salutation
                -> body
                -> new Letter(returnAddress, insideAddress, dateOfLetter, salutation, body, closing);
    }
}
