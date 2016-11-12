package com.zzz.play.setp.copy.zhenfa;

import java.util.Date;

/**
 * 锋矢
 * Created by Administrator on 2016/11/12 0012.
 */
public class FengShi extends ZhenFa {



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
        htmlContent.linkName("传送到锋矢阵");
        htmlContent.linkName("确定传送");
        if(htmlContent.getDocument().text().contains("今天你已进入")){
            lastDate = new Date();
            ableIn = false;
            htmlContent.linkName("返回游戏");
            return false;
        }
        zhandou();
        while (true) {
            if (htmlContent.exitsName("锋矢中心", true)) {
                break;
            } else if (htmlContent.exitsName("下:锋矢阵↓")) {
                zhandou("下:锋矢阵↓");
            } else if (htmlContent.exitsName("左:锋矢阵←")&&ac!=RIGHT) {
                ac = LEFT;
                zhandou("左:锋矢阵←");
            } else if(htmlContent.exitsName("右:锋矢阵→")&&ac!=LEFT) {
                ac = RIGHT;
                zhandou("右:锋矢阵→");
            }
            if(ac ==LEFT){
                ac = RIGHT;
                zhandou("右:锋矢阵→");
            }else{
                ac = LEFT;
                zhandou("左:锋矢阵←");
            }
        }
        htmlContent.linkName("锋矢中心", true);
        htmlContent.linkName("锋矢将军");
        htmlContent.linkName("我要挑战");
        gjJiangJu();
        return true;
    }




}
