package com.zzz.play.setp.copy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 八阵图
 * Created by Administrator on 2016/11/6 0006.
 */
public class BaZhenTu extends FuBen {

    Map<String, String> map = new HashMap<>();

    @Override
    public String inClearLine() {
        return "";
    }

    @Override
    public String outClearLine() {
        return null;
    }

    @Override
    public String saveLine() {
        return "金刚奇书,红宝石";
    }

    /**
     * 云阵：右上左左左左下
     * 天阵：右上上上上上上
     * 地阵：右下下下下下下
     * 蛇阵：右下右下右下右下
     * 虎阵：右下下右右右右
     * 龙阵：右上上右右右右
     * 风阵：右下右右上上右
     * 鸟阵：右右右右下右右
     */
    @Override
    public void ready() {
        goodsTakeout.setGoods("金刚奇书_5");
        goodsTakeout.run();

        map.put("云阵", "右上左左左左下");
        map.put("天阵", "右上上上上上上");
        map.put("地阵", "右下下下下下下");
        map.put("蛇阵", "右下右下右下右下右下");
        map.put("虎阵", "右下下右右右右");
        map.put("龙阵", "右上上右右右右");
        map.put("风阵", "右下右右上上右");
        map.put("鸟阵", "右右右右下右右");
    }


    @Override
    public boolean fbRun() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("黄泥冈");
        htmlContent.linkName("右:泥土路→");
        htmlContent.linkName("右:大树林→");
        htmlContent.linkName("右:黄泥冈→");
        htmlContent.linkName("右:悬崖→");
        inwujiang();
        while (shua());
        outwujiang();
        return true;
    }

    private void go() {
        String text = htmlContent.getText();
        if (text.length() > 8) {
            text = text.substring(0, 8);
        }
        String line = null;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (text.contains(entry.getKey())) {
                line = entry.getValue();
                break;
            }
        }
        if (line != null) {
            char[] chars = line.toCharArray();
            for (char aChar : chars) {
                htmlContent.linkName(Character.toString(aChar) + ":", true);
                zhanDou1();
            }
        }
        htmlContent.linkName("阵中心", true);
    }

    /**
     * 战斗
     */
    public void zhanDou1() {
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("返回游戏");
    }


    /**
     * 战斗
     */
    public void zhanDou(String name) {
        htmlContent.linkName("武将");
        htmlContent.linkName("供给粮草");
        htmlContent.linkName("返回游戏");
        htmlContent.linkName(name);
        htmlContent.linkName("攻击", true);
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("返回游戏");
    }

    public void inwujiang() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("武将");
        htmlContent.linkName("撤消近身");
        htmlContent.linkName("撤消近身");
        htmlContent.linkName("撤消近身");
        htmlContent.linkName("撤消近身");
        htmlContent.linkName("我的军队");
        htmlContent.linkName("炮灰", true);
        htmlContent.linkName("设为近身军队");
        htmlContent.linkName("我的军队");
        htmlContent.linkName("防御", true);
        htmlContent.linkName("设为近身军队");
        htmlContent.linkName("我的军队");
        htmlContent.linkName("辅2", true);
        htmlContent.linkName("设为近身军队");
        htmlContent.linkName("我的军队");
        htmlContent.linkName("主C", true);
        htmlContent.linkName("设为近身军队");
        htmlContent.linkName("我的武将");
        htmlContent.linkName("返回游戏");
    }

    public void outwujiang() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("武将");
        htmlContent.linkName("撤消近身");
        htmlContent.linkName("撤消近身");
        htmlContent.linkName("撤消近身");
        htmlContent.linkName("撤消近身");
        htmlContent.linkName("我的军队");
        htmlContent.linkName("防御", true);
        htmlContent.linkName("设为近身军队");
        htmlContent.linkName("我的军队");
        htmlContent.linkName("辅1", true);
        htmlContent.linkName("设为近身军队");
        htmlContent.linkName("我的军队");
        htmlContent.linkName("辅2", true);
        htmlContent.linkName("设为近身军队");
        htmlContent.linkName("我的军队");
        htmlContent.linkName("主C", true);
        htmlContent.linkName("设为近身军队");
        htmlContent.linkName("我的武将");
        htmlContent.linkName("返回游戏");
    }

    private boolean shua() {
        htmlContent.linkName("八阵图道士");
        htmlContent.linkName("传送到八阵图(需金刚奇书x1)");
        htmlContent.linkName("刷新");
        if (htmlContent.getText().contains("今天你已进入")||htmlContent.getText().contains("给我一本金刚奇书")) {
            ableIn = false;
            lastDate = new Date();
            logger.error(htmlContent.htmlPanel.user.getName() + "八阵图副本结束!");
            htmlContent.linkName("返回游戏");
            return false;
        }
        go();
        htmlContent.linkName("阵守将", true);
        zhanDou1();
        htmlContent.linkName("进入中军阵");
        zhanDou("中军守将");
        htmlContent.linkName("进入中军人门");
        zhanDou("中军人门守将");
        htmlContent.linkName("进入中军地门");
        zhanDou("中军地门守将");
        htmlContent.linkName("进入中军天门");
        zhanDou("中军天门守将");
        htmlContent.linkName("进入中军阵");
        zhanDou("中军元帅");
        return true;
    }


}



















