package com.zzz.play.bean;

/**
 * 属性配置类
 */
public class SetBean {
    private boolean yinzi;
    private boolean baiyin;
    private long xiuli = 36000000;

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
}