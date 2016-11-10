package com.zzz.play.setp.classStep.impl;

import com.zzz.play.setp.sup.SecondRefresh;
import com.zzz.play.util.HtmlContent;

import java.util.Date;

/**
 * 修神
 * Created by dell_2 on 2016/11/10.
 */
public class XiuShen extends SecondRefresh {

    private HtmlContent htmlContent;

    public XiuShen(HtmlContent htmlContent) {
        this.htmlContent = htmlContent;
    }

    @Override
    public boolean run() {
        fresh();
        if (!ableIn) {
            return false;
        }
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("我的庄院");
        htmlContent.linkName("上:庄院广场↑");
        htmlContent.linkName("右:练武场→");
        htmlContent.linkName("田武师");
        htmlContent.linkName("开启今日修神");
        htmlContent.linkName("返回游戏");
        ableIn = false;
        lastDate = new Date();
        return true;
    }

}
