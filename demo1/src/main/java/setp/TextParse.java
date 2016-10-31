package setp;

import util.HtmlContent;
import util.StepUtil;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class TextParse {

    private LinkedList<Step> linkedList;
    private LinkedList<Step> baseList;

    private File file;


    private HtmlContent htmlContent;

    public TextParse() {
        linkedList = new LinkedList<>();
        baseList = new LinkedList<>();
    }

    public static TextParse getInstance(String file, HtmlContent htmlContent) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String str = TextParse.class.getResource("/").getPath() + file;
        TextParse textParse = new TextParse();
        textParse.file = new File(str);
        textParse.htmlContent = htmlContent;
        textParse.bulid();
        return textParse;
    }

    private void bulid() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        linkedList.clear();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while (line != null) {
            addStep(line.trim());
            line = reader.readLine();
        }
    }

    private void addStep(String line) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (line.startsWith("base")) {
            Step step = StepUtil.getStep(line.substring(4));
            step.setHtmlContent(htmlContent);
            baseList.add(step);
        } else {
            buildNotBaseStep(line);
        }
    }

    private void buildNotBaseStep(String line) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Step step;
        if (line.startsWith("b")) {
            step = StepUtil.getStep(line.substring(1));
            step.setBase(true);
        } else if (line.startsWith("mb")) {
            step = StepUtil.getStep(line.substring(2));
            step.setMb(true);
        } else {
            step = StepUtil.getStep(line);
        }
        step.setStep(this);
        step.setHtmlContent(htmlContent);
        linkedList.add(step);
    }


    public void run() {
        linkedList.forEach(Step::run);
    }

    public void baseRun() {
        baseList.forEach(Step::run);
    }

    public void ontStepRun(Step Step){

    }
}


















