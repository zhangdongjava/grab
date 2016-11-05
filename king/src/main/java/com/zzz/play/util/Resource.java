package com.zzz.play.util;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by dell_2 on 2016/11/2.
 */
public class Resource {

    private static String fileName = "systemKing.txt";
    private static String SHU_QIAN_FILE_NAME = "shuQianKing.properties";
    public static String bootPathName = "bootPath";

    public static String bootPath;
    public static Properties properties = new Properties();
    /**
     * 书签
     */
    public static Properties shuqian = new Properties();


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
        loadShuQian();
    }

    public static void loadShuQian() {
        File file = new File(SHU_QIAN_FILE_NAME);
        if (file.exists()) {
            try {
                shuqian.load(new FileInputStream(file));
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
        bootPath = properties.get(bootPathName).toString();
    }

    /**
     * 添加书签
     *
     * @param url
     * @param qu
     * @param role
     * @throws IOException
     */
    public static void addSq(String url, String qu, String role) throws IOException {
        File file = new File(SHU_QIAN_FILE_NAME);
        shuqian.put(role, qu + "###" + url);
        FileOutputStream outputStream = new FileOutputStream(file);
        shuqian.store(outputStream, "书签列表");
    }

    public static void main(String[] args) throws IOException {

    }
}
