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

    public void addPanel(String name, String url) {
        try {
            this.add(name, new HtmlPanel(url, mainWindow));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
