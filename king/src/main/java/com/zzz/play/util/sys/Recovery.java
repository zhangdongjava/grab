package com.zzz.play.util.sys;

import com.zzz.play.ui.MainWindow;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 恢复工具
 * Created by Administrator on 2016/11/13 0013.
 */
public class Recovery {

    private static Logger logger = Logger.getLogger(Recovery.class);
    private Map<String, String> map = Collections.synchronizedMap(new LinkedHashMap<>());
    private MainWindow mainWindow;

    public Recovery(MainWindow mainWindow) {

        this.mainWindow = mainWindow;
    }

    public void addCache(String name, String url) {
        map.put(name, url);
        save();
    }

    private void save() {
        try {
            FileOutputStream fos = new FileOutputStream(mainWindow.htmlPanels.get(0).user.getName());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
        } catch (IOException e) {
            logger.error("临时恢复保存失败!" + map);
        }
    }

    public Map<String, String> open(String file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (Map<String, String>) ois.readObject();
            ois.close();
            return map;
        } catch (IOException e) {
            logger.error("临时恢复读取失败!" + e.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
