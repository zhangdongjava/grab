package com.zzz.log;

import org.apache.log4j.Logger;

/**
 * Created by dell_2 on 2016/11/11.
 */
public class Test {

    private static Logger logger = Logger.getLogger(Test.class);

    public static void main(String[] args) {
        logger.error("这是测试的啊！！！");
    }
}
