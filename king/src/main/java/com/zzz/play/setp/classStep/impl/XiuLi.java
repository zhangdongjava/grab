package com.zzz.play.setp.classStep.impl;

import com.zzz.play.setp.impl.config.BaseStep;
import com.zzz.play.setp.sup.SecondRefresh;
import com.zzz.play.setp.sys.FormSubmit;
import com.zzz.play.setp.sys.GoodsUse;
import com.zzz.play.util.HtmlContent;
import com.zzz.play.util.sys.SetProperties;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 装备修理
 * Created by dell_2 on 2016/11/10.
 */
public class XiuLi extends BaseStep {

    private HtmlContent htmlContent;

    private long lastTime;
    private long jiange = 100000;

    public XiuLi(HtmlContent htmlContent) {
        this.htmlContent = htmlContent;
    }

    public boolean run() {
        if (System.currentTimeMillis() - lastTime < jiange) {
            return false;
        }
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("赤甲");
        htmlContent.linkName("装备");
        htmlContent.linkName("修理所有");
        htmlContent.linkName("返回游戏");
        xiuLili("周武师", "仙兵");
        xiuLili("柴进", "仙兵");
        xiuLili("鲁智深", "仙兵");
        xiuLili("徐宁", "冲锋战车");
        htmlContent.linkName("返回游戏");
        lastTime = System.currentTimeMillis();
        return true;
    }

    private void xiuLili(String... names) {
        htmlContent.linkName("武将");
        for (String name : names) {
            if (!htmlContent.linkName(name, true).isSuccess()) {
                htmlContent.linkName("返回游戏");
                return;
            }
        }
        htmlContent.linkName("装备");
        htmlContent.linkName("修理所有");
        htmlContent.linkName("返回游戏");
    }

}
