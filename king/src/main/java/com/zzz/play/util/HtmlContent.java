package com.zzz.play.util;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.core.CoreController;
import com.zzz.play.exception.StopCurrStepException;
import com.zzz.play.setp.Step;
import com.zzz.play.setp.TextParse;
import com.zzz.play.setp.sys.FinishCombat;
import com.zzz.play.ui.HtmlPanel;
import com.zzz.play.util.http.HttpRequest;
import com.zzz.play.util.sys.GoodsNumUtil;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class HtmlContent {

    private Logger logger = Logger.getLogger(HtmlContent.class);
    public boolean printLog = false;
    private static int LINE_COUNT = 120;
    private Document document;
    public String baseUrl;
    public int TIME_WAIT = HtmlPanel.DEFAULT_WAIT;
    public static long CLICK_TIME = System.currentTimeMillis();
    private boolean validate = false;
    private Map<String, LinkBean> urlMap = new HashMap<>();
    public HtmlPanel htmlPanel;

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

    //private Map<String, String> urlReplace = new HashMap<>();

    private HttpRequest httpRequest;

    private long lastTime;
    private long lastLogTime;
    LinkBean linkBean;
    //副本
    LinkBean copyLinkBean;
    private String text;
    /**
     * 接触验证
     */
    private ValidationKill validationKill;


    public HtmlContent(String url, HtmlPanel htmlPanel, CoreController controller) {
        lastTime = System.currentTimeMillis();
        this.controller = controller;
        validationKill = new ValidationKill(this);
        linkBean = new LinkBean();
        copyLinkBean = new LinkBean();
        initUtil();
        setBaseUrl(url);
        this.htmlPanel = htmlPanel;
        httpRequest = new HttpRequest();
        zdz = new FinishCombat(this);
        selfLinkUrl(url, 0);
    }

    private void initUtil() {
        controller.utilDto.goodsNumUtil = new GoodsNumUtil();
        controller.utilDto.goodsNumUtil.setHtmlContent(this);
    }


    /**
     * 获取地址
     *
     * @param name
     * @return
     */
    public LinkBean getUrl(String name, boolean like) {
        String url;
        linkBean.reset();
        Elements elements = document.getElementsByTag("a");
        Element element = getAelement(elements, like, name, linkBean);
        if (element != null) {
            url = element.attr("href");
            linkBean.setUrl(url);
            linkBean.setClickName(element.text());
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
        this.linkBean.reset();
        linkBean = this.linkBean;
        Elements elements = document.getElementsByTag("a");
        Element element = getAelementNotName(elements, name, linkBean, notName);
        if (element != null) {
            String url = element.attr("href");
            linkBean.setUrl(url);
            linkBean.setClickName(element.text());
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
        copyLinkBean.copy(this.linkBean);
        try {
            if (exitsName("继续", false)) {
                linkUrl(getUrl("继续", false).getUrl());
            }
            if (exitsName("解除验证", false)) {
                validationKill.kill();
            }
            if (document.text().contains("战斗中，不能参战")) {
                zdz.run();
            }
            if (document.text().contains("事件容器已满")) {
                linkName("返回游戏");
                throw new StopCurrStepException("事件容器已满！");
            }
        } finally {
        }
        this.linkBean.copy(copyLinkBean);
    }


    public boolean linkUrl(String url) {
        return linkUrl(url, 0);
    }

    public boolean selfLinkUrl(String url) {
        return selfLinkUrl(url, 0);
    }

    private boolean selfLinkUrl(String url, int count) {
        if (count > LINE_COUNT) throw new RuntimeException("链接断开!");
        try {
            await();
            url = cleckUrl(url);
            String html = httpRequest.sendGet(url);
            document = Jsoup.parse(html);
            linkEnd();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            selfLinkUrl(url, count + 1);
        } catch (Exception e) {
            e.printStackTrace();
            selfLinkUrl(url, count + 1);
        }
        return true;
    }


    private boolean linkUrl(String url, int count) {
        if (count > LINE_COUNT) throw new RuntimeException("链接断开!");
        try {
            await();
            url = cleckUrl(url);
            document = Jsoup.parse(new URL(url), 2000);
            linkEnd();
            if (printLog) {
                logger.error(document.text());
            }
            printfUrl(url);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            System.out.println(count + 1 + "次尝试链接..." + url);
            linkUrl(url, count + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 打印地址到文件中10分钟一次
     *
     * @param url
     */
    private void printfUrl(String url) {
        long currTime = System.currentTimeMillis();
        if (currTime - lastLogTime > 600000) {
            logger.error(htmlPanel.user.getName() + "-->" + url);
            lastLogTime = currTime;
            htmlPanel.addCache(url);
        }
    }


    private void linkEnd() {
        delForms = document.getElementsByTag("form").remove();
        text = document.text();
        document.getElementsByTag("img").remove();
        urlMap.clear();
        long currTime = System.currentTimeMillis();
        htmlPanel.setShowTime(currTime - lastTime);
        lastTime = currTime;
        buildAelements();
        htmlPanel.setHtml(document.html());
        vailte();
        controller.pageChange();
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
        linkBean.reset();
        Elements as = document.getElementsByTag("a");
        for (Element a : as) {
            if (!like && a.text().equals(name)) {
                if (index == 0) {
                    String url = a.attr("href");
                    linkBean.setUrl(url);
                    linkBean.setSuccess(linkUrl(url));
                    linkBean.setClickName(a.text());
                    return linkBean;
                }
                index--;
            } else if (like && a.text().contains(name)) {
                if (index == 0) {
                    String url = a.attr("href");
                    linkBean.setUrl(url);
                    linkBean.setClickName(a.text());
                    linkBean.setSuccess(linkUrl(url));
                    return linkBean;
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

    public void setBaseUrl(String url) {
        String temp = url.substring(0, url.indexOf("/", 9) + 1);
        if (temp.trim().equals("")) {
            temp = url;
        }
        baseUrl = temp;
    }

    public String cleckUrl(String url) {
        if (!(url.trim().startsWith("http"))) {
            url = baseUrl + url;
        } else {
            setBaseUrl(url);
        }
        return url;
    }

    public String buildUrl(String url) {
        if (!(url.trim().startsWith("http"))) {
            url = baseUrl + url;
        }
//        for (Map.Entry<String, String> entry : urlReplace.entrySet()) {
//            url = url.replace(entry.getKey(), entry.getValue());
//        }
        return url;
    }

    private void buildAelements() {
        Elements elements = document.getElementsByTag("a");
        for (Element element : elements) {
            element.attr("href", buildUrl(element.attr("href")));
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

    public String getText() {
        return text;
    }
}




















