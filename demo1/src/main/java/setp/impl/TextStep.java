package setp.impl;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class TextStep extends BaseStep {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static TextStep parse(String line) {
        TextStep testStep = new TextStep();
        if (line.startsWith("like")) {
            testStep.setLike(true);
            testStep.setText(line.trim().substring(4));
        } else {
            testStep.setText(line.trim());
        }

        return testStep;
    }

    public void run() {
        baseRun();
        textParse.ontStepRun(this);
        htmlContent.linkName(text, like);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TextStep{");
        sb.append("text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
