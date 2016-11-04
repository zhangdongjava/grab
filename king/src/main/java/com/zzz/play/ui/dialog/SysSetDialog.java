package com.zzz.play.ui.dialog;

import com.zzz.play.ui.MainWindow;
import com.zzz.play.util.Resource;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class SysSetDialog extends JDialog {
    JLabel label1 = new JLabel("脚本路径:");
    JTextField scriptPath = new JTextField();
    JButton ok = new JButton("确定");
    JButton cancel = new JButton("取消");
    private MainWindow parent;

    public SysSetDialog(MainWindow parent) {
        super(parent);
        this.parent = parent;
        this.setModal(true);
        this.setLayout(null);
        this.setSize(400, 300);
        label1.setBounds(50, 30, 100, 50);
        scriptPath.setBounds(156, 30, 200, 30);
        ok.setBounds(100, 200, 80, 50);
        cancel.setBounds(200, 200, 80, 50);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SysSetDialog.this.setVisible(false);
            }
        });
        ok.addActionListener((e) -> addXs());
        cancel.addActionListener((e) -> cancel());
        this.setLocationRelativeTo(null);
        this.add(label1);
        this.add(scriptPath);
        this.add(ok);
        this.add(cancel);
    }

    private void addXs() {
        try {
            Resource.add(Resource.bootPathName, scriptPath.getText());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showConfirmDialog(parent, "保存失败!" + e.toString());
        }
        cancel();

    }

    private void cancel() {
        this.setVisible(false);
    }

}