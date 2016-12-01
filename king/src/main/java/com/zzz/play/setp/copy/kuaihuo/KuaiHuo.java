package com.zzz.play.setp.copy.kuaihuo;

import com.zzz.play.setp.sup.SecondRefresh;
import com.zzz.play.util.resource.KuaiHuoResource;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class KuaiHuo extends SecondRefresh {

    private KuaiHuoFb huoFb;

    @Override
    public boolean run() {
        if (!ableIn) {
            return false;
        }
        ready();
        init();
        fbrun();
        return true;
    }

    private void ready() {
        goodsSave.setGoods("蒙汗药,虎皮");
        goodsSave.run();
        goodsSale2.setGoods("柳虫皮,蜂刺,蛇皮");
        goodsSale2.run();
        buyDrug.setName("万年灵芝");
        buyDrug.setNum("300");
        buyDrug.run();
    }

    private void fbrun() {
        if (huoFb != null) {
            if (!huoFb.run()) {
                lastDate = new Date();
                ableIn = false;
            }else{
                goodsSave.setGoods("快活,烈炎");
                goodsSave.run();
            }

        }
    }

    private void init() {
        if (huoFb != null) return;
        String myName = htmlContent.htmlPanel.user.getName();
        String name = KuaiHuoResource.getZhu(myName);
        if (name != null) {
            huoFb = new KuaiHuoZhu(htmlContent, name);
        } else {
            name = KuaiHuoResource.getFu(myName);
            if (name != null) {
                huoFb = new KuaiHuoFu(htmlContent, name);
            }
        }
    }
}

















