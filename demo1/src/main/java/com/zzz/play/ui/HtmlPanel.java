package com.zzz.play.ui;

import com.zzz.play.inter.impl.GlobalObserver;
import com.zzz.play.setp.impl.BaseStep;
import com.zzz.play.test.Test;
import com.zzz.play.util.GlobalUtil;
import com.zzz.play.util.HtmlContent;
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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class HtmlPanel extends JFXPanel {

    private static final String url = "http://xfhero1.yytou.com/gCmd.do?cmd=7&sid=6r8sp03bnb2zcs8bodvqp";

    public static final int WIDTH = 300;
    public static final int HEIGHT = 500;

    private HtmlContent content;
    private GlobalUtil globalUtil;

    private MainWindow mainWindow;

    private WebView view;
    private Button stop;
    private Button script;

    public HtmlPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.setLayout(null);
        run();

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
                try {
                    //组装各个对象
                    assemble(urlTextField);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Test.run(content, MainWindow.scripts, globalUtil);
            });
            stop.setOnAction(event -> stopGoon());
            script.setOnAction(event -> script());
        });
    }

    /**
     * 弹出脚本功能窗口
     */
    private void script() {
        Test.loadParse();
    }

    /**
     * 暂停或继续
     */
    public void stopGoon() {
        Platform.runLater(() -> {
            if (BaseStep.IS_WAIT) {
                WebEngine engine = view.getEngine();
                String location = engine.getLocation();
                if (location != null && !"".equals(location)) {
                    System.out.println(location);
                    content.linkUrl(location);
                }
                BaseStep.IS_WAIT = false;
                stop.setText("stop");
                BaseStep.anotify();
            } else {
                BaseStep.IS_WAIT = true;
                stop.setText("go on");
            }
        });
    }

    public void assemble(TextField urlTextField) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        globalUtil = new GlobalUtil();
        content = HtmlContent.initHtmlContent(urlTextField.getText(), mainWindow, globalUtil);
        GlobalObserver globalObserver = new GlobalObserver(globalUtil);
        content.addObserver(globalObserver);
    }

}
