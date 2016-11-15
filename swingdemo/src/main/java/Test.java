import http.HttpRequest;

/**
 * Created by dell_2 on 2016/11/14.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        HttpRequest request = new HttpRequest();
        request.sendGet("http://entry.yytou.com/choiceAreaInput.do?gameId=g518&key=2750528_168356B5E42F88B253906780BE6DC864&qd=");
        String ss = request.sendGet("http://entry.yytou.com/entryGame.do?appId=g518.2&qd=");
        System.out.println(ss);
    }
}
