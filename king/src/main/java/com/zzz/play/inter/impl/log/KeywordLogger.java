package com.zzz.play.inter.impl.log;

import com.zzz.play.inter.Logger;
import com.zzz.play.util.HtmlContent;

/**
 * Created by dell_2 on 2016/11/22.
 */
public class KeywordLogger implements Logger {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(KeywordLogger.class);

    public static String[] aKeyWord = {"天怒神兽"};

    @Override
    public void run(HtmlContent htmlContent) {
        if(htmlContent==null)return;
        String text = htmlContent.getText();
        if(text.length()>50){
            text = text.substring(0,50);
        }
        for (String s : aKeyWord) {
            if(htmlContent.exitsName(s)){
                logger.error(htmlContent.htmlPanel.user.getName()+"->>"+text);
            }
        }
    }
}
