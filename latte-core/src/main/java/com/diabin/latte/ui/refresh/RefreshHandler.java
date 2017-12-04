package com.diabin.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.diabin.latte.app.Latte;
import com.diabin.latte.net.RestClient;
import com.diabin.latte.net.callback.ISuccess;
import com.diabin.latte.ui.recycler.DataConverter;
import com.diabin.latte.ui.recycler.MultipleRecyclerAdapter;

/**
 * Created by yingping on 2017/11/28.
 */
//刷新助手
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{
    private  final SwipeRefreshLayout REFRESH_LAYOUT;
    private  final PagingBean BEAN;
    private  final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter =null;
    private final DataConverter CONVERTER;

    public RefreshHandler(SwipeRefreshLayout refresh_layout,
                          RecyclerView recyclerView,
                          DataConverter converter,
                          PagingBean bean) {
        REFRESH_LAYOUT = refresh_layout;
        this.RECYCLERVIEW =recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        //        监听滑动事件
        REFRESH_LAYOUT.setOnRefreshListener(this);


    }

    public static  RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView,
                                         DataConverter converter){
    return new RefreshHandler(swipeRefreshLayout,recyclerView,converter,new PagingBean());
    }
    private  void refresh(){
        //开始加载
       REFRESH_LAYOUT.setRefreshing(true);

    }

    public  void firstPage(String url){
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setTotal(object.getInteger("total"))
                            .setPageSize(object.getInteger("page_size"));
                        //设置adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this,RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                })
                .build()
                .get();
    }
    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
