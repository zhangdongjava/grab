package com.zzz.play.setp.copy;

import com.zzz.play.setp.copy.zhenfa.*;
import com.zzz.play.setp.impl.config.BaseStep;
import com.zzz.play.util.HtmlContent;

import java.util.LinkedList;

/**
 * 刷阵
 * Created by Administrator on 2016/11/12 0012.
 */
public class ZhenList extends BaseStep {
    LinkedList<ZhenFa> zhenFas = new LinkedList<>();

    @Override
    public void setHtmlContent(HtmlContent htmlContent) {
        super.setHtmlContent(htmlContent);
        for (ZhenFa zhenFa : zhenFas) {
            zhenFa.setHtmlContent(htmlContent);
        }
    }

    public ZhenList() {
        zhenFas.add(new HeYi());
        zhenFas.add(new YuLin());
        zhenFas.add(new ChangShe());
        zhenFas.add(new CheXuan());
        zhenFas.add(new Chonge());
        zhenFas.add(new fangYuan());
        zhenFas.add(new FengShi());
        zhenFas.add(new YanXing());
        zhenFas.add(new YanYue());
    }

    @Override
    public boolean run() {
        for (ZhenFa zhenFa : zhenFas) {
            zhenFa.run();
        }
        return true;
    }
}
