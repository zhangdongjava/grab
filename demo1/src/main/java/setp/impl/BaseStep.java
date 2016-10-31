package setp.impl;

import org.jsoup.select.Elements;
import setp.Step;
import setp.TextParse;
import util.HtmlContent;

/**
 * Created by dell_2 on 2016/10/29.
 */
public  class BaseStep implements Step {

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
    public void run() {

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

    public static void await() {
        if (IS_WAIT) {
            synchronized (BaseStep.class) {
                try {
                    BaseStep.class.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static void anotify() {
        synchronized (BaseStep.class) {
            BaseStep.class.notify();
        }
    }
}
