package com.zzz.play.util.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzz.play.bean.UserInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 书签加载保存工具
 * Created by dell_2 on 2016/11/16.
 */
public class UiResourceUtil {

    /**
     * 打开多个书签
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static List<UserInfo> bookManyload(File file) throws IOException {
        return JSON.parseArray(loadJson(file), UserInfo.class);
    }

    /**
     * 保存多个书签
     *
     * @param file
     * @param list
     */
    public static void bookManysave(File file, LinkedList<UserInfo> list) throws IOException {
        saveJson(list, file);
    }

    /**
     * 保存缓存
     *
     * @param map
     * @param filePath
     * @throws IOException
     */
    public static void cacheSave(Map<String, UserInfo> map, String filePath) throws IOException {
        saveJson(map, new File(filePath));
    }

    /**
     * 保存缓存
     *
     * @param file
     * @throws IOException
     */
    public static Map<String, UserInfo> openCache(File file) throws IOException {
        JSONObject jo = JSON.parseObject(loadJson(file));
        Map<String, UserInfo> map2 = new HashMap<>();
        for (Map.Entry<String, Object> entry : jo.entrySet()) {
            map2.put(entry.getKey(), JSON.parseObject(entry.getValue().toString(), UserInfo.class));
        }
        return map2;
    }


    private static void saveJson(Object map, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(JSON.toJSONString(map,true).getBytes());
        fos.close();
    }

    private static String loadJson(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] buf = new byte[fis.available()];
        fis.read(buf);
        fis.close();
        return new String(buf);
    }
}




















