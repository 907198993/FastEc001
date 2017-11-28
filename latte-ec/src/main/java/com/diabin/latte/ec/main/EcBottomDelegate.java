package com.diabin.latte.ec.main;

import android.graphics.Color;

import com.diabin.latte.delegates.BaseDelegate;
import com.diabin.latte.delegates.bottom.BaseBottomDelegate;
import com.diabin.latte.delegates.bottom.BottomItemDelegate;
import com.diabin.latte.delegates.bottom.BottomTabBean;
import com.diabin.latte.delegates.bottom.ItemBuilder;
import com.diabin.latte.ec.main.index.IndexDelegate;
import com.diabin.latte.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by fei on 2017/8/2.
 */
//底部导航根，实现setitems方法，添加图标，文字和界面。同时初始化选中的颜色和第一个显示的界面
public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {

        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items=new LinkedHashMap<>();

        items.put(new BottomTabBean("{fa-home}","主页"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"),new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"),new IndexDelegate());
              //添加完资源后build
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
