package com.zzz.play.bean;

/**
 * 属性配置类
 */
public class SetBean {
    private boolean yinzi;
    private boolean baiyin;
    /**
     * 是否花钱答题
     */
    private boolean dati;
    private long xiuli = 36000000;
    /**
     * 是否执行系统脚本
     */
    private boolean runsys;

    public boolean isYinzi() {
        return yinzi;
    }

    public void setYinzi(boolean yinzi) {
        this.yinzi = yinzi;
    }

    public boolean isBaiyin() {
        return baiyin;
    }

    public void setBaiyin(boolean baiyin) {
        this.baiyin = baiyin;
    }

    public long getXiuli() {
        return xiuli;
    }

    public void setXiuli(long xiuli) {
        this.xiuli = xiuli;
    }

    public boolean isDati() {
        return dati;
    }

    public void setDati(boolean dati) {
        this.dati = dati;
    }

    public boolean isRunsys() {
        return runsys;
    }

    public void setRunsys(boolean runsys) {
        this.runsys = runsys;
    }
}