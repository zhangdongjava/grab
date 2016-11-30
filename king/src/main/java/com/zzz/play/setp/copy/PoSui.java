package com.zzz.play.setp.copy;

import java.util.Date;

/**
 * 破碎梦境石
 * Created by Administrator on 2016/11/6 0006.
 */
public class PoSui extends FuBen {

    @Override
    public String inClearLine() {
        return "柳虫,青蛇皮,金创药";
    }

    @Override
    public String outClearLine() {
        return null;
    }

    @Override
    public String saveLine() {
        return "";
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
        htmlContent.linkName("上东京");
        htmlContent.linkName("上:北大街↑");
        htmlContent.linkName("古神兵传说");
        htmlContent.linkName("入梦(破碎梦晶石x1)");
        if (htmlContent.getText().contains("今天你已进入")) {
            ableIn = false;
            lastDate = new Date();
            logger.error(htmlContent.htmlPanel.user.getName()  + "破碎梦晶石副本结束!");
            htmlContent.linkName("返回游戏");
            return false;
        }
        zhanDou();
        htmlContent.linkName("下:阴暗↓");
        zhanDou();
        htmlContent.linkName("右:阴暗→");
        zhanDou();
        htmlContent.linkName("下:阴暗↓");
        zhanDou();
        htmlContent.linkName("上:阴暗↑");
        htmlContent.linkName("右:阴暗→");
        zhanDou();
        htmlContent.linkName("上:阴暗↑");
        zhanDou();
        htmlContent.linkName("下:阴暗↓");
        htmlContent.linkName("右:阴暗→");
        zhanDou();
        htmlContent.linkName("右:阴暗深处→");
        zhanDou();
        htmlContent.linkName("梦魇头目");
        htmlContent.linkName("攻击梦魇头目");
        zhanDou();
        htmlContent.linkName("进入剑冢");
        htmlContent.linkName("莫邪");
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("右:心魔之所→");
        htmlContent.linkName("心魔");
        htmlContent.linkName("返回心魔");
        htmlContent.linkName("攻击心魔");
        zhanDou2();
        htmlContent.linkName("心魔");
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("进入噩梦之所");
        htmlContent.linkName("噩梦");
        htmlContent.linkName("攻击噩梦");
        zhanDou2();
        htmlContent.linkName("左:心魔之所←");
        htmlContent.linkName("左:剑冢←");
        htmlContent.linkName("莫邪");
        htmlContent.linkName("返回游戏");
        return true;
    }


    /**
     * 战斗
     */
    public void zhanDou() {
        htmlContent.linkName("梦魇小怪");
        htmlContent.linkName("攻击", true);
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("返回游戏");
    }

    /**
     * 药品战斗
     */
    public void zhanDou2() {
        while (htmlContent.exitsName("万年灵芝")) {
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
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("返回游戏");
    }

    public void loginIn(){
        htmlContent.linkName("进入密道");
        htmlContent.linkName("确定进入");
        while (true){
            if(htmlContent.exitsName("下:毒虫殿↓")){
                break;
            }else if(htmlContent.exitsName("下:密道↓")){
                htmlContent.linkName("下:密道↓");
                zhanDou();
            } else if(htmlContent.exitsName("右:密道→")){
                htmlContent.linkName("右:密道→");
                zhanDou();
            }
        }
        htmlContent.linkName("下:毒虫殿↓");
        htmlContent.linkName("恶毒蝎子王");
        zhanDou();
        htmlContent.linkName("巨型青蛙王");
        zhanDou();
        htmlContent.linkName("奇异毒蛇王");
        zhanDou();
        htmlContent.linkName("吸血蝙蝠王");
        zhanDou();
        htmlContent.linkName("进入巫婆殿");
        htmlContent.linkName("巫婆.叶比亚");
        zhanDou();
    }

}



















