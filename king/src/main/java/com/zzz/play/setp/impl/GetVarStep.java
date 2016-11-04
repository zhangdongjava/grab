package com.zzz.play.setp.impl;

/**
 * Created by Administrator on 2016/11/1 0001.
 */
public class GetVarStep extends ManyStep {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean run() {
        if (utilDto.varUtil.getVar(name)) {
            utilDto.varUtil.addVar(name, false);
            return super.run();
        }
        return false;
    }
}
