package com.zzz.play.setp.copy.zhenfa;

import java.util.Date;

/**
 * 长蛇
 * Created by Administrator on 2016/11/12 0012.
 */
public class ChangShe extends ZhenFa {



    @Override
    public String inClearLine() {
        return null;
    }

    @Override
    public String saveLine() {
        return null;
    }

    @Override
    public void ready() {
        buyDrug.setName("万年灵芝");
        buyDrug.setNum("300");
        buyDrug.run();
    }

    @Override
    public boolean fbRun() {
        htmlContent.linkName("传送到长蛇阵");
        htmlContent.linkName("确定传送");
        if(htmlContent.getText().contains("今天你已进入")){
            lastDate = new Date();
            ableIn = false;
            htmlContent.linkName("返回游戏");
            return false;
        }
        zhandou();
        while (true&&!Thread.currentThread().isInterrupted()) {
            if (htmlContent.exitsName("长蛇中心", true)) {
                break;
            } else if (htmlContent.exitsName("下:长蛇阵↓")) {
                ac = DOWN;
                zhandou("下:长蛇阵↓");
            } else if (htmlContent.exitsName("左:长蛇阵←")&&ac!=RIGHT) {
                ac = LEFT;
                zhandou("左:长蛇阵←");
            } else if(htmlContent.exitsName("右:长蛇阵→")&&ac!=LEFT) {
                ac = RIGHT;
                zhandou("右:长蛇阵→");
            }else {
                if(ac ==LEFT){
                    ac = RIGHT;
                    zhandou("右:长蛇阵→");
                }else if(ac == RIGHT){
                    ac = LEFT;
                    zhandou("左:长蛇阵←");
                }else if(ac == DOWN){
                    ac = UP;
                    zhandou("上:长蛇阵↑");
                }else{
                    ac = DOWN;
                    zhandou("下:长蛇阵↓");
                }
            }
            
        }
        htmlContent.linkName("长蛇中心", true);
        htmlContent.linkName("长蛇将军");
        htmlContent.linkName("我要挑战");
        gjJiangJu();
        return true;
    }




}
