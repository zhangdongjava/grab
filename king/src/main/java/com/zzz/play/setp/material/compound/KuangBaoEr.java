package com.zzz.play.setp.material.compound;

import com.zzz.play.setp.material.HuPi;
import com.zzz.play.setp.sup.SecondRefresh;
import com.zzz.play.util.sys.GoodsNumUtil;

import java.util.Date;

/**
 * 狂暴二合成
 * Created by dell_2 on 2016/11/11.
 */
public class KuangBaoEr extends SecondRefresh {

    private int bingPo;

    private HuPi huPiScript = new HuPi();
    /**
     * 刷到的虎皮数量
     */
    private int getHuPi;

    /**
     * 是否运行中
     */
    private boolean runIng = false;

    private static int HU_NUM = 30;
    private static int BING_NUM = 5;
    private static int MENG_NUM = 50;

    @Override
    public boolean run() {
        fresh();
        if (!ableIn) {
            return false;
        }
        utilDto.clearUtil.clearPack(htmlContent);
        contrl();
        return super.run();
    }

    /**
     * 控制
     */
    private void contrl() {
        if (runIng) {
            shua();
        } else {
            init();
            shua();
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
        bingPo = numUtil.map.get("冰魄珠") / BING_NUM;
        numUtil.clear();
        if (bingPo == 0) {
            lastDate = new Date();
            ableIn = false;
            return;
        }
        goodsTakeout.setGoods("景阳岗虎皮_"+(bingPo*HU_NUM));
        goodsTakeout.run();
        runIng = true;
        numUtil.setNames("景阳岗虎皮");
        numUtil.run();
        getHuPi = numUtil.map.get("景阳岗虎皮");
        System.out.println(numUtil.map);
        System.out.println("冰魄->" + bingPo * BING_NUM);
        numUtil.clear();
    }

    /**
     * 刷千虫丝
     */
    private void shua() {
        huPiScript.setHtmlContent(htmlContent);
        huPiScript.setUtilDto(utilDto);
        huPiScript.run();
        getHuPi += huPiScript.getNum();
        GoodsNumUtil numUtil = utilDto.goodsNumUtil;
        int xymh = (getHuPi / HU_NUM * MENG_NUM);
        if (xymh == 0) {
            return;
        }
        goodsTakeout.setGoods("蒙汗药_"+xymh);
        goodsTakeout.run();
        numUtil.setNames("蒙汗药");
        numUtil.run();
        //物品里的蒙汗药数量
        int ssmh = numUtil.map.get("蒙汗药");
        System.out.println("物品里有:虎皮->" + getHuPi + ",冰魄珠->" + (bingPo * BING_NUM) + ",蒙汗药->" + ssmh);
        if (ssmh >= MENG_NUM && getHuPi >= HU_NUM) {
            hecheng();
        }
    }

    /**
     * 合成强体
     */
    private void hecheng() {
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("景阳岗");
        htmlContent.linkName("上:山路↑");
        htmlContent.linkName("上:山下↑");
        htmlContent.linkName("上:山路↑");
        htmlContent.linkName("上:树林↑");
        htmlContent.linkName("上:景阳岗上↑");
        htmlContent.linkName("行者.武松");
        htmlContent.linkName("合成狂暴二");
        while (htmlContent.getDocument().text().contains("完成合成")) {
            getHuPi -= HU_NUM;
            bingPo -= 1;
            htmlContent.linkName("返回武松");
            htmlContent.linkName("合成狂暴二");
        }
        htmlContent.linkName("返回武松");
        htmlContent.linkName("返回游戏");
        goodsSave.setGoods("狂暴二,蒙汗药");
        goodsSave.run();
        if (bingPo <= 0) {
            runIng = false;
            getHuPi = 0;
        }

    }

}
