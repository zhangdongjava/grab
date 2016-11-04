package com.zzz.play.setp.impl.config;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class WhileNotConditionStep extends BaseStep {

    private String whileText;


    private WhileNotConditionStep() {
    }

    public String getWhileText() {
        return whileText;
    }

    public void setWhileText(String whileText) {
        this.whileText = whileText;
    }


    public static WhileNotConditionStep parse(String line) {
        WhileNotConditionStep whileStep = new WhileNotConditionStep();
        line = line.replace("while2", "");
        if (line.startsWith("like")) {
            whileStep.setLike(true);
            whileStep.setWhileText(line.substring(4));
        } else {
            whileStep.setWhileText(line);
        }
        return whileStep;
    }

    public boolean run() {
        baseRun();
        boolean res = false;
        while (htmlContent.exitsName(whileText, like)) {
            textParse.ontStepRun(this);
            htmlContent.linkName(whileText, like);
            res = true;
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
