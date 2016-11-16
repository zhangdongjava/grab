package com.zzz.play.util.ui;

import com.zzz.play.bean.UserInfo;
import com.zzz.play.ui.HtmlPanel;
import com.zzz.play.ui.MainWindow;
import com.zzz.play.ui.TabPanel;
import com.zzz.play.util.resource.BookmarkUtil;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * 菜单操作
 * Created by dell_2 on 2016/11/16.
 */
public class MenuOpera {

    private MainWindow mainWindow;
    private TabPanel tabPanel;
    private JFileChooser jChooser;

    public MenuOpera(MainWindow mainWindow, TabPanel tabPanel) {
        jChooser = new JFileChooser();
        jChooser.setCurrentDirectory(new File(""));//设置默认打开路径
        this.mainWindow = mainWindow;
        this.tabPanel = tabPanel;
    }

    /**
     * 打开多个标签
     */
    public void openManyBookMark() {
        jChooser.setDialogType(JFileChooser.OPEN_DIALOG);//设置保存对话框
        jChooser.showDialog(mainWindow, "打开多个书签");
        File file = jChooser.getSelectedFile();
        if (file != null) {
            try {
                java.util.List<UserInfo> list = BookmarkUtil.load(file);
                for (UserInfo user : list) {
                    user.setLogin(true);
                    tabPanel.addPanel(user);
                }
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(mainWindow, "打开失败!" + e.toString());
                e.printStackTrace();
            }
        }

    }

    /**
     * 保存多个标签
     */
    public void saveManyBookMark() {
        jChooser = new JFileChooser();
        jChooser.setCurrentDirectory(new File(""));//设置默认打开路径
        jChooser.setDialogType(JFileChooser.SAVE_DIALOG);//设置保存对话框
        jChooser.showDialog(mainWindow, "保存书签");
        File file = jChooser.getSelectedFile();
        if (file != null) {
            try {
                LinkedList<UserInfo> list = new LinkedList<>();
                for (HtmlPanel htmlPanel : mainWindow.htmlPanels) {
                    if (!htmlPanel.user.isLogin()) {
                        JOptionPane.showConfirmDialog(mainWindow, "这是缓存!!");
                        return;
                    }
                    if (htmlPanel.user.getName() != null
                            && htmlPanel.user.getDaqu() != null
                            && !htmlPanel.isWait) {
                        list.add(htmlPanel.user);
                    }
                }
                if (list.size() > 0) {
                    BookmarkUtil.save(file, list);
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showConfirmDialog(mainWindow, e.toString());
            }

        }
    }
}
