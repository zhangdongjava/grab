package com.zzz.play.ui;

import com.zzz.play.util.resource.Resource;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import java.awt.*;

public class WebView extends JPanel implements HyperlinkListener {

    private JEditorPane editorPane;

    public String url;

    /**
     * 字体aaa
     */
    private Font font;

    public WebView(int w, int h) throws Exception {
        editorPane = new JEditorPane();
        font=new Font("宋体",Font.PLAIN, Resource.FONT_SIZE);
        this.setLayout(null);
        //放到滚动窗格中才能滚动查看所有内容
        JScrollPane scrollPane = new JScrollPane(editorPane);
        //设置是显示网页 html 文件,可选项
        editorPane.setContentType("text/html");
        //设置成只读，如果是可编辑，你会看到显示的样子也是不一样的，true显示界面
        editorPane.setEditable(false);
        //要能响应网页中的链接，则必须加上超链监听器
        editorPane.addHyperlinkListener(this);
        editorPane.setFont(font);
        //让editorPane总是填满整个窗体
        scrollPane.setBounds(0, 0, w, h);
        this.add(scrollPane);
    }


    public void setHtml(String html) {
        editorPane.setText(html);
    }


    //超链监听器，处理对超级链接的点击事件，但对按钮的点击还捕获不到
    public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            JEditorPane pane = (JEditorPane) e.getSource();
            if (e instanceof HTMLFrameHyperlinkEvent) {
                HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) e;
                HTMLDocument doc = (HTMLDocument) pane.getDocument();
                doc.processHTMLFrameHyperlinkEvent(evt);
            } else {
                try {
                    url = (e.getURL().toString());
                    pane.setPage(e.getURL());
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
    }

    public void setFont(int size){
        font =new Font("宋体",Font.PLAIN,size);
        editorPane.setFont(font);
    }

}  