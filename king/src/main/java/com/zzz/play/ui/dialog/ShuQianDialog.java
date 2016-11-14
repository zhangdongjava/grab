package com.zzz.play.ui.dialog;

import com.zzz.play.ui.MainWindow;
import com.zzz.play.util.resource.Resource;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * 书签弹窗
 */
public class ShuQianDialog extends JDialog {
    JLabel label1 = new JLabel("角色:");
    JTextField role = new JTextField();
    JLabel label2 = new JLabel("大区:");
    JTextField daqu = new JTextField();
    JLabel label3 = new JLabel("地址:");
    JTextField url = new JTextField();
    JButton ok = new JButton("确定");
    JButton cancel = new JButton("取消");
    private MainWindow parent;

    public ShuQianDialog(MainWindow parent) {
        super(parent);
        this.parent = parent;
        this.setModal(true);
        this.setLayout(null);
        this.setSize(400, 300);
        label1.setBounds(50, 30, 100, 50);
        role.setBounds(156, 30, 200, 30);
        label2.setBounds(50, 80, 100, 50);
        daqu.setBounds(156, 80, 200, 30);
        label3.setBounds(50, 130, 100, 50);
        url.setBounds(156, 130, 200, 30);
        ok.setBounds(100, 200, 80, 50);
        cancel.setBounds(200, 200, 80, 50);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ShuQianDialog.this.setVisible(false);
            }
        });
        ok.addActionListener((e) -> addXs());
        cancel.addActionListener((e) -> cancel());
        this.setLocationRelativeTo(null);
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(role);
        this.add(daqu);
        this.add(url);
        this.add(ok);
        this.add(cancel);
    }

    private void addXs() {
        try {
            Resource.addSq(url.getText(), daqu.getText(),role.getText());
            cancel();
            parent.addShuQian(role.getText()+"###"+daqu.getText()+"###"+url.getText());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showConfirmDialog(parent, "保存失败!" + e.toString());
        }
    }

    private void cancel() {
        this.setVisible(false);
    }

}