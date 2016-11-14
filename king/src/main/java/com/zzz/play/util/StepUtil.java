package com.zzz.play.util;

import com.zzz.play.setp.Step;
import com.zzz.play.setp.copy.*;
import com.zzz.play.setp.impl.config.*;
import com.zzz.play.setp.material.compound.*;
import com.zzz.play.setp.sys.QiangXian;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *   else if (line.startsWith("shirengu")) {
 step = new ShiRenGu();
 } else if (line.startsWith("qiangXian")) {
 step = new QiangXian();
 } else if (line.startsWith("qiangTi")) {
 step = new QiangTi();
 } else if (line.startsWith("jingGang")) {
 step = new JingGang();
 } else if (line.equals("kuangBao")) {
 step = new KuangBao();
 } else if (line.equals("kuangBaoEr")) {
 step = new KuangBaoEr();
 } else if (line.startsWith("xueShan")) {
 step = new XueShan();
 } else if (line.startsWith("taiwei")) {
 step = new TaiWei();
 } else if (line.startsWith("podong")) {
 step = new BingPoDong();
 } else if (line.equals("dangkou")) {
 step = new DangKou();
 } else if (line.equals("poSuiMengJingShi")) {
 step = new PoSui();
 }
 * Created by dell_2 on 2016/10/29.
 */
public class StepUtil {

    private static Map<String, Class<?>> map = new HashMap<>();
    private static Map<String, Class<?>> manymap = new HashMap<>();
    private static Map<String, Class<?>> classmap = new HashMap<>();

    static {
        map.put("while1", WhileHasConditionStep.class);
        map.put("while2", WhileNotConditionStep.class);
        map.put("for", ForStep.class);
        map.put("whileNot", WhileNotTextStep.class);
        map.put("wait", TimeWait.class);
        map.put("put", PutVarStep.class);
        manymap.put("first{", FirstStep.class);
        manymap.put("many{", ManyStep.class);
        manymap.put("timeNormal", TimeStep.class);
        manymap.put("timeGlobal", TimeGlobalStep.class);
        manymap.put("getVar", GetVarStep.class);
        classmap.put("heif", HeiFeng.class);
        classmap.put("zhen", ZhenList.class);
        classmap.put("shirengu", ShiRenGu.class);
        classmap.put("qiangXian", QiangXian.class);
        classmap.put("QiangTi", QiangTi.class);
        classmap.put("jingGang", JingGang.class);
        classmap.put("kuangBao", KuangBao.class);
        classmap.put("kuangBaoEr", KuangBaoEr.class);
        classmap.put("xueShan", XueShan.class);
        classmap.put("taiwei", TaiWei.class);
        classmap.put("podong", BingPoDong.class);
        classmap.put("dangkou", DangKou.class);
        classmap.put("poSuiMengJingShi", PoSui.class);
    }

    public static Step getStep(String line) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (String key : map.keySet()) {
            if (line.contains(key)) {
                Class cls = map.get(key);
                Method parse = cls.getMethod("parse", String.class);
                Step step = (Step) parse.invoke(null, line);
                return step;
            }
        }
        return TextStep.parse(line);
    }

    public static ManyStep getManny(String line) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        if (line.startsWith("time")) {
            return buildTimeStep(line);
        } else if (line.startsWith("getVar")) {
            return buildVarStep(line);
        }
        Class cls = manymap.get(line);
        if (cls != null) {
            ManyStep step = (ManyStep) cls.newInstance();
            return step;
        }
        return null;
    }

    public static TimeStep buildTimeStep(String line) throws IllegalAccessException, InstantiationException {
        String[] lines = line.split("_");
        Class cls = manymap.get(lines[0]);
        if (cls != null) {
            TimeStep step = (TimeStep) cls.newInstance();
            step.setInAdvance(Integer.valueOf(lines[1]));
            return step;
        }
        return null;
    }

    public static ManyStep buildVarStep(String line) throws IllegalAccessException, InstantiationException {
        String[] lines = line.split("_");
        GetVarStep getVarStep = new GetVarStep();
        getVarStep.setName(lines[1]);
        return getVarStep;
    }

    public static Step getClassSetp(String line) throws IllegalAccessException, InstantiationException {
        Class<?> cla = classmap.get(line);
        if(cla!= null){
            return (Step) cla.newInstance();
        }
        return null;
    }

    public static Step getClassNameSetp(String line) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class<?> cla = Class.forName(line);
        if(cla!= null){
            return (Step) cla.newInstance();
        }
        return null;
    }
}
