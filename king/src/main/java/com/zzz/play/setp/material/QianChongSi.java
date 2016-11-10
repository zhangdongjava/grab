package com.zzz.play.setp.material;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.setp.impl.config.BaseStep;

/**
 * Created by dell_2 on 2016/11/10.
 */
public class QianChongSi extends BaseStep {

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
        htmlContent.linkName("史家庄");
        go("右:柳树林→");
        go("上:柳树林↑");
        go("上:柳树林↑");
        go("下:柳树林↓");
        go("下:柳树林↓");
        go("下:柳树林↓");
        go("下:柳树林↓");
        go("上:柳树林↑");
        go("上:柳树林↑");
        go("右:柳树林→");
        go("右:柳树林→");
        go("下:柳树林↓");
        go("上:柳树林↑");
        go("右:柳树树→");
        go("左:柳树林←");
        go("左:柳树林←");
        go("上:柳树林↑");
        go("右:柳树林→");
        htmlContent.linkName("右:大柳树→");
        for (int i = 0; i < 2; i++) {
            shePi();
        }
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("史家庄");
        htmlContent.linkName("史家庄丁");
        htmlContent.linkName("配制千虫丝");
        while (htmlContent.getDocument().text().contains("完成配")) {
            num++;
            htmlContent.linkName("返回史家庄丁");
            htmlContent.linkName("配制千虫丝");
        }
        htmlContent.linkName("返回史家庄丁");
        htmlContent.linkName("返回游戏");
    }

    public void go(String name) {
        htmlContent.linkName(name);
        daliu();
        daliu();
        liu();
        liu();
        liu();
    }

    /**
     * 大柳虫
     */
    public void daliu() {
        LinkBean r = htmlContent.linkName("大柳虫");
        if (r.isSuccess()) {
            zhandou();
        }
    }

    /**
     * 柳虫
     */
    public void liu() {
        LinkBean r = htmlContent.linkName("柳虫");
        if (r.isSuccess()) {
            zhandou();
        }
    }

    public void shePi() {
        htmlContent.linkName("青蛇");
        htmlContent.linkName("攻击青蛇");
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("青蛇皮", true);
        htmlContent.linkName("返回游戏");
    }

    public void zhandou() {
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        while (htmlContent.exitsName("柳虫皮", true)) {
            htmlContent.linkName("柳虫皮", true);
        }
        htmlContent.linkName("返回游戏");
    }
}
