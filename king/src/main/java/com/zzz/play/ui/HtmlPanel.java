package com.zzz.play.ui;

import com.zzz.play.bean.UserInfo;
import com.zzz.play.core.CoreController;
import com.zzz.play.inter.impl.GlobalObserver;
import com.zzz.play.ui.dialog.ScriptDialog;
import com.zzz.play.util.*;
import com.zzz.play.util.page.ClearUtil;
import com.zzz.play.util.resource.Resource;
import com.zzz.play.util.sys.LoginUtil;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class HtmlPanel extends JPanel {

    private String url;
    public static final int WIDTH = MainWindow.width - 18;
    public static final int HEIGHT = MainWindow.height - 62;
    public static Integer DEFAULT_WAIT = Resource.WAIT;
    public HtmlContent content;
    public GlobalUtil globalUtil;
    public HtmlView view;
    private JButton pause;
    //private Button closeBtn;
    private JButton kill;
    private JButton go;
    //private Button go2;
    private JButton script;
    // private Button script2;
    private JLabel showTime;
    //字体大小
    private JTextField fontVal;
    //刷新间隔
    private JTextField interval;
    //设置刷新间隔字体大小按钮
    private JButton setBtn;
    //是否html刷新
    private JButton logBtn;
    //加载地址
    private JButton loadBtn;
    //核心控制器
    public CoreController controller;
    //工具载体
    public UtilDto utilDto;
    //脚本选择弹窗
    private ScriptDialog scriptDialog;
    private TabPanel tabPanel;
    //主窗口
    public MainWindow mainWindow;
    private Object startLock = new Object();
    private JTextField urlTextField;
    public boolean isWait = false;
    public boolean ruing = false;
    public UserInfo user;
    public LoginUtil loginUtil;

    public boolean isClose;


    public HtmlPanel(TabPanel tabPanel, UserInfo user, MainWindow mainWindow) throws Exception {
        this.tabPanel = tabPanel;
        this.mainWindow = mainWindow;
        this.setLayout(null);
        this.setBounds(0, 0, WIDTH, HEIGHT);
        this.user = user;
        this.url = user.getUrl();
        run();
    }


    public void setHtml(String html) {
        System.out.println(html);
        view.setHtml(html);
    }

    public void setShowTime(long time,String url) {
        urlTextField.setText(url);
        showTime.setText(Long.toString(time));
    }


    public void run() throws Exception {
        view = new HtmlView(WIDTH, HEIGHT - 90);
        showTime = new JLabel("showTime");
        fontVal = new JTextField();
        fontVal.setText(Double.toString(Resource.FONT_SIZE));
        interval = new JTextField();
        interval.setText(DEFAULT_WAIT.toString());
        setBtn = new JButton("设置");
        kill = new JButton("停止");
        loadBtn = new JButton("→");
        loginUtil = new LoginUtil(this);
        urlTextField = new JTextField();
        go = new JButton("go");
        // go2 = new Button("go2");
        pause = new JButton("pause");
        // closeBtn = new Button("关闭");
        pause.setEnabled(false);
        script = new JButton("脚本");
        // script2 = new Button("脚本2");
        logBtn = new JButton("ui on");

        go.addActionListener(event -> goScript());
        // go2.setOnAction(event -> goScript2());
        pause.addActionListener(event -> pauseGoon());
        script.addActionListener(event -> script(user.getScritps1()));
        // script2.setOnAction(event -> script(user.getScritps2()));
        setBtn.addActionListener(event -> setProperty());
        logBtn.addActionListener(event -> logSet());
        loadBtn.addActionListener(event -> loadBtn());
        kill.addActionListener(event -> kill());
        // closeBtn.setOnAction(event -> close());
        //第一排
        urlTextField.setBounds(0, 0, WIDTH - 100, 30);
        showTime.setBounds(WIDTH - 80, 0, 70, 30);
        //第二排
        go.setBounds(0, 30, 60, 30);
        pause.setBounds(70, 30, 80, 30);
        script.setBounds(160, 30, 60, 30);
        logBtn.setBounds(230, 30, 80, 30);
        kill.setBounds(320, 30, 60, 30);
        //第三排
        fontVal.setBounds(0, 60, 60, 30);
        interval.setBounds(70, 60, 60, 30);
        setBtn.setBounds(140, 60, 60, 30);
        //html显示
        view.setBounds(0, 90, WIDTH, HEIGHT - 90);
        this.add(urlTextField);
        this.add(showTime);
        this.add(go);
        this.add(pause);
        this.add(script);
        this.add(logBtn);
        this.add(kill);
        this.add(fontVal);
        this.add(interval);
        this.add(setBtn);
        this.add(view);
        HtmlPanel.this.init();
        joinGame();
    }


    /**
     * 停止当前运行脚本线程
     */
    private void kill() {
        isClose = false;
        controller.kill();
    }

    /**
     * 运行脚本停止
     */
    public void killed() {
        System.out.println(this.user.getName() + "->>脚本终止!");
        go.setEnabled(true);
        pause.setEnabled(false);
        utilDto.waitNotfiy.wait = false;
        pause.setText("pause");
        JOptionPane.showConfirmDialog(mainWindow, user.getName() + "-->脚本停止!");
    }

    /**
     * 加载地址
     */
    private void loadBtn() {
        content.linkUrl(urlTextField.getText());
    }

    private void logSet() {
        if (content.printLog) {
            content.printLog = !content.printLog;
            logBtn.setText("ui on");
        } else {
            content.printLog = !content.printLog;
            logBtn.setText("ui off");
        }
    }

    private void setProperty() {
        int in = Integer.valueOf(interval.getText());
        content.TIME_WAIT = in;
    }

    private void goScript() {

        if (user.getScritps1().isEmpty()) {
            JOptionPane.showConfirmDialog(mainWindow, "没有选择脚本!");
            return;
        }
        ruing = true;
        String location = view.url;
        if (location != null && !"".equals(location)) {
            content.setBaseUrl(location);
            content.linkUrl(location);
        }
        controller.run(content);
        go.setEnabled(false);
        pause.setEnabled(true);
    }

    /**
     * 弹出脚本功能窗口
     */
    private void script(LinkedList<String> scripts) {
        scriptDialog.showUi(scripts);
    }

    String text = null;

    /**
     * 暂停或继续
     */
    public void pauseGoon() {

        if (utilDto.waitNotfiy.wait) {
            isWait = false;
            utilDto.waitNotfiy.wait = false;
            String location = view.url;
            if (location != null && !"".equals(location)) {
                content.linkUrl(location);
            }
            text = ("pause");
            System.out.println("开始唤醒!");
            utilDto.waitNotfiy.anotfiy();
            System.out.println("唤醒成功!");
        } else {
            utilDto.waitNotfiy.wait = true;
            isWait = true;
            text = ("go on");
        }
        pause.setText(text);
    }

    public void init() {
        if (user.getScritps1() == null) {
            user.setScritps1(new LinkedList<>());
        }
        if (user.getScritps2() == null) {
            user.setScritps2(new LinkedList<>());
        }
        scriptDialog = new ScriptDialog(this);
        for (String s : user.getScritps1()) {
            scriptDialog.addScript(s);
        }
        utilDto = new UtilDto();
        utilDto.waitNotfiy = new WaitNotfiy();
        utilDto.varUtil = new VarUtil();
        utilDto.clearUtil = new ClearUtil();
        globalUtil = new GlobalUtil();
        controller = new CoreController(globalUtil, utilDto);
        controller.globalUtil = globalUtil;
        controller.htmlPanel = this;
        controller.user = user;
        try {
            assemble();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 加入游戏 等待页面初始化唤醒
     */
    private void joinGame() {
        ThreadPoolUtil.addThread(() -> {
            //如果需要登录就登录
            if (user.isLogin()) {
                try {
                    loginUtil.login();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //主脚本不为空就自动运行
            if (!user.getScritps1().isEmpty()) {
                controller.loadParse();
                goScript();
            }
        });
    }

    public void assemble() throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        content = new HtmlContent(user.getUrl(), this, controller);
        controller.content = content;

        GlobalObserver globalObserver = new GlobalObserver(globalUtil);
        controller.addObserver(globalObserver);
    }

    /**
     * 更新脚本
     *
     * @param
     */
    public void resetScript() {
        controller.loadParse();
    }

    /**
     * 将地址缓存上用于临时恢复
     *
     * @param url
     */
    public void addCache(String url) {
        user.setCurrUrl(url);
        mainWindow.addCache(user.getName(), user.copy());
    }
}
