package com.zzz.play.ui;

import javax.swing.*;
import java.util.Map;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class MainWindow extends JFrame {


    private TabPanel tabPanel;
    private MyDialog myDialog;

    private JLabel jLabel = new JLabel("暂无物品!");

    // public static String[] scripts = {"scripts/材料/柳虫"};
    public static String[] scripts = {"scripts/材料/血印分身蒙汗药", "scripts/材料/柳虫", "scripts/材料/大白菜"};

    public static int count = 0;

    public MainWindow() {
        this.setSize(400, 600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tabPanel = new TabPanel(this);
        this.setContentPane(tabPanel);
        myDialog = new MyDialog(this);
        initMenu();
        this.setVisible(true);
    }

    private void initMenu() {
        JMenu jm = new JMenu("选项卡");     //创建JMenu菜单对象
        JMenuItem addTab = new JMenuItem("添加");  //菜单项
        addTab.addActionListener((e) -> addTab());
        jm.add(addTab);   //将菜单项目添加到菜单
        JMenuBar br = new JMenuBar();  //创建菜单工具栏
        br.add(jm);      //将菜单增加到菜单工具栏
        this.setJMenuBar(br);  //为 窗体设置  菜单工具栏
    }

    private void addTab() {
        System.out.println("myDialog");
        myDialog.setVisible(true);
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//Nimbus风格，jdk6
        new MainWindow();
    }


    public void showGoods(Map<String, Integer> goods) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, Integer> entry : goods.entrySet()) {
            stringBuffer.append(entry.getKey() + ":" + entry.getValue() + ",");
        }
        if (stringBuffer.length() > 0)
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        jLabel.setText(stringBuffer.toString());
    }

    public void addTab(String name, String url) {
        tabPanel.addPanel(name, url);
        count++;
    }
}
