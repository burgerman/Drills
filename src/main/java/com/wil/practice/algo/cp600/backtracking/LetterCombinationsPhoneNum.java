package com.wil.practice.algo.cp600.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsPhoneNum {

    private static Map<Integer, Character[]> dict;
    private static Character[] letters;
    private static void initializeDict() {
        dict = new HashMap<>(32, 1.0f);
        char c = 'a';
        for (int i=2; i<=9; i++) {
            if (i==7 || i==9) {
                letters = new Character[4];
                for (int j=0; j<4; j++) {
                    letters[j] = c;
                    c++;
                }
            } else {
                letters = new Character[3];
                for (int j=0; j<3; j++) {
                    letters[j] = c;
                    c++;
                }
            }
            dict.put(i, letters);
        }

    }

    private static void dfs (List<String> combinations, String digits, int index, StringBuilder current) {
        if (index == digits.length()) {
            combinations.add(current.toString());
            return;
        }
        letters = dict.get(digits.charAt(index)-'0');
        for (Character c : letters) {
            current.append(c);
            dfs(combinations, digits, index+1, current);
            current.deleteCharAt(current.length()-1);
        }
    }

    private static List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) return null;
        List<String> res = new ArrayList<>();
        initializeDict();
        dfs(res, digits, 0, new StringBuilder());
        return res;
    }

    public static void main(String[] args) {

       String digits =  "23";
       letterCombinations(digits).stream().forEach(l -> System.out.printf("%s\t",l));
       String digits2 = "7";
       System.out.println();
       letterCombinations(digits2).stream().forEach(l -> System.out.printf("%s\t",l));
    }

}
