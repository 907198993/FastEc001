package com.diabin.latte.app;

import com.diabin.latte.util.storage.LattePreference;

/**
 * Created by fei on 2017/8/1.
 */

// 管理用户信息
public class AccountManager {

    private enum SignTag{
        SIGN_TAG
    }

    //保存用户登录状态，登陆后调用
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    //是否登录了
    private static boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    //检查
    public static void checkAccount(IUserChecker checker){
        if(isSignIn()){
            //执行登录的回调
            checker.onSignIn();
        }else {
            //没有登录的回调
            checker.onNotSignIn();
        }
    }

}
