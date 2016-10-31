package com.zzz.play.setp;

import com.zzz.play.util.HtmlContent;

/**
 * Created by dell_2 on 2016/10/29.
 */
public interface Step {

    boolean run();

    void setStep(TextParse textParse);

    TextParse getTextParse();

    HtmlContent getHtmlContent();

    void setHtmlContent(HtmlContent htmlContent);

    void setBase(boolean base);

    void setMb(boolean mb);

    void baseRun();

    void mbRun();

    void setLineNum(int num);

    int  getLineNum();

}
