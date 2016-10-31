package com.zzz.play.setp.impl;

import com.zzz.play.setp.Step;

import java.util.LinkedList;

/**
 * 多个步骤合在一起的
 * Created by dell_2 on 2016/10/31.
 */
public class ManyStep extends BaseStep {

    protected LinkedList<Step> steps;

    public ManyStep() {
        steps = new LinkedList<>();
    }

    @Override
    public boolean run() {
        for (Step step : steps) {
            step.run();
        }
        return false;
    }

    public void addStep(Step step) {
        steps.add(step);
    }
}
