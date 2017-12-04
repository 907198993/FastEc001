package com.diabin.latte.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by yingping on 2017/12/4.
 */


public class MultipleViewHolder extends BaseViewHolder {
    private MultipleViewHolder(View view) {
        super(view);
    }
//    简单工厂模式包装
    public static MultipleViewHolder create(View view){
        return new MultipleViewHolder(view);
    }
}
