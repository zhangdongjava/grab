package com.zzz.play.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by dell_2 on 2016/11/18.
 */
public class ThreadPoolUtil {

    private static ExecutorService service = Executors.newFixedThreadPool(20);

    public static Future<?> addThread(Runnable task) {
        return service.submit(task);
    }

}
