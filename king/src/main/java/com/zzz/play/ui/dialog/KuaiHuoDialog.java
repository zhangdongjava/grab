package com.zzz.play.ui.dialog;

import com.zzz.play.ui.MainWindow;
import com.zzz.play.util.resource.KuaiHuoResource;
import com.zzz.play.util.resource.Resource;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * 书签弹窗
 */
public class KuaiHuoDialog extends JDialog {
    JLabel label1 = new JLabel("主:");
    JTextField zhu = new JTextField();
    JLabel label2 = new JLabel("副:");
    JTextField fu = new JTextField();
    JButton ok = new JButton("确定");
    JButton cancel = new JButton("取消");
    private MainWindow parent;

    public KuaiHuoDialog(MainWindow parent) {
        super(parent);
        this.parent = parent;
        this.setModal(true);
        this.setLayout(null);
        this.setSize(400, 300);
        label1.setBounds(50, 30, 100, 50);
        zhu.setBounds(156, 30, 200, 30);
        label2.setBounds(50, 80, 100, 50);
        fu.setBounds(156, 80, 200, 30);
        ok.setBounds(100, 200, 80, 50);
        cancel.setBounds(200, 200, 80, 50);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                KuaiHuoDialog.this.setVisible(false);
            }
        });
        ok.addActionListener((e) -> addXs());
        cancel.addActionListener((e) -> cancel());
        this.setLocationRelativeTo(null);
        this.add(label1);
        this.add(label2);
        this.add(zhu);
        this.add(fu);
        this.add(ok);
        this.add(cancel);
    }

    private void addXs() {
        try {
            KuaiHuoResource.add(zhu.getText().trim(), fu.getText().trim());
            cancel();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showConfirmDialog(parent, "保存失败!" + e.toString());
        }
    }

    private void cancel() {
        this.setVisible(false);
    }

}