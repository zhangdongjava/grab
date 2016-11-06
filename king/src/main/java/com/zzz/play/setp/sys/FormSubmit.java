package com.zzz.play.setp.sys;

import com.zzz.play.setp.impl.config.BaseStep;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 调教form表单用于 招募购买等...
 * Created by dell_2 on 2016/11/2.
 */
public class FormSubmit extends BaseStep {

    private String value;

    public FormSubmit(String value) {
        if (value != null && !"".equals(value.trim())) {
            this.value = value.trim();
        }
    }

    @Override
    public boolean run() {
        Elements forms = htmlContent.delForms;
        if (forms.size() > 0) {
            Element form = forms.get(0);
            submit(form);
        }
        return true;
    }

    /**
     * 提交form表单
     *
     * @param form
     */
    public void submit(Element form) {

    }
}
