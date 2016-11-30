package com.zzz.play.setp.copy.gaoji;

import com.sun.corba.se.impl.logging.ActivationSystemException;
import com.zzz.play.setp.copy.FuBen;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell_2 on 2016/11/28.
 */
public class JinGangQuan extends FuBen {

    private static String LEFT = "左:金刚圈←";
    private static String RIGHT = "右:金刚圈→";
    private static String UP = "上:金刚圈↑";
    private static String DOWN = "下:金刚圈↓";
    private static String[] actions = {UP, RIGHT, DOWN, LEFT};
    private static Map<String, String> map = new HashMap<>();
    private static Map<String, String[]> map2 = new HashMap<>();

    private int len = 10;

    static {
        map.put(UP, DOWN);
        map.put(RIGHT, LEFT);
        map.put(DOWN, UP);
        map.put(LEFT, RIGHT);
        map2.put(LEFT, new String[]{UP, DOWN});
        map2.put(RIGHT, new String[]{UP, DOWN});
        map2.put(UP, new String[]{LEFT, RIGHT});
        map2.put(DOWN, new String[]{LEFT, RIGHT});
    }

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

    }


    @Override
    public boolean fbRun() {
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("上东京");
        htmlContent.linkName("下:南大街↓");
        htmlContent.linkName("红发侏儒");
        htmlContent.linkName("传送到金刚伏魔副本");
        htmlContent.linkName("确定传送");
        htmlContent.linkName("天灵子");
        htmlContent.linkName("进入金刚圈");
        zhanDou1();
        go();
        return true;
    }

    private void go() {
        boolean isOut = false;
        xh:
        while (true) {
            for (String action : actions) {
                if (isAction(action)) {
                    isOut = true;
                    break xh;
                }
            }
        }
        while (isOut) {
            htmlContent.linkName("刷新");
        }
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
     * 是否有出路
     *
     * @return
     */
    private boolean isout() {
        for (String s : actions) {
            if (!htmlContent.exitsName(s)) {
                System.out.println(s + "->不存在!");
                return true;
            }
        }
        return false;
    }

    private boolean isAction(String action) {
        String[] acs = map2.get(action);
        String action1 = acs[0];
        String action2 = acs[1];
        String fAction = map.get(action);
        for (int i = 0; i < len; i++) {
            if (clickAction(action1)) {
                return true;
            }
            for (int j = 0; j < len; j++) {
                if (clickAction(action)) {
                    return true;
                }
            }
            for (int j = 0; j < len; j++) {
                if (clickAction(fAction)) {
                    return true;
                }
            }
        }
        for (int i = 0; i < len; i++) {
            htmlContent.linkName(action2);
        }
        for (int i = 0; i < len; i++) {
            if (clickAction(action2)) {
                return true;
            }
            for (int j = 0; j < len; j++) {
                if (clickAction(action)) {
                    return true;
                }
            }
            for (int j = 0; j < len; j++) {
                if (clickAction(fAction)) {
                    return true;
                }
            }
        }
        for (int i = 0; i < len; i++) {
            htmlContent.linkName(action1);
        }
        return false;
    }

    private boolean clickAction(String action) {
        if (isout()) {
            return true;
        }
        htmlContent.linkName(action);
        zhanDou1();
        return false;
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
        htmlContent.linkName("武松", true);
        htmlContent.linkName("设为近身武将");
        htmlContent.linkName("我的武将");
        htmlContent.linkName("鲁智深", true);
        htmlContent.linkName("设为近身武将");
        htmlContent.linkName("我的武将");
        htmlContent.linkName("周武师", true);
        htmlContent.linkName("设为近身武将");
        htmlContent.linkName("我的武将");
        htmlContent.linkName("柴进", true);
        htmlContent.linkName("设为近身武将");
        htmlContent.linkName("我的武将");
        htmlContent.linkName("返回游戏");
    }

    public void outwujiang() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("武将");
        htmlContent.linkName("武松", true);
        htmlContent.linkName("撤销近身武将");
        htmlContent.linkName("我的武将");
        htmlContent.linkName("史进", true);
        htmlContent.linkName("设为近身武将");
        htmlContent.linkName("我的武将");
        htmlContent.linkName("返回游戏");
    }

    private boolean shua() {
        htmlContent.linkName("八阵图道士");
        htmlContent.linkName("传送到八阵图(需金刚奇书x1)");
        htmlContent.linkName("刷新");
        if (htmlContent.getText().contains("今天你已进入")) {
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

    public static void main(String[] args) {
        System.out.println(20 << 1);
    }
}