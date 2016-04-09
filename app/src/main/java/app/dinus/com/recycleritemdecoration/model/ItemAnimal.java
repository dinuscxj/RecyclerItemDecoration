package app.dinus.com.recycleritemdecoration.model;


import android.support.annotation.DrawableRes;

public class ItemAnimal implements ItemDrawable {
    @DrawableRes
    private int mDrawableRes;

    private String mAnimalName;

    public ItemAnimal(int drawableRes, String animalName) {
        this.mDrawableRes = drawableRes;
        this.mAnimalName = animalName;
    }

    @Override
    public int getDrawableRes() {
        return mDrawableRes;
    }

    public String getAnimalName() {
        return mAnimalName;
    }
}
