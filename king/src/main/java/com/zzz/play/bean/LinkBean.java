package com.zzz.play.bean;

/**
 * Created by dell_2 on 2016/10/31.
 */
public class LinkBean {

    private boolean success;
    private String clickName;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getClickName() {
        return clickName;
    }

    public void setClickName(String clickName) {
        this.clickName = clickName;
    }

    public void reset() {
        success = false;
        clickName = null;
        url = null;

    }
}
