package com.zzz.play.setp.sys;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.setp.impl.config.BaseStep;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
public class BuyDrug extends BaseStep {

    private String name;
    /**
     * 共需要多少个
     */
    private String num;
    /**
     * 要购买的个数 = num - 现有的
     */
    private String buyNum;
    /**
     * 当前背包数量
     */
    private int currNum;

    FormSubmit formSubmit;

    public BuyDrug() {
        formSubmit = new FormSubmit();
    }

    public BuyDrug(String line) {
        String[] lines = line.split("_");
        name = lines[0];
        num = lines[1];
        formSubmit = new FormSubmit();
        formSubmit.setValue(num);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        formSubmit.setValue(num);
        this.num = num;
    }

    @Override
    public boolean run() {
        if (!cleck()) {
            return false;
        }
        formSubmit.setHtmlContent(htmlContent);
        formSubmit.setUtilDto(utilDto);
        formSubmit.setValue(buyNum);
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("上东京");
        htmlContent.linkName("上:北大街↑");
        htmlContent.linkName("右:妙手药铺→");
        htmlContent.linkName("妙手回春");
        htmlContent.linkName("购买物品");
        htmlContent.linkName(name, true);
        formSubmit.run();
        System.out.println(htmlContent.htmlPanel.name+":"+htmlContent.getDocument().text());
        htmlContent.linkName("返回游戏");
        return true;
    }

    /**
     * 检测要品数量来判断买多少个
     * 不要购买返回false
     */
    private boolean cleck() {
        currNum = 0;
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("物品");
        htmlContent.linkName("消耗品");
        htmlContent.linkName("首页");
        htmlContent.linkName("上页");
        do {
            if (checkPage())
                break;
        } while (htmlContent.linkName("下.页").isSuccess());
        htmlContent.linkName("返回游戏");
        int buy = Integer.valueOf(num) - currNum;
        buyNum = String.valueOf(buy);
        System.out.println("现有:" + currNum + ",共需要:" + num + ",需要购买:" + buy);
        return buy > 0;
    }

    /**
     * 卖出当页物品
     */
    public boolean checkPage() {
        ExistSale existSale = existSale();
        if (existSale.exist) {
            String name = existSale.name;
            cleckOne(name);
        }
        return existSale.exist;
    }

    public void cleckOne(String name) {
        LinkBean res = htmlContent.linkName(name, true);
        name = res.getClickName();
        if (name.contains("x")) {
            currNum = Integer.valueOf(name.substring(name.indexOf("x") + 1).trim());
        } else {
            currNum = 1;
        }
    }


    /**
     * 检测是否有商品存在
     *
     * @return
     */
    public ExistSale existSale() {
        ExistSale existSale = new ExistSale();
        if (htmlContent.exitsName(name, true)) {
            existSale.exist = true;
            existSale.name = name;
            return existSale;
        }
        return existSale;
    }

    private class ExistSale {
        public boolean exist = false;
        public String name = null;
    }
}













































