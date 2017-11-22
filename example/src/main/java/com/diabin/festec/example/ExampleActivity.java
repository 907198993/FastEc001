package com.diabin.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.diabin.latte.activities.ProxyActivity;

import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.ec.launcher.LauncherDelegate;
import com.diabin.latte.ec.launcher.LauncherScrollDelegate;

public class ExampleActivity extends ProxyActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       final ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }


    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
        //return new LauncherScrollDelegate();
    }
}