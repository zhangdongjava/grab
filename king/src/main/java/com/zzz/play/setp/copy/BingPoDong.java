package com.zzz.play.setp.copy;

import java.util.Date;

/**
 * 冰魄洞
 * Created by Administrator on 2016/11/6 0006.
 */
public class BingPoDong extends FuBen {

    @Override
    public String inClearLine() {
        return "柳虫,青蛇皮,金创药";
    }

    @Override
    public String outClearLine() {
        return "【神行,黑松果,黑狼";
    }

    @Override
    public String saveLine() {
        return "冰魄珠";
    }


    @Override
    public void ready() {
        buyDrug.setName("万年灵芝");
        buyDrug.setNum("300");
        buyDrug.run();
    }


    @Override
    public boolean fbRun() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("冰魄洞");
        htmlContent.linkName("下:枯木↓");
        htmlContent.linkName("红角兽王");
        htmlContent.linkName("攻击红角兽王");
        zhanDou();
        htmlContent.linkName("上:冰魄洞口↑");
        htmlContent.linkName("冰封老人");
        htmlContent.linkName("进入冰魄洞副本");
        htmlContent.linkName("传送进冰魄洞");
        if (htmlContent.getText().contains("今天你已进入")) {
            ableIn = false;
            lastDate = new Date();
            logger.error(htmlContent.htmlPanel.user.getDaqu() + "冰魄洞结束!");
            htmlContent.linkName("返回",true);
            htmlContent.linkName("返回",true);
            htmlContent.linkName("返回游戏");
            return false;
        }
        if (htmlContent.getText().contains("对不起，需要提供红角兽王鬃毛x1")) {
            return false;
        }
        htmlContent.linkName("返回游戏");
        sd();
        sd("上:水洞↑");
        sd("右:水洞→");
        sd("下:水洞↓");
        sd("下:水洞↓");
        sd("左:水洞←");
        sd("上:水洞↑");
        sd("右:水洞→");
        sd("右:水洞→");
        htmlContent.linkName("水洞守卫");
        zhanDou(8);
        htmlContent.linkName("水灵怪兽");
        zhanDou(8);
        htmlContent.linkName("进入木洞");
        htmlContent.linkName("返回游戏");
        md();
        md("上:木洞↑");
        md("上:木洞↑");
        md("右:木洞→");
        md("下:木洞↓");
        md("下:木洞↓");
        md("下:木洞↓");
        md("下:木洞↓");
        md("左:木洞←");
        md("上:木洞↑");
        md("上:木洞↑");
        md("右:木洞→");
        md("右:木洞→");
        htmlContent.linkName("木洞守卫");
        zhanDou(8);
        htmlContent.linkName("木灵怪兽");
        zhanDou(8);
        htmlContent.linkName("进入冰洞");
        htmlContent.linkName("返回游戏");
        bd();
        bd("上:冰洞↑");
        bd("上:冰洞↑");
        bd("右:冰洞→");
        bd("右:冰洞→");
        bd("下:冰洞↓");
        bd("左:冰洞←");
        bd("左:冰洞←");
        bd("下:冰洞↓");
        bd("右:冰洞→");
        bd("右:冰洞→");
        bd("下:冰洞↓");
        bd("左:冰洞←");
        bd("左:冰洞←");
        bd("下:冰洞↓");
        bd("右:冰洞→");
        bd("右:冰洞→");
        bd("上:冰洞↑");
        bd("上:冰洞↑");
        bd("右:冰洞→");
        htmlContent.linkName("冰洞守卫");
        zhanDou(8);
        htmlContent.linkName("冰灵怪兽");
        zhanDou(8);
        htmlContent.linkName("进入黑金桥");
        htmlContent.linkName("黑金使者");
        zhanDouShizhe();
        htmlContent.linkName("进入紫土桥");
        htmlContent.linkName("紫土使者");
        zhanDouShizhe();
        htmlContent.linkName("进入铁木桥");
        htmlContent.linkName("铁木使者");
        zhanDouShizhe();
        htmlContent.linkName("进入寒水桥");
        htmlContent.linkName("寒水使者");
        zhanDouShizhe();
        htmlContent.linkName("进入烈焰桥");
        htmlContent.linkName("烈焰使者");
        zhanDouShizhe();
        htmlContent.linkName("进入冰魄洞");
        htmlContent.linkName("天怒神兽");
        zhanDou(15);
        htmlContent.linkName("冰魄异兽");
        zhanDouShizhe();
        runNum++;
        wjHz();
        return true;
    }


    /**
     * 战斗
     */
    public void zhanDou(int size) {
        htmlContent.linkName("攻击", true);
        for (int i = 0; i < size; i++) {
            htmlContent.linkName("万年灵芝");
        }
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("红角兽王鬃毛", true);
        htmlContent.linkName("冰魄珠", true);
        htmlContent.linkName("禁军", true);
        htmlContent.linkName("御林", true);
        htmlContent.linkName("魄力", true);
        htmlContent.linkName("返回游戏");
    }

    /**
     * 战斗
     */
    public void zhanDouShizhe() {
        htmlContent.linkName("攻击", true);
        while ((!htmlContent.getText().contains("冰洞守卫(0)"))
                && htmlContent.exitsName("万年灵芝")) {
            htmlContent.linkName("万年灵芝");
        }
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("红角兽王鬃毛", true);
        htmlContent.linkName("冰魄珠", true);
        htmlContent.linkName("禁军", true);
        htmlContent.linkName("御林", true);
        htmlContent.linkName("魄力", true);
        htmlContent.linkName("返回游戏");
    }

    /**
     * 战斗
     */
    public void zhanDou() {
        zhanDou(8);
    }

    /**
     * 攻击水洞守卫
     */
    public void sd(String name) {
        htmlContent.linkName(name);
        sd();
    }

    /**
     * 攻击水洞守卫
     */
    public void sd() {
        htmlContent.linkName("水洞守卫");
        htmlContent.linkName("攻击水洞守卫");
        while ((!htmlContent.getText().contains("步兵(0)"))
                && htmlContent.exitsName("万年灵芝")) {
            htmlContent.linkName("万年灵芝");
        }
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("御林", true);
        htmlContent.linkName("禁军", true);
        htmlContent.linkName("返回游戏");
    }

    /**
     * 攻击木洞守卫
     */
    public void md(String name) {
        htmlContent.linkName(name);
        md();
    }

    /**
     * 攻击木洞守卫
     */
    public void md() {
        htmlContent.linkName("木洞守卫");
        htmlContent.linkName("攻击木洞守卫");
        while ((!htmlContent.getText().contains("步兵(0)"))
                && htmlContent.exitsName("万年灵芝")) {
            htmlContent.linkName("万年灵芝");
        }
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("御林", true);
        htmlContent.linkName("禁军", true);
        htmlContent.linkName("返回游戏");
    }

    /**
     * 攻击冰洞守卫
     */
    public void bd(String name) {
        htmlContent.linkName(name);
        bd();
    }

    /**
     * 攻击冰洞守卫
     */
    public void bd() {
        htmlContent.linkName("冰洞守卫");
        htmlContent.linkName("攻击冰洞守卫");
        while ((!htmlContent.getText().contains("步兵(0)"))
                && htmlContent.exitsName("万年灵芝")) {
            htmlContent.linkName("万年灵芝");
        }
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("御林", true);
        htmlContent.linkName("禁军", true);
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

    /**
     * 获取门票
     *
     * @return
     */
    public void getMenPiao() {
        htmlContent.linkName("宝库守卫");
        htmlContent.linkName("攻击宝库守卫");
        htmlContent.linkName("万年灵芝");
        htmlContent.linkName("万年灵芝");
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("钥匙", true);
        htmlContent.linkName("钥匙", true);
        htmlContent.linkName("钥匙", true);
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("进入太尉宝库副本");
    }
}



















