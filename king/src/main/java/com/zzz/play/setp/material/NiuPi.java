package com.zzz.play.setp.material;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.setp.impl.config.BaseStep;

/**
 * Created by dell_2 on 2016/11/10.
 */
public class NiuPi extends BaseStep {

    /**
     * 刷到的数量
     */
    private int num;

    @Override
    public boolean run() {
        num = 0;
        shua();
        return true;
    }

    public int getNum() {
        return num;
    }

    public void shua() {
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("上东京");
        htmlContent.linkName("右:东大街→");
        htmlContent.linkName("右:东大街→");
        for (int i = 0; i < 20; i++) {
            niuPi();
        }

    }

    public void niuPi() {
        htmlContent.linkName("老黄牛");
        htmlContent.linkName("攻击老黄牛");
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("牛皮", true);
        if (htmlContent.getDocument().text().contains("捡到牛皮x1")) {
            num++;
        }
        htmlContent.linkName("返回游戏");
    }

}
