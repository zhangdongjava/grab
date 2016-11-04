package com.zzz.play.util;

/**
 * Created by dell_2 on 2016/11/1.
 */
public class WaitNotfiy {

    private Object lock = new Object();

    public boolean wait = false;

    public void await() {
        if (!wait) return;
        synchronized (lock) {
            try {
                System.out.println("进入等待");
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public void anotfiy() {
        synchronized (lock) {
            System.out.println("开始唤醒!");
            lock.notifyAll();
            System.out.println("唤醒结束!");
        }
    }

}
