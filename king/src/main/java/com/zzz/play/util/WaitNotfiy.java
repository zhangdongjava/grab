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
                lock.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void anotfiy() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }

}
