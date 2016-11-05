package com.zzz.play.util.sys;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.ui.HtmlPanel;
import com.zzz.play.util.HtmlContent;
import com.zzz.play.util.http.HttpRequest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by dell_2 on 2016/11/5.
 */
public class LoginUtil {

    private HtmlPanel htmlPanel;
    private HtmlContent content;
    String urlTemp;

    public LoginUtil(HtmlPanel htmlPanel) {
        this.htmlPanel = htmlPanel;
    }


    public void login() throws InterruptedException {

        content = htmlPanel.content;
        linkName(htmlPanel.daqu.trim());
        LinkBean link = content.getUrl(htmlPanel.name.trim(), true);
        urlTemp = link.getUrl().replace("entry.yytou.com", "hero2.yytou.com");
        content.selfLinkUrl(urlTemp);
        linkName(htmlPanel.daqu.trim());
        linkName("点击进入");
        do {
            TimeUnit.SECONDS.sleep(1);
        } while (!content.exitsName("进入游戏", true));
        linkName("进入游戏(高级)");
        linkName("进入游戏");
        linkName("我知道了");
        linkName("进入游戏(高级)");
    }

    public static void main(String[] args) throws IOException {
        String url = "http://xfhero1.yytou.com/gCmd.do?cmd=2&sid=evt4xw3bt59a08wkc1scn";
        HttpRequest httpRequest = new HttpRequest();
        String res = HttpRequest.sendGet(url);
        System.out.println(res);
    }


    public void linkName(String name) {
        urlTemp = content.getUrl(name, true).getUrl();
        if (urlTemp != null) {
            content.selfLinkUrl(urlTemp);
        }
    }

}
