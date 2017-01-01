import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zzz.play.bean.UserInfo;
import com.zzz.play.ui.dialog.ScriptDialog;
import com.zzz.play.util.resource.UiResourceUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by dell_2 on 2016/11/16.
 */
public class Test {

    static String json = "{\"大嘴哥\":{\"currUrl\":\"http://hero2.yytou.com/gCmd.do?cmd=22&sid=o4w8lk3c97tgnpwjm41kp\",\"daqu\":\"微信2区\",\"login\":false,\"name\":\"大嘴哥\",\"scritps1\":[\"副本/冰魄洞\",\"测试/招募赤甲\",\"合成/狂暴奇书\",\"合成/金刚奇书\",\"材料/虎皮\",\"测试/庄园只有房屋\",\"测试/修理\",\"材料/大白菜\",\"材料/金刚罩1\",\"高级副本/破碎梦境石\",\"高级副本/荡寇\",\"高级副本/阵法\",\"书怪/摘星子\"],\"scritps2\":[],\"url\":\"http://entry.yytou.com/choiceAreaInput.do?gameId=g518&key=2638010_A459C1EB1904BDF636912019E395A0A1&qd=\"},\"酱油哥\":{\"currUrl\":\"http://hero2.yytou.com/gCmd.do?cmd=20&sid=xowh0h3c97to4dwjq74eg\",\"daqu\":\"微信2区\",\"login\":false,\"name\":\"酱油哥\",\"scritps1\":[\"合成/金刚奇书\",\"合成/狂暴奇书\",\"副本/冰魄洞\",\"材料/高级万虫羹\",\"材料/金刚罩1\",\"材料/大白菜\",\"测试/修理\",\"测试/招募赤甲\",\"书怪/单刷摘星子\",\"材料/流花河图\"],\"scritps2\":[],\"url\":\"http://entry.yytou.com/choiceAreaInput.do?gameId=g518&key=2638050_CDDCF405206239A1E2F6DCCF36F7F634&qd=\"},\"狂战\":{\"currUrl\":\"http://hero2.yytou.com/gCmd.do?cmd=28&sid=cdjail3c97tvptwjzf88z\",\"daqu\":\"微信2区\",\"login\":false,\"name\":\"狂战\",\"scritps1\":[\"副本/冰魄洞\",\"材料/高级万虫羹\",\"材料/金刚罩1\",\"材料/大白菜\",\"书怪/单刷摘星子\",\"测试/招募赤甲\",\"测试/修理\"],\"scritps2\":[],\"url\":\"http://entry.yytou.com/choiceAreaInput.do?gameId=g518&key=2638474_8DB38C892703E49128F8070A18C5FD2A&qd=\"},\"剑者\":{\"currUrl\":\"http://hero2.yytou.com/gCmd.do?cmd=20&sid=a91vf53c97u3eqwk0nppz\",\"daqu\":\"微信2区\",\"login\":false,\"name\":\"剑者\",\"scritps1\":[\"副本/冰魄洞\",\"材料/高级万虫羹\",\"材料/金刚罩1\",\"材料/大白菜\",\"书怪/单刷摘星子\",\"测试/招募赤甲\",\"测试/修理\"],\"scritps2\":[],\"url\":\"http://entry.yytou.com/choiceAreaInput.do?gameId=g518&key=2638469_264F90A4F975EB929F9E677D86523B31&qd=\"},\"艹死你个大嘴\":{\"currUrl\":\"http://hero2.yytou.com/gCmd.do?cmd=20&sid=ku0him3c97ub2rwk4vppg\",\"daqu\":\"微信2区\",\"login\":false,\"name\":\"艹死你个大嘴\",\"scritps1\":[\"副本/冰魄洞\",\"材料/高级万虫羹\",\"材料/金刚罩1\",\"材料/大白菜\",\"书怪/单刷摘星子\",\"测试/招募赤甲\",\"测试/修理\"],\"scritps2\":[],\"url\":\"http://entry.yytou.com/choiceAreaInput.do?gameId=g518&key=2456068_74BF0A0BD7595E6C1C194B60EAC2AEEF&qd=\"},\"哈嘿\":{\"currUrl\":\"http://hero2.yytou.com/gCmd.do?cmd=21&sid=hpl9ho3c97uis4wkdpr6y\",\"daqu\":\"微信2区\",\"login\":false,\"name\":\"哈嘿\",\"scritps1\":[\"副本/冰魄洞\",\"材料/高级万虫羹\",\"材料/金刚罩1\",\"材料/大白菜\",\"书怪/单刷摘星子\",\"测试/招募赤甲\",\"测试/修理\"],\"scritps2\":[],\"url\":\"http://entry.yytou.com/choiceAreaInput.do?gameId=g518&key=2638479_40173A93491CB901727305AE72BC1B01&qd=\"},\"zzz\":{\"currUrl\":\"http://hero2.yytou.com/gCmd.do?cmd=22&sid=dlyz4c3c97uqhwwki2xhl\",\"daqu\":\"微信2区\",\"login\":false,\"name\":\"zzz\",\"scritps1\":[\"副本/冰魄洞\",\"材料/高级万虫羹\",\"材料/金刚罩1\",\"材料/大白菜\",\"书怪/单刷摘星子\",\"测试/招募赤甲\",\"测试/修理\"],\"scritps2\":[],\"url\":\"http://entry.yytou.com/choiceAreaInput.do?gameId=g518&key=2638507_8D6641AE6B968987CE82EAE01F8790BD&qd=\"}}";

    public static void main(String[] args) throws Exception {

       Connection str =  Jsoup.connect("http://hero2.yytou.com/gCmd.do?cmd=8&sid=bzkzdx3e2et65ab5ijxdl");
        Document document = str.get();
        String html  = document.html();
        String newHtml  = document.html();
        int index = html.indexOf("你看到:");
        if(index!=-1){
            int index2 = html.indexOf("<br>",index+1);
            newHtml = html.substring(0,index)+html.substring(index2);
        }
        System.out.println(html);
        System.out.println(newHtml);
        // File file = new File("C:\\Users\\dell_2\\Desktop\\xiaoxiong\\cache/yy2");
       // File file2 = new File("C:\\Users\\dell_2\\Documents\\axiao/shuqian/yy2");
       // zhuanHuan(file,file2);
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
