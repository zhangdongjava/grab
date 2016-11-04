package com.zzz.play.util.page;

import com.zzz.play.exception.StopCurrStepException;
import com.zzz.play.setp.Step;
import com.zzz.play.setp.sys.GoodsSale;
import com.zzz.play.setp.sys.GoodsSave;
import com.zzz.play.util.HtmlContent;

/**
 * 清理负重
 * Created by dell_2 on 2016/11/4.
 */
public class ClearUtil {

    private static String LOAD = "你的负载过重";

    /**
     * 剩余负重
     */
    private int fz;

    /**
     * 买脚本
     */
    GoodsSale goodsSale;

    /**
     * 存脚本
     */
    GoodsSave save;

    /**
     * @param step
     * @return
     */
    public boolean addMarkStep(Step step) {
        //卖脚本
        if (step instanceof GoodsSale) {
            if (goodsSale == null) {
                goodsSale = (GoodsSale) step;
            } else {
                goodsSale.putStep((GoodsSale) step);
            }
            return false;
        } else if (step instanceof GoodsSave) {
            if (save == null) {
                save = (GoodsSave) step;
            } else {
                save.putStep((GoodsSave) step);
            }
            return false;
        }
        return true;
    }

    public void clear(HtmlContent htmlContent) {
        if (htmlContent == null) return;
        if (htmlContent != null && htmlContent.getDocument().text().contains(LOAD)) {
            System.out.println("满负重保存");
            clearPack(htmlContent);
            clickFz(htmlContent);
            htmlContent.linkName("返回游戏");
        }
    }

    public void fzClear(HtmlContent htmlContent) {
        if (htmlContent == null) return;
        bulidFz(htmlContent);
        getFz(htmlContent);
    }

    public void bulidFz(HtmlContent htmlContent) {
        System.out.println("开始检测负重...");
        htmlContent.linkName("返回", true);
        htmlContent.linkName("物品");
        String lineStr = htmlContent.getDocument().text();
        String[] lines = lineStr.substring(0, 25).split("\\s");
        for (String line : lines) {
            if (line != null && !"".equals(line.trim())) {
                if (line.startsWith("负重:")) {
                    System.out.println("检测到负重........" + line);
                    line = line.substring(3);
                    int index = line.indexOf("/");
                    int cu = Integer.valueOf(line.substring(0, index));
                    int sum = Integer.valueOf(line.substring(index + 1));
                    fz = sum - cu;
                    System.out.println("检测结果........" + fz);
                    break;
                }
            }
        }
        htmlContent.linkName("返回游戏");
    }

    /**
     * 获取负重
     *
     * @param htmlContent
     */
    private void getFz(HtmlContent htmlContent) {
        if (fz < 200) {
            System.out.println("负重检测保存" + fz);
            clearPack(htmlContent);
        }
        clickFz(htmlContent);
    }

    public void clearPack(HtmlContent htmlContent) {
        htmlContent.linkName("返回游戏");
        goodsSale.run();
        save.run();
        htmlContent.linkName("返回游戏");
    }


    private void clickFz(HtmlContent htmlContent) {
        bulidFz(htmlContent);
        if (fz < 200) {
            throw new StopCurrStepException("负重不狗!");
        }
        htmlContent.linkName("返回游戏");
    }

}
