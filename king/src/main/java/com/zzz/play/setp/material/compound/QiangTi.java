package com.zzz.play.setp.material.compound;

import com.zzz.play.setp.material.QianChongSi;
import com.zzz.play.setp.sup.SecondRefresh;
import com.zzz.play.util.UtilDto;
import com.zzz.play.util.sys.GoodsNumUtil;

import java.util.Date;

/**
 * 刷强体奇书
 * Created by dell_2 on 2016/11/10.
 */
public class QiangTi extends SecondRefresh {

    private int hu;
    private int huang;
    private int qian;
    private QianChongSi qianChongSi = new QianChongSi();
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
        utilDto.clearUtil.clearPack(htmlContent);
        contrl();
        return super.run();
    }

    /**
     * 控制
     */
    private void contrl() {
        if (runIng) {
            if (qian <= 0) {
                hecheng();
            } else {
                shuaQianChong();
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
        numUtil.setNames("虎骨", "黄金虎骨", "千虫丝");
        numUtil.run();
        hu = numUtil.map.get("虎骨");
        huang = numUtil.map.get("黄金虎骨");
        qian = numUtil.map.get("千虫丝");
        if (hu > huang) {
            hu = huang;
        }
        if (hu == 0) {
            lastDate = new Date();
            ableIn = false;
            return;
        }
        runIng = true;
        qian = (hu * 3) - qian;
        System.out.println(numUtil.map);
        System.out.println("虎骨->" + hu);
        System.out.println("需要千虫丝->" + qian);
        numUtil.clear();
    }

    /**
     * 刷千虫丝
     */
    private void shuaQianChong() {
        qianChongSi.setHtmlContent(htmlContent);
        qianChongSi.setUtilDto(utilDto);
        qianChongSi.run();
        System.out.println("需要千虫丝:"+qian);
        qian -= qianChongSi.getNum();
        System.out.println("还需要千虫丝:"+qian);
        if (qian <= 0) {
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
        htmlContent.linkName("黑风岭");
        htmlContent.linkName("黑风岭砍柴老者");
        htmlContent.linkName("研制强体奇书");
        while (htmlContent.getDocument().text().contains("完成研制")) {
            htmlContent.linkName("返回黑风岭砍柴老者");
            htmlContent.linkName("研制强体奇书");
        }
        htmlContent.linkName("返回黑风岭砍柴老者");
        htmlContent.linkName("返回游戏");
        goodsSale2.setGoods(".虎骨");
        goodsSale2.run();
        goodsSave.setGoods("强体奇书");
        goodsSave.run();
        qian = 0;
        hu = 0;
        huang = 0;
    }


}
