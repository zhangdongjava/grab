package com.zzz.play.ui;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/11/1.
 */
public class TabPanel extends JTabbedPane {

    private MainWindow mainWindow;

    public TabPanel(MainWindow mainWindow) {
        super(JTabbedPane.LEFT);
        this.mainWindow = mainWindow;
    }

    public void addPanel(String name, String url, String daqu, LinkedList<String> script) {
        try {
            HtmlPanel panel = new HtmlPanel(url, mainWindow, name, daqu, script);
            this.add(name, panel);
            mainWindow.addHtmlPanel(panel);
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
