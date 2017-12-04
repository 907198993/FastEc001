package com.diabin.latte.ui.recycler;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * Created by yingping on 2017/12/3.
 */

//建造者
public class MultipleEntityBuilder {
    private  static final LinkedHashMap<Object,Object> FIELDS = new LinkedHashMap<>();

    public MultipleEntityBuilder() {
//        清除之前的数据
        FIELDS.clear();
    }

    public final MultipleEntityBuilder  setItemType(int itemType){
        FIELDS.put(MultipleFields.ITEM_TYPE,itemType);
        return this;
    }

    public final MultipleEntityBuilder  setField(Object key, Object value){
        FIELDS.put(key,value);
        return this;
    }

    public final MultipleEntityBuilder  setFields(LinkedHashMap<?,?> map){
        FIELDS.putAll(map);
        return this;
    }

    //当调用build时候，实例化MultipleItemEntity 。传入FIELDS
    public final MultipleItemEntity build(){
      return   new MultipleItemEntity(FIELDS);
    }
}
