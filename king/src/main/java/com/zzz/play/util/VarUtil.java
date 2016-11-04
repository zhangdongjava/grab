package com.zzz.play.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2016/11/1 0001.
 */
public class VarUtil {

    private Map<String, Boolean> vars = new ConcurrentHashMap<>();

    public void addVar(String name, Boolean val) {
        vars.put(name, val);
    }

    public boolean getVar(String name) {
        Boolean b = vars.get(name);
        if (b == null) return false;
        return b;
    }
}
