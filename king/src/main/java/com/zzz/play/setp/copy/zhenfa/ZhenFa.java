package com.zzz.play.setp.copy.zhenfa;

import com.zzz.play.setp.sup.SecondRefresh;
import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public abstract class ZhenFa extends SecondRefresh {
    protected static int LEFT = 0;
    protected static int DOWN = 1;
    protected static int RIGHT = 2;
    protected int ac;

    protected Logger logger = Logger.getLogger("副本");

    /**
     * 进入副本清理物品字符串
     *
     * @return
     */
    public abstract String inClearLine();


    /**
     * 要保存的物品
     *
     * @return
     */
    public abstract String saveLine();


    /**
     * 准备东西
     *
     * @return
     */
    public abstract void ready();

    @Override
    public boolean run() {
        fresh();
        if (!ableIn) {
            return false;
        }
        goodsSale2.setGoods(inClearLine());
        goodsSale2.run();
        ready();
        goTo();
        if (!fbRun()) {
            return false;
        }
        goodsSave.setGoods(saveLine());
        goodsSave.run();
        return true;
    }

    public abstract boolean fbRun();

    /**
     * 去阵法大师
     */
    public void goTo() {
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("上沧州");
        htmlContent.linkName("右:街道→");
        htmlContent.linkName("右:沧州广场→");
        htmlContent.linkName("阵法大师");
    }

    /**
     * 攻击将军
     */
    public void gjJiangJu() {
        while (htmlContent.exitsName("将军部", true)&&htmlContent.exitsName("万年灵芝",true)) {
            htmlContent.linkName("万年灵芝");
        }
        zhandou();
    }

    /**
     * 战斗
     */
    public void zhandou() {
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
        htmlContent.linkName("x", true);
        htmlContent.linkName("返回游戏");
    }

    public void zhandou(String action) {
        htmlContent.linkName(action);
        zhandou();
    }

}
