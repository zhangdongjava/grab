package com.zzz.play.setp.classStep.impl;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.setp.sup.SecondRefresh;
import com.zzz.play.util.HtmlContent;
import com.zzz.play.util.ValidationKill;
import com.zzz.play.util.resource.DaTiUtil;
import com.zzz.play.util.sys.SetProperties;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * 答题
 * Created by dell_2 on 2016/11/10.
 */
public class DaTi extends SecondRefresh {

    private HtmlContent htmlContent;

    private int total = 0;
    private int sure = 0;

    private String wen = null;
    private String danan = null;
    private String key = null;
    private Map<String, String> map = new HashMap<>();
    private Map<String, LinkedList<String>> error = new HashMap<>();

    public DaTi(HtmlContent htmlContent) {
        this.htmlContent = htmlContent;
        map.putAll(DaTiUtil.hashMap);
        System.out.println("正确个数:" + map.size());
        error.putAll(DaTiUtil.errorMap);
        System.out.println("错误个数:" + error.size());
    }

    @Override
    public boolean run() {
        fresh();
        if (!ableIn) {
            return false;
        }
        return arun();

    }

    private boolean arun() {
        htmlContent.linkName("返回游戏");
        if (!htmlContent.getText().contains("[东京广场]")) {
            htmlContent.linkName("功能菜单");
            htmlContent.linkName("神行千里");
            htmlContent.linkName("上东京");
        }

        htmlContent.linkName("长须老者");
        htmlContent.linkName("状元答题");
        htmlContent.linkName("在线时间兑换");
        while (dati()) ;
        htmlContent.linkName("返回游戏");
        ableIn = false;
        lastDate = new Date();
        DaTiUtil.saveMap();
        DaTiUtil.saveError();
        System.out.println("正确率:" + ((double) sure / total));
        htmlContent.linkName("返回游戏");
        return true;
    }

    private boolean dati() {
        String text = htmlContent.getText();
        if (SetProperties.getSetBean().isDati()) {
            if ((text.contains("你今天已经回答了") || text.contains("你已经没有足够的银两答题")) && !htmlContent.exitsName("确认继续答题")) {
                return false;
            }
        } else {
            if (htmlContent.exitsName("确认继续答题")||text.contains("你今天已经回答了")) {
                return false;
            }
        }


        wen = null;
        danan = null;
        try {
            LinkBean res = null;
            htmlContent.linkName("确认继续答题");
            text = htmlContent.getText();
            String[] datas = text.split("\\s");
            if (datas.length > 3) {
                wen = getWen(datas);
            }
            String da = getDan(wen);
            if (da != null) {
                res = htmlContent.linkName(da, true);
                if (!res.isSuccess()) {
                    DaTiUtil.hashMap.remove(key);
                }
            }
            if (res == null || !res.isSuccess()) {
                String[] errors = getError(wen);
                System.out.println("errors:" + Arrays.toString(errors));
                res = htmlContent.linkName("、", errors);
                if (!res.isSuccess()) {
                    res = htmlContent.linkName("、", 1, true);
                    DaTiUtil.errorMap.remove(wen);
                }
                if (res.getClickName() != null) {
                    danan = res.getClickName().substring(1).trim();
                }
            }
            count();
        } catch (Exception e) {

        }
        return true;
    }

    /**
     * 根据问题获取错误答案
     *
     * @param wen
     * @return
     */
    private String[] getError(String wen) {
        String[] errors = {};
        for (Map.Entry<String, LinkedList<String>> entry : error.entrySet()) {
            if (wen.equals(entry.getKey()) || wen.contains(entry.getKey())) {
                return entry.getValue().toArray(errors);
            }
        }
        return errors;
    }

    private void save() {
        if (wen != null && danan != null) {
            if (wen.length() > 5) {
                map.put(wen.substring(1, wen.length() - 1), danan);
                DaTiUtil.hashMap.put(wen.substring(1, wen.length() - 1), danan);
            }
        }
    }

    private void count() {
        if (htmlContent.getText().contains("对不起")) {
            total++;
            if (wen != null && danan != null) {
                DaTiUtil.addError(wen, danan);
            }
        } else if (htmlContent.getText().contains("恭喜你")) {
            total++;
            sure++;
            save();
        }

    }

    private String getWen(String[] datas) {
        for (int i = 0; i < datas.length; i++) {
            if (datas[i].contains("今天的第")) {
                return datas[i + 1];
            }
        }
        return null;
    }

    private String getDan(String wen) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (wen.equals(entry.getKey()) || wen.contains(entry.getKey())) {
                key = entry.getKey();
                return entry.getValue();
            }
        }
        return null;
    }
}
