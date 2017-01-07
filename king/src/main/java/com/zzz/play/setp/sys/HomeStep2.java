package com.zzz.play.setp.sys;

import com.zzz.play.exception.StopCurrStepException;
import com.zzz.play.setp.impl.config.BaseStep;

/**
 * 庄园升级home2即可调用
 * Created by dell_2 on 2016/11/2.
 */
public class HomeStep2 extends BaseStep {

    private String text;


    private int homeLv;


    /**
     * 剩余建筑
     */
    private int surplus;
    /**
     * 总建筑数量
     */
    private int total;

    /**
     * 升级下标
     */
    private int upgradeIndex;

    private int shijian;


    @Override
    public boolean run() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("庄院");
        try {
            start();
        } catch (StopCurrStepException ex) {
            System.out.println("结束!!");
        }
        return true;
    }

    public void start() {
        htmlContent.linkName("升级");
        htmlContent.linkName("确定升级");
        htmlContent.linkName("返回庄院管理");
        buildCount();
        if (shijian < 2) return;
        build();
        upgrade();
    }

    public void upgrade() {

        create();
        upgradeBuild();
    }

    /**
     * 建造
     */
    public void create() {
        System.out.println("建造!");
        //建造房屋
        if (surplus > 0) {
            htmlContent.linkName("建造", 0);
            htmlContent.linkName("确定建造");
            surplus--;
        }
    }

    /**
     * 升级建筑
     */
    public void upgradeBuild() {
        System.out.println("升级!");
        upgradeBuild(0);
    }

    /**
     * 升级建筑
     */
    public void upgradeBuild(int i) {

        htmlContent.linkName("间", i, true);
        do {
            upgradeIndex = 0;
            String text = htmlContent.getText();
            String[] lines = text.split("\\s");
            for (String line : lines) {
                if (shijian < 2) return;
                if (line != null && !"".equals(line.trim()))
                    upgradeBuild(line.trim());
            }
        } while (htmlContent.linkName("下.页").isSuccess());
        htmlContent.linkName("返回庄院管理");
    }

    public void upgradeBuild(String line) {
        if (line.contains("(")) {
            if (line.contains("正在升级")) {
                return;
            }

            int i1 = line.indexOf("(");
            int i2 = line.indexOf("级");
            int lv = Integer.valueOf(line.substring(i1 + 1, i2));
            if (lv < homeLv) {
                htmlContent.linkName("升级", upgradeIndex);
                htmlContent.linkName("确定升级");
                upgradeIndex--;
                shijian--;
            }
            upgradeIndex++;
        }
    }


    /**
     * 构建分析数据
     */
    public void build() {
        text = htmlContent.getText();
        String[] lines = text.split("\\s");
        for (String line : lines) {
            if (line != null && !"".equals(line.trim()))
                build(line.trim());
        }
    }

    public void build(String line) {
        if (line.startsWith("等级:")) {
            homeLv = Integer.valueOf(line.substring(3));

        } else if (line.startsWith("建筑:")) {
            int index = line.indexOf("/");
            total = Integer.valueOf(line.substring(index + 1));
            String str = line.substring(3, line.indexOf("/"));
            int yo = 0;
            if (str.contains("+")) {
                for (String s : str.split("\\+")) {
                    yo += Integer.valueOf(s);
                }
            } else {
                yo = Integer.valueOf(str);
            }
            surplus = total - yo;
        }
    }


    /**
     * 计算剩余事件
     */
    private void buildCount() {
        String[] lines = htmlContent.getText().split("\\s");
        for (String line : lines) {
            if (line.startsWith("事件:")) {
                int index = line.indexOf("/");
                shijian = (Integer.valueOf(line.substring(index + 1)) - Integer.valueOf(line.substring(3, index)));
            }
        }
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HomeStep{");
        //sb.append("text='").append(text).append('\'');
        sb.append(", homeLv=").append(homeLv);
        sb.append(", surplus=").append(surplus);
        sb.append('}');
        return sb.toString();
    }


}






















