package com.zzz.play.setp.copy;

import com.zzz.play.bean.LinkBean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dell_2 on 2016/11/19.
 */
public class DiXiaGuCheng extends FuBen {

    Set<String> gws = new HashSet<>();
    LinkBean linkBean;

    public DiXiaGuCheng() {
        gws.add("了望守卫(魂)");
        gws.add("天兵(魂)");
        gws.add("地兵(魂)");
        gws.add("阁楼护卫(魂");
        gws.add("四方守卫(魂)");
        gws.add("太守(魂)");
        gws.add("玄武(魂)");
        gws.add("青龙(魂)");
        gws.add("白虎(魂)");
        gws.add("朱雀(魂)");
    }

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
        return "魂魄";
    }

    @Override
    public void ready() {

    }

    @Override
    public boolean fbRun() {
        htmlContent.linkName("功能菜单");
        htmlContent.linkName("神行千里");
        htmlContent.linkName("地下古城");
        if (htmlContent.getText().contains("今天你已进入")) {
            ableIn = false;
            lastDate = new Date();
            logger.error(htmlContent.htmlPanel.user.getName()  + "地下古城结束!");
            htmlContent.linkName("返回游戏");
            return false;
        }
        htmlContent.linkName("进入古城中心");
        gcdb("上:古城北街↑");
        gcdb("上:废墟↑");
        gcdb("上:废墟↑");
        gcdb("下:废墟↓");
        htmlContent.linkName("下:古城北街↓");
        gcdb("左:废墟←");
        gcdb("左:废墟←");
        gcdb("左:废墟←");
        gcdb("左:城墙←");
        gcdb("上:城墙↑");
        gcdb("右:废墟→");
        gcdb("右:西北阁→");
        gcdb("左:废墟←");
        gcdb("左:城墙←");
        gcdb("下:城墙↓");
        gcdb("右:废墟→");
        gcdb("右:废墟→");
        gcdb("右:废墟→");
        gcdb("上:废墟↑");
        gcdb("上:废墟↑");
        gcdb("左:废墟←");
        gcdb("左:将军府←");
        htmlContent.linkName("镇边将军", true);
        zhandou();
        gcdb("左:城墙←");
        gcdb("上:西北城角↑");
        gcdb("右:城墙→");
        gcdb("右:城墙→");
        gcdb("右:城墙→");
        gcdb("进入玄武");
        htmlContent.linkName("左:城墙←");
        htmlContent.linkName("左:城墙←");
        htmlContent.linkName("左:城墙←");
        htmlContent.linkName("左:城墙←");
        htmlContent.linkName("左:西北城角←");
        htmlContent.linkName("下:城墙↓");
        htmlContent.linkName("右:将军府→");
        htmlContent.linkName("右:废墟→");
        htmlContent.linkName("右:废墟→");
        htmlContent.linkName("下:废墟↓");
        htmlContent.linkName("下:废墟↓");
        htmlContent.linkName("右:古城北街→");
        htmlContent.linkName("下:古城中心↓");
        gcdb("右:古城东街→");
        gcdb("上:废墟↑");
        gcdb("上:废墟↑");
        gcdb("上:废墟↑");
        gcdb("右:废墟→");
        gcdb("右:废墟→");
        gcdb("下:太守府↓");
        gcdb("左:东北阁←");
        htmlContent.linkName("右:太守府→");
        htmlContent.linkName("上:废墟↑");
        htmlContent.linkName("左:废墟←");
        htmlContent.linkName("左:废墟←");
        htmlContent.linkName("左:废墟←");
        htmlContent.linkName("下:废墟↓");
        htmlContent.linkName("下:废墟↓");
        htmlContent.linkName("下:废墟↓");
        htmlContent.linkName("下:古城东街↓");
        gcdb("右:废墟→");
        gcdb("上:废墟↑");
        gcdb("右:废墟→");
        gcdb("右:城墙→");
        gcdb("进入青龙");
        gcdb("上:城墙↑");
        gcdb("上:城墙↑");
        gcdb("上:城墙↑");
        gcdb("上:东北城角↑");
        gcdb("左:城墙←");
        gcdb("左:城墙←");
        gcdb("左:城墙←");
        htmlContent.linkName("右:城墙→");
        htmlContent.linkName("右:城墙→");
        htmlContent.linkName("右:东北城角→");
        htmlContent.linkName("下:城墙↓");
        htmlContent.linkName("下:城墙↓");
        htmlContent.linkName("下:城墙↓");
        htmlContent.linkName("左:废墟←");
        gcdb("下:废墟↓");
        gcdb("左:废墟←");
        htmlContent.linkName("左:古城东街←");
        htmlContent.linkName("左:古城中心←");
        gcdb("左:古城西街←");
        gcdb("左:废墟←");
        gcdb("左:废墟←");
        gcdb("下:废墟↓");
        gcdb("下:废墟↓");
        gcdb("下:废墟↓");
        gcdb("下:废墟↓");
        gcdb("右:废墟→");
        gcdb("下:城墙↓");
        gcdb("左:城墙←");
        gcdb("左:西南城角←");
        gcdb("上:城墙↑");
        gcdb("上:城墙↑");
        gcdb("上:城墙↑");
        gcdb("进入白虎");
        htmlContent.linkName("下:城墙↓");
        htmlContent.linkName("下:城墙↓");
        htmlContent.linkName("下:城墙↓");
        htmlContent.linkName("下:城墙↓");
        htmlContent.linkName("下:西南城角↓");
        htmlContent.linkName("右:城墙→");
        htmlContent.linkName("右:城墙→");
        htmlContent.linkName("上:废墟↑");
        htmlContent.linkName("左:废墟←");
        htmlContent.linkName("上:废墟↑");
        htmlContent.linkName("上:废墟↑");
        htmlContent.linkName("上:废墟↑");
        htmlContent.linkName("右:废墟→");
        htmlContent.linkName("右:古城西街→");
        gcdb("下:废墟↓");
        gcdb("左:废墟←");
        gcdb("下:西南阁↓");
        gcdb("右:废墟→");
        gcdb("下:废墟↓");
        gcdb("下:城墙↓");
        htmlContent.linkName("上:废墟↑");
        htmlContent.linkName("上:废墟↑");
        htmlContent.linkName("左:西南阁←");
        htmlContent.linkName("上:废墟↑");
        htmlContent.linkName("右:废墟→");
        htmlContent.linkName("上:古城西街↑");
        htmlContent.linkName("右:古城中心→");
        gcdb("下:古城南街↓");
        gcdb("右:废墟→");
        gcdb("右:废墟→");
        gcdb("右:废墟→");
        gcdb("右:废墟→");
        gcdb("右:城墙→");
        gcdb("下:城墙↓");
        gcdb("下:城墙↓");
        gcdb("下:东南城角↓");
        htmlContent.linkName("上:城墙↑");
        htmlContent.linkName("上:城墙↑");
        htmlContent.linkName("上:城墙↑");
        htmlContent.linkName("左:废墟←");
        htmlContent.linkName("左:废墟←");
        gcdb("下:东南阁↓");
        gcdb("下:废墟↓");
        gcdb("下:城墙↓");
        gcdb("右:城墙→");
        gcdb("上:废墟↑");
        gcdb("上:废墟↑");
        htmlContent.linkName("下:废墟↓");
        htmlContent.linkName("下:城墙↓");
        htmlContent.linkName("左:城墙←");
        htmlContent.linkName("上:废墟↑");
        htmlContent.linkName("上:东南阁↑");
        gcdb("左:废墟←");
        gcdb("左:废墟←");
        gcdb("下:废墟↓");
        gcdb("右:废墟→");
        gcdb("下:城墙↓");
        gcdb("进入朱雀");
        return false;
    }

    private void gcdb(String action) {
        htmlContent.linkName(action);
        for (String gw : gws) {
            linkBean = htmlContent.linkName(gw, true);
            if (linkBean.isSuccess()) {
                break;
            }
        }
        if (!linkBean.isSuccess()) return;
        htmlContent.linkName("攻击", true);
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("返回游戏");
    }

    private void gcdb() {
        htmlContent.linkName("古城地兵(魂", true);

    }

    private void zhandou() {
        htmlContent.linkName("攻击", true);
        while (htmlContent.exitsName("普通攻击")) {
            htmlContent.linkName("普通攻击");
        }
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("x", true);
        htmlContent.linkName("返回游戏");
    }

}
