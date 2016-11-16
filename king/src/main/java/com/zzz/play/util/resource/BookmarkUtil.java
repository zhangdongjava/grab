package com.zzz.play.util.resource;

import com.alibaba.fastjson.JSON;
import com.zzz.play.bean.UserInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 书签加载保存工具
 * Created by dell_2 on 2016/11/16.
 */
public class BookmarkUtil {

    public static List<UserInfo> load(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] buf = new byte[fis.available()];
        fis.read(buf);
        String json = new String(buf);
        fis.close();
        return JSON.parseArray(json, UserInfo.class);
    }

    /**
     * 保存书签
     *
     * @param file
     * @param list
     */
    public static void save(File file, LinkedList<UserInfo> list) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(JSON.toJSONString(list).getBytes());
        fos.close();
    }
}
