package setp.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 到点执行的
 * Created by dell_2 on 2016/10/31.
 */
public class TimeStep extends ManyStep {


    public static void main(String[] args) throws InterruptedException {
        Date date = new Date();
        TimeUnit.SECONDS.sleep(1);
        Date curr = new Date();
        System.out.println(curr);
        Calendar cd = Calendar.getInstance();
        cd.setTime(curr);
        cd.add(Calendar.MINUTE, -1);
        curr = cd.getTime();
        System.out.println(date.before(curr));
    }
}
