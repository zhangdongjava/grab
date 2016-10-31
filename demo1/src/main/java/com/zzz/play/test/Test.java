package com.zzz.play.test;

import com.zzz.play.setp.TextParse;
import com.zzz.play.setp.sys.GoodsSale;
import com.zzz.play.ui.MainWindow;
import com.zzz.play.util.HtmlContent;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class Test {

    static LinkedList<TextParse> textParses = new LinkedList<>();


    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException, InstantiationException, UnsupportedLookAndFeelException, ClassNotFoundException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//Nimbus风格，jdk6
        MainWindow mainWindow = new MainWindow();


    }

    public static void run(HtmlContent content, String[] files) {
        //testRun(content);
        testScript(content);
        //scriptRun(content, files);
    }

    public static void testRun(HtmlContent content) {
        new Thread(() -> {
            GoodsSale lcClear = new GoodsSale("柳虫_柳虫残骸");
            lcClear.setHtmlContent(content);
            lcClear.run();
        }).start();

    }

    public static void testScript(HtmlContent content) {
        new Thread(() -> {
            try {
                TextParse textParse = TextParse.getInstance("fresh", content);
                while (true) {
                    textParse.run();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void scriptRun(HtmlContent content, String[] files) {
        new Thread(() -> {
            TextParse textParse = null;
            try {
                for (String file : files) {
                    textParse = TextParse.getInstance(file, content);
                    textParses.add(textParse);
                }
                while (true) {
                    try {
                        textParses.forEach(TextParse::run);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("运行脚本异常!" + e.toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("解析脚本 文件失败!" + e.toString());
            }
        }).start();
    }
}
