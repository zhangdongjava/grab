package com.zzz.play.setp.copy;

import com.zzz.play.setp.impl.config.BaseStep;
import com.zzz.play.setp.sys.BuyDrug;
import com.zzz.play.setp.sys.GoodsSale2;
import com.zzz.play.util.HtmlContent;
import com.zzz.play.util.UtilDto;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
public abstract class FuBen extends BaseStep {

    protected BuyDrug buyDrug = new BuyDrug();
    /**
     * 是否可以进入副本
     */
    protected boolean ableIn = true;
    /**
     * 运行了多少次
     */
    protected int runNum;


    @Override
    public void setHtmlContent(HtmlContent htmlContent) {
        super.setHtmlContent(htmlContent);
        buyDrug.setHtmlContent(htmlContent);
    }

    @Override
    public void setUtilDto(UtilDto utilDto) {
        super.setUtilDto(utilDto);
        buyDrug.setUtilDto(utilDto);
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
     * 准备东西
     *
     * @return
     */
    public abstract void ready();

    @Override
    public boolean run() {
        if (!ableIn) {
            return false;
        }
        GoodsSale2 goodsSale2 = new GoodsSale2(inClearLine());
        goodsSale2.setHtmlContent(htmlContent);
        goodsSale2.setUtilDto(utilDto);
        goodsSale2.run();
        ready();
        fbRun();
        goodsSale2.clear();
        goodsSale2.setGoods(outClearLine());
        goodsSale2.run();
        return true;
    }

    public abstract void fbRun();
}
































