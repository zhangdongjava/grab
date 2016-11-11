package com.zzz.play.setp.sup;

import com.zzz.play.setp.impl.config.BaseStep;
import com.zzz.play.setp.sys.BuyDrug;
import com.zzz.play.setp.sys.GoodsSale2;
import com.zzz.play.setp.sys.GoodsSave2;
import com.zzz.play.setp.sys.GoodsTakeout;
import com.zzz.play.util.HtmlContent;
import com.zzz.play.util.UtilDto;

import java.util.Calendar;
import java.util.Date;

/**
 * 超类
 * Created by dell_2 on 2016/11/10.
 */
public abstract class SecondRefresh extends BaseStep {

    protected BuyDrug buyDrug = new BuyDrug();

    protected GoodsSale2 goodsSale2 = new GoodsSale2();
    protected GoodsSave2 goodsSave = new GoodsSave2();
    protected GoodsTakeout goodsTakeout =new GoodsTakeout();

    @Override
    public void setHtmlContent(HtmlContent htmlContent) {
        super.setHtmlContent(htmlContent);
        buyDrug.setHtmlContent(htmlContent);
        goodsSale2.setHtmlContent(htmlContent);
        goodsSave.setHtmlContent(htmlContent);
        goodsTakeout.setHtmlContent(htmlContent);
    }

    @Override
    public void setUtilDto(UtilDto utilDto) {
        super.setUtilDto(utilDto);
        buyDrug.setUtilDto(utilDto);
        goodsSale2.setUtilDto(utilDto);
        goodsSave.setUtilDto(utilDto);
        goodsTakeout.setUtilDto(utilDto);
    }

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
