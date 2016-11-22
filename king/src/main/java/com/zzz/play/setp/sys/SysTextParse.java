package com.zzz.play.setp.sys;

import com.zzz.play.inter.Runable;
import com.zzz.play.setp.Step;
import com.zzz.play.setp.activity.GeiXingGuang;
import com.zzz.play.setp.classStep.impl.XiuShen;
import com.zzz.play.util.HtmlContent;

import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/11/10.
 */
public class SysTextParse implements Runable {

    private HtmlContent htmlContent;

    private LinkedList<Step> steps;

    public SysTextParse(HtmlContent htmlContent) {
        this.htmlContent = htmlContent;
        steps = new LinkedList<>();
        steps.add(new XiuShen(htmlContent));
        steps.add(new SaveBrank(htmlContent));
        //steps.add(new GeiXingGuang(htmlContent));
    }


    @Override
    public boolean run() {
        for (Step step : steps) {
            step.run();
        }
        return false;
    }

    @Override
    public String getFileName() {
        return "系统运行器";
    }

    @Override
    public boolean isClear() {
        return false;
    }
}
