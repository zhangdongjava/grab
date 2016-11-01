package com.zzz.play.util;

import com.zzz.play.setp.Step;
import com.zzz.play.setp.TextParse;

import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/11/1.
 */
public class GlobalUtil {


    private HtmlContent htmlContent;

    public boolean change;

    private LinkedList<Step> steps;

    public GlobalUtil() {
        steps = new LinkedList<>();
    }

    public void addStep(Step step) {
        steps.add(step);
    }

    public void run() {
        System.out.println("执行全局脚本");
        for (Step step : steps) {
            if (!change) {
                change = step.run();
            } else {
                step.run();
            }
        }
    }

}
