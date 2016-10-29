package ui;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class HtmlPanel extends JEditorPane implements HyperlinkListener {

    public HtmlPanel() {
        this.setLayout(null);
        //设置是显示网页 html 文件,可选项
        this.setContentType("text/html");
        //设置成只读，如果是可编辑，你会看到显示的样子也是不一样的，true显示界面
        this.setEditable(false);
        //要能响应网页中的链接，则必须加上超链监听器
        this.addHyperlinkListener(this);
    }

    public void setHtml(String html) {
        this.setText(html);
    }

    @Override
    public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            JEditorPane pane = (JEditorPane) e.getSource();
            if (e instanceof HTMLFrameHyperlinkEvent) {
                HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) e;
                HTMLDocument doc = (HTMLDocument) pane.getDocument();
                doc.processHTMLFrameHyperlinkEvent(evt);
            } else {
                try {
                    System.out.println(e.getURL());
                    pane.setPage(e.getURL());
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
    }
}
