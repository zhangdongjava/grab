package com.zzz.play.setp.copy;

/**
 * 太尉副本
 * Created by Administrator on 2016/11/6 0006.
 */
public class TaiWei extends FuBen {

    @Override
    public String inClearLine() {
        return "柳虫,青蛇皮,金创药";
    }

    @Override
    public String outClearLine() {
        return "【残阳,黑松果,黑狼";
    }

    @Override
    public String saveLine() {
        return "太尉";
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
        htmlContent.linkName("太尉宝库");
        htmlContent.linkName("进入太尉宝库副本");
        if (htmlContent.getDocument().text().contains("今天你已进入黑风岭副本")) {
            ableIn = false;
            System.out.println(htmlContent.htmlPanel.name + "黑风副本结束!");
            htmlContent.linkName("返回游戏");
            return false;
        }
        while (htmlContent.getDocument().text().contains("需要使用宝库白银钥匙x1")) {
            htmlContent.linkName("返回太尉宝库玄铁大门");
            getMenPiao();
        }
        htmlContent.linkName("宝库铁甲兵");
        htmlContent.linkName("攻击宝库铁甲兵");
        zhanDou();
        htmlContent.linkName("进入下.一石阶");
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("宝库精甲兵");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("上:石室↑");
        htmlContent.linkName("宝库铁甲守将");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("下:石阶↓");
        htmlContent.linkName("下:石室↓");
        htmlContent.linkName("宝库精甲守将");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("上:石阶↑");
        htmlContent.linkName("进入过道");
        htmlContent.linkName("宝库钢甲兵");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("进入下.一过道");
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("宝库银甲兵");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("上:铁屋↑");
        htmlContent.linkName("太尉宝库四将.黄将军");
        htmlContent.linkName("攻击", true);
        zhanDou();

        htmlContent.linkName("右:铁屋→");
        htmlContent.linkName("太尉宝库四将", true);
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("左:铁屋←");
        htmlContent.linkName("下:过道↓");
        htmlContent.linkName("下:铁屋↓");
        htmlContent.linkName("太尉宝库四将", true);
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("右:铁屋→");
        htmlContent.linkName("太尉宝库四将", true);
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("左:铁屋←");
        htmlContent.linkName("上:过道↑");
        htmlContent.linkName("进入秘道");
        htmlContent.linkName("宝库白银守将");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("进入下.一秘道");
        htmlContent.linkName("宝库黄金守将");
        htmlContent.linkName("攻击", true);
        zhanDou();
        htmlContent.linkName("进入下.一秘道");
        htmlContent.linkName("下:秘道↓");
        htmlContent.linkName("左:秘道←");
        htmlContent.linkName("左:秘道←");
        htmlContent.linkName("下:秘道↓");
        htmlContent.linkName("下:秘道↓");
        htmlContent.linkName("右:秘道→");
        htmlContent.linkName("下:秘道↓");
        htmlContent.linkName("右:秘道→");
        htmlContent.linkName("右:藏宝库巨门→");
        htmlContent.linkName("宝库守卫大将军.高不低");
        htmlContent.linkName("攻击", true);
        htmlContent.linkName("万年灵芝");
        zhanDou();
        htmlContent.linkName("进入太尉宝库");
        htmlContent.linkName("宝库守卫元帅.高破风");
        htmlContent.linkName("攻击", true);
        htmlContent.linkName("万年灵芝");
        htmlContent.linkName("万年灵芝");
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
        htmlContent.linkName("太尉", true);
        htmlContent.linkName("太尉", true);
        htmlContent.linkName("太尉", true);
        htmlContent.linkName("太尉", true);
        htmlContent.linkName("返回游戏");
    }

    /**
     * 获取进去材料
     */
    public void getCl() {
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
        htmlContent.linkName("钥匙", true);
        htmlContent.linkName("钥匙", true);
        htmlContent.linkName("钥匙", true);
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("进入太尉宝库副本");
    }
}


















