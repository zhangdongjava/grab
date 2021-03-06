package com.zzz.play.setp.activity;

import com.zzz.play.setp.sup.SecondRefresh;
import com.zzz.play.util.HtmlContent;

import java.util.Date;

/**
 * 活动史老太公领取星光
 * Created by dell_2 on 2016/11/10.
 */
public class GeiXingGuang extends SecondRefresh {

    private HtmlContent htmlContent;

    public GeiXingGuang(HtmlContent htmlContent) {
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
        htmlContent.linkName("史家庄");
        htmlContent.linkName("下:前院↓");
        htmlContent.linkName("下:大院↓");
        htmlContent.linkName("下:史家大厅↓");
        htmlContent.linkName("下:后院↓");
        htmlContent.linkName("右:主人房→");
        htmlContent.linkName("史老太公");
        htmlContent.linkName("领取新区每日奖励");
        htmlContent.linkName("返回游戏");
        lastDate = new Date();
        ableIn = false;
        return true;
    }

}
