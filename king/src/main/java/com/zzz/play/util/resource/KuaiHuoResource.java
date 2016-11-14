package com.zzz.play.util.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class KuaiHuoResource {

    private static Properties zhu = new Properties();
    private static Properties fhu = new Properties();
    static {
        load();
    }
    private static void load(){
        File file = new File("res/kuaihuo.properties");
        try {
            FileInputStream fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
