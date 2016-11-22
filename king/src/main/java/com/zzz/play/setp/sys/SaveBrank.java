package com.zzz.play.setp.sys;

import com.zzz.play.setp.Step;
import com.zzz.play.setp.impl.config.BaseStep;
import com.zzz.play.setp.sup.SecondRefresh;
import com.zzz.play.util.HtmlContent;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 保存金币
 * Created by dell_2 on 2016/11/10.
 */
public class SaveBrank extends BaseStep {

    private HtmlContent htmlContent;

    private FormSubmit formSubmit;

    private Set<String> notSave = new HashSet<>();

    public SaveBrank(HtmlContent htmlContent) {
        this.htmlContent = htmlContent;
        formSubmit = new FormSubmit();
        formSubmit.setHtmlContent(htmlContent);
        formSubmit.setValue("10000000");
        notSave.add("大嘴哥");
    }


    public boolean run() {
        if (notSave.contains(htmlContent.htmlPanel.user.getName())) {
            return false;
        }
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("帮派");
        htmlContent.linkName("帮派管理");
        htmlContent.linkName("帮派资源");
        htmlContent.linkName("捐献银两");
        //你目前有银两60984868。请输入你要捐献的银两数量
        int index1 = htmlContent.getText().indexOf("银两");
        int index2 = htmlContent.getText().indexOf("。");
        int yin = Integer.valueOf(htmlContent.getText().substring(index1 + 1, index2));
        if (yin > 20000000) {
            formSubmit.run();
        }
        htmlContent.linkName("返回游戏");

        return true;
    }

}