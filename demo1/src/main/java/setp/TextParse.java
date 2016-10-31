package setp;

import setp.impl.BaseStep;
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

    public static TextParse getInstance(String file, HtmlContent htmlContent) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        String str = TextParse.class.getResource("/").getPath() + file;
        TextParse textParse = new TextParse();
        textParse.file = new File(str);
        textParse.htmlContent = htmlContent;
        textParse.bulid();
        return textParse;
    }

    private void bulid() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        linkedList.clear();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        int index = 0;
        while (line != null) {
            Step step = addStep(line.trim());
            step.setLineNum(++index);
            line = reader.readLine();
        }
    }

    private Step addStep(String line) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        Step step;
        if (line.startsWith("base")) {
            step= StepUtil.getStep(line.substring(4));
            step.setHtmlContent(htmlContent);
            step.setStep(this);
            baseList.add(step);
        } else {
            step =  buildNotBaseStep(line);
        }
        return step;
    }

    private Step buildNotBaseStep(String line) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        Step step;
        if (line.startsWith("b")) {
            step = StepUtil.getStep(line.substring(1));
            step.setBase(true);
        } else if (line.startsWith("mb")) {
            step = StepUtil.getStep(line.substring(2));
            step.setMb(true);
        } else if (line.startsWith("class")) {
            String className = line.substring(5);
            step = (Step) Class.forName(className).newInstance();
        }else {
            step = StepUtil.getStep(line);
        }
        step.setStep(this);
        step.setHtmlContent(htmlContent);
        linkedList.add(step);
        return step;
    }


    public void run() {
        linkedList.forEach((step -> {
           // System.out.println("num:"+step.getLineNum());
            step.run();
        }));
    }

    public void baseRun() {
        baseList.forEach(Step::run);
    }

    public void ontStepRun(Step step){
        step.mbRun();
        BaseStep.await();
    }
}


















