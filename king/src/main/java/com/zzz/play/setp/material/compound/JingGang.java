package com.zzz.play.setp.material.compound;

import com.zzz.play.setp.material.NiuPi;
import com.zzz.play.setp.sup.SecondRefresh;
import com.zzz.play.util.sys.GoodsNumUtil;

import java.util.Date;

/**
 * 金刚奇书合成
 * Created by dell_2 on 2016/11/11.
 */
public class JingGang extends SecondRefresh {

    private int mitu;
    private int niupi;
    private int du;
    private NiuPi niuPi = new NiuPi();
    /**
     * 是否运行中
     */
    private boolean runIng = false;

    @Override
    public boolean run() {

        fresh();
        if (!ableIn) {
            return false;
        }
        contrl();
        return super.run();
    }

    /**
     * 控制
     */
    private void contrl() {
        if (runIng) {
            if (niupi <= 0) {
                hecheng();
            } else {
                shua();
            }
        } else {
            init();
        }
    }

    /**
     * 初始化材料数量，从物品中获取到
     */
    public void init() {
        GoodsNumUtil numUtil = utilDto.goodsNumUtil;
        goodsTakeout.setGoods("太尉秘图_20");
        goodsTakeout.run();
        numUtil.setNames("太尉秘图", "牛皮");
        numUtil.run();
        mitu = numUtil.map.get("太尉秘图") / 2;
        if (mitu == 0) {
            lastDate = new Date();
            ableIn = false;
            return;
        }
        runIng = true;
        niupi = numUtil.map.get("牛皮");
        du = mitu * 4;

        niupi = mitu * 20 - niupi;
        System.out.println(numUtil.map);
        System.out.println("太尉->" + mitu);
        System.out.println("需要牛皮->" + niupi);
        numUtil.clear();
    }

    /**
     * 刷千虫丝
     */
    private void shua() {
        niuPi.setHtmlContent(htmlContent);
        niuPi.setUtilDto(utilDto);
        niuPi.run();
        System.out.println("需要牛皮:" + niupi);
        niupi -= niuPi.getNum();
        System.out.println("还需要牛皮:" + niupi);
        if (niupi <= 0) {
            hecheng();
        }
    }

    /**
     * 合成强体
     */
    private void hecheng() {
        goodsTakeout.setGoods("太尉秘图_20,变异菜青虫毒液_" + du);
        goodsTakeout.run();
        runIng = false;
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("上东京");
        htmlContent.linkName("上:北大街↑");
        htmlContent.linkName("游方和尚");
        htmlContent.linkName("研制金刚奇书");
        while (htmlContent.getText().contains("完成研制")) {
            htmlContent.linkName("返回游方和尚");
            htmlContent.linkName("研制金刚奇书");
        }
        htmlContent.linkName("返回游方和尚");
        htmlContent.linkName("返回游戏");
        goodsSave.setGoods("金刚奇书");
        goodsSave.run();
        niupi = 0;
        mitu = 0;
    }

}
