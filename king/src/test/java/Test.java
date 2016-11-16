import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zzz.play.bean.UserInfo;
import com.zzz.play.ui.dialog.ScriptDialog;
import com.zzz.play.util.resource.UiResourceUtil;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by dell_2 on 2016/11/16.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\dell_2\\Desktop\\xiaoxiong\\cache/yy2");
        File file2 = new File("C:\\Users\\dell_2\\Documents\\axiao/shuqian/yy2");
        zhuanHuan(file,file2);
        //cache(file);
        //test(file2);
    }

    public static void test(File file) throws IOException {
        List<UserInfo> users = UiResourceUtil.bookManyload(file);
        System.out.println(JSON.toJSONString(users, SerializerFeature.PrettyFormat));
    }

    public static void zhuanHuan(File resource, File target) throws Exception {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(resource));
        FileOutputStream fos = new FileOutputStream(target);
        LinkedList<UserInfo> list = (LinkedList<UserInfo>) stream.readObject();
        for (int i = 0; i < list.size(); i++) {
            UserInfo user = list.get(i);
            user.setLogin(true);
            buildScripts(user.getScritps1());
            buildScripts(user.getScritps2());
            list.set(i, user);
        }
        String json = JSON.toJSONString(list, SerializerFeature.PrettyFormat);
        fos.write(json.getBytes());
        fos.close();
    }

    public static void cache(File resource) throws Exception {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(resource));
        Map<String,UserInfo> map = ( Map<String,UserInfo>) stream.readObject();
        for (Map.Entry<String, UserInfo> entry : map.entrySet()) {
            UserInfo user = entry.getValue();
            user.setLogin(true);
            buildScripts(user.getScritps1());
            buildScripts(user.getScritps2());
        }
        String json = JSON.toJSONString(map, SerializerFeature.PrettyFormat);
        System.out.println(json);
    }

    private static void buildScripts(LinkedList<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            strings.set(i, ScriptDialog.url_name.get(strings.get(i)));
        }
    }
}
