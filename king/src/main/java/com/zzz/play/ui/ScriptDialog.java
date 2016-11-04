package com.zzz.play.ui;

import com.zzz.play.util.Resource;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dell_2 on 2016/11/1.
 */
public class ScriptDialog extends JDialog {

    public static String scriptRoot = null;
    private static Map<String, String> allScripts;
    private HtmlPanel htmlPanel;
    private JList<String> list;
    private JList<String> clickList;
    private DefaultListModel<String> defaultListModel;

    private JButton ok;

    private Set<String> selectList;

    private boolean is_init = false;

    static {
        allScripts = new ConcurrentHashMap<>();
        Resource.load();
    }

    public ScriptDialog(HtmlPanel htmlPanel) {
        super(htmlPanel.mainWindow);
        this.setModal(true);
        this.setLayout(null);
        this.setSize(400, 600);
        this.setLocationRelativeTo(null);
        this.htmlPanel = htmlPanel;
    }

    private ScriptDialog(Frame frame) {
        super(frame);
    }

    private void init() {

        defaultListModel = new DefaultListModel<>();
        list = new JList(allScripts.keySet().toArray());
        selectList = new HashSet<>();
        clickList = new JList(defaultListModel);
        ok = new JButton("确定");

        list.setBackground(Color.gray);
        clickList.setBackground(Color.gray);
        list.setBounds(0, 0, 150, 500);
        list.addListSelectionListener((e) -> select(e));
        clickList.addListSelectionListener((e) -> remove(e));
        clickList.setBounds(160, 0, 150, 500);
        ok.setBounds(100, 520, 60, 30);
        ok.addActionListener((e) -> ok());
        this.add(ok);
        this.add(list);
        this.add(clickList);
    }

    private void remove(ListSelectionEvent e) {
        JList<String> clickList = (JList<String>) e.getSource();
        String value = clickList.getSelectedValue();
        System.out.println(value);
        if (selectList.contains(value)) {
            System.out.println("存在!");
            defaultListModel.removeElement(value);
            selectList.remove(value);
        }
    }

    private void select(ListSelectionEvent e) {
        String value = list.getSelectedValue();
        if (!selectList.contains(value)) {
            defaultListModel.addElement(value);
            selectList.add(value);
        }
    }

    public void showUi() {
        scriptRoot = Resource.bootPath;
        build();
        this.setVisible(true);
    }

    private void ok() {
        htmlPanel.cleatScript();
        for (int i = 0; i < defaultListModel.size(); i++) {
            htmlPanel.addScript(allScripts.get(defaultListModel.get(i)));
        }
        this.setVisible(false);
        htmlPanel.reloadScript();
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//Nimbus风格，jdk6
        JFrame jFrame = new JFrame();
        jFrame.setSize(100, 200);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        ScriptDialog scriptDialog = new ScriptDialog(jFrame);
        jFrame.setVisible(true);
        scriptDialog.setVisible(true);
    }


    public void build() {
        if (scriptRoot == null) {
            JOptionPane.showConfirmDialog(this.htmlPanel.mainWindow, "请设置脚本根路径!");
            return;
        }
        File file = new File(scriptRoot);
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.isFile()) {
                allScripts.put(file1.getName(), file1.getPath());
            } else {
                openDir(file1, file1.getName());
            }
        }
        if (!is_init) {
            init();
            is_init = true;
        }
    }

    public void openDir(File dir, String name) {
        File[] files = dir.listFiles();
        for (File file1 : files) {
            if (file1.isFile()) {
                allScripts.put(name + "/" + file1.getName(), file1.getPath());
            } else {
                openDir(file1, name + "/" + file1.getName());
            }
        }
    }
}
