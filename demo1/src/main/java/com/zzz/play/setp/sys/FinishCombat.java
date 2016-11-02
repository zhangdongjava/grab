package com.zzz.play.setp.sys;

import com.zzz.play.setp.impl.BaseStep;
import com.zzz.play.util.HtmlContent;

/**
 * 结束战斗 解决战斗中问题
 * Created by dell_2 on 2016/11/2.
 */
public class FinishCombat extends BaseStep {

    public FinishCombat(HtmlContent htmlContent) {
        this.htmlContent = htmlContent;
    }

    @Override
    public boolean run() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("战场");
        htmlContent.linkName("VS", true);
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        while (htmlContent.exitsName("x", true)) {
            htmlContent.linkName("x", true);
        }
        htmlContent.linkName("返回游戏");
        return true;
    }
}
