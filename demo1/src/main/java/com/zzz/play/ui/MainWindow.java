package com.zzz.play.ui;

import javax.swing.*;
import java.util.Map;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class MainWindow extends JFrame {

    private HtmlPanel htmlPanel;

    private JLabel jLabel = new JLabel("暂无物品!");

    public static String[] scripts = {"材料/血印分身蒙汗药", "材料/大白菜", "材料/柳虫"};

    public MainWindow() {
        this.setSize(320, 600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        htmlPanel = new HtmlPanel(this);
        htmlPanel.setBounds(0, 40, HtmlPanel.WIDTH, HtmlPanel.HEIGHT);
        jLabel.setBounds(5, 5, 650, 40);
        this.add(htmlPanel);
        this.add(jLabel);
        this.setVisible(true);
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//Nimbus风格，jdk6
        new MainWindow();
    }

    public void setHtml(String html) {
        htmlPanel.setHtml(html);
    }

    public void showGoods(Map<String, Integer> goods) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, Integer> entry : goods.entrySet()) {
            stringBuffer.append(entry.getKey() + ":" + entry.getValue() + ",");
        }
        if (stringBuffer.length() > 0)
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        jLabel.setText(stringBuffer.toString());
    }

    public void stopGoon() {
        htmlPanel.stopGoon();
    }
}
