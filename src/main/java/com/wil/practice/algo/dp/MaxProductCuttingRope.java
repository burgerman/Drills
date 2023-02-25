package com.wil.practice.algo.dp;

public class MaxProductCuttingRope {

    /**
     * DP
     * @param length
     * @return
     */
    static int maxProduct(int length) {

        int[] val = new int[length+1];
        val[0] = val[1] = 0;
        for(int i=1; i<=length; i++) {
            for(int j = 1; j<=i; j++) {
                //绳子至少被剪一次，绳子长度最小为2
                //假设第一刀剪在j的位置(一段绳子长为j) 则剩下段长度为( i - j )
                //对于剩余绳子有两种情况：继续剪；不剪  （对二者取最大值最为val[i]的值）
                // 不剪则乘积为: j*(i-j)
                // 剪则乘积为: j*val[i-j]
                val[i]= Math.max(val[i], Math.max((i-j)*j, val[i-j]*j));
            }
        }
        return val[length];
    }

    /**
     * Greedy
     * @param length
     * @return
     */
    static int maxProduct2(int length) {
        if(length<2) {
            return 0;
        }
        if(length==2 || length==3) {
            return length-1;
        }
        int val = 1;
        while(length>4) {
            length = length-3;
            val = 3 * val;
        }
        return val * length;
    }


        public static void main(String[] args) {
            System.out.println( maxProduct(10));
            System.out.println( maxProduct2(10));
        }

}
