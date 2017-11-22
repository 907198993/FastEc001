package com.diabin.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.ec.R;
import com.diabin.latte.ui.launcher.LauncherHolderCreator;
import com.diabin.latte.ui.launcher.ScrollLauncherTag;
import com.diabin.latte.util.storage.LattePreference;

import java.util.ArrayList;

/**
 * Created by yingping on 2017/11/22.
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {
    private ConvenientBanner<Integer> mConvenientBanner = null;

    private  static  final ArrayList<Integer> RES = new ArrayList<>();


    private void init(){
        RES.add(R.mipmap.launcher1);
        RES.add(R.mipmap.launcher2);
        RES.add(R.mipmap.launcher3);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(),RES)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(true);
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        init();
    }

    @Override
    public void onItemClick(int position) {
   if(position == RES.size()-1){
       LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
         //检查用户是否登录了

      }
    }
}
