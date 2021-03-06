package com.zzz.play.core;

import com.zzz.play.bean.LinkBean;
import com.zzz.play.bean.UserInfo;
import com.zzz.play.exception.RunStopException;
import com.zzz.play.exception.StopCurrStepException;
import com.zzz.play.inter.Observer;
import com.zzz.play.inter.Runable;
import com.zzz.play.setp.Step;
import com.zzz.play.setp.TextParse;
import com.zzz.play.setp.impl.config.ManyStep;
import com.zzz.play.setp.sys.SysTextParse;
import com.zzz.play.ui.HtmlPanel;
import com.zzz.play.ui.dialog.ScriptDialog;
import com.zzz.play.util.GlobalUtil;
import com.zzz.play.util.HtmlContent;
import com.zzz.play.util.ThreadPoolUtil;
import com.zzz.play.util.UtilDto;
import com.zzz.play.util.sys.LoggerUtil;
import com.zzz.play.util.sys.SetProperties;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 核心控制器 整个运行把控
 * Created by dell_2 on 2016/10/29.
 */
public class CoreController {

    private static Logger logger = Logger.getLogger(CoreController.class);
    /**
     * 缓存集合1
     */
    public LinkedList<Runable> cache1 = new LinkedList<>();


    /**
     * 全局观察者
     */
    public LinkedList<Observer> observers = new LinkedList<>();
    public HtmlContent content;
    public UserInfo user;
    public GlobalUtil globalUtil;
    public UtilDto utilDto;
    public HtmlPanel htmlPanel;
    private static String[] goods = {"禁军", "御林", "好汉印", "神奇丹药", "高级锻造石", "天书油墨"};
    private boolean execChange = false;

    private SysTextParse sysTextParse;

    private Future<?> future;
    public boolean runing = true;

    private boolean scriptReload = false;

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
            if (content != null && content.getText().contains("战斗已经结束!")) {
                for (String good : goods) {
                    LinkBean res = content.linkName(good, true);
                    if (res.isSuccess()) {
                        logger.error(content.getText());
                    }
                }
            }
            //日志记录
            LoggerUtil.loggerUtil.run(content);
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

//    /**
//     * 开始运行
//     *
//     * @param content
//     */
//    public void run2(HtmlContent content) {
//        this.content = content;
//        run2();
//        //testRun(content)
//    }

    /**
     * 脚本
     */
    public void loadParse() {
        if (content == null) return;
        globalUtil.clear();
        cache1.clear();
        scriptReload = true;
        addSysScript();
        TextParse textParse;
        for (String file : user.getScritps1()) {
            try {
                if (ScriptDialog.allScripts.containsKey(file)) {
                    textParse = TextParse.getInstance(ScriptDialog.allScripts.get(file), content, utilDto, this);
                    textParse.setFileName(file);
                    cache1.add(textParse);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(file + "解析脚本 文件失败!" + e.toString());
            }

        }
    }

    private void addSysScript() {
        if (SetProperties.getSetBean().isRunsys()) {
            if (sysTextParse == null) {
                try {
                    sysTextParse = new SysTextParse(content,this.utilDto,this);
                } catch (Exception e) {
                    System.out.println("sysTextParse初始化失败!!");
                    return;
                }
            }
            cache1.add(sysTextParse);
        }
    }

    /**
     * 启动主脚本
     */
    private void run() {
        List<Runable> runParses = new LinkedList<>();
        if (cache1.isEmpty()) {
            loadParse();
        }
        future = ThreadPoolUtil.addThread(() -> {
            runing = true;

            System.out.println(user.getName() + "-->脚本开始!");
            run:
            while (runing) {
                System.out.println("运行一遍脚本!");
                if (scriptReload || runParses.isEmpty()) {//脚本重新加载了 才会拿来执行
                    runParses.clear();
                    runParses.addAll(cache1);
                    scriptReload = false;
                }
                runScripts(runParses);
                System.gc();
            }
            htmlPanel.killed();
        });

    }


    private void runScripts(List<Runable> runParses) {
        boolean exec = true;
        for (Runable parse : runParses) {
            System.out.println("运行一个脚本");
            if (!runing) return;
            try {
                if (parse.isClear() && exec) {
                    utilDto.clearUtil.fzClear(content);
                }
                exec = parse.run();
            } catch (StopCurrStepException e) {
                System.out.println(parse.getFileName() + "->" + e.toString());
            } catch (RunStopException e) {
                System.out.println(htmlPanel.user.getName() + "->>脚本终止!");
                return;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(parse.getFileName() + "->运行脚本异常!" + e.toString());
            }
        }
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

    /**
     * 终止脚本
     */
    public void kill() {
        runing = false;
        if(future != null){
            future.cancel(true);
        }
    }

}
