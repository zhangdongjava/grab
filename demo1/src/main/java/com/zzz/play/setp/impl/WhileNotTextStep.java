package com.zzz.play.setp.impl;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class WhileNotTextStep extends BaseStep {

    private String whileText;

    private String notText;

    private WhileNotTextStep() {
    }

    public void setNotText(String notText) {
        this.notText = notText;
    }

    public String getWhileText() {
        return whileText;
    }

    public void setWhileText(String whileText) {
        this.whileText = whileText;
    }


    public static WhileNotTextStep parse(String line) {
        WhileNotTextStep notTextStep = new WhileNotTextStep();
        String[] datas = line.split("whileNot");
        for (String data : datas) {
            if (data != null && !"".equals(data.trim())) {
                if (notTextStep.getWhileText() == null) {
                    notTextStep.setWhileText(data);
                } else {
                    notTextStep.setNotText(data);
                }
            }
        }
        return notTextStep;
    }

    public boolean run() {
        boolean res = false;
        baseRun();
        while (htmlContent.exitsName(whileText, notText)) {
            res = true;
            textParse.ontStepRun(this);
            htmlContent.linkName(whileText, notText);
        }

        return res;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("WhileNotConditionStep{");
        sb.append("whileText='").append(whileText).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
