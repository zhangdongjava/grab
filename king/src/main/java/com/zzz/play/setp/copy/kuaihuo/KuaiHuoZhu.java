package com.zzz.play.setp.copy.kuaihuo;

import com.sun.prism.impl.BaseMesh;
import com.zzz.play.util.HtmlContent;

/**
 * 快活先进脚本
 * Created by Administrator on 2016/11/14 0014.
 */
public class KuaiHuoZhu extends KuaiHuoFb {

    private String name;


    public KuaiHuoZhu(HtmlContent htmlContent, String name) {
        super(htmlContent);
        this.name = name;
    }

    @Override
    public boolean run() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("队伍");
        htmlContent.linkName("创建队伍");
        while (!htmlContent.exitsName(name,true)) {
            htmlContent.linkName("返回游戏");
            htmlContent.linkName("队伍");
        }
        htmlContent.linkName("允许");
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("景阳岗");
        htmlContent.linkName("追星捕快");
        htmlContent.linkName("传送至快活林副本");
        if(htmlContent.getText().contains("今天你已进入")){
            return false ;
        }
        zhanDouSwb();
        zhanDouSwb("进入防风林");
        zhanDouSwb("上:防风林↑");
        zhanDouSwb("上:防风林↑");
        zhanDouSwb("上:防风林↑");
        zhanDouSwb("下:防风林↓");
        zhanDouSwb("下:防风林↓");
        zhanDouSwb("下:防风林↓");
        zhanDouSwb("下:防风林↓");
        zhanDouSwb("下:防风林↓");
        zhanDouSwb("下:防风林↓");
        zhanDouSwb("上:防风林↑");
        zhanDouSwb("上:防风林↑");
        zhanDouSwb("上:防风林↑");
        zhanDouDz("进入东林","快活林四弟子");
        zhanDouDz("进入东路","快活林三弟子");
        zhanDouDz("进入东冲","快活林二弟子");
        zhanDouDz("进入东山","快活林大弟子");
        zhanDouDz("进入会合点","快活林大师兄");
        while (!htmlContent.exitsName(name,true)){
            htmlContent.linkName("刷新");
        }
        zhanDouDz("进入快活牌坊","快活林三当家");
        zhanDouDz("进入快活隘口","快活林二当家");
        zhanDouDz("进入快活客栈","快活林大当家");
        htmlContent.linkName("进入厢房");
        htmlContent.linkName("武将");
        htmlContent.linkName("史进",true);
        htmlContent.linkName("撤销近身武将");
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("快活林长老");
        htmlContent.linkName("攻击快活林长老");
        while (!htmlContent.getText().contains("快剑手(0)")){
            htmlContent.linkName("万年灵芝");
        }
        while (!htmlContent.getText().contains("快剑手(0)")){
            htmlContent.linkName("万年灵芝");
        }
        while (htmlContent.exitsName("普通攻击")){
            htmlContent.linkName("刷新");
        }
        htmlContent.linkName("x",true);
        htmlContent.linkName("x",true);
        htmlContent.linkName("x",true);
        htmlContent.linkName("x",true);
        htmlContent.linkName("x",true);
        htmlContent.linkName("x",true);
        htmlContent.linkName("x",true);
        htmlContent.linkName("x",true);
        htmlContent.linkName("x",true);
        htmlContent.linkName("x",true);
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("武将");
        htmlContent.linkName("史进",true);
        htmlContent.linkName("设为近身武将");
        htmlContent.linkName("返回游戏");
        return true;
    }


}














