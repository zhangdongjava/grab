package com.zzz.play.setp.copy.zhenfa;

import java.util.Date;

/**
 * 雁形
 * Created by Administrator on 2016/11/12 0012.
 */
public class YanXing extends ZhenFa {



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
        htmlContent.linkName("传送到雁形阵");
        htmlContent.linkName("确定传送");
        if(htmlContent.getText().contains("今天你已进入")){
            lastDate = new Date();
            ableIn = false;
            htmlContent.linkName("返回游戏");
            return false;
        }
        zhandou();
        while (true) {
            if (htmlContent.exitsName("雁形中心", true)) {
                break;
            } else if (htmlContent.exitsName("下:雁形阵↓")&&ac!=UP) {
                ac = DOWN;
                zhandou("下:雁形阵↓");
            } else if (htmlContent.exitsName("左:雁形阵←")&&ac!=RIGHT) {
                ac = LEFT;
                zhandou("左:雁形阵←");
            } else if(htmlContent.exitsName("右:雁形阵→")&&ac!=LEFT) {
                ac = RIGHT;
                zhandou("右:雁形阵→");
            }else if(htmlContent.exitsName("上:雁形阵↑")&&ac!=DOWN) {
                ac = UP;
                zhandou("上:雁形阵↑");
            }else {
                if(ac ==LEFT){
                    ac = RIGHT;
                    zhandou("右:雁形阵→");
                }else if(ac == RIGHT){
                    ac = LEFT;
                    zhandou("左:雁形阵←");
                }else if(ac == DOWN){
                    ac = UP;
                    zhandou("上:雁形阵↑");
                }else{
                    ac = DOWN;
                    zhandou("下:雁形阵↓");
                }
            }

        }
        htmlContent.linkName("雁形中心", true);
        htmlContent.linkName("雁形将军");
        htmlContent.linkName("我要挑战");
        gjJiangJu();
        return true;
    }




}
