package com.zzz.play.util.sys;

import com.alibaba.fastjson.JSON;
import com.zzz.play.bean.SetBean;
import com.zzz.play.util.resource.UiResourceUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by dell_2 on 2016/12/5.
 */
public class SetProperties {

    private static SetBean setBean;

    public synchronized static SetBean getSetBean() {
        if (setBean == null) {
            File file = new File("res/set.json");
            if (file.exists()) {
                try {
                    String json = UiResourceUtil.loadJson(file);
                    setBean = JSON.parseObject(json,SetBean.class);
                } catch (IOException e) {
                    e.printStackTrace();
                    setDefault();
                }
            } else {
                setDefault();
                try {
                    UiResourceUtil.saveJson(setBean, file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return setBean;
    }

    private static void setDefault() {
        SetBean setBean = new SetBean();
        setBean.setBaiyin(true);
        setBean.setYinzi(true);
        setBean.setXiuli(36000000);
        SetProperties.setBean = setBean;
    }


}
