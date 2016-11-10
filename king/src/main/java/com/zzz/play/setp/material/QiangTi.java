package com.zzz.play.setp.material;

import com.zzz.play.setp.impl.config.BaseStep;
import com.zzz.play.util.sys.GoodsNumUtil;

/**
 * 刷强体奇书
 * Created by dell_2 on 2016/11/10.
 */
public class QiangTi extends BaseStep {

    private int hu;
    private int huang;
    private int qian;
    private boolean run = true;

    @Override
    public boolean run() {
        init();
        contrl();
        return super.run();
    }

    /**
     * 控制
     */
    private void contrl() {
        if(qian>0){

        }
    }

    /**
     * 初始化材料数量，从物品中获取到
     */
    public void init() {
        GoodsNumUtil numUtil = utilDto.goodsNumUtil;
        numUtil.setNames("虎骨", "黄金虎骨", "千虫丝");
        numUtil.run();
        hu = numUtil.map.get("虎骨");
        huang = numUtil.map.get("黄金虎骨");
        qian = numUtil.map.get("千虫丝");
        if (hu > huang) {
            hu = huang;
        }
        if(hu == 0){
            run = false;
        }
        qian = (hu * 3) - qian;
        numUtil.clear();
    }

    /**
     * 刷千虫丝
     */
    private void shuaQianChong(){

    }


}
