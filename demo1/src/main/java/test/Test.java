package test;

import setp.TextParse;
import ui.MainWindow;
import util.HtmlContent;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class Test {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException, InstantiationException, UnsupportedLookAndFeelException, ClassNotFoundException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//Nimbus风格，jdk6
        String str = TextParse.class.getResource("/").getPath() + "dbc";
        File file = new File(str);
        MainWindow mainWindow = new MainWindow();
        HtmlContent content = HtmlContent.initHtmlContent("http://xfhero1.yytou.com/gCmd.do?cmd=77c2&sid=25pq4s3bj1h3667y06scnl", mainWindow);
        new Thread(() -> {
            TextParse textParse = null;
            try {
                textParse = TextParse.getInstance(file, content);
                for (int i = 0; i < 103; i++) {
                    textParse.run();
                }
                System.out.println("脚本结束!!");
            } catch (Exception e) {
                System.out.println("解析脚本 文件失败!" + e.toString());
            }
        }).start();
    }
}
