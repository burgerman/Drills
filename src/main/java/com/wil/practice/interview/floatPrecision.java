package com.wil.practice.interview;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * 避免精度丢失，尽量将浮点数转为BigDecimal，并且要使用BigDecimal.valueOf()函数。
 * 如果要用new函数，就要现将浮点数转为字符串，否则同样会丢失精度
 */
public class floatPrecision {

    public static void main(String[] args) {

        float a = 0.1f;
        float b = 0.1f;
        float c = 0.1f;

        float d = a+b+c;
        float e = d*3;
        float f = e*1000000;

        // 0.100000001490116119384765625
        System.out.println(new BigDecimal(a));
        // 900000.0625
        System.out.println(new BigDecimal(d));
        // 0.300000011920928955078125
        System.out.println(new BigDecimal(e));
        // 0.900000035762786865234375
        System.out.println(new BigDecimal(f));
        /**
         * Approach I: Use BigDecimal to calculate
         */
        //Pre-process
        System.out.println("The approach of pre-processing: ");
        BigDecimal a1 = BigDecimal.valueOf(a).setScale(1, RoundingMode.FLOOR);
        BigDecimal b1 = BigDecimal.valueOf(b).setScale(1, RoundingMode.FLOOR);
        BigDecimal c1 = BigDecimal.valueOf(c).setScale(1, RoundingMode.FLOOR);
        BigDecimal d1 = a1.add(b1).add(c1);
        BigDecimal e1 = d1.multiply(BigDecimal.valueOf(3));
        BigDecimal f1 = e1.multiply(BigDecimal.valueOf(1000000));
        // 900000.0
        System.out.println("f1: "+ f1);
        //Post process
        BigDecimal d2 = BigDecimal.valueOf(d).setScale(1, RoundingMode.FLOOR);
        BigDecimal e2 = BigDecimal.valueOf(e).setScale(1,RoundingMode.FLOOR);
        BigDecimal f2 = BigDecimal.valueOf(f).setScale(0, RoundingMode.FLOOR);
        System.out.println("The approach of post-processing: ");
        // 900000
        System.out.println("f2: "+f2);

        /**
         * Approach II: Use NumberFormat to normalize the output
         *
         */
        System.out.println("Approach II: ");
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(1);
        nf.setRoundingMode(RoundingMode.FLOOR);
        // 900000.06
        System.out.println(f);
        // 900,000
        System.out.println(nf.format(f));


    }

}
