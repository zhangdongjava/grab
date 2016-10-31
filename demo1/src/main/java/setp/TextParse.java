package setp;

import setp.impl.BaseStep;
import setp.impl.FirstStep;
import setp.impl.ManyStep;
import setp.sys.GoodsSale;
import util.HtmlContent;
import util.StepUtil;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class TextParse {

    public final int NORMAL = 0;
    public final int BASE = 1;
    public final int TIME = 2;

    public int statu = 0;

    private LinkedList<Step> linkedList;
    private LinkedList<Step> baseList;

    private File file;


    private HtmlContent htmlContent;

    private ManyStep manyStep;

    private boolean inMang = false;

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
            if (step != null) {
                step.setLineNum(++index);
            }
            line = reader.readLine();
        }
    }

    private Step addStep(String line) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        Step step = null;
        if (line.startsWith("base")) {
            if (line.endsWith("{")) {
                manyStep = StepUtil.getManny(line.substring(4));
                inMang = true;
                baseList.add(manyStep);
                manyStep.setStep(this);
                manyStep.setHtmlContent(htmlContent);
            } else {
                step = StepUtil.getStep(line.substring(4));
                step.setHtmlContent(htmlContent);
                step.setStep(this);
                baseList.add(step);
            }

        } else {
            step = buildNotBaseStep(line);
        }
        return step;
    }

    private Step buildNotBaseStep(String line) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        Step step = null;
        if (line.startsWith("b")) {
            step = StepUtil.getStep(line.substring(1));
            step.setBase(true);
        } else if (line.startsWith("mb")) {
            step = StepUtil.getStep(line.substring(2));
            step.setMb(true);
        } else if (line.startsWith("sale")) {
            step = new GoodsSale(line.substring(4));
        } else if (line.endsWith("{")) {
            manyStep = StepUtil.getManny(line);
            manyStep.setStep(this);
            manyStep.setHtmlContent(htmlContent);
            inMang = true;
            linkedList.add(manyStep);
        } else if (line.equals("}")) {
            inMang = false;
            manyStep = null;
        } else {
            step = StepUtil.getStep(line);
        }
        if (step != null) {
            step.setStep(this);
            step.setHtmlContent(htmlContent);
            if (!inMang) {
                linkedList.add(step);
            }else{
                manyStep.addStep(step);
            }
        }

        return step;
    }


    public void run() {
        linkedList.forEach((step -> {
            // System.out.println("num:"+step.getLineNum());
            step.run();
        }));
    }

    public void baseRun() {
        if (statu == NORMAL) {
            statu = BASE;
            baseList.forEach(Step::run);
            statu = NORMAL;
        }
    }

    public void ontStepRun(Step step) {
        step.mbRun();
        BaseStep.await();
    }
}


















