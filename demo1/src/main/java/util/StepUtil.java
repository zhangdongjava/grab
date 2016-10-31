package util;

import setp.Step;
import setp.impl.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class StepUtil {

    private static Map<String, Class<?>> map = new HashMap<>();
    private static Map<String, Class<?>> manymap = new HashMap<>();

    static {
        map.put("while1", WhileHasConditionStep.class);
        map.put("while2", WhileNotConditionStep.class);
        map.put("for", ForStep.class);
        map.put("whileNot", WhileNotTextStep.class);
        map.put("wait", TimeWait.class);
        manymap.put("first{", FirstStep.class);
        manymap.put("many{", ManyStep.class);
        manymap.put("fresh{", TimeStep.class);
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
        Class cls = manymap.get(line);
        if (cls != null) {
            ManyStep step = (ManyStep) cls.newInstance();
            return step;
        }
        return null;
    }
}
