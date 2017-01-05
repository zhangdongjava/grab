package com.zzz.play.setp.classStep;

import com.zzz.play.setp.impl.config.BaseStep;
import com.zzz.play.setp.sys.FormSubmit;
import com.zzz.play.setp.sys.GoodsSale2;
import com.zzz.play.setp.sys.GoodsSave2;
import com.zzz.play.setp.sys.SaveBrank;
import com.zzz.play.util.HtmlContent;

/**
 * Created by dell_2 on 2017/1/5.
 */
public class TaYuMao extends BaseStep {
    //裁缝铺下标
    private int cfIndex;
    //裁缝铺最小等级
    private int minLv;
    //打造数量
    private int num;
    //每个裁缝铺次数
    private int count;
    //剩余事件
    private int shijian;
    private int cfNum;

    private FormSubmit formSubmit;

    private GoodsSave2 goodsSave;
    private GoodsSale2 goodsSale;

    public TaYuMao() {//踏云帽（士兵）(40级)
        formSubmit = new FormSubmit();
        goodsSave = new GoodsSave2();
        goodsSave.setGoods("踏云帽（士兵");
        goodsSale = new GoodsSale2();
        goodsSale.setGoods("踏云帽");
    }

    @Override
    public boolean run() {
        goodsSave.run();
        cfIndex = 0;
        cfNum = 0;
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("庄院");
        htmlContent.linkName("庄院\\");
        buildCount();
        htmlContent.linkName("间", 6, true);
        paseCf();
        daZao();
        return false;
    }

    @Override
    public void setHtmlContent(HtmlContent htmlContent) {
        super.setHtmlContent(htmlContent);
        formSubmit.setHtmlContent(htmlContent);
        goodsSale.setHtmlContent(htmlContent);
        goodsSave.setHtmlContent(htmlContent);
    }

    private void buildCount() {
        String[] lines = htmlContent.getText().split("\\s");
        for (String line : lines) {
            if (line.startsWith("事件:")) {
                int index = line.indexOf("/");
                shijian = (Integer.valueOf(line.substring(index + 1)) - Integer.valueOf(line.substring(3, index)));
            }
        }
    }

    private void paseCf() {
        minLv = 100;
        String[] lines = htmlContent.getText().split("\\s");
        for (String line : lines) {
            if (line.endsWith("级)")) {
                int lv = Integer.valueOf(line.substring(6, line.length() - 2));
                if (lv < minLv) {
                    minLv = lv;
                }
                cfNum++;
            }
        }
        num = minLv * 10;
        formSubmit.setValue(String.valueOf(num));
        count = shijian / cfNum;
    }


    private void daZao() {
        for (int i = 0; i < cfNum; i++) {
            htmlContent.linkName("裁缝铺", i, true);
            if (htmlContent.getText().contains("正在打造踏云帽")) {
                return;
            }
            for (int j = 0; j < count; j++) {
                if ((i + 1) % 6 == 0) {
                    daZao("打造士兵防具");
                } else {
                    daZao("打造武将防具");
                }
                if (!htmlContent.exitsName("打造士兵防具")) {
                    break;
                }
            }
            htmlContent.linkName("返回裁缝铺列表");
        }
        htmlContent.linkName("返回游戏");
        if(cfNum>0){
            goodsSale.run();
        }
    }

    private void daZao(String type) {
        htmlContent.linkName(type);
        htmlContent.linkName("帽子");
        htmlContent.linkName("踏云帽", true);
        formSubmit.run();
    }


    public static void main(String[] args) {
        new TaYuMao().buildCount();
    }
}
