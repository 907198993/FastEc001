package com.diabin.latte.app;

import android.content.Context;


/**
 * Created by fei on 2017/7/30.
 */

public final class Latte {

public static Configurator init(Context context){
//getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
    Configurator.getInstance().getLatteConfigs().put(ConfigType.APPLICATION_CONTEXT,context.getApplicationContext());
    return Configurator.getInstance();
}

//public static HashMap<Object,Object> getConfigurations(){
//    return Configurator.getInstance().getLatteConfigs();
//}
//
//public static Context getApplication(){
//    return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
//}

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigType.APPLICATION_CONTEXT);
    }

}
