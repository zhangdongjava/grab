package com.zzz.play.setp.classStep.impl;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.setp.sup.SecondRefresh;
import com.zzz.play.util.HtmlContent;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 答题
 * Created by dell_2 on 2016/11/10.
 */
public class DaTi extends SecondRefresh {

    private HtmlContent htmlContent;

    private int total = 0;
    private int sure = 0;

    public DaTi(HtmlContent htmlContent) {
        this.htmlContent = htmlContent;
    }

    @Override
    public boolean run() {
        fresh();
        if (!ableIn) {
            return false;
        }
        return arun();

    }

    private boolean arun() {
        htmlContent.linkName("返回游戏");
        if (!htmlContent.getText().contains("[东京广场]")) {
            htmlContent.linkName("功能菜单");
            htmlContent.linkName("神行千里");
            htmlContent.linkName("上东京");
        }

        htmlContent.linkName("长须老者");
        htmlContent.linkName("状元答题");
        htmlContent.linkName("在线时间兑换");
        while (dati()) ;
        htmlContent.linkName("返回游戏");
        ableIn = false;
        lastDate = new Date();
        goodsSave.run();
        return true;
    }

    private boolean dati() {
        htmlContent.linkName("确认继续答题");
        LinkBean res = htmlContent.linkName("、", 1, true);
        System.out.println(res.getClickName());
        if(htmlContent.getText().contains("对不起")){
            total++;
        }else if(htmlContent.getText().contains("恭喜你")){
            total++;
            sure ++;
            System.out.println("正确率:"+(double)(sure/total));
        }
        return true;
    }

}
