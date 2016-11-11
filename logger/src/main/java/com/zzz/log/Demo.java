package com.zzz.log;

import org.apache.log4j.Logger;

/**
 * Created by dell_2 on 2016/11/11.
 */
public class Demo {

    private static Logger logger = Logger.getLogger(Demo.class);

    public static void main(String[] args) {
        logger.error("这是demo的啊！！！");
    }
}
