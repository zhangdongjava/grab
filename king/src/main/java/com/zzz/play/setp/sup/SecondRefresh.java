package com.zzz.play.setp.sup;

import com.zzz.play.setp.impl.config.BaseStep;

import java.util.Calendar;
import java.util.Date;

/**
 * 超类
 * Created by dell_2 on 2016/11/10.
 */
public class SecondRefresh extends BaseStep {

    /**
     * 是否可以运行
     */
    protected boolean ableIn = true;

    /**
     * 结束运行时时间 过了这天就刷新
     */
    protected Date lastDate;


    protected void fresh() {
        if (lastDate == null) {
            return;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(lastDate);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        if (c1.get(Calendar.DAY_OF_MONTH) != c2.get(Calendar.DAY_OF_MONTH)) {
            ableIn = true;
        }
    }
}
