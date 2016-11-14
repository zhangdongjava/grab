package com.zzz.play.util;

import com.zzz.play.mark.Global;
import com.zzz.play.setp.Step;

import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/11/1.
 */
public class GlobalUtil {

    private LinkedList<Step> steps;

    public GlobalUtil() {
        steps = new LinkedList<>();
    }

    public void clear(){
        steps.clear();
    }

    public void addStep(Step step) {
        if (step instanceof Global)
            steps.add(step);
    }

    public void run() {
        for (Step step : steps) {
            step.run();
        }
    }

}
