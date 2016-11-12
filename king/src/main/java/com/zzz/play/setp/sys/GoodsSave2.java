package com.zzz.play.setp.sys;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.mark.SaveMark;
import com.zzz.play.setp.impl.config.BaseStep;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 柳虫残害清理
 * Created by dell_2 on 2016/10/31.
 */
public class GoodsSave2 extends BaseStep {

    private LinkedList<String> liuNames = new LinkedList<>();

    private Set<String> sets = new HashSet<>();

    public GoodsSave2(String line) {
        String[] vals = line.split(",");
        for (String val : vals) {
            if (val != null && !"".equals(val.trim())) {
                sets.add(val);
            }
        }

    }

    public GoodsSave2() {
    }


    @Override
    public boolean run() {
        if(sets.isEmpty()){
            return false;
        }
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("帮派");
        htmlContent.linkName("帮派管理");
        htmlContent.linkName("帮派仓库");
        htmlContent.linkName("存入物品");
        htmlContent.linkName("首页");
        htmlContent.linkName("上页");
        do {
            savePage();
        } while (htmlContent.linkName("下.页").isSuccess());
        htmlContent.linkName("返回游戏");
        liuNames.clear();
        return false;
    }


    /**
     * 保存当页物品
     */
    public void savePage() {
        ExistSale existSale = existSale();
        while (existSale.exist) {
            String name = existSale.name;
            saveOne(name);
            existSale = existSale();
        }
    }

    /**
     * 保存一个商品
     *
     * @param name 商品名称
     */
    public void saveOne(String name) {
        LinkBean res = htmlContent.linkName(name, getNotNames());
        liuNames.add(res.getClickName());
        htmlContent.linkName("全部存入");
        htmlContent.linkName("返回物品列表");
        System.out.println("保存了所有" + res.getClickName());
    }


    /**
     * 检测是否有保存商品存在
     *
     * @return
     */
    public ExistSale existSale() {
        ExistSale existSale = new ExistSale();
        for (String s : sets) {
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

    public void putStep(GoodsSave2 step) {
        sets.addAll(step.sets);
    }

    public void setGoods(String line) {
        if (line == null) return;
        String[] vals = line.split(",");
        for (String val : vals) {
            if (val != null && !"".equals(val.trim())) {
                sets.add(val);
            }
        }

    }
}




