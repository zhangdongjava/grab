package com.zzz.play.util.page;

import com.zzz.play.exception.StopCurrStepException;
import com.zzz.play.setp.Step;
import com.zzz.play.setp.sys.GoodsSale;
import com.zzz.play.setp.sys.GoodsSave;
import com.zzz.play.ui.HtmlPanel;
import com.zzz.play.util.HtmlContent;

/**
 * 清理负重
 * Created by dell_2 on 2016/11/4.
 */
public class ClearUtil {

    private static String LOAD = "你的负载过重";
    private int minFz = 50;

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

    public ClearUtil() {
        goodsSale = new GoodsSale("柳虫");

    }

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
        htmlContent.linkName("返回", true);
        htmlContent.linkName("物品");
        String lineStr = htmlContent.getDocument().text();
        String[] lines = lineStr.substring(0, 25).split("\\s");
        for (String line : lines) {
            if (line != null && !"".equals(line.trim())) {
                if (line.startsWith("负重:")) {
                    line = line.substring(3);
                    int index = line.indexOf("/");
                    int cu = Integer.valueOf(line.substring(0, index));
                    int sum = Integer.valueOf(line.substring(index + 1));
                    fz = sum - cu;
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
        if (fz < minFz) {
            clearPack(htmlContent);
            clickFz(htmlContent);
        }
    }

    public void clearPack(HtmlContent htmlContent) {
        htmlContent.linkName("返回游戏");
        goodsSale.setHtmlContent(htmlContent);
        if (goodsSale != null) {
            goodsSale.run();
        }
        if (save != null) {
            save.run();
        }
        htmlContent.linkName("返回游戏");
    }


    private void clickFz(HtmlContent htmlContent) {
        bulidFz(htmlContent);
        if (fz < minFz) {
            throw new StopCurrStepException("负重不狗!");
        }
        htmlContent.linkName("返回游戏");
    }

}
