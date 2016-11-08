package com.zzz.play.ui.dialog;

import com.zzz.play.ui.HtmlPanel;
import com.zzz.play.ui.TabPanel;
import com.zzz.play.util.Resource;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dell_2 on 2016/11/1.
 */
public class ScriptDialog extends JDialog {

    public static String scriptRoot = null;
    private static Map<String, String> allScripts;
    private static Map<String, List<String>> mapList;
    private static Map<String, String> url_name;
    private HtmlPanel htmlPanel;
    private ScrollPane jScrollPane;
    private JList<String> clickList;
    private DefaultListModel<String> defaultListModel;
    private JTabbedPane tabPanel;
    private JButton ok;
    private JButton reload;

    private Set<String> selectList;

    private boolean is_init = false;

    static {
        allScripts = new LinkedHashMap<>();
        url_name = new HashMap<>();
        mapList = new HashMap<>();
        Resource.load();
    }

    public ScriptDialog(HtmlPanel htmlPanel) {
        super(htmlPanel.mainWindow);
        tabPanel = new JTabbedPane(JTabbedPane.LEFT);
        this.setModal(true);
        this.setLayout(null);
        this.setSize(400, 600);
        this.setLocationRelativeTo(null);
        this.htmlPanel = htmlPanel;
        build();
    }

    private ScriptDialog(Frame frame) {
        super(frame);
    }

    private void init() {
        JPanel panel = new JPanel();
        defaultListModel = new DefaultListModel<>();
        selectList = new HashSet<>();
        clickList = new JList(defaultListModel);
        ok = new JButton("确定");
        reload = new JButton("重载");
        clickList.setBackground(Color.gray);
        clickList.addListSelectionListener((e) -> remove(e));
        clickList.setBounds(250, 0, 150, 500);
        ok.setBounds(100, 520, 60, 30);
        reload.setBounds(200, 520, 60, 30);
        ok.addActionListener((e) -> ok());
        reload.addActionListener((e) -> reload());
        tabPanel.setBounds(0, 0, 250, 500);
        addList();
        this.add(tabPanel);
        this.add(ok);
        this.add(reload);
        this.add(clickList);
    }

    private void addList() {
        for (Map.Entry<String, List<String>> entry : mapList.entrySet()) {
            JList<String> list;
            list = new JList(entry.getValue().toArray());
            list.setBackground(Color.gray);
            list.setBounds(0, 0, 145, 480);
            list.addListSelectionListener((e) -> select(e));
            jScrollPane = new ScrollPane();
            jScrollPane.setBounds(0, 0, 150, 500);
            jScrollPane.add(list);
            tabPanel.add(entry.getKey(), jScrollPane);
        }

    }

    private void reload() {
        allScripts.clear();
        url_name.clear();
        mapList.clear();
        build();
        tabPanel.removeAll();
        addList();
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
        JList<String> list = (JList<String>) e.getSource();
        String value = list.getSelectedValue();
        if (!selectList.contains(value)) {
            defaultListModel.addElement(value);
            selectList.add(value);
        }
    }

    public void showUi() {
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
        scriptRoot = Resource.bootPath;
        if (scriptRoot == null) {
            JOptionPane.showConfirmDialog(this.htmlPanel.mainWindow, "请设置脚本根路径!");
            return;
        }
        File file = new File(scriptRoot);
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.isDirectory()) {
                mapList.put(file1.getName(), new LinkedList<>());
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
                mapList.get(name).add(name + "/" + file1.getName());
                allScripts.put(name + "/" + file1.getName(), file1.getPath());
                url_name.put(file1.getPath(), name + "/" + file1.getName());
            }
        }
    }

    public void addScript(String url) {
        String name = url_name.get(url);
        defaultListModel.addElement(name);
        selectList.add(name);
    }
}
