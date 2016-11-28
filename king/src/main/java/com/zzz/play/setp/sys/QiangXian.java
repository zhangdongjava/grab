package com.zzz.play.setp.sys;

import com.zzz.play.setp.impl.config.BaseStep;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by dell_2 on 2016/11/11.
 */
public class QiangXian extends BaseStep {

    @Override
    public boolean run() {
        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        System.out.println("抢仙->>"+h+":"+min);
        if ((h == 18 && min >= 50)) {
            go();
            while (!htmlContent.exitsName("VS", true)) {
                htmlContent.linkName("刷新");
            }
            htmlContent.linkName("VS", true);
            boolean qianDao = false;
            while (true) {
                if(htmlContent.exitsName("普通攻击")){
                    qianDao = true;
                    break;
                }else if (htmlContent.exitsName("攻", true)) {
                    htmlContent.linkName("攻", true);
                } else if (htmlContent.exitsName("返回", true)) {
                    htmlContent.linkName("返回", true);
                } else {
                    break;
                }
            }
            if(qianDao){
                qianDao();
            }
            htmlContent.linkName("返回游戏",true);
            return true;
        }
        return false;
    }

    public void go(){
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("上东京");
        htmlContent.linkName("上:北大街↑");
        htmlContent.linkName("爬大柱");
        htmlContent.linkName("紫禁卫士");
        htmlContent.linkName("攻击紫禁卫士");
        while (htmlContent.exitsName("普通攻击")){
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("紫禁卫士");
        htmlContent.linkName("进入紫禁之巅");
        htmlContent.linkName("给银两",true);

    }

   public void qianDao(){
       htmlContent.linkName("攻方");
       htmlContent.linkName("攻方");
       htmlContent.linkName("攻方");
       while (htmlContent.exitsName("万年灵芝")){
           htmlContent.linkName("万年灵芝");
       }
       while (htmlContent.exitsName("普通攻击")){
           htmlContent.linkName("普通攻击");
       }
       htmlContent.linkName("x",true);
       htmlContent.linkName("x",true);
       htmlContent.linkName("x",true);
       htmlContent.linkName("x",true);
       htmlContent.linkName("x",true);
       htmlContent.linkName("x",true);
       htmlContent.linkName("x",true);
       htmlContent.linkName("x",true);
   }
}
