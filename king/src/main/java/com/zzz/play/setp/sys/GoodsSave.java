package com.zzz.play.setp.sys;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.mark.SaveMark;
import com.zzz.play.setp.impl.config.BaseStep;

import java.util.*;

/**
 * 柳虫残害清理
 * Created by dell_2 on 2016/10/31.
 */
public class GoodsSave extends BaseStep implements SaveMark {

    private LinkedList<String> liuNames = new LinkedList<>();

    private Set<String> sets = new HashSet<>();

    public GoodsSave(String line) {
        String[] vals = line.split(",");
        for (String val : vals) {
            if (val != null && !"".equals(val.trim())) {
                sets.add(val);
            }
        }

    }

    @Override
    public boolean run() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("帮派");
        htmlContent.linkName("帮派管理");
        htmlContent.linkName("帮派仓库");
        htmlContent.linkName("存入物品");
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
        LinkBean res = htmlContent.linkName(name, true);
        String text = htmlContent.getDocument().text();
        htmlContent.linkName("全部存入");
        System.out.println("保存了所有" + text);
    }


    /**
     * 检测是否有保存商品存在
     *
     * @return
     */
    public ExistSale existSale() {
        ExistSale existSale = new ExistSale();
        for (String s : sets) {
            if (htmlContent.exitsName(s, true)) {
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




