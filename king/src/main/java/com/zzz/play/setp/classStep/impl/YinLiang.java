package com.zzz.play.setp.classStep.impl;

import com.zzz.play.setp.sup.SecondRefresh;
import com.zzz.play.setp.sys.GoodsUse;
import com.zzz.play.util.HtmlContent;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 双倍银两使用
 * Created by dell_2 on 2016/11/10.
 */
public class YinLiang extends SecondRefresh {

    private HtmlContent htmlContent;

    private GoodsUse goodsUse;

    private int num = 1;
    private Set<String> notSave = new HashSet<>();

    public YinLiang(HtmlContent htmlContent) {
        this.htmlContent = htmlContent;
        goodsUse = new GoodsUse("双倍银两卡_" + (num + 1));
        goodsUse.setHtmlContent(htmlContent);
        notSave.add("哈嘿");
    }

    @Override
    public boolean run() {
        fresh();
        if (!ableIn) {
            return false;
        }
        return arun();

    }

    private boolean arun() {
        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        if (((h != 8 && h != 9 && h != 10))) {
            return false;
        }
        if (!notSave.contains(htmlContent.htmlPanel.user.getName())) {
            htmlContent.linkName("返回游戏");
            htmlContent.linkName("功能菜单");
            htmlContent.linkName("神行千里");
            htmlContent.linkName("上东京");
            htmlContent.linkName("上:北大街↑");
            htmlContent.linkName("活动大使");
            htmlContent.linkName("回馈点", true);
            for (int i = 0; i < num; i++) {
                htmlContent.linkName("倍银两卡(绑)", true);
                htmlContent.linkName("回馈点兑换奖励");
            }
            htmlContent.linkName("返回游戏");
        }

        goodsUse.run();
        ableIn = false;
        lastDate = new Date();
        return true;
    }

}
