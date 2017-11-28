package com.diabin.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.diabin.latte.delegates.bottom.BottomItemDelegate;
import com.diabin.latte.ec.R;


/**
 * Created by fei on 2017/8/2.
 */

public class IndexDelegate extends BottomItemDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {


    }
}
