package com.zzz.play.ui;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by dell_2 on 2016/11/1.
 */
public class TabPanel extends JTabbedPane {

    private MainWindow mainWindow;

    public TabPanel(MainWindow mainWindow) {
        super(JTabbedPane.LEFT);
        this.mainWindow = mainWindow;
    }

    public void addPanel(String name, String url, String daqu) {
        try {
            HtmlPanel panel = new HtmlPanel(url, mainWindow,name,daqu);
            this.add(name, panel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
