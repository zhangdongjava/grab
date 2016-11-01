package com.zzz.play.ui;

import javax.swing.*;

/**
 * Created by dell_2 on 2016/11/1.
 */
public class TabPanel extends JTabbedPane {

    private MainWindow mainWindow;

    public TabPanel(MainWindow mainWindow) {
        super(JTabbedPane.LEFT);
        this.mainWindow = mainWindow;
    }

    public void addPanel(String name, String url) {
        this.add(name, new HtmlPanel(url, mainWindow));
    }
}
