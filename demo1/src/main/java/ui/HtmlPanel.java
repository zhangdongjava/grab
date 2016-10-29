package ui;

import javax.swing.*;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class HtmlPanel extends JEditorPane {

    public HtmlPanel() {
        this.setLayout(null);
        //设置是显示网页 html 文件,可选项
        this.setContentType("text/html");
        //设置成只读，如果是可编辑，你会看到显示的样子也是不一样的，true显示界面
        this.setEditable(false);
    }

    public void setHtml(String html) {
        this.setText(html);
    }
}
