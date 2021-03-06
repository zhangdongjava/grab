package com.zzz.play.setp.impl.config;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class ForStep extends BaseStep {

    private String whileText;
    private Integer forCount;

    private ForStep() {
    }

    public String getWhileText() {
        return whileText;
    }

    public void setWhileText(String whileText) {
        this.whileText = whileText;
    }

    public Integer getForCount() {
        return forCount;
    }

    public void setForCount(Integer forCount) {
        this.forCount = forCount;
    }

    public static ForStep parse(String line) {
        ForStep forStep = new ForStep();
        String[] datas = line.split("for");
        for (String data : datas) {
            if (data != null && !"".equals(data.trim())) {
                if (forStep.getWhileText() == null) {
                    if (data.startsWith("like")) {
                        forStep.setLike(true);
                        forStep.setWhileText(data.substring(4));
                    } else {
                        forStep.setWhileText(data);
                    }

                } else {
                    forStep.setForCount(Integer.valueOf(data));
                }
            }
        }
        return forStep;
    }

    public boolean run() {
        boolean res = false;
        baseRun();
        for (int i = 0; i < forCount; i++) {
            if (!htmlContent.exitsName(whileText, like))
                return res;
            res = true;
            textParse.ontStepRun(this);
            htmlContent.linkName(whileText, like);
        }
        return res;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ForStep{");
        sb.append("whileText='").append(whileText).append('\'');
        sb.append(", forCount=").append(forCount);
        sb.append('}');
        return sb.toString();
    }
}
