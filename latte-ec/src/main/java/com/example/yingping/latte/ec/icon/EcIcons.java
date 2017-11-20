package com.example.yingping.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by yingping on 2017/11/18.
 */

public enum  EcIcons implements Icon {
   icon_scan('\ue642');

    private  char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
