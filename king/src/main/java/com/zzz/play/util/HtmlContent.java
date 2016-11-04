package com.zzz.play.util;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.core.CoreController;
import com.zzz.play.exception.StopCurrStepException;
import com.zzz.play.setp.Step;
import com.zzz.play.setp.TextParse;
import com.zzz.play.setp.sys.FinishCombat;
import com.zzz.play.ui.HtmlPanel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class HtmlContent {
    private static int LINE_COUNT = 120;
    private Document document;
    public String baseUrl;
    public static int TIME_WAIT = 900;
    public static long CLICK_TIME = System.currentTimeMillis();
    private boolean validate = false;
    private Map<String, LinkBean> urlMap = new HashMap<>();
    private HtmlPanel htmlPanel;

    /**
     * 删除的form表单
     */
    public Elements delForms;

    /**
     * 解决战斗中
     */
    private Step zdz;

    private TextParse currParse;

    /**
     * 核心控制器
     */
    private CoreController controller;


    public HtmlContent(String url, HtmlPanel htmlPanel, CoreController controller) {
        this.controller = controller;
        setBaseUrl(url);
        this.htmlPanel = htmlPanel;
        zdz = new FinishCombat(this);
        linkUrl(url, 0);
    }

    /**
     * 获取地址
     *
     * @param name
     * @return
     */
    private LinkBean getUrl(String name, boolean like) {
        String url;
        LinkBean linkBean = new LinkBean();
        Elements elements = document.getElementsByTag("a");
        Element element = getAelement(elements, like, name, linkBean);
        if (element != null) {
            url = element.attr("href");
            linkBean.setUrl(url);
            return linkBean;
        }
        return linkBean;
    }

    /**
     * 获取模糊name地址但不是notName
     *
     * @param name
     * @return
     */
    private LinkBean getUrl(String name, String... notName) {
        LinkBean linkBean = urlMap.get(name + "_" + notName);
        if (linkBean != null) return linkBean;
        linkBean = new LinkBean();
        Elements elements = document.getElementsByTag("a");
        Element element = getAelementNotName(elements, name, linkBean, notName);
        if (element != null) {
            String url = element.attr("href");
            linkBean.setUrl(url);
            urlMap.put(name + "_" + notName, linkBean);
        }
        return linkBean;
    }


    private Element getAelement(Elements elements, boolean like, String name, LinkBean linkBean) {
        for (Element element : elements) {
            if (!like) {
                if (element.text().equals(name)) {
                    linkBean.setClickName(element.text());
                    return element;
                }
            } else {
                if (element.text().contains(name)) {
                    linkBean.setClickName(element.text());
                    return element;
                }

            }
        }
        return null;
    }

    private Element getAelementNotName(Elements elements, String name, LinkBean linkBean, String... notName) {
        for (Element element : elements) {
            String text = element.text();
            if (text.contains(name) && !textContainArray(text, notName)) {
                linkBean.setClickName(text);
                return element;
            }
        }
        return null;
    }

    private boolean textContainArray(String text, String... notNames) {
        for (String notName : notNames) {
            if (notName != null) {
                if (text.contains(notName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 超链接name是否存在
     *
     * @param name
     * @return
     */
    public boolean exitsName(String name, boolean like) {
        return getUrl(name, like).getUrl() != null;
    }

    /**
     * 模糊name但不是notName的超链接是否存在
     *
     * @param name
     * @param notName
     * @return
     */
    public boolean exitsName(String name, String... notName) {
        return (getUrl(name, notName).getUrl()) != null;
    }

    /**
     * 链接超链接name
     *
     * @param name
     */
    public LinkBean linkName(String name, boolean like) {
        LinkBean linkBean = getUrl(name, like);
        String url = linkBean.getUrl();
        if (url != null) {
            linkBean.setSuccess(linkUrl(url));
            return linkBean;
        }
        return linkBean;
    }

    /**
     * 链接超链接name
     *
     * @param name
     */
    public LinkBean linkName(String name) {
        return linkName(name, false);
    }

    /**
     * 链接超链接name
     *
     * @param name
     */
    public LinkBean linkName(String name, String... notName) {
        LinkBean linkBean = getUrl(name, notName);
        String url = linkBean.getUrl();
        if (url != null) {
            linkBean.setSuccess(linkUrl(url));
            return linkBean;
        }
        return linkBean;
    }


    public void vailte() {
        try {
            if (exitsName("继续", false)) {
                linkUrl(getUrl("继续", false).getUrl());
            }
            if (exitsName("解除验证", false)) {
                ValidationKill.getValidationKill(this).kill();
            }
            if (document.text().contains("战斗中，不能参战")) {
                zdz.run();
            }
            if (document.text().contains("事件容器已满")) {
                linkName("返回游戏");
                throw new StopCurrStepException("事件容器已满！");
            }
            if (Thread.currentThread().isInterrupted()) {
                linkName("返回游戏");
                throw new StopCurrStepException("当前线程中断!");
            }

        } finally {
        }
    }


    public boolean linkUrl(String url) {
        return linkUrl(url, 0);
    }

    private boolean linkUrl(String url, int count) {
        if (count > LINE_COUNT) throw new RuntimeException("链接断开!");
        try {
            await();
            url = cleckUrl(url);
            document = Jsoup.parse(new URL(url), 2000);
            delForms = document.getElementsByTag("form").remove();
            document.getElementsByTag("img").remove();
            urlMap.clear();
            buildAelements();
            htmlPanel.setHtml(document.html());
            vailte();
            controller.pageChange();
        }catch (SocketTimeoutException e){
            System.out.println(count + 1 + "次尝试链接..." + url);
            linkUrl(url, count + 1);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(count + 1 + "次尝试链接..." + url);
            linkUrl(url, count + 1);
        }
        return true;
    }

    public LinkBean linkName(String name, int index) {
        return linkName(name, index, false);
    }

    /**
     * @param name  名称
     * @param index 下标
     * @param like  是否模糊
     * @return
     */
    public LinkBean linkName(String name, int index, boolean like) {
        if (!validate) {
            vailte();
        }
        LinkBean linkBean = new LinkBean();
        Elements as = document.getElementsByTag("a");
        for (Element a : as) {
            if (!like && a.text().equals(name)) {
                if (index == 0) {
                    String url = a.attr("href");
                    linkBean.setUrl(url);
                    linkBean.setSuccess(linkUrl(url));
                    break;
                }
                index--;
            } else if (like && a.text().contains(name)) {
                if (index == 0) {
                    String url = a.attr("href");
                    linkBean.setUrl(url);
                    linkBean.setSuccess(linkUrl(url));
                    break;
                }
                index--;
            }
        }
        return linkBean;
    }


    private void await() throws InterruptedException {
        Thread.sleep(TIME_WAIT);
        CLICK_TIME = System.currentTimeMillis();
    }

    public String getHtml() {
        return document.html();
    }

    private void setBaseUrl(String url) {
        baseUrl = url.substring(0, url.indexOf("/", 9) + 1);
    }

    public String cleckUrl(String url) {
        if (!(url.trim().startsWith("http"))) {
            url = baseUrl + url;
        }
        return url;
    }

    private void buildAelements() {
        Elements elements = document.getElementsByTag("a");
        for (Element element : elements) {
            element.attr("href", cleckUrl(element.attr("href")));
        }
    }

    public Document getDocument() {
        return document;
    }

    public TextParse getCurrParse() {
        return currParse;
    }

    public void setCurrParse(TextParse currParse) {
        this.currParse = currParse;
    }

}




















