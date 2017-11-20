package com.example.yingping.latte.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yingping on 2017/11/20.
 */

public class RequestCallbacks implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error) {
        REQUEST = request;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
    }

    @Override
    public void onResponse(Call call, Response response) {
    if(response.isSuccessful()){
        if(call.isExecuted()){
            if(SUCCESS!=null){
                SUCCESS.onSuccess(response.body().toString());
            }
        }
    }else {
        if(ERROR!=null){
            ERROR.onError(response.code(),response.message());
        }
    }
    }

    @Override
    public void onFailure(Call call, Throwable throwable) {
    if(FAILURE!=null){
            FAILURE.onFailure();
     }
     if(REQUEST!=null){
         REQUEST.onRequestEnd();
     }
    }
}
