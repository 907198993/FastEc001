package com.diabin.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Switch;
import android.widget.Toast;

import com.diabin.latte.activities.ProxyActivity;

import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.ec.launcher.LauncherDelegate;
import com.diabin.latte.ec.sign.ISignListener;
import com.diabin.latte.ec.sign.SignInDelegate;
import com.diabin.latte.ec.sign.SignUpDelegate;
import com.diabin.latte.ui.launcher.ILauncherListener;
import com.diabin.latte.ui.launcher.OnLauncherFinishTag;

public class ExampleActivity extends ProxyActivity implements ISignListener,ILauncherListener{
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
//        return new SignUpDelegate();
        return new LauncherDelegate();
        //return new LauncherScrollDelegate();
    }

    @Override
    public void OnSignInSuccess() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void OnSignUpSuccess() {
        Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch(tag){
            case SIGNED:
                Toast.makeText(this,"用户登录了",Toast.LENGTH_LONG).show();
                break;
            case NOT_SIGNED:
                Toast.makeText(this,"用户没登录",Toast.LENGTH_LONG).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
