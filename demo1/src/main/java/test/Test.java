package test;

import setp.TextParse;
import ui.MainWindow;
import util.HtmlContent;

import javax.swing.*;
import java.io.File;
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

        new Thread(() -> {
            TextParse textParse = null;
            try {
                for (String file : files) {
                    textParse = TextParse.getInstance(file, content);
                    textParses.add(textParse);
                }
                while (true) {
                    textParses.forEach(TextParse::run);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("解析脚本 文件失败!" + e.toString());
            }
        }).start();
    }
}
