package com.zzz.play.test;

import com.zzz.play.setp.TextParse;
import com.zzz.play.setp.sys.GoodsSale;
import com.zzz.play.ui.MainWindow;
import com.zzz.play.util.GlobalUtil;
import com.zzz.play.util.GoodsUtil;
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
    static LinkedList<TextParse> runParses = new LinkedList<>();
    static HtmlContent content;
    static String[] files;
    static GlobalUtil globalUtil;


    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException, InstantiationException, UnsupportedLookAndFeelException, ClassNotFoundException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//Nimbus风格，jdk6
        MainWindow mainWindow = new MainWindow();


    }

    public static void run(HtmlContent content, String[] files, GlobalUtil globalUtil) {
        Test.content = content;
        Test.files = files;
        Test.globalUtil = globalUtil;
        loadParse();
        //testRun(content)
        // testScript(content);
        scriptRun(content, files);
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
                TextParse textParse = TextParse.getInstance("材料/血印分身蒙汗药", content, globalUtil);
                while (true) {
                    textParse.run();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void loadParse() {
        textParses.clear();
        TextParse textParse = null;
        for (String file : files) {
            try {
                textParse = TextParse.getInstance(file, content, globalUtil);
                textParse.setFileName(file);
                textParses.add(textParse);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(file + "解析脚本 文件失败!" + e.toString());
            }

        }
    }

    public static void scriptRun(HtmlContent content, String[] files) {
        new Thread(() -> {
            TextParse textParse = null;


            while (true) {
                runParses.clear();
                runParses.addAll(textParses);
                try {
                    for (TextParse parse : runParses) {
                        textParse = parse;
                        textParse.run();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(textParse.getFileName() + "->运行脚本异常!" + e.toString());
                }
            }

        }).start();
    }
}
