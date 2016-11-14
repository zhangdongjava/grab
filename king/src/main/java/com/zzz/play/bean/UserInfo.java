package com.zzz.play.bean;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Administrator on 2016/11/4 0004.
 */
public class UserInfo implements Serializable {
    private static long serialVersionUID = 1L;
    /**
     * 是否登录
     */
    private boolean login;
    private String name;
    private String daqu;
    private String url;
    private String currUrl;
    private LinkedList<String> scritps1;
    private LinkedList<String> scritps2;

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

    public LinkedList<String> getScritps1() {
        return scritps1;
    }

    public void setScritps1(LinkedList<String> scritps1) {
        this.scritps1 = scritps1;
    }

    public LinkedList<String> getScritps2() {
        return scritps2;
    }

    public void setScritps2(LinkedList<String> scritps2) {
        this.scritps2 = scritps2;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }


    public String getCurrUrl() {
        return currUrl;
    }

    public void setCurrUrl(String currUrl) {
        this.currUrl = currUrl;
    }
}
