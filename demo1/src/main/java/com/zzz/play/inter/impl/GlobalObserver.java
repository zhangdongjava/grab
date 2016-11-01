package com.zzz.play.inter.impl;

import com.zzz.play.inter.Observer;
import com.zzz.play.setp.TextParse;
import com.zzz.play.util.GlobalUtil;

/**
 * Created by dell_2 on 2016/11/1.
 */
public class GlobalObserver implements Observer {

    private GlobalUtil globalUtil;

    public GlobalObserver(GlobalUtil globalUtil) {
        this.globalUtil = globalUtil;
    }

    @Override
    public void run(TextParse textParse) {
        globalUtil.run(textParse);
    }
}
