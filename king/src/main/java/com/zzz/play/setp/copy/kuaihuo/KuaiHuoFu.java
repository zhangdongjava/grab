package com.zzz.play.setp.copy.kuaihuo;

import com.zzz.play.util.HtmlContent;

import java.util.concurrent.TimeUnit;

/**
 * 快活后进脚本
 * Created by Administrator on 2016/11/14 0014.
 */
public class KuaiHuoFu extends KuaiHuoFb {

    private String name;

    public KuaiHuoFu(HtmlContent htmlContent, String name) {
        super(htmlContent);
        this.name = name;
    }

    @Override
    public boolean run() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("队伍");
        htmlContent.linkName("提取");
        htmlContent.linkName("退出队伍");
        htmlContent.linkName("确定退出");
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("好友");
        htmlContent.linkName(name,true);
        htmlContent.linkName(name,true);
        htmlContent.linkName("加入队伍");
        htmlContent.linkName("返回游戏");
        while (!htmlContent.getText().contains(name+"允许了你加入队伍")) {
            htmlContent.linkName("刷新");
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {Thread.currentThread().interrupt();}
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
        zhanDouSwb("下:防风林↓");
        zhanDouSwb("下:防风林↓");
        zhanDouSwb("下:防风林↓");
        zhanDouSwb("下:防风林↓");
        zhanDouSwb("上:防风林↑");
        zhanDouSwb("上:防风林↑");
        zhanDouDz("进入西林","快活林八弟子");
        zhanDouDz("进入西路","快活林七弟子");
        zhanDouDz("进入西冲","快活林六弟子");
        zhanDouDz("进入西山","快活林五弟子");
        htmlContent.linkName("进入会合点");
        while (htmlContent.exitsName("快活林大师兄")){
            htmlContent.linkName("刷新");
        }
        htmlContent.linkName("进入快活牌坊");
        while (htmlContent.exitsName("快活林三当家")){
            htmlContent.linkName("刷新");
        }
        htmlContent.linkName("进入快活隘口");
        while (htmlContent.exitsName("快活林二当家")){
            htmlContent.linkName("刷新");
        }
        htmlContent.linkName("进入快活客栈");
        while (htmlContent.exitsName("快活林大当家")){
            htmlContent.linkName("刷新");
        }
        htmlContent.linkName("进入厢房");
        while (!htmlContent.exitsName("VS",true)){
            htmlContent.linkName("刷新");
        }
        htmlContent.linkName("VS",true);
        htmlContent.linkName("攻方");
        htmlContent.linkName("返回战场");
        while (!htmlContent.getText().contains("战斗已经结束")){
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("队伍");
        htmlContent.linkName("提取");
        htmlContent.linkName("退出队伍");
        htmlContent.linkName("确定退出");
        htmlContent.linkName("返回游戏");
        return true;
    }


}














