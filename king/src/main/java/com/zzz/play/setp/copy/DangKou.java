package com.zzz.play.setp.copy;

import java.util.Date;

/**
 * 荡寇
 * Created by Administrator on 2016/11/6 0006.
 */
public class DangKou extends FuBen {

    @Override
    public String inClearLine() {
        return "柳虫,青蛇皮,金创药,蜂王浆,龙皮";
    }

    @Override
    public String outClearLine() {
        return null;
    }

    @Override
    public String saveLine() {
        return "";
    }


    @Override
    public void ready() {

    }


    @Override
    public boolean fbRun() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("物品");
        htmlContent.linkName("其它");
        htmlContent.linkName("上页");
        htmlContent.linkName("首页");
        while (!htmlContent.exitsName("御赐讨伐令箭",true)){
            htmlContent.linkName("下.页");
        }
        htmlContent.linkName("御赐讨伐令箭",true);
        htmlContent.linkName("荡寇");
        if (htmlContent.getText().contains("今天你已进入")) {
            ableIn = false;
            lastDate = new Date();
            logger.error(htmlContent.htmlPanel.user.getDaqu()  + "荡寇副本结束!");
            htmlContent.linkName("返回游戏");
            return false;
        }
        htmlContent.linkName("下:草原↓");
        htmlContent.linkName("下:草原↓");
        htmlContent.linkName("右:草原→");
        htmlContent.linkName("下:绿洲↓");
        htmlContent.linkName("下:绿洲↓");
        htmlContent.linkName("右:红帐篷→");
        htmlContent.linkName("波斯人入侵者");
        zhanDou();
        htmlContent.linkName("波斯人入侵者");
        zhanDou();
        htmlContent.linkName("波斯人入侵者");
        zhanDou();
        if(htmlContent.exitsName("进入密道")){
            loginIn();
            return true;
        }
        htmlContent.linkName("左:绿洲←");
        htmlContent.linkName("左:绿洲←");
        htmlContent.linkName("左:绿洲←");
        htmlContent.linkName("下:黑帐篷↓");
        htmlContent.linkName("叛乱喇嘛");
        zhanDou();
        htmlContent.linkName("叛乱喇嘛");
        zhanDou();
        htmlContent.linkName("叛乱喇嘛");
        zhanDou();
        if(htmlContent.exitsName("进入密道")){
            loginIn();
            return true;
        }

        htmlContent.linkName("上:绿洲↑");
        htmlContent.linkName("左:草原←");
        htmlContent.linkName("下:草原↓");
        htmlContent.linkName("下:红黑帐篷↓");
        htmlContent.linkName("波斯人入侵者头领");
        zhanDou();
        htmlContent.linkName("叛乱喇嘛头子");
        zhanDou();
        loginIn();
        return true;
    }


    /**
     * 战斗
     */
    public void zhanDou() {
        htmlContent.linkName("攻击", true);
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("返回游戏");
    }

    public void loginIn(){
        htmlContent.linkName("进入密道");
        htmlContent.linkName("确定进入");
        while (true){
            if(htmlContent.exitsName("下:毒虫殿↓")){
                break;
            }else if(htmlContent.exitsName("下:密道↓")){
                htmlContent.linkName("下:密道↓");
            } else if(htmlContent.exitsName("右:密道→")){
                htmlContent.linkName("右:密道→");
            }
            zhanDou();
        }
        htmlContent.linkName("下:毒虫殿↓");
        htmlContent.linkName("恶毒蝎子王");
        zhanDou();
        htmlContent.linkName("巨型青蛙王");
        zhanDou();
        htmlContent.linkName("奇异毒蛇王");
        zhanDou();
        htmlContent.linkName("吸血蝙蝠王");
        zhanDou();
        htmlContent.linkName("进入巫婆殿");
        htmlContent.linkName("巫婆.叶比亚");
        zhanDou();
    }

}



















