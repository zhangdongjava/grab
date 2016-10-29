package util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ui.MainWindow;

import javax.swing.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class HtmlContent {
    private static int LINE_COUNT = 10;
    private Document document;
    public static String baseUrl;
    public static int TIME_WAIT = 900;
    public static long CLICK_TIME = System.currentTimeMillis();

    private static ThreadLocal<HtmlContent> threadLocal = new InheritableThreadLocal<HtmlContent>();

    private Map<String, String> urlMap = new HashMap<>();
    /**
     * 物品计数工具
     */
    private GoodsUtil goodsUtil;

    private MainWindow mainWindow;

    private HtmlContent(String url, MainWindow mainWindow) {
        setBaseUrl(url);
        this.mainWindow = mainWindow;
        linkUrl(url, 0);
    }

    public static HtmlContent initHtmlContent(String url, MainWindow window) {
        HtmlContent htmlContent = threadLocal.get();
        if (htmlContent != null) {
            return htmlContent;
        }
        synchronized (HtmlContent.class) {
            htmlContent = threadLocal.get();
            if (htmlContent == null) {
                htmlContent = new HtmlContent(url, window);
                htmlContent.goodsUtil = new GoodsUtil(window);
                threadLocal.set(htmlContent);
            }
        }
        return htmlContent;
    }

    public static HtmlContent getHtmlContent() {
        HtmlContent htmlContent = threadLocal.get();
        if (htmlContent == null) {
            throw new RuntimeException("util.HtmlContent not init !");
        }
        return htmlContent;
    }

    /**
     * 获取地址
     *
     * @param name
     * @return
     */
    private String getUrl(String name, boolean like) {
        String url;
        Elements elements = document.getElementsByTag("a");
        Element element = getAelement(elements, like ? null : name);
        if (element != null) {
            url = baseUrl + element.attr("href");
            return url;
        }
        return null;
    }

    /**
     * 获取模糊name地址但不是notName
     *
     * @param name
     * @return
     */
    private String getUrl(String name, String notName) {
        String url = urlMap.get(name + "_" + notName);
        if (url != null) return url;
        Elements elements = document.getElementsByTag("a");
        Element element = getAelementNotName(elements, name, notName);
        if (element != null) {
            url = baseUrl + element.attr("href");
            urlMap.put(name + "_" + notName, url);
        }
        return url;
    }


    private Element getAelement(Elements elements, String name) {
        for (int i = elements.size() - 1; i >= 0; i--) {
            Element element = elements.get(i);
            if (name != null) {
                if (element.text().equals(name)) {
                    System.out.println(element.text());
                    return element;
                }
            } else {
                if (element.text().contains(name)) {
                    System.out.println(element.text());
                    return element;
                }

            }
        }
        return null;
    }

    private Element getAelementNotName(Elements elements, String name, String notName) {
        for (int i = elements.size() - 1; i >= 0; i--) {
            Element element = elements.get(i);
            String text = element.text();
            if (text.contains(name) && !text.contains(notName)) {
                if (text.contains("x1")) {
                    goodsUtil.addGoods(text);
                }
                return element;
            }
        }
        return null;
    }

    /**
     * 超链接name是否存在
     *
     * @param name
     * @return
     */
    public boolean exitsName(String name, boolean like) {
        return getUrl(name, like) != null;
    }

    /**
     * 模糊name但不是notName的超链接是否存在
     *
     * @param name
     * @param notName
     * @return
     */
    public boolean exitsName(String name, String notName) {
        return getUrl(name, notName) != null;
    }

    /**
     * 链接超链接name
     *
     * @param name
     */
    public void linkName(String name, boolean like) {
        vailte();
        String url = getUrl(name, like);
        if (url == null) {
            cick_jx();
            url = getUrl(name, like);
            if (url != null) {
                linkUrl(url);
            }
        } else {
            linkUrl(url);
        }
    }

    /**
     * 链接超链接name
     *
     * @param name
     */
    public void linkName(String name, String notName) {
        vailte();
        String url = getUrl(name, notName);
        if (url == null) {
            cick_jx();
            url = getUrl(name, notName);
            if (url != null) {
                linkUrl(url);
            }
        } else {
            linkUrl(url);
        }
    }

    private void cick_jx() {
        while (exitsName("继续", false)) {
            linkUrl(getUrl("继续", false));
        }
    }

    private void vailte() {
        if (exitsName("解除验证", false)) {
            JOptionPane.showConfirmDialog(mainWindow, "结束验证!");
            throw new RuntimeException("解除验证");
        }
    }


    public void linkUrl(String url) {
        linkUrl(url, 0);
    }

    private void linkUrl(String url, int count) {
        if (count > LINE_COUNT) return;
        try {
            await();
            document = Jsoup.parse(new URL(url), 2000);
            urlMap.clear();
            mainWindow.setHtml(document.html());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(count + 1 + "次尝试链接..." + url);
            linkUrl(url, count + 1);
        }
    }

    private void await() throws InterruptedException {
        int time = TIME_WAIT - (int) (System.currentTimeMillis() - CLICK_TIME);
        if (time > 0) {
            Thread.sleep(time);
        }
        CLICK_TIME = System.currentTimeMillis();
    }

    public String getHtml() {
        return document.html();
    }

    private void setBaseUrl(String url) {
        baseUrl = url.substring(0, url.indexOf("/", 9) + 1);
    }

}




















