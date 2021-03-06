package com.zzz.play.util.http;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpRequest {

    StringBuffer cookie = null;
    StringBuilder result = new StringBuilder();

    public String sendGet(String url) throws IOException {
        result.delete(0,result.length());
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setConnectTimeout(2000);
            if (cookie != null) {
                connection.setRequestProperty("Cookie", cookie.toString());
            }
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append("\r\n");
                result.append(line);
            }
            buildCookie(connection.getHeaderFields());
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result.toString();
    }


    private void buildCookie(Map<String, List<String>> maps) {
        String cookieskey = "Set-Cookie";
        List<String> coolist = maps.get(cookieskey);
        if(coolist==null)return;
        Iterator<String> it = coolist.iterator();
        if (cookie == null) {
            cookie = new StringBuffer();
            cookie.append("");
            while (it.hasNext()) {
                cookie.append(it.next());
            }
            System.out.println(cookie);
        }
    }


}