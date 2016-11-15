package com.zzz.play.util.resource;

import javax.swing.*;
import java.io.*;
import java.util.Properties;

/**
 * Created by dell_2 on 2016/11/2.
 */
public class Resource {

    private static String fileName = "res/systemKing.txt";
    private static String SHU_QIAN_FILE_NAME = "res/shuQianKing.properties";
    private static String UI = "res/ui.properties";
    public static String bootPathName = "bootPath";

    public static String bootPath;
    public static Integer UI_WIDTH = 500;
    public static Integer UI_HEIGHT = 600;
    public static double FONT_SIZE = 1.3;
    public static Integer WAIT = 550;
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
                FONT_SIZE = Double.valueOf(ui.getProperty("fontSize"));
                WAIT = Integer.valueOf(ui.getProperty("wait"));
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(file + "文件不存在！");
            }
        } else {

            ui.put("width", UI_WIDTH);
            ui.put("height", UI_HEIGHT);
            ui.put("fontSize", FONT_SIZE);
            ui.put("wait", WAIT);
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                ui.store(outputStream, "界面配置");
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
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
