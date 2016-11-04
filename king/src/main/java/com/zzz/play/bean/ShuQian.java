package com.zzz.play.bean;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Administrator on 2016/11/4 0004.
 */
public class ShuQian implements Serializable {
    private String name;
    private String daqu;
    private String url;
    private LinkedList<String> scritps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDaqu() {
        return daqu;
    }

    public void setDaqu(String daqu) {
        this.daqu = daqu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LinkedList<String> getScritps() {
        return scritps;
    }

    public void setScritps(LinkedList<String> scritps) {
        this.scritps = scritps;
    }
}
