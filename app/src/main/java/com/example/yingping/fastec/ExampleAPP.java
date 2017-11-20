package com.example.yingping.fastec;

import android.app.Application;

import com.example.yingping.latte.app.Latte;
import com.example.yingping.latte.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by yingping on 2017/11/18.
 */

public class ExampleApp  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this).

                withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1")
                .
                configure();
    }
}
