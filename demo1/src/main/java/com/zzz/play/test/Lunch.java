package com.zzz.play.test;

import com.zzz.play.exception.StopCurrStepException;
import com.zzz.play.setp.TextParse;
import com.zzz.play.setp.sys.GoodsSale;
import com.zzz.play.util.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 实例化
 * Created by dell_2 on 2016/10/29.
 */
public class Lunch {

    ExecutorService service = Executors.newFixedThreadPool(20);

    public LinkedList<TextParse> textParses = new LinkedList<>();
    public LinkedList<TextParse> runParses = new LinkedList<>();
    public HtmlContent content;
    public List<String> files;
    public GlobalUtil globalUtil;
    public UtilDto utilDto;

    /**
     * 运行脚本的线程
     */
    private Thread runSetpTheard;

    public Lunch(GlobalUtil globalUtil, UtilDto utilDto) {
        this.globalUtil = globalUtil;
        this.utilDto = utilDto;
    }

    public void run(HtmlContent content, List<String> files) {
        this.content = content;
        this.files = files;
        loadParse();
        scriptRun();
        //testScript(content);
        //testRun(content)
    }

    public void testRun(HtmlContent content) {
        new Thread(() -> {
            GoodsSale lcClear = new GoodsSale("柳虫_柳虫残骸");
            lcClear.setHtmlContent(content);
            lcClear.run();
        }).start();

    }

    public void testScript(HtmlContent content) {
        new Thread(() -> {
            try {
                TextParse textParse = TextParse.getInstance("scripts/材料/血印分身蒙汗药", content, globalUtil, utilDto);
                while (true) {
                    textParse.run();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void loadParse() {
        if (content == null) return;
        textParses.clear();
        TextParse textParse = null;
        for (String file : files) {
            try {
                textParse = TextParse.getInstance(file, content, globalUtil, utilDto);
                textParse.setFileName(file);
                textParses.add(textParse);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(file + "解析脚本 文件失败!" + e.toString());
            }

        }
    }

    public void scriptRun() {
        service.submit(() -> {
            while (!Thread.interrupted()) {
                runParses.clear();
                runParses.addAll(textParses);
                for (TextParse parse : runParses) {
                    try {
                        parse.run();
                    } catch (StopCurrStepException e) {
                        System.out.println(parse.getFileName() + "->" + e.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(parse.getFileName() + "->运行脚本异常!" + e.toString());
                    }
                }
            }
        });
    }


    public void waitRunEnd() {
        System.out.println("开始等待!");
        synchronized (runSetpTheard) {
            try {
                runSetpTheard.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("结束等待!");
    }

    public void notifyRunStart() {
        System.out.println("开始唤醒!");
        synchronized (runSetpTheard) {
            runSetpTheard.notifyAll();
        }
    }


}
