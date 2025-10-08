package com.wil.practice.datastructure.queue;

import java.util.ArrayDeque;

public class DotaSenate {

    private static String predictPartyVictory(String senate) {
        ArrayDeque<Integer> queueRadiant = new ArrayDeque<>();
        ArrayDeque<Integer> queueDire = new ArrayDeque<>();
        int n = senate.length();
        for (int i=0; i<n; i++) {
            if (senate.charAt(i) == 'R') {
                queueRadiant.offer(i);
            }
            else {
                queueDire.offer(i);
            }
        }

        int radiant, dire;
        while (!queueRadiant.isEmpty() && !queueDire.isEmpty()) {
            radiant = queueRadiant.poll();
            dire = queueDire.poll();
            // The initiative is determined by the index ordering
            // Specifically, the smaller index has the initiative in each round
            if (radiant < dire) queueRadiant.offer(radiant + n);
            else queueDire.offer(dire + n);
        }

        if (queueRadiant.isEmpty()) return "Dire";
        else return "Radiant";
    }

    public static void main(String[] args) {
        String s1 = "RD";
        String s2 = "RDD";
        String s3 = "DDRRR";

        System.out.printf(" %s%n", predictPartyVictory(s1));
        System.out.printf(" %s%n", predictPartyVictory(s2));
        System.out.printf(" %s%n", predictPartyVictory(s3));
    }
}
