package com.zzz.play.setp.impl;

/**
 * Created by Administrator on 2016/10/31 0031.
 */
public class TimeWait extends BaseStep {

    private int time;

    private boolean res = false;

    @Override
    public boolean run() {
        res = false;
        setDate();
        awaitTime();
        return res;
    }

    public static TimeWait parse(String line) {
        TimeWait timeWait = new TimeWait();
        timeWait.time = Integer.valueOf(line.substring(4).trim());
        return timeWait;
    }

    private void setDate() {
        String text = htmlContent.getDocument().text();
        String[] datas = text.split("\\s");
        for (String data : datas) {
            if (data.contains("分钟刷新")) {
                buildDate(data.trim());
                return;
            }
        }
    }

    private void buildDate(String line) {
        Integer fen = Integer.valueOf(line.substring(2, line.indexOf("分")));
        if (fen <= this.time) {
            res = true;
        }
    }

    private void awaitTime() {
        if (!res) return;
        while (htmlContent.getDocument().text().contains("分钟刷新")) {
            htmlContent.linkName("刷新");
        }
    }
}
