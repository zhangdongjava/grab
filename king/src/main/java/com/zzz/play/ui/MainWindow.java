package com.zzz.play.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class MainWindow extends JFrame {

    private JLabel L_img;
    private JLabel L_img2;
    private PopupMenu pop;
    private MenuItem open, close;
    private TrayIcon trayicon;
    private TabPanel tabPanel;
    private MyDialog myDialog;
    private SysSetDialog sysSetDialog;

    private static MainWindow mainWindow;

    private JLabel jLabel = new JLabel("暂无物品!");

    // public static String[] scripts = {"scripts/材料/大柳虫"};
    public static String[] scripts = {"scripts/材料/血印分身蒙汗药", "scripts/材料/大柳虫", "scripts/材料/大白菜"};

    public static int count = 0;

    public MainWindow() {
        this.setSize(400, 600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tabPanel = new TabPanel(this);
        this.setContentPane(tabPanel);
        myDialog = new MyDialog(this);
        sysSetDialog = new SysSetDialog(this);
        initMenu();
        initToTray();
        this.setAlwaysOnTop(false);
        this.setVisible(true);
        mainWindow = this;
    }

    public static MainWindow getWindow() {
        return mainWindow;
    }

    private void initMenu() {
        JMenu jm = new JMenu("选项卡");     //创建JMenu菜单对象
        JMenu set = new JMenu("设置");     //创建JMenu菜单对象
        JMenuItem addTab = new JMenuItem("添加");  //菜单项
        JMenuItem scriptPath = new JMenuItem("脚本路径");  //菜单项
        addTab.addActionListener((e) -> addTab());
        scriptPath.addActionListener((e) -> scriptPath());
        jm.add(addTab);   //将菜单项目添加到菜单
        set.add(scriptPath);   //将菜单项目添加到菜单
        JMenuBar br = new JMenuBar();  //创建菜单工具栏
        br.add(jm);      //将菜单增加到菜单工具栏
        br.add(set);      //将菜单增加到菜单工具栏
        this.setJMenuBar(br);  //为 窗体设置  菜单工具栏
    }

    private void scriptPath() {
        sysSetDialog.setVisible(true);
    }

    /**
     * 初始化最小化托盘
     */
    private void initToTray() {
        L_img = new JLabel();
        L_img2 = new JLabel();

        pop = new PopupMenu();
        open = new MenuItem("open");
        open.addActionListener((e) -> openFrame());

        close = new MenuItem("close");
        close.addActionListener((e) -> System.exit(-1));

        pop.add(open);
        pop.add(close);
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image icon = getToolkit().getImage(getClass().getResource("/image/icon.png"));
            trayicon = new TrayIcon(icon, "java swing", pop);
            trayicon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        if (getExtendedState() == JFrame.ICONIFIED) {
                            openFrame();// 还原窗口
                        } else {
                            // 设置窗口状态(最小化至托盘)
                            setExtendedState(JFrame.ICONIFIED);
                        }
                    }
                }
            });

            try {
                tray.add(trayicon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
        this.addWindowListener(new WindowAdapter() {
            //窗口最小化
            @Override
            public void windowIconified(WindowEvent e) {
                setVisible(false);// 设置为不可见
            }
        });
    }

    private void addTab() {
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

    /**
     * 从托盘显示出来
     */
    public void openFrame() {
        setVisible(true);// 设置为可见
        //setAlwaysOnTop(true);// 设置置顶
        // 设置窗口状态(在最小化状态弹出显示)
        setExtendedState(JFrame.NORMAL);
    }
}
