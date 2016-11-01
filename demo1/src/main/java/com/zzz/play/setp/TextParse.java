package com.zzz.play.setp;

import com.zzz.play.exception.StepBackException;
import com.zzz.play.mark.Global;
import com.zzz.play.setp.impl.BaseStep;
import com.zzz.play.setp.impl.ManyStep;
import com.zzz.play.setp.sys.GoodsSale;
import com.zzz.play.util.GlobalUtil;
import com.zzz.play.util.GoodsUtil;
import com.zzz.play.util.HtmlContent;
import com.zzz.play.util.StepUtil;

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

    /**
     * 当前setp下标用于后退一步使用
     */
    private int currNormalIndex = 0;
    /**
     * 当前basesetp下标用于后退一步使用
     */
    private int currBaseIndex = 0;

    private String fileName;

    /**
     * 全局脚本工具对象
     */
    private GlobalUtil globalUtil;

    public TextParse() {
        linkedList = new LinkedList<>();
        baseList = new LinkedList<>();
    }

    public static TextParse getInstance(String file, HtmlContent htmlContent, GlobalUtil globalUtil) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        String str = TextParse.class.getResource("/").getPath() + file;
        TextParse textParse = new TextParse();
        textParse.globalUtil = globalUtil;
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
            if (!line.trim().equals("")) {
                Step step = addStep(line.trim());
                if (step != null) {
                    step.setLineNum(++index);
                }
            }
            line = reader.readLine();
        }
    }

    private Step addStep(String line) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        Step step = null;
        if (line.startsWith("base")) {
            if (line.endsWith("{")) {
                manyStep = StepUtil.getManny(line.substring(4));
                if (buildMany()) {
                    baseList.add(manyStep);
                }
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
            if (buildMany()) {
                linkedList.add(manyStep);
            }
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
            } else {
                manyStep.addStep(step);
            }
        }

        return step;
    }

    /**
     * @return 是否添加到集合
     */
    private boolean buildMany() {
        manyStep.setStep(this);
        manyStep.setHtmlContent(htmlContent);
        inMang = true;
        return manyTypeJudge();
    }

    private boolean manyTypeJudge() {
        //全局
        if (manyStep instanceof Global) {
            //加入全局脚本中
            globalUtil.addStep(manyStep);
            //返回false  不加入本textParse中
            return false;
        }
        return true;
    }


    public void run() {
        htmlContent.setCurrParse(this);
        for (currNormalIndex = 0; currNormalIndex < linkedList.size(); currNormalIndex++) {
            try {
                //System.out.println("普通脚本:" + linkedList.get(currNormalIndex));
                if (currNormalIndex < 0) currNormalIndex = 0;
                Step step = linkedList.get(currNormalIndex);
                beforStepRun(step);
                step.run();
            } catch (StepBackException e) {
                currNormalIndex -= 2;
            }
        }
    }

    public void baseRun() {
        if (statu == NORMAL) {
            statu = BASE;
            for (currBaseIndex = 0; currBaseIndex < baseList.size(); currBaseIndex++) {
                try {
                    if (currBaseIndex < 0) currBaseIndex = 0;
                    //System.out.println("base脚本:" + baseList.get(currBaseIndex));
                    baseList.get(currBaseIndex).run();
                } catch (StepBackException e) {
                    currNormalIndex -= 2;
                }
            }
            statu = NORMAL;
        }
    }

    public void ontStepRun(Step step) {
        step.mbRun();
        BaseStep.await();
    }

    public void beforStepRun(Step step) {
        htmlContent.observersRun(this);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public GlobalUtil getGlobalUtil() {
        return globalUtil;
    }

    public void setGlobalUtil(GlobalUtil globalUtil) {
        this.globalUtil = globalUtil;
    }
}


















