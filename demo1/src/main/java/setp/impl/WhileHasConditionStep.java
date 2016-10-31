package setp.impl;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class WhileHasConditionStep extends BaseStep {

    private String whileText;
    private String triggerText;
    private boolean triggerLike = false;
    private boolean whileLike = false;

    public void setTriggerLike(boolean triggerLike) {
        this.triggerLike = triggerLike;
    }

    public void setWhileLike(boolean whileLike) {
        this.whileLike = whileLike;
    }

    private WhileHasConditionStep() {
    }

    public String getWhileText() {
        return whileText;
    }

    public void setWhileText(String whileText) {
        this.whileText = whileText;
    }

    public String getTriggerText() {
        return triggerText;
    }

    public void setTriggerText(String triggerText) {
        this.triggerText = triggerText;
    }

    public static WhileHasConditionStep parse(String line) {
        WhileHasConditionStep whileStep = new WhileHasConditionStep();
        String[] datas = line.split("while1");
        for (String data : datas) {
            if (data != null && !"".equals(data.trim())) {
                if (whileStep.getWhileText() == null) {
                    if (data.startsWith("like")) {
                        whileStep.setWhileLike(true);
                        whileStep.setWhileText(data.substring(4));
                    } else {
                        whileStep.setWhileText(data);
                    }

                } else {
                    if (data.startsWith("like")) {
                        whileStep.setTriggerLike(true);
                        whileStep.setTriggerText(data.substring(4));
                    } else {
                        whileStep.setTriggerText(data);
                    }

                }
            }
        }
        return whileStep;
    }

    public void run() {
        baseRun();
        while (!htmlContent.exitsName(triggerText, triggerLike)
                &&htmlContent.exitsName(whileText, whileLike)) {
            mbRun();
            await();
            htmlContent.linkName(whileText, whileLike);
        }
        baseRun();
        htmlContent.linkName(triggerText, triggerLike);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("WhileHasConditionStep{");
        sb.append("whileText='").append(whileText).append('\'');
        sb.append(", triggerText='").append(triggerText).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
