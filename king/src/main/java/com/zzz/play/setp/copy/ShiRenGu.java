package com.zzz.play.setp.copy;

import java.util.Date;

/**
 * 食人谷
 * Created by Administrator on 2016/11/6 0006.
 */
public class ShiRenGu extends FuBen {

    @Override
    public String inClearLine() {
        return "柳虫,青蛇皮,金创药";
    }

    @Override
    public String outClearLine() {
        return "【玉清,黑松果,黑狼";
    }

    @Override
    public String saveLine() {
        return "狂暴一";
    }


    @Override
    public void ready() {
        buyDrug.setName("万年灵芝");
        buyDrug.setNum("100");
        buyDrug.run();
    }


    @Override
    public boolean fbRun() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("食人谷");
        htmlContent.linkName("进入食人谷副本");
        if (htmlContent.getDocument().text().contains("今天你已进入食人谷副本")) {
            ableIn = false;
            lastDate = new Date();
            logger.error(htmlContent.htmlPanel.name + "食人谷副本结束!");
            return false;
        }
        gjSj();
        while (htmlContent.getDocument().text().contains("进入食人谷副本，必须攀下一个悬崖")) {
            htmlContent.linkName("返回食人谷悬崖");
            gjSj();
        }
        htmlContent.linkName("守谷小喽罗");
        htmlContent.linkName("攻击守谷小喽罗");
        zhanDou();
        htmlContent.linkName("进入悬崖大树");
        htmlContent.linkName("大吼猴");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("下:食人谷口↓");
        htmlContent.linkName("进入一线天");
        htmlContent.linkName("剧毒奇寒蛇");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("进入狭小石门");
        for (int i = 0; i < 3; i++) {
            htmlContent.linkName("食人谷守卫");
            htmlContent.linkName("攻击", true);
            zhanDou();
        }
        htmlContent.linkName("进入小河边");
        htmlContent.linkName("食人谷力士");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("进入河底");
        htmlContent.linkName("食人怪鱼");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("左:小河边←");
        htmlContent.linkName("上:小路↑");
        htmlContent.linkName("右:巨型峡谷→");
        htmlContent.linkName("食人谷护法");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("上:奇异果园↑");
        htmlContent.linkName("食人谷果农");
        htmlContent.linkName("配置狂暴一");
        htmlContent.linkName("返回", true);
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("下:巨型峡谷↓");
        htmlContent.linkName("进入谷内小屋");
        htmlContent.linkName("食人谷五当家");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("右:大堂→");
        htmlContent.linkName("食人谷四当家");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("上:北偏厅↑");
        htmlContent.linkName("食人谷三当家");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("下:大堂↓");
        htmlContent.linkName("下:南偏厅↓");
        htmlContent.linkName("食人谷二当家");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("上:大堂↑");
        htmlContent.linkName("进入暗室");
        htmlContent.linkName("食人谷大当家");
        htmlContent.linkName("攻击", true);
        htmlContent.linkName("万年灵芝");
        htmlContent.linkName("万年灵芝");
        htmlContent.linkName("万年灵芝");
        zhanDou();
        runNum++;
        wjHz();
        return true;
    }

    /**
     * 获取蛇筋
     */
    public void gjSj() {
        for (int i = 0; i < 5; i++) {
            htmlContent.linkName("巨蛇");
            htmlContent.linkName("攻击巨蛇");
            htmlContent.linkName("万年灵芝");
            while (htmlContent.exitsName("普通攻击")) {
                htmlContent.linkName("普通攻击");
            }
            htmlContent.linkName("蛇筋", true);
            htmlContent.linkName("返回游戏");
        }
        htmlContent.linkName("进入食人谷副本");
    }

    /**
     * 战斗
     */
    public void zhanDou() {
        for (int i = 0; i < 5; i++) {
            htmlContent.linkName("万年灵芝");
        }
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("返回游戏");
    }

    /**
     * 武将换装
     */
    public void wjHz() {
        htmlContent.linkName("武将");
        htmlContent.linkName("周武师", true);
        htmlContent.linkName("装备");
        htmlContent.linkName("自动换装");
        htmlContent.linkName("返回游戏");
    }
}



















