package com.zzz.play.setp.sys;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.mark.SaveMark;
import com.zzz.play.setp.impl.config.BaseStep;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * 仓库取出物品
 * Created by dell_2 on 2016/10/31.
 */
public class GoodsTakeout extends BaseStep implements SaveMark {

    private LinkedList<String> liuNames = new LinkedList<>();

    private Map<String, Integer> maps = new HashMap<>();

    public GoodsTakeout(String line) {
        String[] vals = line.split(",");
        for (String val : vals) {
            if (val != null && !"".equals(val.trim())) {
                String[] vas = val.split("_");
                maps.put(vas[0], Integer.valueOf(vas[1]));
            }
        }

    }

    @Override
    public boolean run() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("帮派");
        htmlContent.linkName("帮派管理");
        htmlContent.linkName("帮派仓库");
        htmlContent.linkName("取出物品");
        do {
            takeOutPage();
        } while (htmlContent.linkName("下.页").isSuccess());
        htmlContent.linkName("返回游戏");
        liuNames.clear();
        return false;
    }


    /**
     * 保存当页物品
     */
    public void takeOutPage() {
        ExistSale existSale = existSale();
        while (existSale.exist) {
            String name = existSale.name;
            takeOutOne(name);
            existSale = existSale();
        }
    }

    /**
     * 保存一个商品
     *
     * @param name 商品名称
     */
    public void takeOutOne(String name) {
        int num = maps.get(name);
        LinkBean res = htmlContent.linkName(name, getNotNames());
        String clickName = res.getClickName();
        int index = clickName.indexOf("x");
        clickName = clickName.substring(0, index);
        liuNames.add(clickName);
        submitForm(num);
        htmlContent.linkName("返回物品列表");
        System.out.println("取出物品:" + clickName + "x" + num);
    }

    public void submitForm(int num) {
        Elements forms = htmlContent.delForms;
        Element form = forms.get(0);
        String action = form.attr("action");
        htmlContent.linkUrl(action + "&submit=取出&submit=取出&num=" + num);
    }

    /**
     * 检测是否有保存商品存在
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




