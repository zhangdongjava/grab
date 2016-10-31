package com.zzz.play.setp.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 到点执行的
 * Created by dell_2 on 2016/10/31.
 */
public class TimeStep extends ManyStep {

    private Date date = new Date();

    @Override
    public boolean run() {
        boolean res = false;
        Date date1 = getNowBeforOneMinute();
        if (date1.after(date)) {
            res = super.run();
            setDate();
        }
        return res;
    }

    /**
     * 获取当前时间前一分钟
     *
     * @return
     */
    private Date getNowBeforOneMinute() {
        Date curr = new Date();
        Calendar cd = Calendar.getInstance();
        cd.setTime(curr);
        cd.add(Calendar.MINUTE, 1);
        cd.add(Calendar.SECOND, 30);
        return cd.getTime();
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
        Date curr = new Date();
        Calendar cd = Calendar.getInstance();
        cd.setTime(curr);
        cd.add(Calendar.MINUTE, fen);
        date = cd.getTime();
    }

    public static void main(String[] args) throws InterruptedException {
        Date date = new Date();
        TimeUnit.SECONDS.sleep(1);
        Date curr = new Date();
        System.out.println(curr);
        Calendar cd = Calendar.getInstance();
        cd.setTime(curr);
        cd.add(Calendar.MINUTE, -1);
        curr = cd.getTime();
        System.out.println(curr.before(date));
    }
}
