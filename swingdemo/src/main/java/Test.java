import http.HttpRequest;

import java.util.Random;

/**
 * Created by dell_2 on 2016/11/14.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Random random = new Random();
        StringBuilder name = new StringBuilder();
        StringBuilder pwd = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            name.append(random.nextInt(10));
            pwd.append(random.nextInt(10));
        }
        //HttpRequest httpRequest = new HttpRequest();
        //httpRequest.sendGet("http://wapok.cn/reg.php?gid=&phpsid=c5c730c196f3908b0906dd7774c44060");
        //String url = "http://wapok.cn/reg.php?do=reg&phpsid=c5c730c196f3908b0906dd7774c44060&agentid=&submit=submit&gameId=&name=" + name + "&password=" + pwd;
       // String context = httpRequest.sendGet(url);
        //System.out.println(url);
        System.out.println(name);
        System.out.println(pwd);
       // System.out.println(context);
    }
}
