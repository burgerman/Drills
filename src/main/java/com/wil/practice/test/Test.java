package com.wil.practice.test;

import assignment.meo.entity.Koala;
import assignment.meo.entity.Point;
import assignment.meo.entity.Tree;
import assignment.meo.utils.PointStatusPostProcessor;
import assignment.meo.utils.RandomNum;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

public class Test {
    private static Pattern stopFlag = Pattern.compile("(?i)D");
    public static void main(String[] args) {
//       File file = FileUtils.getFile("src/main/resources/updatedTrees.txt");
//        for(int i =0; i<5; i++) {
//           updateTreeFile(file, String.valueOf(i));
//        }

//        ArrayList<Integer> arr = new ArrayList<>();
//        for (int i=0; i<10; i++) {
//            arr.add(i);
//        }
//        int n = 5;
       // arr.removeIf(p-> n<arr.size()&&RandomNum.starvingDeathRate()<4);
//        int num = 0;
//        int size = arr.size();
//        while (num<size && arr.size()>n) {
//            int t = arr.get(num);
//            if(RandomNum.starvingDeathRate()<4) {
//               arr.removeIf(e-> e.equals(t));
//            }
//            num++;
//        }
//        System.out.println(arr);
        Tree tree1 = new Tree(1, 5);
        Tree tree2 = new Tree(0.34, 2);
        Tree tree3 = new Tree(0.9, 3);
        Tree tree4 = new Tree(0.4, 5);
        Tree tree5 = new Tree(0, 4);
        ArrayList<Tree> trees = new ArrayList<>();
        trees.add(tree1);
        trees.add(tree2);
        trees.add(tree3);
        trees.add(tree4);
        trees.add(tree5);
        Koala koala1 = new Koala(12,0);
        Koala koala2 = new Koala(10,0);
        Koala koala3 = new Koala(5,0);
        Koala koala4 = new Koala(4,1);
        Koala koala5 = new Koala(2,1);
        Koala koala6 = new Koala(18,1);
        Koala koala7 = new Koala(2,0);
        ArrayList<Koala> h = new ArrayList<>();
        h.add(koala1);
        h.add(koala2);
        h.add(koala3);
        h.add(koala7);
        ArrayList<Koala> i = new ArrayList<>();
        i.add(koala4);
        i.add(koala5);
        i.add(koala6);
        Point point = new Point(trees, 3, 4, h, i);
        System.out.println(PointStatusPostProcessor.pointUpdate(point));

    }


    public static void updateTreeFile(File file, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str).append("\r\n");
        try {
            FileUtils.writeStringToFile(file, sb.toString(), StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
