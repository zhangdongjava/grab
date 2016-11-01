package com.zzz.play.ui;

import com.zzz.play.inter.impl.GlobalObserver;
import com.zzz.play.setp.impl.BaseStep;
import com.zzz.play.test.Lunch;
import com.zzz.play.util.GlobalUtil;
import com.zzz.play.util.HtmlContent;
import com.zzz.play.util.UtilDto;
import com.zzz.play.util.WaitNotfiy;
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

    private String url = "http://xfhero1.yytou.com/gCmd.do?cmd=7&sid=6r8sp03bnb2zcs8bodvqp";

    public static final int WIDTH = 300;
    public static final int HEIGHT = 500;

    public HtmlContent content;
    public GlobalUtil globalUtil;

    private WebView view;
    private Button stop;
    private Button script;

    public Lunch lunch;

    public UtilDto utilDto;

    private ScriptDialog scriptDialog;
    public MainWindow mainWindow;

    private LinkedList<String> scripts;

    public HtmlPanel(String url, MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.setLayout(null);
        this.url = url;
        scripts = new LinkedList<>();
        scriptDialog = new ScriptDialog(this);
        run();
        lunch = new Lunch();
    }

    public void setHtml(String html) {
        Platform.runLater(() -> view.getEngine().loadContent(html));
    }


    public void run() {
        Platform.runLater(() -> {
            view = new WebView();

            Group root = new Group();
            Scene scene1 = new Scene(root, WIDTH, HEIGHT);
            HtmlPanel.this.setScene(scene1);
            Double widthDouble = new Integer(WIDTH).doubleValue();
            Double heightDouble = new Integer(HEIGHT).doubleValue();
            VBox box = new VBox(10);
            HBox urlBox = new HBox(10);
            final TextField urlTextField = new TextField();
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
            go.setOnAction(event -> {
                if (scripts.isEmpty()) {
                    JOptionPane.showConfirmDialog(mainWindow, "没有选择脚本!");
                    return;
                }
                try {
                    //组装各个对象
                    //if(MainWindow.count<2)
                    assemble(urlTextField);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lunch.run(content, scripts, globalUtil, utilDto);
                go.setVisible(false);
            });
            stop.setOnAction(event -> stopGoon());
            script.setOnAction(event -> script());
        });
    }

    /**
     * 弹出脚本功能窗口
     */
    private void script() {
        scriptDialog.setVisible(true);
    }

    /**
     * 暂停或继续
     */
    public void stopGoon() {
        Platform.runLater(() -> {
            if (utilDto.waitNotfiy.wait) {
                WebEngine engine = view.getEngine();
                String location = engine.getLocation();
                if (location != null && !"".equals(location)) {
                    System.out.println(location);
                    content.linkUrl(location);
                }
                utilDto.waitNotfiy.wait = false;
                stop.setText("stop");
                utilDto.waitNotfiy.anotfiy();
            } else {
                utilDto.waitNotfiy.wait = true;
                stop.setText("go on");
            }
        });
    }

    public void assemble(TextField urlTextField) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        globalUtil = new GlobalUtil();
        utilDto = new UtilDto();
        utilDto.waitNotfiy = new WaitNotfiy();
        content = HtmlContent.initHtmlContent(urlTextField.getText(), this, globalUtil);
        GlobalObserver globalObserver = new GlobalObserver(globalUtil);
        content.addObserver(globalObserver);
    }

    /**
     * 添加脚本文件地址
     * @param url
     */
    public void addScript(String url) {
        scripts.add(url);
    }

    /**
     * 清空脚本
     */
    public void cleatScript(){
        scripts.clear();
    }

    public void reloadScript(){
        lunch.files = this.scripts;
        lunch.loadParse();
    }
}