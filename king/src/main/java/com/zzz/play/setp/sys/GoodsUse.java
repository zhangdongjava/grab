package com.zzz.play.setp.sys;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.mark.SaleMark;
import com.zzz.play.setp.impl.config.BaseStep;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 柳虫残害清理
 * Created by dell_2 on 2016/10/31.
 */
public class GoodsUse extends BaseStep implements SaleMark {

    private LinkedList<String> liuNames = new LinkedList<>();

    private Map<String, Integer> maps = new HashMap<>();

    public GoodsUse(String line) {
        String[] vals = line.split(",");
        for (String val : vals) {
            if (val != null && !"".equals(val.trim())) {
                String[] vvs = val.split("_");
                if (vvs.length > 0) {
                    String desc = null;
                    if (vvs.length == 2)
                        desc = vvs[1];
                    maps.put(vvs[0], Integer.valueOf(desc));
                }
            }
        }

    }

    public void setGoodsUse(String line) {
        String[] vals = line.split(",");
        for (String val : vals) {
            if (val != null && !"".equals(val.trim())) {
                String[] vvs = val.split("_");
                if (vvs.length > 0) {
                    String desc = null;
                    if (vvs.length == 2)
                        desc = vvs[1];
                    maps.put(vvs[0], Integer.valueOf(desc));
                }
            }
        }

    }

    @Override
    public boolean run() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("物品");
        htmlContent.linkName("首页");
        htmlContent.linkName("上页");
        do {
            await();
            salePage();
        } while (htmlContent.linkName("下.页").isSuccess());
        htmlContent.linkName("返回游戏");
        liuNames.clear();
        return false;
    }


    /**
     * 卖出当页物品
     */
    public void salePage() {
        ExistSale existSale = existSale();
        while (existSale.exist) {
            String name = existSale.name;
            saleOne(name, maps.get(name));
            existSale = existSale();
        }
    }

    /**
     * 卖出一个商品
     *
     * @param name 商品名称
     */
    public void saleOne(String name, int num) {
        LinkBean res = null;
        for (int i = 0; i < num; i++) {
            res = htmlContent.linkName(name, getNotNames());
            htmlContent.linkName("使用");
        }
        liuNames.add(res.getClickName());
    }



    /**
     * 检测是否有卖出商品存在
     *
     * @return
     */
    public ExistSale existSale() {
        ExistSale existSale = new ExistSale();
        for (String s : maps.keySet()) {
            if (htmlContent.exitsName(s, getNotNames())) {
                existSale.exist = true;
                existSale.name = s;
                return existSale;
            }
        }
        return existSale;
    }

    public String[] getNotNames() {
        String[] s = {};
        return liuNames.toArray(s);
    }


    private class ExistSale {
        public boolean exist = false;
        public String name = null;
    }


}




