package com.example.yingping.fastec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.yingping.latte.activities.ProxyActivity;
import com.example.yingping.latte.app.Latte;
import com.example.yingping.latte.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
