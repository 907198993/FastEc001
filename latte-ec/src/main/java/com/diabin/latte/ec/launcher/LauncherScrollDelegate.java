package com.diabin.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.diabin.latte.app.AccountManager;
import com.diabin.latte.app.IUserChecker;
import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.ec.R;
import com.diabin.latte.ui.launcher.ILauncherListener;
import com.diabin.latte.ui.launcher.LauncherHolderCreator;
import com.diabin.latte.ui.launcher.OnLauncherFinishTag;
import com.diabin.latte.ui.launcher.ScrollLauncherTag;
import com.diabin.latte.util.storage.LattePreference;

import java.util.ArrayList;

/**
 * Created by yingping on 2017/11/22.
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {
    private ConvenientBanner<Integer> mConvenientBanner = null;
    private ILauncherListener mILauncherListener = null;
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ILauncherListener){
            mILauncherListener= (ILauncherListener) activity;
        }
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
       AccountManager.checkAccount(new IUserChecker() {
           @Override
           public void onSignIn() {
               if(mILauncherListener!=null){
                   mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
               }
           }

           @Override
           public void onNotSignIn() {
               if(mILauncherListener!=null){
                   mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
               }
           }
       });

      }
    }
}
