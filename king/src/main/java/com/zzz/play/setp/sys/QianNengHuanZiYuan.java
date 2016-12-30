package com.zzz.play.setp.sys;

import com.zzz.play.setp.impl.config.BaseStep;

/**
 * 潜能换资源
 * Created by dell_2 on 2016/12/29.
 */
public class QianNengHuanZiYuan extends BaseStep {

    private String ten;

    @Override
    public boolean run() {
        long qianNeng = getQian();
        if (qianNeng < 10000000) {
            return false;
        }else if(qianNeng>100000000){
            ten = "(1000万:500万)";
        }else{
            ten = "(100万:50万)";
        }
        if(!htmlContent.getText().contains("[寒玉狮前厅]")){
            htmlContent.linkName("神行");
            htmlContent.linkName("去郓城");
            htmlContent.linkName("右:郓城西→");
            htmlContent.linkName("右:小路→");
            htmlContent.linkName("右:小路→");
            htmlContent.linkName("右:小路→");
            htmlContent.linkName("上:官道↑");
            htmlContent.linkName("上:大门↑");
            htmlContent.linkName("上:寒玉狮前厅↑");
        }
        htmlContent.linkName("灵隐和尚");
        htmlContent.linkName("潜能转资源(100:50)");
        htmlContent.linkName("1潜能转化为粮草"+ten);
        htmlContent.linkName("返回灵隐和尚");
        htmlContent.linkName("潜能转资源(100:50)");
        htmlContent.linkName("2潜能转化为木材"+ten);
        htmlContent.linkName("返回灵隐和尚");
        htmlContent.linkName("潜能转资源(100:50)");
        htmlContent.linkName("4潜能转化为石料"+ten);
        htmlContent.linkName("返回灵隐和尚");
        htmlContent.linkName("潜能转资源(100:50)");
        htmlContent.linkName("3潜能转化为生铁"+ten);
        htmlContent.linkName("返回游戏");
        return true;
    }

    private long getQian() {
        htmlContent.linkName("返回", true);
        htmlContent.linkName("状态");
        String[] text = htmlContent.getText().split("\\s");
        for (String s : text) {
            if (s.startsWith("潜能:")) {
                htmlContent.linkName("返回游戏");
                return Long.valueOf(s.substring(3));
            }
        }
        htmlContent.linkName("返回游戏");
        return 0;
    }
}
