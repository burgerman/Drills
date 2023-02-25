package com.wil.practice.interview;

/**
 * Three threads print numbers from 1 to 100 in sequence
 */
public class ThreadsPrint implements Runnable{

    int remainder;

    public ThreadsPrint(int remainder) {
        this.remainder = remainder;
    }

    @Override
    public void run() {
        while (Tmp.val<100) {
            synchronized (Tmp.lock) {
                while (Tmp.val%3 != remainder && Tmp.val<100) {
                    try{
                        //Release the lock
                        Tmp.lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(Tmp.flag) {
                    Thread.currentThread().interrupt();
                }
                while (Tmp.val == 100) {
                    Tmp.flag = true;
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName()+": " + Tmp.val);
                Tmp.val++;
                // awake other threads
                Tmp.lock.notifyAll();
            }
        }
    }


}

class Tmp{
    volatile static boolean flag = false;
    static Object lock = new Object();
    volatile  static int val = 1;
}
