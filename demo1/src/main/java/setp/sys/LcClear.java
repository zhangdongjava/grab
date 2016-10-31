package setp.sys;

import bean.LinkBean;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import setp.impl.BaseStep;

import java.util.LinkedList;

/**
 * 柳虫残害清理
 * Created by dell_2 on 2016/10/31.
 */
public class LcClear extends BaseStep {

    private LinkedList<String> liuNames = new LinkedList<>();

    @Override
    public void run() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("物品");
        htmlContent.linkName("其它");
        delChNotName();
        while (htmlContent.exitsName("上页", false)) {
            htmlContent.linkName("上页");
        }
        delChNotName2();
        htmlContent.linkName("返回游戏");
        liuNames.clear();
        System.out.println("结束!!");
        System.out.println("结束!!");
        System.out.println("结束!!");

    }

    public void delCh() {
        while (!htmlContent.exitsName("大柳虫皮", liuNames.toArray(new String[]{}))) {
            if (!htmlContent.exitsName("下.页", false)) return;
            htmlContent.linkName("下.页");
        }
        if (htmlContent.exitsName("大柳虫皮", liuNames.toArray(new String[]{}))) {
            htmlContent.linkName("大柳虫皮", liuNames.toArray(new String[]{}));
            Document doc = htmlContent.getDocument();
            String content = doc.text();
            if (content.contains("没用的大柳虫残骸")) {
                htmlContent.linkName("卖掉", true);
                Element form = htmlContent.delForms.get(0);
                String action = form.attr("action");
                String num = form.getElementsByTag("input").get(0).val();
                htmlContent.linkUrl(action + "&num=" + num);
                return;
            } else {
                htmlContent.linkName("返回列表");
                delCh();
            }
        }

    }

    public void delChNotName() {
        while (!htmlContent.exitsName("大柳虫皮", true)) {
            if (!htmlContent.exitsName("下.页", false)) return;
            htmlContent.linkName("下.页");
        }
        if (htmlContent.exitsName("大柳虫皮", true)) {
            LinkBean res = htmlContent.linkName("大柳虫皮", true);
            Document doc = htmlContent.getDocument();
            String content = doc.text();
            if (content.contains("没用的大柳虫残骸")) {
                htmlContent.linkName("卖掉", true);
                Element form = htmlContent.delForms.get(0);
                String action = form.attr("action");
                String num = form.getElementsByTag("input").get(0).val();
                htmlContent.linkUrl(action + "&num=" + num);
                return;
            } else {
                liuNames.add(res.getClickName());
                htmlContent.linkName("返回列表");
                delCh();
            }
        }
    }

    public void delCh2() {
        while (!htmlContent.exitsName("柳虫皮", liuNames.toArray(new String[]{}))) {
            if (!htmlContent.exitsName("下.页", false)) return;
            htmlContent.linkName("下.页");
        }
        if (htmlContent.exitsName("柳虫皮", liuNames.toArray(new String[]{}))) {
            htmlContent.linkName("柳虫皮", liuNames.toArray(new String[]{}));
            Document doc = htmlContent.getDocument();
            String content = doc.text();
            if (content.contains("没用的柳虫残骸")) {
                htmlContent.linkName("卖掉", true);
                Element form = htmlContent.delForms.get(0);
                String action = form.attr("action");
                String num = form.getElementsByTag("input").get(0).val();
                htmlContent.linkUrl(action + "&num=" + num);
                return;
            } else {
                htmlContent.linkName("返回列表");
                delCh2();
            }
        }

    }

    public void delChNotName2() {
        while (!htmlContent.exitsName("柳虫皮", "大柳虫皮")) {
            if (!htmlContent.exitsName("下.页", false)) return;
            htmlContent.linkName("下.页");
        }
        if (htmlContent.exitsName("柳虫皮", true)) {
            LinkBean res = htmlContent.linkName("柳虫皮", "大柳虫皮");
            Document doc = htmlContent.getDocument();
            String content = doc.text();
            if (content.contains("没用的柳虫残骸")) {
                htmlContent.linkName("卖掉", true);
                Element form = htmlContent.delForms.get(0);
                String action = form.attr("action");
                String num = form.getElementsByTag("input").get(0).val();
                htmlContent.linkUrl(action + "&num=" + num);
                return;
            } else {
                liuNames.add(res.getClickName());
                System.out.println(liuNames.toArray(new String[]{}));
                htmlContent.linkName("返回列表");
                delCh2();
            }
        }
    }
}




