package com.wil.practice.algo.integers;

import java.math.BigInteger;

public class BigIntegerDrill {


    private static int getGreatestCommonDivisor(int a, int b) {
        BigInteger a1 = BigInteger.valueOf(a);
        BigInteger b1 = BigInteger.valueOf(b);
        return a1.gcd(b1).intValue();
    }


}
