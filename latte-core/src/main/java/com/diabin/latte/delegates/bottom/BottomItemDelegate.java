package com.diabin.latte.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.diabin.latte.delegates.LatteDelegate;

/**
 * Created by yingping on 2017/11/27.
 */

//为每个界面添加一个返回推出功能。
public  abstract  class BottomItemDelegate extends LatteDelegate  implements View.OnKeyListener{
  private  long mExitTime = 0;
  private  static final int EXIT_TIME = 2000;


    @Override
    public void onResume() {
        super.onResume();
        //防止双击无效
        final View rootView = getView();
        if(rootView!=null){
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK&& event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-mExitTime)>mExitTime){
                Toast.makeText(getContext(),"双击退出",Toast.LENGTH_LONG).show();
                mExitTime = System.currentTimeMillis();
            }else{
                _mActivity.finish();
                if(mExitTime!=0){
                    mExitTime =0;
                }
            }
            return true;
        }
        return false;
    }
}
