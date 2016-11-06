package com.zzz.play.setp.impl.config;

/**
 * 执行清理程序
 * Created by dell_2 on 2016/10/29.
 */
public class ClearStep extends BaseStep {


    public boolean run() {
        utilDto.clearUtil.clearPack(htmlContent);
        return true;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ClearStep{");
        sb.append('}');
        return sb.toString();
    }
}
