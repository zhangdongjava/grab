package com.zzz.play.setp.copy.kuaihuo;

import com.zzz.play.setp.sup.SecondRefresh;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class KuaiHuo extends SecondRefresh{

    private KuaiHuoFb huoFb;

    @Override
    public boolean run() {
        if(!ableIn){
            return false;
        }
        ready();
        init();
        fbrun();
        return true;
    }

    private void ready(){
        goodsSave.setGoods("蒙汗药,虎皮");
        goodsSave.run();
        goodsSale2.setGoods("柳皮");
        goodsSale2.run();
        buyDrug.setName("万年灵芝");
        buyDrug.setNum("300");
    }

    private void fbrun(){

    }

    private void init(){
        if(huoFb!=null)return;
    }
}

















