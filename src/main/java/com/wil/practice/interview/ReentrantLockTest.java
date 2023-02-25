package com.wil.practice.interview;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest implements Runnable{
    private int remainder;

    public ReentrantLockTest(int remainder) {
        this.remainder = remainder;
    }

    @Override
    public void run() {
        while(LockHolder.val<100) {
            Lock lock = LockHolder.myLock(remainder);
            if(lock!=null) {
                try {
                    lock.lock();
                    if(LockHolder.val%LockHolder.num == remainder && LockHolder.val < 100) {
                        System.out.println(Thread.currentThread().getName() + ": " + LockHolder.val);
                        LockHolder.val++;
                    } else {
                        if(LockHolder.flag) {
                            Thread.currentThread().interrupt();
                        }
                        if(LockHolder.val>99) {
                            LockHolder.flag = true;
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

class LockHolder{
    static int num;
    static final Lock lock = new ReentrantLock();
    volatile static int val = 1;
    volatile static boolean flag = false;

    /**
     * provide segment locks
     */
    static HashMap<Integer, Lock> locks;

    public LockHolder (int numOfLocks) {
        init(numOfLocks);
        num = numOfLocks;
    }

    static void init(int cap) {
        if(locks == null) {
            locks = new HashMap<>(cap);
            for(int i=0; i<cap; i++) {
                locks.put(i, new ReentrantLock());
            }
        }
    }

    static Lock myLock(int remainder) {
        return locks.get(remainder) !=null? locks.get(remainder):null;
    }

}
