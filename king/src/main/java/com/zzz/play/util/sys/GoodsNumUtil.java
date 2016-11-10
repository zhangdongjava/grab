package com.zzz.play.util.sys;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.setp.impl.config.BaseStep;

import java.util.*;

/**
 * 获取物品数量
 * Created by dell_2 on 2016/11/10.
 */
public class GoodsNumUtil extends BaseStep {

    private Set<String> sets = new HashSet<>();

    private LinkedList<String> liuNames = new LinkedList<>();

    public Map<String, Integer> map = new HashMap<>();

    public void setNames(String... names) {
        clear();
        sets.addAll(Arrays.asList(names));
        for (String name : names) {
            map.put(name, 0);
        }
    }

    public void clear() {
        sets.clear();
        map.clear();
    }

    @Override
    public boolean run() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("物品");
        htmlContent.linkName("首页");
        htmlContent.linkName("上页");
        do {
            operaPage();
        } while (htmlContent.linkName("下.页").isSuccess());
        htmlContent.linkName("返回游戏");
        liuNames.clear();
        return false;
    }

    /**
     * 保存当页物品
     */
    public void operaPage() {
        ExistSale existSale = existSale();
        while (existSale.exist) {
            String name = existSale.name;
            operaOne(name);
            htmlContent.linkName("返回列表");
            existSale = existSale();
        }
    }

    /**
     * 保存一个商品
     *
     * @param name 商品名称
     */
    public void operaOne(String name) {
        LinkBean res = htmlContent.linkName(name, getNotNames());
        String clickName = res.getClickName();
        liuNames.add(clickName);
        int currNum;
        if (clickName.contains("x")) {
            currNum = Integer.valueOf(clickName.substring(clickName.indexOf("x") + 1).trim());
        } else {
            currNum = 1;
        }
        map.put(name, currNum);
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
}
