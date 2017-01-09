package com.zzz.play.setp.copy;

import com.zzz.play.bean.LinkBean;

import java.util.Date;

/**
 * 八卦阵
 * Created by dell_2 on 2017/1/5.
 */
public class YouAnCi extends FuBen {
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
        return "统帅奇书";
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
        htmlContent.linkName("幽暗池");
        htmlContent.linkName("下:幽暗池↓");
        htmlContent.linkName("下:池底↓");
        htmlContent.linkName("进入白光处(副本)");
        while (htmlContent.getText().contains("中间有一层透明体，需要使用一定能量的")){
            htmlContent.linkName("返回游戏");
            menPiao();
        }
        if (htmlContent.getText().contains("今天你已进入")) {
            ableIn = false;
            lastDate = new Date();
            logger.error(htmlContent.htmlPanel.user.getName() + "幽暗次结束!");
            htmlContent.linkName("返回游戏");
            return false;
        }
        htmlContent.linkName("白光老人");
        htmlContent.linkName("兑换统帅奇书");
        htmlContent.linkName("返回白光老人");
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("下:湿碌小路↓");
        htmlContent.linkName("幽暗守卫");
        fight();
        htmlContent.linkName("进入冰路");
        htmlContent.linkName("冰雪巨熊");
        fight();
        htmlContent.linkName("下:冰路↓");
        htmlContent.linkName("冰雪巨熊");
        fight();
        htmlContent.linkName("冰雪巨熊");
        fight();
        htmlContent.linkName("下:白雪路↓");
        htmlContent.linkName("冰雪巨熊");
        fight();
        htmlContent.linkName("冰雪大巨熊");
        fight();
        htmlContent.linkName("下:白雪路↓");
        htmlContent.linkName("冰雪巨熊");
        fight();
        htmlContent.linkName("冰雪大巨熊");
        fight();
        htmlContent.linkName("下:巨型幽黑大门↓");
        htmlContent.linkName("下:幽暗大厅↓");
        htmlContent.linkName("左:西殿←");
        htmlContent.linkName("西殿殿主");
        fight();
        htmlContent.linkName("右:幽暗大厅→");
        htmlContent.linkName("右:东殿→");
        htmlContent.linkName("东殿殿主");
        fight();
        htmlContent.linkName("左:幽暗大厅←");
        htmlContent.linkName("双空插槽");
        htmlContent.linkName("插入钥匙");
        htmlContent.linkName("返回游戏");
        htmlContent.linkName("进入密道");
        htmlContent.linkName("下:密道↓");
        htmlContent.linkName("下:灵兽堂口↓");
        htmlContent.linkName("幽无邪");
        fight();
        htmlContent.linkName("进入灵兽堂");
        htmlContent.linkName("梦魇灵兽");
        fight();
        return true;
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



    /**
     * 刷门票
     */
    private void menPiao(){
        caiLiao();
        caiLiao();
        htmlContent.linkName("右:池底→");
        caiLiao();
        caiLiao();
        htmlContent.linkName("右:池底→");
        caiLiao();
        caiLiao();
        htmlContent.linkName("右:池底→");
        caiLiao();
        caiLiao();
        htmlContent.linkName("右:池底→");
        caiLiao();
        caiLiao();
        htmlContent.linkName("右:池底→");
        caiLiao();
        caiLiao();
        htmlContent.linkName("右:池底→");
        caiLiao();
        caiLiao();
        htmlContent.linkName("右:池底→");
        caiLiao();
        caiLiao();
        htmlContent.linkName("上:幽暗池↑");
        caiLiao();
        caiLiao();
        htmlContent.linkName("左:幽暗池←");
        caiLiao();
        caiLiao();
        htmlContent.linkName("左:幽暗池←");
        caiLiao();
        caiLiao();
        htmlContent.linkName("左:幽暗池←");
        caiLiao();
        caiLiao();
        htmlContent.linkName("左:幽暗池←");
        caiLiao();
        caiLiao();
        htmlContent.linkName("左:幽暗池←");
        caiLiao();
        caiLiao();
        htmlContent.linkName("左:幽暗池←");
        caiLiao();
        caiLiao();
        htmlContent.linkName("下:池底↓");
        caiLiao();
        caiLiao();
        htmlContent.linkName("右:池底→");
        htmlContent.linkName("进入白光处(副本)");
    }

    private void caiLiao(){
        LinkBean res = htmlContent.linkName("光电",true);
        if(!res.isSuccess()){
           return;
        }
        fight();
    }


}




















