package com.zzz.play.util.sys;

import com.zzz.play.inter.Logger;
import com.zzz.play.inter.impl.log.KeywordLogger;
import com.zzz.play.util.HtmlContent;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dell_2 on 2016/11/22.
 */
public class LoggerUtil {

    List<Logger> loggers;

    public static LoggerUtil loggerUtil = new LoggerUtil();

    private LoggerUtil() {
        loggers = new LinkedList<>();
        initLogger();
    }


    public void run(HtmlContent htmlContent) {
        for (Logger logger : loggers) {
            logger.run(htmlContent);
        }
    }

    private void initLogger() {
        loggers.add(new KeywordLogger());
    }

}
