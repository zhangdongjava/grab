package com.zzz.play.bean;

import java.util.List;

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
    private boolean xiangGuang;

    private int ylNum;

    private List<String> saveYinZi;

    private int interval;

    public SetBean(){
        interval = 10000;
    }

    public int getYlNum() {
        return ylNum;
    }

    public void setYlNum(int ylNum) {
        this.ylNum = ylNum;
    }

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

    public List<String> getSaveYinZi() {
        return saveYinZi;
    }

    public void setSaveYinZi(List<String> saveYinZi) {
        this.saveYinZi = saveYinZi;
    }

    public boolean isXiangGuang() {
        return xiangGuang;
    }

    public void setXiangGuang(boolean xiangGuang) {
        this.xiangGuang = xiangGuang;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}