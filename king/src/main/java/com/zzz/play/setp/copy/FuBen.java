package com.zzz.play.setp.copy;

import com.zzz.play.setp.sup.SecondRefresh;
import com.zzz.play.setp.sys.BuyDrug;
import com.zzz.play.setp.sys.GoodsSale2;
import com.zzz.play.setp.sys.GoodsSave2;
import com.zzz.play.util.HtmlContent;
import com.zzz.play.util.UtilDto;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
public abstract class FuBen extends SecondRefresh {

    protected BuyDrug buyDrug = new BuyDrug();

    protected GoodsSale2 goodsSale2 = new GoodsSale2(inClearLine());
    protected GoodsSave2 goodsSave = new GoodsSave2(saveLine());

    /**
     * 运行了多少次
     */
    protected int runNum;


    @Override
    public void setHtmlContent(HtmlContent htmlContent) {
        super.setHtmlContent(htmlContent);
        buyDrug.setHtmlContent(htmlContent);
        goodsSale2.setHtmlContent(htmlContent);
        goodsSave.setHtmlContent(htmlContent);
    }

    @Override
    public void setUtilDto(UtilDto utilDto) {
        super.setUtilDto(utilDto);
        buyDrug.setUtilDto(utilDto);
        goodsSale2.setUtilDto(utilDto);
        goodsSave.setUtilDto(utilDto);
    }

    /**
     * 进入副本清理物品字符串
     *
     * @return
     */
    public abstract String inClearLine();

    /**
     * 推出副本清理物品字符串
     *
     * @return
     */
    public abstract String outClearLine();

    /**
     * 保存物品集合
     *
     * @return
     */
    public abstract String saveLine();


    /**
     * 准备东西
     *
     * @return
     */
    public abstract void ready();

    @Override
    public boolean run() {
        fresh();
        if (!ableIn) {
            return false;
        }
        goodsSale2.run();
        ready();
        if (!fbRun()) {
            return false;
        }
        goodsSave.run();
        goodsSale2.clear();
        goodsSale2.setGoods(outClearLine());
        goodsSale2.run();
        return true;
    }

    public abstract boolean fbRun();


}
































