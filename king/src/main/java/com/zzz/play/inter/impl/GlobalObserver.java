package com.zzz.play.inter.impl;

import com.zzz.play.inter.Observer;
import com.zzz.play.mark.OneStepRun;
import com.zzz.play.util.GlobalUtil;

/**
 * Created by dell_2 on 2016/11/1.
 */
public class GlobalObserver implements Observer, OneStepRun {

    private GlobalUtil globalUtil;

    private boolean change = false;

    public GlobalObserver(GlobalUtil globalUtil) {
        this.globalUtil = globalUtil;
    }

    @Override
    public void run() {
        globalUtil.run();
    }

    @Override
    public boolean change() {
        return change;
    }
}
