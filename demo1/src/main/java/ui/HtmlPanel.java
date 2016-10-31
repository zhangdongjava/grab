package ui;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.TouchPoint;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import setp.impl.BaseStep;
import test.Test;
import util.HtmlContent;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class HtmlPanel extends JFXPanel {

    private static final String urlStart = "http://";

    public static final int WIDTH = 300;
    public static final int HEIGHT = 500;

    private HtmlContent content;

    private MainWindow mainWindow;

    private WebView view;
    private Button stop;

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
            urlTextField.setText("http://xfhero45.yytou.com/gCmd.do?cmd=565b&sid=4q0txn3blvkfn7hquvl7m");
            Button go = new Button("go");
            stop = new Button("stop");
            urlTextField.setPrefWidth(WIDTH - 150);
            urlBox.getChildren().addAll(urlTextField, go, stop);
            view.setMinSize(widthDouble, heightDouble - 100);
            view.setMaxSize(widthDouble, heightDouble - 50);
            view.setPrefSize(widthDouble, heightDouble - 50);
            root.getChildren().add(view);
            box.getChildren().add(urlBox);
            box.getChildren().add(view);
            root.getChildren().add(box);
            go.setOnAction(event -> {
                content = HtmlContent.initHtmlContent(urlTextField.getText(), mainWindow);
                Test.run(content, MainWindow.scripts);
            });
            stop.setOnAction(event -> stopGoon());
        });
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

}
