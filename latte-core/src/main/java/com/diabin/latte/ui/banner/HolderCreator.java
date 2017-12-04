package com.diabin.latte.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by yingping on 2017/12/4.
 */

public class HolderCreator implements CBViewHolderCreator {
    @Override
    public Object createHolder() {
        return new ImageHolder();
    }
}
