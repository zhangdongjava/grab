package com.zzz.play.setp.sys;

import com.zzz.play.exception.StopCurrStepException;
import com.zzz.play.setp.impl.config.BaseStep;

/**
 * 庄园升级home即可调用
 * Created by dell_2 on 2016/11/2.
 */
public class HomeStep extends BaseStep {

    private String text;


    private int homeLv;
    /**
     * 房屋
     */
    private int fwLNum;
    /**
     * 农田
     */
    private int ntNum;
    /**
     * 伐木场
     */
    private int fmcNum;
    /**
     * 采石场
     */
    private int cscNum;
    /**
     * 铁矿场
     */
    private int tkcNum;

    /**
     * 建造下标 用来记录建造采石场还是农田等
     */
    private int jzIndex;

    /**
     * 是否有建造
     */
    private boolean create;
    /**
     * 是否有升级建筑
     */
    private boolean upgrade;


    /**
     * 剩余建筑
     */
    private int surplus;
    /**
     * 总建筑数量
     */
    private int total;
    /**
     * 升级建筑个数
     */
    private int upgradeCount = 5;
    /**
     * 升级下标
     */
    private int upgradeIndex;

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
        build();
        upgrade();
    }

    public void upgrade() {
        create = false;
        upgrade = false;
        create();
        upgradeBuild();
    }

    /**
     * 建造
     */
    public void create() {
        System.out.println("建造!");
        int num = getMin();
        if (num < 4 && surplus > 2) {
            htmlContent.linkName("建造", jzIndex);
            htmlContent.linkName("确定建造");
            create = true;
            surplus--;
            create();
        }
        //建造房屋
        if (surplus > 2) {
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
        for (int i = 1; i < upgradeCount; i++) {
            upgradeBuild(i);
        }
        upgradeBuild(0);
    }

    /**
     * 升级建筑
     */
    public void upgradeBuild(int i) {

        htmlContent.linkName("间", i, true);
        do {
            upgradeIndex = 0;
            String text = htmlContent.getDocument().text();
            String[] lines = text.split("\\s");
            for (String line : lines) {
                if (line != null && !"".equals(line.trim())&&surplus>2)
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
                surplus--;
                htmlContent.linkName("升级", upgradeIndex);
                htmlContent.linkName("确定升级");
                upgradeIndex--;
                upgrade = true;
            }
            upgradeIndex++;
        }
    }


    /**
     * 构建分析数据
     */
    public void build() {
        text = htmlContent.getDocument().text();
        String[] lines = text.split("\\s");
        for (String line : lines) {
            if (line != null && !"".equals(line.trim()))
                build(line.trim());
        }
    }

    public void build(String line) {
        if (line.startsWith("等级:")) {
            homeLv = Integer.valueOf(line.substring(3));

        } else if (line.startsWith("农田:")) {
            ntNum = Integer.valueOf(line.substring(3, 4));
            if (line.contains("正在建1间")) {
                ntNum++;
            }
        } else if (line.startsWith("伐木场:")) {
            fmcNum = Integer.valueOf(line.substring(4, 5));
            if (line.contains("正在建1间")) {
                fmcNum++;
            }
        } else if (line.startsWith("采石场:")) {
            cscNum = Integer.valueOf(line.substring(4, 5));
            if (line.contains("正在建1间")) {
                cscNum++;
            }
        } else if (line.startsWith("铁矿场:")) {
            tkcNum = Integer.valueOf(line.substring(4, 5));
            if (line.contains("正在建1间")) {
                tkcNum++;
            }
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

    public int getMin() {
        jzIndex = 1;
        int min = fmcNum;
        if (ntNum > fmcNum) {
            jzIndex = 2;
            min = ntNum;
        }
        if (min > cscNum) {
            jzIndex = 3;
            min = cscNum;
        }
        if (min > tkcNum) {
            jzIndex = 4;
            min = tkcNum;
        }
        return min;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HomeStep{");
        //sb.append("text='").append(text).append('\'');
        sb.append(", homeLv=").append(homeLv);
        sb.append(", fwLNum=").append(fwLNum);
        sb.append(", ntNum=").append(ntNum);
        sb.append(", fmcNum=").append(fmcNum);
        sb.append(", cscNum=").append(cscNum);
        sb.append(", tkcNum=").append(tkcNum);
        sb.append(", jzIndex=").append(jzIndex);
        sb.append(", create=").append(create);
        sb.append(", surplus=").append(surplus);
        sb.append('}');
        return sb.toString();
    }


}






















