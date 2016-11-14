package com.zzz.play.setp.copy;

import java.util.Date;

/**
 * 雪山副本
 * Created by Administrator on 2016/11/6 0006.
 */
public class XueShan extends FuBen {

    @Override
    public String inClearLine() {
        return "柳虫,青蛇皮,金创药,蜂王浆,龙皮";
    }

    @Override
    public String outClearLine() {
        return "【残阳,黑松果,黑狼";
    }

    @Override
    public String saveLine() {
        return "";
    }


    @Override
    public void ready() {

    }


    @Override
    public boolean fbRun() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("少华山");
        htmlContent.linkName("少华山石匠");
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("少华山石匠");
        htmlContent.linkName("传送至落雪山副本");
        if (htmlContent.getText().contains("今天你已进入")) {
            ableIn = false;
            lastDate = new Date();
            logger.error(htmlContent.htmlPanel.user.getDaqu()  + "落雪山副本结束!");
            htmlContent.linkName("返回游戏");
            return false;
        }
        htmlContent.linkName("落雪山草寇");
        zhanDou();
        htmlContent.linkName("进入雪地");
        htmlContent.linkName("右:雪地→");
        htmlContent.linkName("右:雪地→");
        htmlContent.linkName("上:雪地↑");
        htmlContent.linkName("右:冰河→");
        htmlContent.linkName("冰河巨鱼");
        zhanDou();
        htmlContent.linkName("左:雪地←");
        htmlContent.linkName("下:雪地↓");
        htmlContent.linkName("左:雪地←");
        htmlContent.linkName("左:雪地←");
        htmlContent.linkName("左:雪地←");
        htmlContent.linkName("上:雪地↑");
        htmlContent.linkName("左:雪地机关二←");
        htmlContent.linkName("打开机关");
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("右:雪地→");
        htmlContent.linkName("下:雪地↓");
        htmlContent.linkName("右:雪地→");
        htmlContent.linkName("右:雪地→");
        htmlContent.linkName("上:白雪地↑");
        htmlContent.linkName("上:冰原↑");
        htmlContent.linkName("落雪山小首领");
        zhanDou();
        htmlContent.linkName("进入雪原");
        htmlContent.linkName("落雪山首领");
        zhanDou();
        htmlContent.linkName("进入白雪河");
        htmlContent.linkName("落雪山七当家");
        zhanDou();
        htmlContent.linkName("进入寒冰路");
        htmlContent.linkName("落雪山六当家");
        zhanDou();
        htmlContent.linkName("进入寒冰坡");
        htmlContent.linkName("落雪山五当家");
        zhanDou();

        htmlContent.linkName("进入白雪路");
        htmlContent.linkName("落雪山四当家");
        zhanDou();
        htmlContent.linkName("进入落雪山顶");
        htmlContent.linkName("落雪山三当家");
        zhanDou();
        htmlContent.linkName("上:落雪洞二↑");
        htmlContent.linkName("落雪山大当家");
        zhanDou();
        runNum++;
        return true;
    }


    /**
     * 战斗
     */
    public void zhanDou() {
        htmlContent.linkName("攻击", true);
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("冰珠", true);
        htmlContent.linkName("太尉", true);
        htmlContent.linkName("太尉", true);
        htmlContent.linkName("魄力", true);
        htmlContent.linkName("返回游戏");
    }

}



















