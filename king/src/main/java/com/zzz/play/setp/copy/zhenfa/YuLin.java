package com.zzz.play.setp.copy.zhenfa;

/**
 * 鱼鳞
 * Created by Administrator on 2016/11/12 0012.
 */
public class YuLin extends ZhenFa {
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
    }

    @Override
    public boolean fbRun() {
        htmlContent.linkName("传送到鱼鳞阵");
        htmlContent.linkName("确定传送");
        zhandou();
        while (true) {
            if (htmlContent.exitsName("鱼鳞中心", true)) {
                break;
            } else if (htmlContent.exitsName("下:鱼鳞阵↓")) {
                zhandou("下:鱼鳞阵↓");
            } else if (htmlContent.exitsName("右:鱼鳞阵→")) {
                zhandou("右:鱼鳞阵→");
            } else {
                zhandou("左:鱼鳞阵←");
            }
        }
        htmlContent.linkName("鱼鳞中心", true);
        htmlContent.linkName("鱼鳞将军");
        htmlContent.linkName("我要挑战");
        gjJiangJu();
        return true;
    }




}
