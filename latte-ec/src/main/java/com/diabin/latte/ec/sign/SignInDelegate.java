package com.diabin.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.View;
import android.support.design.widget.TextInputEditText;
import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.ec.R;
import com.diabin.latte.ec.R2;
import com.diabin.latte.net.RestClient;
import com.diabin.latte.net.callback.ISuccess;
import com.diabin.latte.util.log.LatteLogger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by yingping on 2017/11/26.
 */
//登录界面
public class SignInDelegate  extends LatteDelegate{

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword=null;

    @OnClick(R2.id.btn_sign_in)
    void  onClickSignIn(){
     if(checkForm()){
         RestClient.builder()
                 .url("http://114.67.235.114/RestServer/api/user_profile.php")
                 .params("email",mEmail.toString())
                 .params("password",mPassword.toString())
                 .success(new ISuccess() {
                     @Override
                     public void onSuccess(String response) {
                         LatteLogger.json("user_profile",response);
                         SignHandler.onSignIn(response,mISignListener);
                     }
                 })
                 .build()
                 .post();
     }
    }

    //去注册
    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink(){
        start(new SignUpDelegate());
    }

    private  ISignListener mISignListener = null;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof  ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }


    private boolean checkForm(){


        final String email=mEmail.getText().toString();
        final String password=mPassword.getText().toString();


        boolean isPass=true;



        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("邮箱输入有误！");
            isPass=false;
        }else {
            mEmail.setError(null);
        }


        if(password.isEmpty() || password.length()<6){
            mPassword.setError("请填写至少6位的密码！");
            isPass=false;
        }else {
            mPassword.setError(null);
        }


        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
