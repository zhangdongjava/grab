package com.zzz.play.setp.sys;

import com.zzz.play.setp.impl.config.BaseStep;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
public class BuyDrug extends BaseStep {

    private String name;
    private String num;
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
}
























