package com.zzz.play.setp.copy.zhenfa;

import java.util.Date;

/**
 * 偃月
 * Created by Administrator on 2016/11/12 0012.
 */
public class YanYue extends ZhenFa {


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
        htmlContent.linkName("传送到偃月阵");
        htmlContent.linkName("确定传送");
        if (htmlContent.getDocument().text().contains("今天你已进入")) {
            lastDate = new Date();
            ableIn = false;
            htmlContent.linkName("返回游戏");
            return false;
        }
        zhandou();
        while (true) {
            if (htmlContent.exitsName("偃月中心", true)) {
                break;
            } else if (htmlContent.exitsName("下:偃月阵↓") && ac != UP) {
                ac = DOWN;
                zhandou2("下:偃月阵↓");
            } else if (htmlContent.exitsName("左:偃月阵←") && ac != RIGHT) {
                ac = LEFT;
                zhandou2("左:偃月阵←");
            } else if (htmlContent.exitsName("右:偃月阵→") && ac != LEFT) {
                ac = RIGHT;
                zhandou2("右:偃月阵→");
            } else if (htmlContent.exitsName("上:偃月阵↑") && ac != DOWN) {
                ac = UP;
                zhandou2("上:偃月阵↑");
            } else {
                if (ac == LEFT) {
                    ac = RIGHT;
                    zhandou2("右:偃月阵→");
                } else if (ac == RIGHT) {
                    ac = LEFT;
                    zhandou2("左:偃月阵←");
                } else if (ac == DOWN) {
                    ac = UP;
                    zhandou2("上:偃月阵↑");
                } else {
                    ac = DOWN;
                    zhandou2("下:偃月阵↓");
                }
            }

        }
        htmlContent.linkName("偃月中心", true);
        htmlContent.linkName("偃月将军");
        htmlContent.linkName("我要挑战");
        zhandou2();
        return true;
    }


}