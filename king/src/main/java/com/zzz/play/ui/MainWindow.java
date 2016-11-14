package com.zzz.play.ui;


import com.zzz.play.bean.UserInfo;
import com.zzz.play.ui.dialog.MyDialog;
import com.zzz.play.ui.dialog.ShuQianDialog;
import com.zzz.play.ui.dialog.ShuQianOpenDialog;
import com.zzz.play.ui.dialog.SysSetDialog;
import com.zzz.play.util.Resource;
import com.zzz.play.util.sys.Recovery;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class MainWindow extends JFrame {

    public static int width = Resource.UI_WIDTH;
    public static int height = Resource.UI_HEIGHT;
    //临时恢复使用工具
    private Recovery recovery;
    private JLabel L_img;
    private JLabel L_img2;
    private PopupMenu pop;
    private MenuItem open, close;
    private TrayIcon trayicon;
    private TabPanel tabPanel;
    private MyDialog myDialog;
    private SysSetDialog sysSetDialog;
    private ShuQianDialog shuQianDialog;
    private ShuQianOpenDialog qianOpenDialog;
    private JFileChooser jChooser;
    /**
     * 选项卡集合
     */
    public LinkedList<HtmlPanel> htmlPanels;

    private static MainWindow mainWindow;
    private static ExecutorService exec = Executors.newFixedThreadPool(2);

    private JLabel jLabel = new JLabel("暂无物品!");

    // public static String[] scripts = {"scripts/材料/大柳虫"};
    public static String[] scripts = {"scripts/材料/血印分身蒙汗药", "scripts/材料/大柳虫", "scripts/材料/大白菜"};

    public static int count = 0;

    private static Logger logger = Logger.getLogger(MainWindow.class);

    public MainWindow() {
        this.setSize(width, height);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tabPanel = new TabPanel(this);
        this.setContentPane(tabPanel);
        myDialog = new MyDialog(this);
        sysSetDialog = new SysSetDialog(this);
        shuQianDialog = new ShuQianDialog(this);
        qianOpenDialog = new ShuQianOpenDialog(this);
        htmlPanels = new LinkedList<>();
        recovery = new Recovery(this);
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
        JMenu shuqian = new JMenu("书签");     //创建JMenu菜单对象
        JMenuItem addTab = new JMenuItem("添加");  //菜单项
        JMenuItem scriptPath = new JMenuItem("脚本路径");  //菜单项
        JMenuItem name = new JMenuItem("名称");  //菜单项
        JMenuItem shuqianAdd = new JMenuItem("添加");  //菜单项
        JMenuItem shuqianOpen = new JMenuItem("打开");  //菜单项
        JMenuItem all = new JMenuItem("保存所有");  //菜单项
        JMenuItem open = new JMenuItem("打开所有");  //菜单项
        JMenuItem openCache = new JMenuItem("打开缓存");  //菜单项
        addTab.addActionListener((e) -> addTab());
        scriptPath.addActionListener((e) -> scriptPath());
        shuqianAdd.addActionListener((e) -> shuqianAdd());
        shuqianOpen.addActionListener((e) -> shuqianOpen());
        all.addActionListener((e) -> all());
        name.addActionListener((e) -> setName());
        open.addActionListener((e) -> exec.submit(() -> open()));
        openCache.addActionListener((e) -> exec.submit(() -> openCache()));
        jm.add(addTab);   //将菜单项目添加到菜单
        set.add(scriptPath);   //将菜单项目添加到菜单
        set.add(name);   //将菜单项目添加到菜单
        shuqian.add(shuqianAdd);   //将菜单项目添加到菜单
        shuqian.add(shuqianOpen);   //将菜单项目添加到菜单
        shuqian.add(all);   //将菜单项目添加到菜单
        shuqian.add(open);   //将菜单项目添加到菜单
        shuqian.add(openCache);   //将菜单项目添加到菜单
        JMenuBar br = new JMenuBar();  //创建菜单工具栏
        br.add(jm);      //将菜单增加到菜单工具栏
        br.add(set);      //将菜单增加到菜单工具栏
        br.add(shuqian);      //将菜单增加到菜单工具栏
        this.setJMenuBar(br);  //为 窗体设置  菜单工具栏
    }

    /**
     * 打开缓存
     */
    private void openCache() {
        jChooser = new JFileChooser();
        jChooser.setCurrentDirectory(new File(""));//设置默认打开路径
        jChooser.setDialogType(JFileChooser.OPEN_DIALOG);//设置保存对话框
        jChooser.showDialog(this, "打开缓存");
        File file = jChooser.getSelectedFile();
        if (file != null) {
            Map<String, UserInfo> map = recovery.open(file);
            for (UserInfo user : map.values()) {
                user.setLogin(false);
                tabPanel.addPanel(user);
            }
        }
    }

    /**
     * 设置名称
     */
    private void setName() {
        String name = (String) JOptionPane.showInputDialog(null, "请输入窗口名称：/n", "title", JOptionPane.PLAIN_MESSAGE, null, null, "在这输入");
        trayicon.setToolTip(name);
    }

    private void open() {
        jChooser = new JFileChooser();
        jChooser.setCurrentDirectory(new File(""));//设置默认打开路径
        jChooser.setDialogType(JFileChooser.OPEN_DIALOG);//设置保存对话框
        jChooser.showDialog(this, "打开所有");
        File file = jChooser.getSelectedFile();
        if (file != null) {
            try {
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
                LinkedList<UserInfo> list = (LinkedList<UserInfo>) stream.readObject();
                for (UserInfo user : list) {
                    user.setLogin(true);
                    tabPanel.addPanel(user);
                }
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(this, "打开失败!" + e.toString());
                e.printStackTrace();
            }
        }
    }

    private void all() {
        jChooser = new JFileChooser();
        jChooser.setCurrentDirectory(new File(""));//设置默认打开路径
        jChooser.setDialogType(JFileChooser.SAVE_DIALOG);//设置保存对话框
        jChooser.showDialog(this, "保存书签");
        File file = jChooser.getSelectedFile();
        if (file != null) {
            try {
                LinkedList<UserInfo> list = new LinkedList<>();
                for (HtmlPanel htmlPanel : htmlPanels) {
                    if (htmlPanel.user.getName() != null
                            && htmlPanel.user.getDaqu() != null
                            && !htmlPanel.isWait) {
                        list.add(htmlPanel.user);
                    }
                }
                if (list.size() > 0) {
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                    out.writeObject(list);
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
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
        exec.submit(() -> {
            UserInfo user = new UserInfo();
            user.setName(name);
            user.setUrl(url);
            tabPanel.addPanel(user);
        });
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

    /**
     * 添加书签
     */
    private void shuqianAdd() {
        shuQianDialog.setVisible(true);
    }

    private void shuqianOpen() {
        qianOpenDialog.setVisible(true);
    }

    private void scriptPath() {
        sysSetDialog.setVisible(true);
    }

    /**
     * 添加书签
     *
     * @param s
     */
    public void addShuQian(String s) {
        qianOpenDialog.addShuQian(s);
    }

    public void openShuQian(String line) {
        String[] lines = line.split("###");
        exec.submit(() -> {
            UserInfo user = new UserInfo();
            user.setLogin(true);
            user.setName(lines[0]);
            user.setUrl(lines[2]);
            user.setDaqu(lines[1]);
            tabPanel.addPanel(user);
        });

    }

    public void addHtmlPanel(HtmlPanel panel) {
        htmlPanels.add(panel);
    }

    public void addCache(String name, UserInfo userInfo) {
        recovery.addCache(name, userInfo);
        logger.error("内存占用->>"+(Runtime.getRuntime().totalMemory()>>20));
    }
}
