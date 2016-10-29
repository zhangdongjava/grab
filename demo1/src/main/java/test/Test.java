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
        HtmlContent content = HtmlContent.initHtmlContent("http://xfhero45.yytou.com/gCmd.do?cmd=a&sid=epf90c3bjj03n2c5vrd85", mainWindow);
        new Thread(() -> {
            TextParse textParse = null;
            try {
                textParse = TextParse.getInstance(file, content);
               while (true){
                   textParse.run();
               }
            } catch (Exception e) {
                System.out.println("解析脚本 文件失败!" + e.toString());
            }
        }).start();
    }
}
