package com.zzz.play.setp.impl;

/**
 * Created by Administrator on 2016/11/1 0001.
 */
public class PutVarStep extends BaseStep {

    private String name;
    private String type;
    private String value;

    public static PutVarStep parse(String line) {
        line = line.substring(3);
        String[] datas = line.split("_");
        PutVarStep putVarStep = new PutVarStep();
        putVarStep.name = datas[0];
        putVarStep.type = datas[1];
        putVarStep.value = datas[2];
        return putVarStep;
    }

    @Override
    public boolean run() {
        boolean val = false;
        if (type.equals("text")) {
            val = htmlContent.getDocument().text().contains(value);
        } else if ("val".equals(type)) {
            val = Boolean.valueOf(value);
        } else if ("a".equals(type)) {
            val = htmlContent.exitsName(value, true);
        }
        utilDto.varUtil.addVar(name, val);
        return false;
    }
}
