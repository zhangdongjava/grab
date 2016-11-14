package com.zzz.play.util.resource;

import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class KuaiHuoResource {

    private static Properties zhu = new Properties();
    private static Properties fu = new Properties();
    private static final String FILE_NAME = "res/kuaihuo.properties";
    static {
        load();
    }

    private static void load() {
        File file = new File(FILE_NAME);
        try {
            FileInputStream fis = new FileInputStream(file);
            zhu.load(fis);
            for (Map.Entry<Object, Object> entry : zhu.entrySet()) {
                fu.put(entry.getValue(), entry.getKey());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getZhu(String name) {
        if (zhu.get(name) != null) {
            return zhu.get(name).toString();
        }
        return null;
    }

    public static String getFu(String name) {
        if (fu.get(name) != null) {
            return fu.get(name).toString();
        }
        return null;
    }

    public static void add(String zhuName,String fuName) throws IOException {
        zhu.put(zhuName,fuName);
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        zhu.store(fos,"快活列表");
        fos.close();
    }

}
