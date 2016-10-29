package ui;

import javax.swing.*;
import java.util.Map;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class MainWindow extends JFrame {

    private  HtmlPanel htmlPanel;

    private JLabel jLabel = new JLabel("暂无物品!");

    public MainWindow(){
        this.setSize(520,700);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        htmlPanel = new HtmlPanel();
        htmlPanel.setBounds(0,100,360,560);
        jLabel.setBounds(5,5,650,40);
        this.add(htmlPanel);
        this.add(jLabel);
        this.setVisible(true);
    }

    public static void main(String[] args) {

    }

    public void setHtml(String html){
        htmlPanel.setHtml(html);
    }

    public void showGoods(Map<String, Integer> goods) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, Integer> entry : goods.entrySet()) {
            stringBuffer.append(entry.getKey()+":"+entry.getValue()+",");
        }
        if(stringBuffer.length()>0)
        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        jLabel.setText(stringBuffer.toString());
    }
}
