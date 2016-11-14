package com.zzz.play.setp.sys;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.setp.impl.config.BaseStep;

/**
 * [任务]身份文书:1/1;
 * basefirst{
 * 劫镖强盗队长
 * 万年灵芝
 * 普通攻击while2
 * 返回游戏
 * }
 * b上:小树林↑
 * b上:小树林↑
 * b下:小树林↓
 * b下:小树林↓
 * b右:小树林→
 * b上:小树林↑
 * b上:小树林↑
 * b右:小树林→
 * b下:小树林↓
 * b下:小树林↓
 * b左:小树林←
 * b左:小树林←
 * Created by dell_2 on 2016/11/9.
 */
public class ShenFenWenShu extends BaseStep {
    public static String[] actions = {
            "下:小树林↓", "下:小树林↓", "右:小树林→", "上:小树林↑", "上:小树林↑", "右:小树林→",
            "下:小树林↓", "下:小树林↓", "左:小树林←", "左:小树林←", "上:小树林↑", "上:小树林↑"
    };

    @Override
    public boolean run() {
        zou();
        return true;
    }

    public void zou() {
        while (true) {
            for (String action : actions) {
                boolean res = shua(action);
                if (res) {
                    return;
                }
            }
        }
    }

    public boolean shua(String action) {
        htmlContent.linkName(action);
        LinkBean res = htmlContent.linkName("劫镖强盗队长");
        if (!res.isSuccess()) return false;
        htmlContent.linkName("万年灵芝");
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        if (htmlContent.getText().contains("[任务]身份文书:1/1")) {
            htmlContent.linkName("返回游戏");
            return true;
        }
        htmlContent.linkName("返回游戏");
        return false;
    }

}
