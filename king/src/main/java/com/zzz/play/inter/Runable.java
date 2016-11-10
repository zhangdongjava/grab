package com.zzz.play.inter;

/**
 * Created by dell_2 on 2016/11/2.
 */
public interface Runable {

    boolean run();

    String getFileName();

    /**
     * 是否清理
     * @return
     */
    boolean isClear();

    class RunConfig{
        /**
         * 是否清理
         */
        private boolean clear;
        /**
         * 运行次数
         */
        private int count;
    }

}
