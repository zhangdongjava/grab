package com.zzz.play.util;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 解除验证
 * Created by Administrator on 2016/10/30 0030.
 */
public class ValidationKill {

    private HtmlContent htmlContent;
    /**
     * 结果一次无法解析，就重来一次  这个就是重来计数器，大与MAX_COUNT次跑异常
     */
    private int count  = 0;

    private static int MAX_COUNT = 10;

    private ValidationKill(HtmlContent htmlContent) {
        this.htmlContent = htmlContent;
    }

    public static ValidationKill getValidationKill(HtmlContent htmlContent) {
        return new ValidationKill(htmlContent);
    }

    public void kill() {
        count = 0;
        kill2();
    }

    private void kill2() {
        if(count > MAX_COUNT) throw new RuntimeException("结果无法解析");
        htmlContent.linkName("解除验证");
        Document document = htmlContent.getDocument();
        String[] text = document.text().split("\\s+");
        parse(text[1]);
    }

    private void parse(String line) {
        Object res = null;
        if (line.startsWith("请输入:")) {
            res = jiafa(line.substring(4, line.indexOf("的")));
        } else if (line.startsWith("请输入文字:")) {
            res = line.substring(6).trim();
        } else {
            count ++;
            htmlContent.linkName("返回游戏",false);
            kill2();
        }
        sendRes(res);
    }

    private Integer jiafa(String line) {
        String[] ss = line.split("\\+");
        int sum = 0;
        for (String s : ss) {
            if (s != null && !"".equals(s.trim())) {
                sum += Integer.valueOf(s);
            }
        }
        return sum;
    }

    private void sendRes(Object res) {
        Elements forms = htmlContent.delForms;
        if(forms.size() != 1){
            throw new RuntimeException("form 个数不对!");
        }
        Element form = forms.get(0);
        String action = htmlContent.cleckUrl(form.attr("action"));
        htmlContent.linkUrl(action+"&guaji_name="+res);
        htmlContent.linkName("返回游戏",false);
        System.out.println(action+"?guaji_name="+res);
    }

}
