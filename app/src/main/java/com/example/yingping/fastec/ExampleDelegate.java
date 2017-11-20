package com.example.yingping.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.yingping.latte.delegates.LatteDelegate;
import com.example.yingping.latte.net.RestCilent;
import com.example.yingping.latte.net.callback.IError;
import com.example.yingping.latte.net.callback.IFailure;
import com.example.yingping.latte.net.callback.ISuccess;

/**
 * Created by yingping on 2017/11/19.
 */

public class ExampleDelegate  extends LatteDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        test();
    }
    private  void test(){
        RestCilent.builder()
                 .url("http://news.baidu.com")
                 .success(new ISuccess() {
                     @Override
                     public void onSuccess(String response) {
                         System.out.print(response);
                         Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                     }
                 })
                 .failure(new IFailure() {
                     @Override
                     public void onFailure() {

                     }
                 })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
