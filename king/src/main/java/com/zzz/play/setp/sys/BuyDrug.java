package com.zzz.play.setp.sys;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.setp.impl.config.BaseStep;
import com.zzz.play.util.HtmlContent;

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
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("上东京");
        htmlContent.linkName("上:北大街↑");
        htmlContent.linkName("右:妙手药铺→");
        htmlContent.linkName("妙手回春");
        htmlContent.linkName("购买物品");
        htmlContent.linkName(name, true);
        formSubmit.run();
        htmlContent.linkName("返回游戏");
        return true;
    }

    /**
     * 检测要品数量来判断买多少个
     * 不要购买返回false
     */
    private boolean cleck() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("物品");
        htmlContent.linkName("首页");
        htmlContent.linkName("上页");
        do {
            await();
            checkPage();
        } while (htmlContent.linkName("下.页").isSuccess());
        return true;
    }

    /**
     * 卖出当页物品
     */
    public void checkPage() {
        ExistSale existSale = existSale();
        while (existSale.exist) {
            String name = existSale.name;
            cleckOne(name);
            existSale = existSale();
        }
    }

    public void cleckOne(String name) {
        LinkBean res = htmlContent.linkName(name, true);
        System.out.println(res.getClickName());
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













































