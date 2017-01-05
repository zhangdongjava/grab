package com.zzz.play.setp.copy;

import com.zzz.play.bean.LinkBean;

import java.util.Date;

/**
 * 八卦阵
 * Created by dell_2 on 2017/1/5.
 */
public class BaGuaZhen extends FuBen {
    @Override
    public String inClearLine() {
        return null;
    }

    @Override
    public String outClearLine() {
        return null;
    }

    @Override
    public String saveLine() {
        return "四象符,星宿符,八卦符";
    }

    @Override
    public void ready() {
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("赤甲", true);
        htmlContent.linkName("灵犀玉兔");
        htmlContent.linkName("五彩神牛");
        htmlContent.linkName("装备");
        htmlContent.linkName("修理所有");
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("主C", true);
        htmlContent.linkName("灵犀玉兔");
        htmlContent.linkName("五彩神牛");
        htmlContent.linkName("装备");
        htmlContent.linkName("修理所有");
        htmlContent.linkName("返回游戏");
    }

    @Override
    public boolean fbRun() {
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("八卦阵");
        htmlContent.linkName("无极真人");
        htmlContent.linkName("进入五星七曜八卦阵");
        if (htmlContent.getText().contains("今天你已进入")) {
            ableIn = false;
            lastDate = new Date();
            logger.error(htmlContent.htmlPanel.user.getName() + "进入五星七曜八卦阵结束!");
            htmlContent.linkName("返回游戏");
            return false;
        }
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("右:少阳→");
        htmlContent.linkName("右:艮位→");
        htmlContent.linkName("右:东宫苍龙→");
        goFight("右:房→");
        goFight("上:氐↑");
        goFight("右:亢→");
        goFight("上:角↑");
        goFight("下:亢↓");
        goFight("左:氐←");
        goFight("下:房↓");
        goFight("下:心↓");
        goFight("右:尾→");
        goFight("下:箕↓");
        htmlContent.linkName("上:尾↑");
        htmlContent.linkName("左:心←");
        htmlContent.linkName("上:房↑");
        htmlContent.linkName("左:东宫苍龙←");
        shengShou();
        htmlContent.linkName("左:艮位←");
        htmlContent.linkName("左:少阳←");
        htmlContent.linkName("上:坤位↑");
        htmlContent.linkName("左:太阳←");
        htmlContent.linkName("上:乾位↑");
        htmlContent.linkName("上:北宫玄武↑");
        goFight("上:虚↑");
        goFight("左:女←");
        goFight("上:牛↑");
        goFight("左:斗←");
        goFight("右:牛→");
        goFight("下:女↓");
        goFight("右:虚→");
        goFight("右:危→");
        goFight("上:室↑");
        goFight("右:壁→");
        htmlContent.linkName("左:室←");
        htmlContent.linkName("下:危↓");
        htmlContent.linkName("左:虚←");
        htmlContent.linkName("下:北宫玄武↓");
        shengShou();
        htmlContent.linkName("下:乾位↓");
        htmlContent.linkName("下:太阳↓");
        htmlContent.linkName("下:阵心↓");
        htmlContent.linkName("左:少阴←");
        htmlContent.linkName("左:离位←");
        htmlContent.linkName("左:西宫白虎←");
        goFight("左:昂←");
        goFight("上:胃↑");
        goFight("左:娄←");
        goFight("上:奎↑");
        goFight("下:娄↓");
        goFight("右:胃→");
        goFight("下:昂↓");
        goFight("下:毕↓");
        goFight("左:觜←");
        goFight("下:参↓");
        htmlContent.linkName("上:觜↑");
        htmlContent.linkName("右:毕→");
        htmlContent.linkName("上:昂↑");
        htmlContent.linkName("右:西宫白虎→");
        shengShou();
        htmlContent.linkName("右:离位→");
        htmlContent.linkName("右:少阴→");
        htmlContent.linkName("右:阵心→");
        htmlContent.linkName("下:太阴↓");
        htmlContent.linkName("下:巽位↓");
        htmlContent.linkName("下:南宫朱雀↓");
        goFight("下:星↓");
        goFight("左:柳←");
        goFight("下:鬼↓");
        goFight("左:井←");
        goFight("右:鬼→");
        goFight("上:柳↑");
        goFight("右:星→");
        goFight("右:张→");
        goFight("下:翼↓");
        goFight("右:轸→");
        htmlContent.linkName("左:翼←");
        htmlContent.linkName("上:张↑");
        htmlContent.linkName("左:星←");
        htmlContent.linkName("上:南宫朱雀↑");
        shengShou();
        htmlContent.linkName("上:巽位↑");
        baGua();
        htmlContent.linkName("上:太阴↑");
        htmlContent.linkName("左:震位←");
        baGua();
        htmlContent.linkName("上:少阴↑");
        htmlContent.linkName("左:离位←");
        baGua();
        htmlContent.linkName("右:少阴→");
        htmlContent.linkName("上:兑位↑");
        baGua();
        htmlContent.linkName("右:太阳→");
        htmlContent.linkName("上:乾位↑");
        baGua();
        htmlContent.linkName("下:太阳↓");
        htmlContent.linkName("右:坤位→");
        baGua();
        htmlContent.linkName("下:少阳↓");
        htmlContent.linkName("右:艮位→");
        baGua();
        htmlContent.linkName("左:少阳←");
        htmlContent.linkName("下:坎位↓");
        baGua();
        htmlContent.linkName("左:太阴←");
        siXiang();
        htmlContent.linkName("上:阵心↑");
        htmlContent.linkName("左:少阴←");
        siXiang();
        htmlContent.linkName("右:阵心→");
        htmlContent.linkName("上:太阳↑");
        siXiang();
        htmlContent.linkName("下:阵心↓");
        htmlContent.linkName("右:少阳→");
        siXiang();
        htmlContent.linkName("左:阵心←");
        htmlContent.linkName("无敌.阵魂");
        fight();
        return true;
    }


    private void goFight(String action) {
        htmlContent.linkName(action);
        LinkBean res = htmlContent.linkName("星宿.", true);
        if (!res.isSuccess()) {
            return;
        }
        fight();
    }

    private void fight() {
        htmlContent.linkName("攻击", true);
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("x",true);
        htmlContent.linkName("x",true);
        htmlContent.linkName("x",true);
        htmlContent.linkName("x",true);
        htmlContent.linkName("x",true);
        htmlContent.linkName("x",true);
        htmlContent.linkName("x",true);
        htmlContent.linkName("x",true);
        htmlContent.linkName("x",true);
        htmlContent.linkName("返回游戏");
    }

    private void shengShou() {
        LinkBean res = htmlContent.linkName("圣兽.", true);
        if (!res.isSuccess()) {
            return;
        }
        fight();
    }

    private void baGua(){
        LinkBean res = htmlContent.linkName("八卦.", true);
        if (!res.isSuccess()) {
            return;
        }
        fight();
    }

    private void siXiang(){
        LinkBean res = htmlContent.linkName("四象.", true);
        if (!res.isSuccess()) {
            return;
        }
        fight();
    }


}




















