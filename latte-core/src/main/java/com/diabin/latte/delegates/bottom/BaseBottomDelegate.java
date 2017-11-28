package com.diabin.latte.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.diabin.latte.R;
import com.diabin.latte.R2;
import com.diabin.latte.delegates.LatteDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by fei on 2017/8/2.
 */

public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener{

    private final ArrayList<BottomTabBean>TAB_BEANS=new ArrayList<>();

    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES=new ArrayList<>();

    private final LinkedHashMap<BottomTabBean,BottomItemDelegate>ITEMS=new LinkedHashMap<>();
    //当前界面
    private int mCurrentDelegate=0;

    private int mIndexDelegate=0;

    private int mClickedColor= Color.RED;

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar=null;

    public abstract LinkedHashMap<BottomTabBean,BottomItemDelegate>setItems(ItemBuilder builder);


    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }
    //设置初始化第几个界面
    public abstract int setIndexDelegate();

    //设置点击后颜色
    @ColorInt
    public abstract int setClickedColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate=setIndexDelegate();
        if(setClickedColor()!=0){
            mClickedColor=setClickedColor();
        }
        final ItemBuilder builder=ItemBuilder.builder();
//   抽象方法实现后返回的结果，然后把结果存进ITEMS ，循环取出key,value 添加到tab_beans ,item_delegate
        final LinkedHashMap<BottomTabBean,BottomItemDelegate>items=setItems(builder);

        ITEMS.putAll(items);
        for (Map.Entry<BottomTabBean,BottomItemDelegate> item : ITEMS.entrySet()){
            final BottomTabBean key =item.getKey();
            final BottomItemDelegate value =item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //循环items
        final int size=ITEMS.size();
        for (int i=0;i<size;i++){
//            添加自定义的每个item到界面的bottombar
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout,mBottomBar);
            //获取每个子组件
            final RelativeLayout item= (RelativeLayout) mBottomBar.getChildAt(i);
            //设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            //子item的icon 和title
            final IconTextView itemIcon= (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle= (AppCompatTextView) item.getChildAt(1);
            //从tab_beans中获取每个索引然后初始化数据
            final BottomTabBean bean=TAB_BEANS.get(i);
            //初始化数据
            itemIcon.setText(bean.getIcon());
            itemTitle.setText(bean.getTitle());
            //如果下标等于
            if(i == mIndexDelegate){
                itemIcon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }
        }

        final SupportFragment[] delegateArray=ITEM_DELEGATES.toArray(new SupportFragment[size]);
        loadMultipleRootFragment(R.id.bottom_bar_delegate_container,mIndexDelegate,delegateArray);


    }
   //重置颜色
    private void resetColor(){
        final int count = mBottomBar.getChildCount();
        for (int i=0;i<count;i++){
            final RelativeLayout item= (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView itemIcon= (IconTextView) item.getChildAt(0);
            itemIcon.setTextColor(Color.GRAY);
            final AppCompatTextView itemTitle= (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);
        }
    }
   //点击后先重置颜色，然后显示当前的delegate,
    @Override
    public void onClick(View v) {
        final int tag= (int) v.getTag();
        resetColor();
        final RelativeLayout item= (RelativeLayout) v;
        final IconTextView itemIcon= (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickedColor);
        final AppCompatTextView itemTitle= (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickedColor);
        showHideFragment(ITEM_DELEGATES.get(tag),ITEM_DELEGATES.get(mCurrentDelegate));
        //注意先后顺序
        mCurrentDelegate=tag;
    }
}
