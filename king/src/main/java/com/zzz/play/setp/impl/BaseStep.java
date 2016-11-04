package com.zzz.play.setp.impl;

import com.zzz.play.setp.Step;
import com.zzz.play.setp.TextParse;
import com.zzz.play.util.HtmlContent;
import com.zzz.play.util.UtilDto;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class BaseStep implements Step {

    public static boolean IS_WAIT = false;

    private int lineNum;

    protected HtmlContent htmlContent;

    protected TextParse textParse;
    /**
     * 执行前是否执行base点击
     */
    protected boolean base = false;

    /**
     * 每一次执行之前是否执行base点击
     */
    protected boolean mb = false;

    protected boolean like = false;

    protected UtilDto utilDto;

    public void setLike(boolean like) {
        this.like = like;
    }

    public boolean isBase() {
        return base;
    }

    public void setBase(boolean base) {
        this.base = base;
    }

    @Override
    public boolean run() {
        return false;
    }

    @Override
    public void setStep(TextParse textParse) {
        this.textParse = textParse;
    }

    @Override
    public TextParse getTextParse() {
        return textParse;
    }

    @Override
    public HtmlContent getHtmlContent() {
        return htmlContent;
    }

    @Override
    public void setHtmlContent(HtmlContent htmlContent) {
        this.htmlContent = htmlContent;
    }

    public boolean isMb() {
        return mb;
    }

    @Override
    public void setMb(boolean mb) {
        this.mb = mb;
    }

    public void baseRun() {
        TextParse textParse = getTextParse();
        if (textParse != null && (base))
            textParse.baseRun();
    }

    public void mbRun() {
        TextParse textParse = getTextParse();
        if (textParse != null && (mb))
            textParse.baseRun();
    }

    @Override
    public void setLineNum(int num) {
        lineNum = num;
    }

    @Override
    public int getLineNum() {
        return lineNum;
    }

    public void await() {

    }

    @Override
    public void setUtilDto(UtilDto utilDto) {
        this.utilDto = utilDto;
    }


}
