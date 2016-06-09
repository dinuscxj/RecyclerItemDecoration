package com.dinuscxj.recycleritemdecoration.model;

import android.support.annotation.DrawableRes;

public class ItemCartoon implements ItemDrawable {
    @DrawableRes
    private int mDrawableRes;

    private String mCartoon;

    public ItemCartoon(int drawableRes, String cartoon) {
        this.mDrawableRes = drawableRes;
        this.mCartoon = cartoon;
    }

    @Override
    public int getDrawableRes() {
        return mDrawableRes;
    }

    public String getCartoonName() {
        return mCartoon;
    }
}
