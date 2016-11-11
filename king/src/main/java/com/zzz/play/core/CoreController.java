package com.zzz.play.core;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.exception.StopCurrStepException;
import com.zzz.play.inter.Observer;
import com.zzz.play.inter.Runable;
import com.zzz.play.setp.Step;
import com.zzz.play.setp.TextParse;
import com.zzz.play.setp.impl.config.ManyStep;
import com.zzz.play.setp.sys.GoodsSale;
import com.zzz.play.setp.sys.SysTextParse;
import com.zzz.play.ui.HtmlPanel;
import com.zzz.play.ui.MainWindow;
import com.zzz.play.util.GlobalUtil;
import com.zzz.play.util.HtmlContent;
import com.zzz.play.util.UtilDto;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.PipedReader;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 核心控制器 整个运行把控
 * Created by dell_2 on 2016/10/29.
 */
public class CoreController {

    private static Logger logger = Logger.getLogger(CoreController.class);

    ExecutorService service = Executors.newFixedThreadPool(20);
    /**
     * 缓存集合
     */
    public LinkedList<Runable> textParses = new LinkedList<>();
    /**
     * 运行中的集合
     */
    public LinkedList<Runable> runParses = new LinkedList<>();

    /**
     * 全局观察者
     */
    public LinkedList<Observer> observers = new LinkedList<>();
    public HtmlContent content;
    public List<String> files;
    public GlobalUtil globalUtil;
    public UtilDto utilDto;
    public HtmlPanel htmlPanel;
    private static String[] goods = {"禁军", "御林", "好汉印", "神奇丹药", "高级锻造石", "天书油墨"};
    private boolean execChange = false;

    private SysTextParse sysTextParse;

    public CoreController(GlobalUtil globalUtil, UtilDto utilDto) {
        this.globalUtil = globalUtil;
        this.utilDto = utilDto;
    }


    /**
     * 页面改变 就是每次页面加载了调用
     */
    public void pageChange() {
        utilDto.waitNotfiy.await();
        if (execChange) return;
        execChange = true;
        try {
            if (content != null && content.getDocument().text().contains("战斗已经结束!")) {
                logger.error(content.getDocument().text());
                for (String good : goods) {
                    LinkBean res = content.linkName(good, true);
                    System.out.println(res.isSuccess());
                    if(res.isSuccess()){
                        logger.error(content.getDocument().text());
                    }
                }
            }
            utilDto.clearUtil.clear(content);
        } finally {
            execChange = false;
        }
    }

    /**
     * 一个脚本运行后
     */
    public void stepRunAfter() {

    }

    /**
     * 一个脚本运行前
     */

    public void stepRunBefore() {
        for (Observer observer : observers) {
            observer.run();
        }
    }


    /**
     * 开始运行
     *
     * @param content
     */
    public void run(HtmlContent content) {
        this.content = content;
        run();
        //testRun(content)
    }


    public void testRun(HtmlContent content) {
        new Thread(() -> {
            GoodsSale lcClear = new GoodsSale("柳虫_柳虫残骸");
            lcClear.setHtmlContent(content);
            lcClear.run();
        }).start();

    }

    /**
     * 脚本
     */
    public void loadParse() {
        if (content == null) return;
        textParses.clear();
        if (sysTextParse == null) {
            sysTextParse = new SysTextParse(content);
        }
        textParses.add(sysTextParse);
        TextParse textParse;
        for (String file : files) {
            try {
                textParse = TextParse.getInstance(file, content, utilDto, this);
                textParse.setFileName(file);
                textParses.add(textParse);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(file + "解析脚本 文件失败!" + e.toString());
            }

        }
    }

    /**
     * 启动脚本
     */
    private void run() {
        service.submit(() -> {
            while (!Thread.interrupted()) {
                runParses.clear();
                runParses.addAll(textParses);
                for (Runable parse : runParses) {
                    try {
                        if (parse.isClear()) {
                            utilDto.clearUtil.fzClear(content);
                        }
                        parse.run();
                    } catch (StopCurrStepException e) {
                        System.out.println(parse.getFileName() + "->" + e.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(parse.getFileName() + "->运行脚本异常!" + e.toString());
                    }
                }
                System.gc();
            }
        });
    }

    /**
     * 添加全局脚本
     *
     * @param manyStep
     */
    public void addGlobal(ManyStep manyStep) {
        globalUtil.addStep(manyStep);
    }

    /**
     * 添加观察者
     *
     * @param observer
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * @param step
     * @return
     */
    public boolean addMarkStep(Step step) {
        return utilDto.clearUtil.addMarkStep(step);
    }

}
