package util;

import ui.MainWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell_2 on 2016/10/29.
 */
public class GoodsUtil {

    private Map<String, Integer> goods = new HashMap<>();
    private MainWindow window;

    public  GoodsUtil(MainWindow window){
        this.window = window;
    }

    public void addGoods(String name) {
        name = name.trim();
        name = name.substring(0,name.length() - 2);
        Integer count = goods.get(name);
        if (count == null) {
            goods.put(name, 1);
        } else {
            goods.put(name, count + 1);
        }
        window.showGoods(goods);
    }
}
