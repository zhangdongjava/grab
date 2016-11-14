package com.zzz.play.setp.material;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.setp.impl.config.BaseStep;

/**
 * 刷虎皮
 * Created by dell_2 on 2016/11/10.
 */
public class HuPi extends BaseStep {

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
        htmlContent.linkName("景阳岗");
        htmlContent.linkName("上:山路↑");
        htmlContent.linkName("上:山下↑");
        htmlContent.linkName("上:山路↑");
        go("上:树林↑");
        go("左:树林←");
        go("左:树林←");
        go("左:树林←");
        go("右:树林→");
        go("右:树林→");
        go("右:树林→");
        go("右:树林→");
        go("右:树林→");
        go("右:树林→");
        go("上:树林↑");
        go("右:树林→");
        go("下:树林↓");
    }

    public void go(String name) {
        htmlContent.linkName(name);
        dahu();
        dahu();
        hu();
        hu();
        hu();
    }

    /**
     * 大柳虫
     */
    public void dahu() {
        LinkBean r = htmlContent.linkName("景阳岗大虫");
        if (r.isSuccess()) {
            htmlContent.linkName("攻击景阳岗大虫");
            zhandou();
        }
    }

    /**
     * 柳虫
     */
    public void hu() {
        LinkBean r = htmlContent.linkName("景阳岗小大虫");
        if (r.isSuccess()) {
            htmlContent.linkName("攻击景阳岗小大虫");
            zhandou();
        }
    }

    public void zhandou() {
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        while (htmlContent.exitsName("景阳岗虎皮", true)) {
            htmlContent.linkName("景阳岗虎皮", true);
        }
        if (htmlContent.getText().contains("捡到景阳岗虎皮x1")) {
            num++;
        }
        htmlContent.linkName("返回游戏");
    }
}
