package com.zzz.play.util;

import javax.swing.*;
import java.io.*;
import java.util.Properties;

/**
 * Created by dell_2 on 2016/11/2.
 */
public class Resource {

    private static String fileName = "systemKing.txt";
    private static String SHU_QIAN_FILE_NAME = "shuQianKing.properties";
    private static String UI = "ui.properties";
    public static String bootPathName = "bootPath";

    public static String bootPath;
    public static Integer UI_WIDTH = 500;
    public static Integer UI_HEIGHT = 600;
    public static Properties properties = new Properties();
    /**
     * 书签
     */
    public static Properties shuqian = new Properties();
    /**
     * 界面配置
     */
    public static Properties ui = new Properties();

    static {
        load();
    }

    public static void load() {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                FileInputStream in = new FileInputStream(file);
                properties.load(in);
                Object val = properties.get(bootPathName);
                if (val != null) {
                    bootPath = val.toString();
                } else {
                    JOptionPane.showConfirmDialog(null, "请设置脚本路径!");
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(file + "文件不存在！");
            }
        }
        loadShuQian();
        loadUi();
    }

    public static void loadShuQian() {
        File file = new File(SHU_QIAN_FILE_NAME);
        if (file.exists()) {
            try {
                FileInputStream in = new FileInputStream(file);
                shuqian.load(in);
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(file + "文件不存在！");
            }
        }
    }

    public static void loadUi() {
        File file = new File(UI);
        if (file.exists()) {
            try {
                FileInputStream in = new FileInputStream(file);
                ui.load(in);
                UI_WIDTH = Integer.valueOf(ui.getProperty("width"));
                UI_HEIGHT = Integer.valueOf(ui.getProperty("height"));
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(file + "文件不存在！");
            }
        } else {
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
                ui.store(outputStream, "书签列表");
                outputStream.close();
            } catch (Exception e) {

            }

        }
    }

    public static void add(String name, String value) throws IOException {
        properties.put(name, value);
        File file = new File(fileName);
        FileOutputStream outputStream = new FileOutputStream(file);
        properties.store(outputStream, "系统设置");
        outputStream.close();
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
        outputStream.close();
    }

    public static void main(String[] args) throws IOException {

    }
}
