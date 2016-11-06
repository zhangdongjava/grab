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
        String action = form.attr("action");
        StringBuilder url = new StringBuilder(action);
        Elements inputs = form.getElementsByTag("input");
        Element input = inputs.get(0);
        for (int i = 1; i < inputs.size(); i++) {
            Element input2 = inputs.get(i);
            String name = input2.attr("name");
            String val = input2.val();
            url.append("&");
            url.append(name);
            url.append("=");
            url.append(val);

        }
        String name = input.attr("name");
        String val = value;
        if (val == null) {
            val = input.val();
        }
        url.append("&");
        url.append(name);
        url.append("=");
        url.append(val);
        System.out.println(url);
        htmlContent.linkUrl(url.toString());
    }
}
