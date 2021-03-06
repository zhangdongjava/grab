package com.zzz.play.setp;

import com.alibaba.fastjson.JSON;
import com.zzz.play.core.CoreController;
import com.zzz.play.exception.StepBackException;
import com.zzz.play.inter.Runable;
import com.zzz.play.mark.Global;
import com.zzz.play.setp.copy.*;
import com.zzz.play.setp.impl.config.ClearStep;
import com.zzz.play.setp.impl.config.ManyStep;
import com.zzz.play.setp.material.compound.JingGang;
import com.zzz.play.setp.material.compound.KuangBao;
import com.zzz.play.setp.material.compound.KuangBaoEr;
import com.zzz.play.setp.material.compound.QiangTi;
import com.zzz.play.setp.sys.*;
import com.zzz.play.util.HtmlContent;
import com.zzz.play.util.StepUtil;
import com.zzz.play.util.UtilDto;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class TextParse implements Runable {

    public final int NORMAL = 0;
    public final int BASE = 1;
    public final int TIME = 2;

    public int statu = 0;

    private LinkedList<Step> linkedList;
    private LinkedList<Step> baseList;

    private InputStream inputStream;


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

    private UtilDto utilDto;

    /**
     * 核心控制器
     */
    private CoreController controller;
    /**
     * 是否清理
     */
    private boolean clear;

    private int runNum;

    /**
     * 运行参数
     */
    private Runable.RunConfig config;

    public TextParse() {
        linkedList = new LinkedList<>();
        baseList = new LinkedList<>();
    }

    public static TextParse getInstance(String file, HtmlContent htmlContent, UtilDto utilDto, CoreController controller) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        TextParse textParse = new TextParse();
        textParse.inputStream = new FileInputStream(file);
        textParse.htmlContent = htmlContent;
        textParse.utilDto = utilDto;
        textParse.controller = controller;
        textParse.bulid();
        return textParse;
    }

    public static TextParse getInstance(InputStream inputStream, HtmlContent htmlContent, UtilDto utilDto, CoreController controller) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        TextParse textParse = new TextParse();
        textParse.inputStream = inputStream;
        textParse.htmlContent = htmlContent;
        textParse.utilDto = utilDto;
        textParse.controller = controller;
        textParse.bulid();
        return textParse;
    }

    private void bulid() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        linkedList.clear();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();
        config = JSON.parseObject(line, RunConfig.class);
        int index = 1;
        line = reader.readLine();
        while (line != null) {
            if (!line.trim().equals("")) {
                Step step = addStep(line.trim());
                if (step != null) {
                    step.setLineNum(++index);
                    step.setUtilDto(utilDto);
                    step.setCoreController(controller);
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
        Step step = StepUtil.getClassSetp(line);
        if (step == null && line.length() > 3) {
            if (line.startsWith("sale2")) {
                step = new GoodsSale2(line.substring(5));
            } else if (line.startsWith("save2")) {
                step = new GoodsSave2(line.substring(5));
            } else {
                step = StepUtil.getClassFormParamSetp(line);
            }

        }
        if (step == null) {
            if (line.startsWith("class")) {
                step = StepUtil.getClassNameSetp(line.substring(5));
            } else if (line.startsWith("b")) {
                step = StepUtil.getStep(line.substring(1));
                step.setBase(true);
            } else if (line.startsWith("mb")) {
                step = StepUtil.getStep(line.substring(2));
                step.setMb(true);
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
        }

        if (step != null) {
            step.setStep(this);
            step.setHtmlContent(htmlContent);
            if (!inMang) {
                if (markSetp(step))
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
        manyStep.setUtilDto(utilDto);
        manyStep.setHtmlContent(htmlContent);
        manyStep.setCoreController(controller);
        inMang = true;
        return manyTypeJudge();
    }

    private boolean manyTypeJudge() {
        //全局
        if (manyStep instanceof Global) {
            //加入全局脚本中
            controller.addGlobal(manyStep);
            //返回false  不加入本textParse中
            return false;
        }
        return true;
    }

    /**
     * @param step
     * @return 不是标记
     */
    private boolean markSetp(Step step) {
        return controller.addMarkStep(step);
    }


    public boolean run() {
        if (config.getCount() == 0) {
            return false;
        }
        config.countJian();
        boolean exec = false;
        htmlContent.clickFresh();
        for (currNormalIndex = 0; currNormalIndex < linkedList.size(); currNormalIndex++) {
            try {
                //System.out.println("普通脚本:" + linkedList.get(currNormalIndex));
                if (currNormalIndex < 0) currNormalIndex = 0;
                Step step = linkedList.get(currNormalIndex);
                controller.stepRunBefore();
                exec = step.run() || exec;
                controller.stepRunAfter();
            } catch (StepBackException e) {
                currNormalIndex -= 2;
            }
        }
        return exec;
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
    }


    public String getFileName() {
        return fileName;
    }

    @Override
    public boolean isClear() {
        return config.isClear();
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public static void main(String[] args) {
        RunConfig config = JSON.parseObject("{clear:true}", RunConfig.class);
        System.out.println(config);
    }

}


















