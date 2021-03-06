package com.zzz.play.util.sys;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.ui.HtmlPanel;
import com.zzz.play.util.HtmlContent;
import com.zzz.play.util.http.HttpRequest;

import java.io.IOException;
import java.util.Random;
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
        linkName(htmlPanel.user.getDaqu().trim());
        LinkBean link = content.getUrl(htmlPanel.user.getName().trim(), true);
        urlTemp = link.getUrl().replace("entry.yytou.com", "hero2.yytou.com");
        content.selfLinkUrl(urlTemp);
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
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(100));

        }
    }


    public void linkName(String name) {
        urlTemp = content.getUrl(name, true).getUrl();
        if (urlTemp != null) {
            content.selfLinkUrl(urlTemp);
        }
    }

}
