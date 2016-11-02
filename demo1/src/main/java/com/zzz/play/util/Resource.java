package com.zzz.play.util;

import javax.swing.*;
import java.io.*;
import java.util.Properties;

/**
 * Created by dell_2 on 2016/11/2.
 */
public class Resource {

    private static String fileName = "system.txt";
    public static String bootPathName = "bootPath";

    public static String bootPath;
    public static Properties properties = new Properties();

    public static void load() {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                properties.load(new FileInputStream(file));
                Object val = properties.get(bootPathName);
                if (val != null) {
                    bootPath = val.toString();
                } else {
                    JOptionPane.showConfirmDialog(null, "请设置脚本路径!");
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(file + "文件不存在！");
            }
        }
    }

    public static void add(String name, String value) throws IOException {
        properties.put(name, value);
        File file = new File(fileName);
        FileOutputStream outputStream = new FileOutputStream(file);
        properties.store(outputStream, "系统设置");
    }
}
