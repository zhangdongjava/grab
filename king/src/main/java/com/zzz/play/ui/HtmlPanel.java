package com.zzz.play.ui;

import com.zzz.play.core.CoreController;
import com.zzz.play.inter.impl.GlobalObserver;
import com.zzz.play.ui.dialog.ScriptDialog;
import com.zzz.play.util.*;
import com.zzz.play.util.page.ClearUtil;
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
    public static Integer DEFAULT_WAIT = 538;
    public HtmlContent content;
    public GlobalUtil globalUtil;
    public WebView view;
    private Button stop;
    private Button go;
    private Button script;
    private Label showTime;
    //字体大小
    private TextField fontVal;
    //刷新间隔
    private TextField interval;
    //设置刷新间隔字体大小按钮
    private Button setBtn;
    //是否显示日志按钮
    private Button logBtn;
    //加载地址
    private Button loadBtn;
    //核心控制器
    public CoreController controller;
    //工具载体
    public UtilDto utilDto;
    //脚本选择弹窗
    private ScriptDialog scriptDialog;
    //主窗口
    public MainWindow mainWindow;
    //脚本集合
    public LinkedList<String> scripts;
    private Object startLock = new Object();
    private TextField urlTextField;
    private static ExecutorService exec = Executors.newFixedThreadPool(10);
    public boolean isWait = false;
    public boolean ruing = false;
    public String shuQianUrl;
    public LoginUtil loginUtil;
    /**
     * 角色名称
     */
    public String name;
    /**
     * 大区名称
     */
    public String daqu;

    public HtmlPanel(String url, MainWindow mainWindow, String name, String daqu, LinkedList<String> script) throws Exception {
        this.mainWindow = mainWindow;
        this.name = name;
        this.daqu = daqu;
        this.shuQianUrl = url;
        this.scripts = script;
        this.setLayout(null);
        this.url = url;
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
            fontVal.setText("0.9");
            interval = new TextField();
            interval.setPrefWidth(50);
            interval.setPromptText("间隔");
            interval.setText(DEFAULT_WAIT.toString());
            setBtn = new Button("设置");
            loadBtn = new Button("→");
            Group root = new Group();
            view.setFontScale(0.9);
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
            stop = new Button("stop");
            stop.setDisable(true);
            script = new Button("脚本");
            logBtn = new Button("log on");
            urlTextField.setPrefWidth(WIDTH - 20);
            box1.getChildren().addAll(urlTextField, loadBtn);
            box2.getChildren().addAll(go, stop, script, logBtn);
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
            stop.setOnAction(event -> stopGoon());
            script.setOnAction(event -> script());
            setBtn.setOnAction(event -> setProperty());
            logBtn.setOnAction(event -> logSet());
            loadBtn.setOnAction(event -> loadBtn());
            HtmlPanel.this.init();
        });
        joinGame();
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
                logBtn.setText("log on");
            } else {
                content.printLog = !content.printLog;
                logBtn.setText("log off");
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
        if (scripts.isEmpty()) {
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
            stop.setDisable(false);
        });
    }

    /**
     * 弹出脚本功能窗口
     */
    private void script() {
        scriptDialog.showUi();
    }

    String text = null;

    /**
     * 暂停或继续
     */
    public void stopGoon() {

        if (utilDto.waitNotfiy.wait) {
            isWait = false;
            utilDto.waitNotfiy.wait = false;
            WebEngine engine = view.getEngine();
            String location = engine.getLocation();
            if (location != null && !"".equals(location)) {
                content.linkUrl(location);
            }
            text = ("stop");
            System.out.println("开始唤醒!");
            utilDto.waitNotfiy.anotfiy();
            System.out.println("唤醒成功!");
        } else {
            utilDto.waitNotfiy.wait = true;
            isWait = true;
            text = ("go on");
        }
        Platform.runLater(() -> stop.setText(text));
    }

    public void init() {
        if (scripts == null) {
            scripts = new LinkedList<>();
        }
        scriptDialog = new ScriptDialog(this);
        for (String s : scripts) {
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
        exec.submit(() -> {
            synchronized (startLock) {
                try {
                    startLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (daqu != null) {
                try {
                    loginUtil.login();
                    if (!scripts.isEmpty()) {
                        reloadScript();
                        goScript();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
     * 添加脚本文件地址
     *
     * @param url
     */
    public void addScript(String url) {
        scripts.add(url);
    }

    /**
     * 清空脚本
     */
    public void cleatScript() {
        scripts.clear();
    }

    public void reloadScript() {
        controller.files = this.scripts;
        controller.loadParse();
    }
}
