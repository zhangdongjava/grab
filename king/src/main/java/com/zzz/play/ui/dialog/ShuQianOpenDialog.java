package com.zzz.play.ui.dialog;

import com.zzz.play.ui.MainWindow;
import com.zzz.play.util.Resource;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * 书签打开弹窗
 */
public class ShuQianOpenDialog extends JDialog {

    JButton ok = new JButton("确定");
    JButton cancel = new JButton("取消");
    private static int height = 500;
    private MainWindow parent;
    private JList<String> jList;
    private DefaultListModel defaultListModel;

    private static String line="----------------------------------------------------";

    public ShuQianOpenDialog(MainWindow parent) {
        super(parent);
        this.parent = parent;
        this.setModal(true);
        this.setLayout(null);
        this.setSize(400, height);
        ok.setBounds(100, height - 100, 80, 30);
        cancel.setBounds(200, height - 100, 80, 30);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ShuQianOpenDialog.this.setVisible(false);
            }
        });
        ok.addActionListener((e) -> openShuQian());
        cancel.addActionListener((e) -> cancel());
        this.setLocationRelativeTo(null);

        this.add(ok);
        this.add(cancel);
        initList();
    }

    private void initList() {
        defaultListModel = new DefaultListModel();
        jList = new JList<>(defaultListModel);
        jList.setBounds(0,0,350,height-120);
        this.add(jList);
        Resource.load();
        Properties properties = Resource.shuqian;
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            if(entry.getKey().toString().startsWith("--")){
                defaultListModel.addElement(line);
            }else{
                System.out.println(entry.getKey() + "->" + entry.getValue());
                defaultListModel.addElement(entry.getKey() + "###" + entry.getValue());
            }

        }
    }

    public void addShuQian(String line){
        defaultListModel.addElement(line);
    }

    /**
     * 打开书签
     */
    private void openShuQian() {
        String line = jList.getSelectedValue();
        cancel();
        parent.openShuQian(line);
    }

    private void cancel() {
        this.setVisible(false);
    }

}