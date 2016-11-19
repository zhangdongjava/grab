package com.zzz.play.ui;

import com.zzz.play.bean.UserInfo;
import com.zzz.play.core.CoreController;
import com.zzz.play.inter.impl.GlobalObserver;
import com.zzz.play.ui.dialog.ScriptDialog;
import com.zzz.play.util.*;
import com.zzz.play.util.page.ClearUtil;
import com.zzz.play.util.resource.Resource;
import com.zzz.play.util.sys.LoginUtil;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class HtmlPanel extends JFXPanel {

    private String url;
    public static final int WIDTH = MainWindow.width - 100;
    public static final int HEIGHT = MainWindow.height - 100;
    public static Integer DEFAULT_WAIT = Resource.WAIT;
    public HtmlContent content;
    public GlobalUtil globalUtil;
    public WebView view;
    private Button pause;
    //private Button closeBtn;
    private Button kill;
    private Button go;
    //private Button go2;
    private Button script;
   // private Button script2;
    private Label showTime;
    //字体大小
    private TextField fontVal;
    //刷新间隔
    private TextField interval;
    //设置刷新间隔字体大小按钮
    private Button setBtn;
    //是否html刷新
    private Button logBtn;
    //加载地址
    private Button loadBtn;
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
    private TextField urlTextField;
    public boolean isWait = false;
    public boolean ruing = false;
    public UserInfo user;
    public LoginUtil loginUtil;

    public boolean isClose;


    public HtmlPanel(TabPanel tabPanel, UserInfo user, MainWindow mainWindow) throws Exception {
        this.tabPanel = tabPanel;
        this.mainWindow = mainWindow;
        this.setLayout(null);
        this.user = user;
        this.url = user.getUrl();
        run();
    }


    public void setHtml(String html) {
        Platform.runLater(() -> view.getEngine().loadContent(html));
    }

    public void setShowTime(long time) {
        Platform.runLater(() -> showTime.setText(Long.toString(time)));
    }


    public void run() throws InterruptedException {
        Platform.runLater(() -> {
            view = new WebView();
            showTime = new Label();
            fontVal = new TextField();
            fontVal.setPrefWidth(40);
            fontVal.setPromptText("字体");
            fontVal.setText(Double.toString(Resource.FONT_SIZE));
            interval = new TextField();
            interval.setPrefWidth(50);
            interval.setPromptText("间隔");
            interval.setText(DEFAULT_WAIT.toString());
            setBtn = new Button("设置");
            kill = new Button("停止");
            loadBtn = new Button("→");
            Group root = new Group();
            view.setFontScale(Resource.FONT_SIZE);
            loginUtil = new LoginUtil(this);
            Scene scene1 = new Scene(root, WIDTH, HEIGHT);
            HtmlPanel.this.setScene(scene1);
            Double widthDouble = new Integer(WIDTH).doubleValue();
            Double heightDouble = new Integer(HEIGHT).doubleValue();
            VBox box = new VBox(10);
            HBox box1 = new HBox(10);
            HBox box2 = new HBox(10);
            HBox box3 = new HBox(10);
            urlTextField = new TextField();
            urlTextField.setText(url);
            view.getEngine().load(url);
            go = new Button("go");
           // go2 = new Button("go2");
            pause = new Button("pause");
            // closeBtn = new Button("关闭");
            pause.setDisable(true);
            script = new Button("脚本");
           // script2 = new Button("脚本2");
            logBtn = new Button("ui on");
            urlTextField.setPrefWidth(WIDTH - 20);
            box1.getChildren().addAll(urlTextField, loadBtn);
            box2.getChildren().addAll(go, script, pause, logBtn, kill);
            box3.getChildren().addAll(fontVal, interval, setBtn, showTime);
            view.setMinSize(widthDouble, heightDouble - 100);
            view.setMaxSize(widthDouble, heightDouble - 50);
            view.setPrefSize(widthDouble, heightDouble - 50);
            box.getChildren().add(box1);
            box.getChildren().add(box2);
            box.getChildren().add(box3);
            box.getChildren().add(view);
            root.getChildren().add(box);
            go.setOnAction(event -> goScript());
           // go2.setOnAction(event -> goScript2());
            pause.setOnAction(event -> pauseGoon());
            script.setOnAction(event -> script(user.getScritps1()));
           // script2.setOnAction(event -> script(user.getScritps2()));
            setBtn.setOnAction(event -> setProperty());
            logBtn.setOnAction(event -> logSet());
            loadBtn.setOnAction(event -> loadBtn());
            kill.setOnAction(event -> kill());
            // closeBtn.setOnAction(event -> close());
            HtmlPanel.this.init();
        });
        joinGame();
    }

//    private void goScript2() {
//
//        if (user.getScritps2().isEmpty()) {
//            JOptionPane.showConfirmDialog(mainWindow, "没有选择脚本!");
//            return;
//        }
//        kill();
//        ruing = true;
//        WebEngine engine = view.getEngine();
//        String location = engine.getLocation();
//        if (location != null && !"".equals(location)) {
//            System.out.println(location);
//            content.setBaseUrl(location);
//            content.linkUrl(location);
//        }
//        controller.run2(content);
//        Platform.runLater(() -> {
//           // go2.setDisable(true);
//            pause.setDisable(false);
//        });
//    }


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
        Platform.runLater(() -> {
            go.setDisable(false);
            pause.setDisable(true);
            utilDto.waitNotfiy.wait = false;
            pause.setText("pause");
        });
        JOptionPane.showConfirmDialog(mainWindow,user.getName()+"-->脚本停止!");
    }

    /**
     * 加载地址
     */
    private void loadBtn() {
        content.linkUrl(urlTextField.getText());
    }

    private void logSet() {
        Platform.runLater(() -> {
            if (content.printLog) {
                content.printLog = !content.printLog;
                logBtn.setText("ui on");
            } else {
                content.printLog = !content.printLog;
                logBtn.setText("ui off");
            }
        });
    }

    private void setProperty() {
        Platform.runLater(() -> {
            double font = Double.valueOf(fontVal.getText());
            view.setFontScale(font);
            int in = Integer.valueOf(interval.getText());
            content.TIME_WAIT = in;
        });
    }

    private void goScript() {

        if (user.getScritps1().isEmpty()) {
            JOptionPane.showConfirmDialog(mainWindow, "没有选择脚本!");
            return;
        }
        ruing = true;
        WebEngine engine = view.getEngine();
        String location = engine.getLocation();
        if (location != null && !"".equals(location)) {
            System.out.println(location);
            content.setBaseUrl(location);
            content.linkUrl(location);
        }
        controller.run(content);
        Platform.runLater(() -> {
            go.setDisable(true);
            pause.setDisable(false);
        });
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
            WebEngine engine = view.getEngine();
            String location = engine.getLocation();
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
        Platform.runLater(() -> pause.setText(text));
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
        } finally {
            synchronized (startLock) {
                startLock.notifyAll();
            }
        }
    }

    /**
     * 加入游戏 等待页面初始化唤醒
     */
    private void joinGame() {
        ThreadPoolUtil.addThread(() -> {
            synchronized (startLock) {
                try {
                    startLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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
        content = new HtmlContent(urlTextField.getText(), this, controller);
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
