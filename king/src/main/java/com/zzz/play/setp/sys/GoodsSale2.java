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
public class GoodsSale2 extends BaseStep {

    private LinkedList<String> liuNames = new LinkedList<>();

    private Map<String, String> maps = new HashMap<>();

    public GoodsSale2(String line) {
        if(line==null)return;
        String[] vals = line.split(",");
        for (String val : vals) {
            if (val != null && !"".equals(val.trim())) {
                String[] vvs = val.split("_");
                if (vvs.length > 0) {
                    String desc = null;
                    if (vvs.length == 2)
                        desc = vvs[1];
                    maps.put(vvs[0], desc);
                }
            }
        }

    }

    public GoodsSale2() {
    }


    @Override
    public boolean run() {
        if(maps.isEmpty()){
            return false;
        }
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
    public void saleOne(String name, String desc) {

        LinkBean res = htmlContent.linkName(name, getNotNames());
        Document doc = htmlContent.getDocument();
        String content = doc.text();
        if (desc != null && !"".equals(desc.trim())) {
            if (content.contains(desc)) {
                sale();
                System.out.println("卖掉一个商品:" + res.getClickName() + "-->" + desc);
            } else {
                liuNames.add(res.getClickName());
                htmlContent.linkName("返回列表");
            }
        } else {
            sale();
            System.out.println("卖掉一个商品:" + res.getClickName() + "-->" + desc);
        }
    }

    public void sale() {
        htmlContent.linkName("卖掉", true);
        Element form = htmlContent.delForms.get(0);
        String action = form.attr("action");
        String num = form.getElementsByTag("input").get(0).val();
        htmlContent.linkUrl(action + "&num=" + num);
        return;
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

    public void putStep(GoodsSale2 step) {
        for (Map.Entry<String, String> entry : step.maps.entrySet()) {
            maps.put(entry.getKey(), entry.getValue());
        }
    }

    public void clear() {
        maps.clear();
    }

    public void setGoods(String line) {
        if(line==null)return;
        String[] vals = line.split(",");
        for (String val : vals) {
            if (val != null && !"".equals(val.trim())) {
                String[] vvs = val.split("_");
                if (vvs.length > 0) {
                    String desc = null;
                    if (vvs.length == 2)
                        desc = vvs[1];
                    maps.put(vvs[0], desc);
                }
            }
        }

    }
}




