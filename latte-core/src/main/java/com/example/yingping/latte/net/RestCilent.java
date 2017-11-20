package com.example.yingping.latte.net;

import com.example.yingping.latte.net.callback.IError;
import com.example.yingping.latte.net.callback.IFailure;
import com.example.yingping.latte.net.callback.IRequest;
import com.example.yingping.latte.net.callback.ISuccess;
import com.example.yingping.latte.net.callback.RequestCallbacks;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by yingping on 2017/11/20.
 */

public class RestCilent {
    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS =RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;


    public RestCilent(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
    }

    public  static RestClientBuilder builder(){
        return new RestClientBuilder();
    }
    private  void request(HttpMethod method){
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if(REQUEST!=null){
            REQUEST.onRequestStart();
        }
        switch (method){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.get(URL,PARAMS);
                break;
            case PUT:
                call = service.get(URL,PARAMS);
                break;
            case DELETE:
                call = service.get(URL,PARAMS);
                break;
            default:
                break;

        }
        if(call!=null){
            call.enqueue(getRequestCallback());
        }
    }
    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(REQUEST,SUCCESS,FAILURE,ERROR);
    }

    public final void get(){
       request(HttpMethod.GET);
    }

    public final  void post(){
        request(HttpMethod.POST);
    }
    public final  void put(){
        request(HttpMethod.PUT);
    }
    public final  void delete(){
        request(HttpMethod.DELETE);
    }
}
