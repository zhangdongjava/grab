package com.zzz.play.util.sys;

import javafx.beans.property.SetPropertyBase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Administrator on 2017/1/1 0001.
 */
public class HtmlTextUtil {

    public static String buildHtml(Document document) {
        String html = document.html();
        int index = html.indexOf("你看到:");
        if (index != -1) {
            int index2 = html.indexOf("<br>", index + 1);
            html = html.substring(0, index) + html.substring(index2);
        }
        return html;
    }

    public static Document getdDocument(String url) throws IOException {
        Document document = Jsoup.parse(new URL(url), SetProperties.getSetBean().getInterval());
        document = Jsoup.parse(HtmlTextUtil.buildHtml(document));
        return document;
    }

}
