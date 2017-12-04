package com.diabin.latte.ui.recycler;

import java.util.ArrayList;

/**
 * Created by yingping on 2017/12/3.
 */
//    数据转换基类
public  abstract  class DataConverter {

    protected  final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();

    private  String mJsonData = null;

    public  abstract  ArrayList<MultipleItemEntity> convert();
   //把json 数据添加到mjsondata.
    public DataConverter setJsonData(String json){
        this.mJsonData = json;
        return this;
    }
  // 获取添加的json数据
    protected String getJsonData(){
        if(mJsonData == null || mJsonData.isEmpty()){
            throw  new NullPointerException("data is null");
        }else{
            return mJsonData;
        }
    }
}
