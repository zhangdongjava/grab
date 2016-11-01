package com.zzz.play.util;

import com.zzz.play.setp.Step;
import com.zzz.play.setp.TextParse;

import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/11/1.
 */
public class GlobalUtil {


    private HtmlContent htmlContent;

    private LinkedList<Step> steps;

    public GlobalUtil(HtmlContent htmlContent) {
        this.htmlContent = htmlContent;
        steps = new LinkedList<>();
    }

    public void addStep(Step step) {
        steps.add(step);
    }

    public void run(TextParse textParse) {
        for (Step step : steps) {
            step.run();
        }
        textParse.run();
    }

}
