package com.zzz.play.util.page;

import com.zzz.play.mark.SaleMark;
import com.zzz.play.mark.SaveMark;
import com.zzz.play.setp.Step;
import com.zzz.play.util.HtmlContent;

import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/11/4.
 */
public class ClearUtil {

    /**
     * 买脚本
     */
    public LinkedList<Step> sales = new LinkedList<>();

    /**
     * 存脚本
     */
    public LinkedList<Step> saves = new LinkedList<>();

    /**
     * @param step
     * @return
     */
    public boolean addMarkStep(Step step) {
        //卖脚本
        if (step instanceof SaleMark) {
            sales.add(step);
        } else if (step instanceof SaveMark) {
            saves.add(step);
        }
        return true;
    }

    public void clear(HtmlContent htmlContent) {

    }


}
