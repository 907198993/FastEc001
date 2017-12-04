package com.diabin.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.diabin.latte.app.Latte;
import com.diabin.latte.net.RestClient;
import com.diabin.latte.net.callback.ISuccess;

/**
 * Created by yingping on 2017/11/28.
 */
//刷新助手
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {
    private  final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout refresh_layout) {
        REFRESH_LAYOUT = refresh_layout;
//        监听滑动事件
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    private  void refresh(){
        //开始加载
       REFRESH_LAYOUT.setRefreshing(true);

    }

    public  void firstPage(String url){
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Latte.getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }
    @Override
    public void onRefresh() {

    }
}
