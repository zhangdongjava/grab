package com.zzz.play.util.ui;

import com.zzz.play.bean.UserInfo;
import com.zzz.play.ui.HtmlPanel;
import com.zzz.play.ui.MainWindow;
import com.zzz.play.ui.TabPanel;
import com.zzz.play.ui.dialog.ScriptDialog;
import com.zzz.play.util.resource.UiResourceUtil;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import static com.zzz.play.ui.dialog.ScriptDialog.allScripts;

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
                java.util.List<UserInfo> list = UiResourceUtil.bookManyload(file);
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
                    UiResourceUtil.bookManysave(file, list);
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showConfirmDialog(mainWindow, e.toString());
            }

        }
    }

    /**
     * 打开缓存
     */
    public void openCache() {
        jChooser.setDialogType(JFileChooser.OPEN_DIALOG);//设置保存对话框
        jChooser.showDialog(mainWindow, "打开缓存");
        File file = jChooser.getSelectedFile();
        if (file != null) {
            try {
                Map<String, UserInfo> map = UiResourceUtil.openCache(file);
                for (UserInfo user : map.values()) {
                    user.setLogin(false);
                    user.setUrl(user.getCurrUrl());
                    tabPanel.addPanel(user);
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showConfirmDialog(mainWindow, "打开失败!" + e.toString());
            }

        }
    }

    /**
     * 打开脚本
     */
    public void openScript() {
        jChooser.setDialogType(JFileChooser.OPEN_DIALOG);//设置保存对话框
        jChooser.showDialog(mainWindow, "打开脚本");
        File file = jChooser.getSelectedFile();
        if (file != null) {
            try {
                FileInputStream fis = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String line = br.readLine();
                while (line != null && ScriptDialog.allScripts.containsKey(line)) {
                    for (HtmlPanel htmlPanel : mainWindow.htmlPanels) {
                        if (!htmlPanel.user.getScritps1().contains(allScripts.get(line))) {
                            htmlPanel.user.getScritps1().add(line);
                        }
                    }
                    line = br.readLine();
                }
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(mainWindow, "打开失败!" + e.toString());
            }
        }
    }

    /**
     * 设置托盘名称
     */
    public void setName(TrayIcon trayicon) {
        String name = (String) JOptionPane.showInputDialog(null, "请输入窗口名称：", "title", JOptionPane.PLAIN_MESSAGE, null, null, "在这输入");
        trayicon.setToolTip(name);
    }
}
