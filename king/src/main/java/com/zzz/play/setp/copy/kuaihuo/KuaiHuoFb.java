package com.zzz.play.setp.copy.kuaihuo;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.util.HtmlContent;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public abstract class KuaiHuoFb {
    protected HtmlContent htmlContent;

    public KuaiHuoFb(HtmlContent htmlContent){

        this.htmlContent = htmlContent;
    }
    public  abstract void run();
    public void zhanDouSwb(String action){
        htmlContent.linkName(action);
        zhanDouSwb();
    }
    public void zhanDouSwb(){
        LinkBean res = htmlContent.linkName("快活林守卫");
        if(!res.isSuccess()){
            res = htmlContent.linkName("快活林卫兵");
            if(!res.isSuccess()){
                return;
            }
        }
        htmlContent.linkName("攻击",true);
        while (!htmlContent.getText().contains("快剑手(0)")
                &&htmlContent.exitsName("万年灵芝")){
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
        htmlContent.linkName("返回游戏");
    }
    public void zhanDouDz(String action,String name){
        htmlContent.linkName(action);
        htmlContent.linkName(name);
        zhanDouDz();
    }
    public void zhanDouDz(){
        htmlContent.linkName("攻击快活林",true);
        while (!htmlContent.getText().contains("快剑手(0)")
                &&htmlContent.exitsName("万年灵芝")){
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
        htmlContent.linkName("返回游戏");
    }
}
