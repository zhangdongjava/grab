package com.zzz.play.ui.dialog;

import com.zzz.play.bean.UserInfo;
import com.zzz.play.ui.HtmlPanel;
import com.zzz.play.ui.TabPanel;
import com.zzz.play.util.Resource;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dell_2 on 2016/11/1.
 */
public class ScriptDialog extends JDialog {

    public static String scriptRoot = null;
    /**
     * 名称映射地址
     */
    private static Map<String, String> allScripts;
    private static Map<String, List<String>> mapList;
    /**
     * 地址映射名称
     */
    private static Map<String, String> url_name;
    private HtmlPanel htmlPanel;
    private ScrollPane jScrollPane;
    private JList<String> clickList;
    private DefaultListModel<String> defaultListModel;
    private JTabbedPane tabPanel;
    private JButton ok;
    private JButton reload;
    private JButton open;
    private JButton save;
    private JFileChooser jChooser;
    /**
     * 展示的脚本
     */
    private LinkedList<String> showList;

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
        jChooser = new JFileChooser();
        jChooser.setCurrentDirectory(new File(""));//设置默认打开路径
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
        defaultListModel = new DefaultListModel<>();
        selectList = new HashSet<>();
        clickList = new JList(defaultListModel);
        ok = new JButton("确定");
        reload = new JButton("重载");
        open = new JButton("打开");
        save = new JButton("保存");
        clickList.setBackground(Color.gray);
        clickList.addListSelectionListener((e) -> remove(e));
        clickList.setBounds(250, 0, 150, 500);
        ok.setBounds(25, 520, 60, 30);
        reload.setBounds(100, 520, 60, 30);
        open.setBounds(175, 520, 60, 30);
        save.setBounds(250, 520, 60, 30);
        ok.addActionListener((e) -> ok());
        reload.addActionListener((e) -> reload());
        open.addActionListener((e) -> open());
        save.addActionListener((e) -> save());
        tabPanel.setBounds(0, 0, 250, 500);
        addList();
        this.add(tabPanel);
        this.add(ok);
        this.add(open);
        this.add(save);
        this.add(reload);
        this.add(clickList);
    }

    /**
     * 保存脚本
     */
    private void save() {
        jChooser.setDialogType(JFileChooser.SAVE_DIALOG);//设置保存对话框
        jChooser.showDialog(this, "保存脚本");
        File file = jChooser.getSelectedFile();
        if (file != null) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                PrintStream stream = new PrintStream(fos);
                for (String s : showList) {
                    stream.println(url_name.get(s));
                }
                stream.close();
                fos.close();
            } catch (IOException e) {
               JOptionPane.showConfirmDialog(this,"保存失败!"+e.toString());
            }
        }
    }

    private void open() {
        jChooser.setDialogType(JFileChooser.OPEN_DIALOG);//设置保存对话框
        jChooser.showDialog(this, "打开缓存");
        File file = jChooser.getSelectedFile();
        if (file != null) {
            try {
                FileInputStream fis  = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String line = br.readLine();
                while(line!=null && allScripts.containsKey(line)){
                    defaultListModel.addElement(line);
                    selectList.add(line);
                    showList.add(allScripts.get(line));
                    line = br.readLine();
                }
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(this,"打开失败!"+e.toString());
            }
        }
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
            defaultListModel.removeElement(value);
            selectList.remove(value);
            showList.remove(allScripts.get(value));
        }
    }

    private void select(ListSelectionEvent e) {
        JList<String> list = (JList<String>) e.getSource();
        String value = list.getSelectedValue();
        if (!selectList.contains(value)) {
            defaultListModel.addElement(value);
            selectList.add(value);
            showList.add(allScripts.get(value));
        }
    }

    public void showUi(LinkedList<String> showList) {
        this.showList = showList;
        defaultListModel.clear();
        for (String s : showList) {
            defaultListModel.addElement(url_name.get(s));
        }
        this.setVisible(true);
    }

    private void ok() {
        htmlPanel.resetScript();
        this.setVisible(false);
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
