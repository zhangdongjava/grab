package com.zzz.play.setp.copy;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
public class HeiFeng extends FuBen {

    @Override
    public String inClearLine() {
        return "柳虫,青蛇皮,金创药";
    }

    @Override
    public String outClearLine() {
        return "【追风,黑松果,黑狼";
    }

    @Override
    public String saveLine() {
        return "强体奇书";
    }

    private int i = 0;


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
        htmlContent.linkName("黑风岭");
        htmlContent.linkName("黑风岭砍柴老者");
        htmlContent.linkName("进入黑风岭副本");
        if (htmlContent.getText().contains("今天你已进入黑风岭副本")) {
            ableIn = false;
            lastDate = new Date();
            logger.error(htmlContent.htmlPanel.user.getDaqu()  + "黑风副本结束!");
            htmlContent.linkName("返回游戏");
            return false;
        }
        gjHml();
        htmlContent.linkName("右:黑松林→");
        gjHml();
        htmlContent.linkName("右:黑松林→");
        gjHml();
        htmlContent.linkName("右:黑松林→");
        gjHml();
        htmlContent.linkName("右:乱石河边→");
        htmlContent.linkName("进入乱石河");
        i = 0;
        while (htmlContent.getText().contains("从乱石河边->乱石河需要黑松果x5")) {
            getCl();
            if(i>5){
                htmlContent.linkName("返回游戏");
                htmlContent.linkName("队伍");
                htmlContent.linkName("退出队伍");
                htmlContent.linkName("确定退出");
                htmlContent.linkName("返回游戏");
                return false;
            }
        }
        htmlContent.linkName("巨型鲤鱼");
        htmlContent.linkName("攻击巨型鲤鱼");
        zhanDou();
        htmlContent.linkName("进入猛虎坡");
        htmlContent.linkName("病大虫");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("进入猛虎洞");
        htmlContent.linkName("黑风虎");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("进入洞穴");
        for (int i = 0; i < 3; i++) {
            htmlContent.linkName("黑风雌虎");
            htmlContent.linkName("攻击", true);
            zhanDou();
        }
        htmlContent.linkName("进入窄路");
        htmlContent.linkName("黑风雄虎");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("进入潮湿小路");
        htmlContent.linkName("左:内洞←");
        htmlContent.linkName("巨型猛虎");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("右:潮湿小路→");
        htmlContent.linkName("进入巨型洞穴");
        htmlContent.linkName("虎王");
        htmlContent.linkName("攻击", true);
        zhanDou();
        runNum++;
        wjHz();
        return true;
    }

    public void gjHml() {
        htmlContent.linkName("黑松果", true);
        htmlContent.linkName("黑毛狼");
        htmlContent.linkName("攻击黑毛狼");
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("黑狼毛", true);
        htmlContent.linkName("返回游戏");
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
        htmlContent.linkName("虎骨", true);
        htmlContent.linkName("虎骨", true);
        htmlContent.linkName("虎骨", true);
        htmlContent.linkName("魄力", true);
        htmlContent.linkName("返回游戏");
    }

    /**
     * 获取进去材料
     */
    public void getCl() {
        i++;
        htmlContent.linkName("返回乱石河边");
        htmlContent.linkName("左:黑松林←");
        gjHml();
        htmlContent.linkName("下:黑松林↓");
        gjHml();
        htmlContent.linkName("上:黑松林↑");
        gjHml();
        htmlContent.linkName("左:黑松林←");
        gjHml();
        htmlContent.linkName("上:黑松林↑");
        gjHml();
        htmlContent.linkName("下:黑松林↓");
        gjHml();
        htmlContent.linkName("左:黑松林←");
        gjHml();
        htmlContent.linkName("左:黑松林←");
        gjHml();
        for (int i = 0; i < 5; i++) {
            htmlContent.linkName("右:黑松林→");
        }
        htmlContent.linkName("右:乱石河边→");
        htmlContent.linkName("进入乱石河");
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



















