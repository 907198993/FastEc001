package com.diabin.latte.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.WeakHashMap;

/**
 * Created by yingping on 2017/12/3.
 */

public class MultipleItemEntity implements MultiItemEntity {
//   weakedreference  softrefenerce
    private  final ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QUEUE = new ReferenceQueue<>();
//    存储数据
   private  final LinkedHashMap<Object,Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
   private  final SoftReference<LinkedHashMap<Object,Object>> FIELDS_REFERENCE =
           new SoftReference<LinkedHashMap<Object, Object>>(MULTIPLE_FIELDS,ITEM_QUEUE);

    public MultipleItemEntity(LinkedHashMap<Object,Object> fields) {
        FIELDS_REFERENCE.get().putAll(fields);
    }

    public static MultipleEntityBuilder builder(){
        return new MultipleEntityBuilder();
    }
    //控制每个itemtype
    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    // 获取其他具体数据
    public final <T> T getField(Object key){
        return (T) FIELDS_REFERENCE.get().get(key);
    }
    //获取数据
    public final LinkedHashMap<?,?> getFields(){
        return FIELDS_REFERENCE.get();
    }
    //添加数据
    public final MultiItemEntity setField(Object key,Object value){
        FIELDS_REFERENCE.get().put(key,value);
        return this;
    }
 }
