package com.zzz.play.ui;

import com.zzz.play.bean.UserInfo;

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

    public void addPanel(UserInfo user) {
        try {
            HtmlPanel panel = new HtmlPanel(this, user, mainWindow);
            this.add(user.getName(), panel);
            // this.setTabComponentAt(getIndex(panel), panel);//实现这个功能的就这一条最重要的语句
            mainWindow.addHtmlPanel(panel);
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(HtmlPanel htmlPanel) {
        //this.remove(getIndex(htmlPanel));

    }

    //   public int getIndex(HtmlPanel htmlPanel) {
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).equals(htmlPanel)) {
//                return i;
//            }
//
//        }
//        return 0;
    //   }
}
