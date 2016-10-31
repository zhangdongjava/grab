package ui;

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
import test.Test;
import util.HtmlContent;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class HtmlPanel extends JFXPanel {

    private static final String urlStart = "http://";

    private static final int WIDTH = 360;
    private static final int HEIGHT = 560;

    private  MainWindow mainWindow;

    private   WebView view;

    public HtmlPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.setLayout(null);
        run();

    }

    public void setHtml(String html) {
        Platform.runLater(() -> view.getEngine().loadContent(html));
    }


    public void run(){
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
            urlTextField.setText("");
            Button go = new Button("go");
            urlTextField.setPrefWidth(WIDTH - 70);
            urlBox.getChildren().addAll(urlTextField, go);
            view.setMinSize(widthDouble, heightDouble);
            view.setPrefSize(widthDouble, heightDouble);
            final WebEngine eng = view.getEngine();
            root.getChildren().add(view);
            box.getChildren().add(urlBox);
            box.getChildren().add(view);
            root.getChildren().add(box);
            go.setOnAction(event -> {
                HtmlContent content = HtmlContent.initHtmlContent("http://xfhero45.yytou.com/gCmd.do?cmd=575&sid=4q0txn3blvkfn7hquvl7m", mainWindow);
                Test.run(content,MainWindow.scripts);
            });
        });
    }

}
