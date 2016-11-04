package com.zzz.play.ui;

import com.zzz.play.inter.impl.GlobalObserver;
import com.zzz.play.core.CoreController;
import com.zzz.play.util.*;
import com.zzz.play.util.page.ClearUtil;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class HtmlPanel extends JFXPanel {

    private String url;
    public static final int WIDTH = 300;
    public static final int HEIGHT = 500;
    public HtmlContent content;
    public GlobalUtil globalUtil;
    private WebView view;
    private Button stop;
    private Button script;
    public CoreController controller;
    public UtilDto utilDto;
    private ScriptDialog scriptDialog;
    public MainWindow mainWindow;
    private LinkedList<String> scripts;

    private TextField urlTextField;
    /**
     * 选项卡名称
     */
    public String name;

    public HtmlPanel(String url, MainWindow mainWindow) throws Exception {
        this.mainWindow = mainWindow;
        this.setLayout(null);
        this.url = url;
        run();
    }

    public void setHtml(String html) {
        Platform.runLater(() -> view.getEngine().loadContent(html));
    }


    public void run() {
        Platform.runLater(() -> {
            view = new WebView();
            Group root = new Group();
            view.setFontScale(0.8);
            Scene scene1 = new Scene(root, WIDTH, HEIGHT);
            HtmlPanel.this.setScene(scene1);
            Double widthDouble = new Integer(WIDTH).doubleValue();
            Double heightDouble = new Integer(HEIGHT).doubleValue();
            VBox box = new VBox(10);
            HBox urlBox = new HBox(10);
            urlTextField = new TextField();
            urlTextField.setText(url);
            Button go = new Button("go");
            stop = new Button("stop");
            script = new Button("脚本");
            urlTextField.setPrefWidth(WIDTH - 180);
            urlBox.getChildren().addAll(urlTextField, go, stop, script);
            view.setMinSize(widthDouble, heightDouble - 100);
            view.setMaxSize(widthDouble, heightDouble - 50);
            view.setPrefSize(widthDouble, heightDouble - 50);

            root.getChildren().add(view);
            box.getChildren().add(urlBox);
            box.getChildren().add(view);
            root.getChildren().add(box);
            go.setOnAction(event -> go(go));
            stop.setOnAction(event -> stopGoon());
            script.setOnAction(event -> script());
            HtmlPanel.this.init();
        });
    }

    private void go(Button go) {
        if (scripts.isEmpty()) {
            JOptionPane.showConfirmDialog(mainWindow, "没有选择脚本!");
            return;
        }
        WebEngine engine = view.getEngine();
        String location = engine.getLocation();
        if (location != null && !"".equals(location)) {
            System.out.println(location);
            content.linkUrl(location);
        }
        controller.run(content);
        go.setDisable(true);
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
            text = ("go on");
        }
        Platform.runLater(() -> stop.setText(text));
    }

    public void init() {
        scripts = new LinkedList<>();
        scriptDialog = new ScriptDialog(this);
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
        }
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
