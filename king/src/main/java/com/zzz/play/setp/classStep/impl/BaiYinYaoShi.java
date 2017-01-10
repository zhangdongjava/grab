package com.zzz.play.setp.classStep.impl;

import com.zzz.play.setp.sup.SecondRefresh;
import com.zzz.play.setp.sys.GoodsUse;
import com.zzz.play.util.HtmlContent;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 兑换白银钥匙
 * Created by dell_2 on 2016/11/10.
 */
public class BaiYinYaoShi extends SecondRefresh {

    private HtmlContent htmlContent;
    private int num = 5;
    private Set<String> notSave = new HashSet<>();

    public BaiYinYaoShi(HtmlContent htmlContent) {
        this.htmlContent = htmlContent;
        goodsSave.setHtmlContent(htmlContent);
        goodsSave.setGoods(".白银钥匙");
        notSave.add("大嘴哥");
        notSave.add("哈嘿");
        notSave.add("路飞");
    }

    @Override
    public boolean run() {
        if (notSave.contains(htmlContent.htmlPanel.user.getName())) {
            return false;
        }
        fresh();
        if (!ableIn) {
            return false;
        }
        return arun();

    }

    private boolean arun() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("上东京");
        htmlContent.linkName("上:北大街↑");
        htmlContent.linkName("活动大使");
        htmlContent.linkName("在线时间兑换");
        for (int i = 0; i < num; i++) {
            htmlContent.linkName("白银钥匙(3600分钟)", true);
        }
        htmlContent.linkName("返回游戏");
        ableIn = false;
        lastDate = new Date();
        goodsSave.run();
        return true;
    }

}
