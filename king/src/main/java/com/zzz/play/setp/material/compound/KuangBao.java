package com.zzz.play.setp.material.compound;

import com.zzz.play.setp.material.HuPi;
import com.zzz.play.setp.material.NiuPi;
import com.zzz.play.setp.sup.SecondRefresh;
import com.zzz.play.util.sys.GoodsNumUtil;

import java.util.Date;

/**
 * 狂暴奇书合成
 * Created by dell_2 on 2016/11/11.
 */
public class KuangBao extends SecondRefresh {

    private int bingPo;
    /**
     * 还差的虎皮
     */
    private int huPi;
    /**
     * 还差的蒙汗
     */
    private int mengHan;

    /**
     * 一共需要蒙汗药
     */
    private int mengHanTotal;
    private HuPi huPiScript = new HuPi();
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
            if (huPi <= 0 && mengHan <= 0) {
                hecheng();
            } else {
                shua();
            }
        } else {
            init();
            if (huPi <= 0 && mengHan <= 0) {
                hecheng();
            } else {
                shua();
            }
        }
    }

    /**
     * 初始化材料数量，从物品中获取到
     */
    public void init() {
        goodsTakeout.setGoods("冰魄珠_100000");
        goodsTakeout.run();
        GoodsNumUtil numUtil = utilDto.goodsNumUtil;
        numUtil.setNames("冰魄珠");
        numUtil.run();
        bingPo = numUtil.map.get("冰魄珠") / 2;
        numUtil.clear();
        if (bingPo == 0) {
            lastDate = new Date();
            ableIn = false;
            return;
        }
        runIng = true;
        mengHanTotal = bingPo * 30;
        goodsTakeout.setGoods("蒙汗药_" + mengHanTotal);
        goodsTakeout.run();
        numUtil.setNames("蒙汗药", "景阳岗虎皮");
        numUtil.run();
        this.mengHan = mengHanTotal - numUtil.map.get("蒙汗药");
        huPi = bingPo * 16 - numUtil.map.get("景阳岗虎皮");
        System.out.println(numUtil.map);
        System.out.println("冰魄->" + bingPo*2);
        System.out.println("需要蒙汗->" + this.mengHan);
        System.out.println("需要虎皮->" + huPi);
        numUtil.clear();
    }

    /**
     * 刷千虫丝
     */
    private void shua() {
        if (huPi > 0) {
            huPiScript.setHtmlContent(htmlContent);
            huPiScript.setUtilDto(utilDto);
            huPiScript.run();
            System.out.println("需要虎皮:" + huPi);
            huPi -= huPiScript.getNum();
            System.out.println("还需要虎皮:" + huPi);
        }
        if (mengHan > 0) {
            GoodsNumUtil numUtil = utilDto.goodsNumUtil;
            goodsTakeout.setGoods("蒙汗药_" + mengHan);
            goodsTakeout.run();
            numUtil.setNames("蒙汗药");
            numUtil.run();
            this.mengHan = mengHanTotal - (numUtil.map.get("蒙汗药"));
        }


        if (huPi <= 0 && mengHan < 0) {
            hecheng();
        }
    }

    /**
     * 合成强体
     */
    private void hecheng() {
        runIng = false;
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("景阳岗");
        htmlContent.linkName("上:山路↑");
        htmlContent.linkName("上:山下↑");
        htmlContent.linkName("上:山路↑");
        htmlContent.linkName("上:树林↑");
        htmlContent.linkName("上:景阳岗上↑");
        htmlContent.linkName("行者.武松");
        htmlContent.linkName("研制狂暴奇书");
        while (htmlContent.getDocument().text().contains("完成研制")) {
            htmlContent.linkName("返回武松");
            htmlContent.linkName("研制狂暴奇书");
        }
        htmlContent.linkName("返回武松");
        htmlContent.linkName("返回游戏");
        goodsSave.setGoods("狂暴奇书");
        goodsSave.run();
        mengHan = 0;
        huPi = 0;
        bingPo = 0;
    }

}
