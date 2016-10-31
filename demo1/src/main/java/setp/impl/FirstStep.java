package setp.impl;

import setp.Step;

/**
 * 第一步执行了后面才会执行
 * Created by dell_2 on 2016/10/31.
 */
public class FirstStep extends ManyStep {

    @Override
    public boolean run() {
        for (int i = 0; i < steps.size(); i++) {
            Step step = steps.get(i);
            boolean res = step.run();
            System.out.println("运行行脚本:"+step);
            if (!res && i == 0) {
                return false;
            }
        }
        return true;
    }

}
