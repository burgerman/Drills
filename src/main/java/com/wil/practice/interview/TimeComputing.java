package com.wil.practice.interview;

/**
 * 计算当前时间是该周的第几秒 （起始时间: 周日00:00:00）
 */
public class TimeComputing {


    private static long getSecFromSunday(long currentTime) {

        long offset = 4*24*60*60;
        long offset2 = 3*24*60*60;
        long time = currentTime - offset;
        long time2 = currentTime + offset2;

        long weekTime = 7*24*60*60;
        // return time2 % weekTime;
        return time % weekTime;
    }


}
