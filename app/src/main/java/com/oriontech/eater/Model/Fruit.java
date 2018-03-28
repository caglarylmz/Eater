package com.oriontech.eater.Model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by 29182967598 on 26.03.2018.
 */

public class Fruit extends android.support.v7.widget.AppCompatImageView {
    Context context;
    Drawable drawable;
    int imageResourceId;
    public Fruit(Context context,int imageResourceId) {
        super(context);
        this.context=context;
        this.imageResourceId=imageResourceId;
    }

    public Fruit Initialize() {
        Fruit fruit = new Fruit(context,imageResourceId);
        fruit.setImageResource(imageResourceId);
        fruit.setX(-80);
        fruit.setY(-80);
        fruit.setMaxHeight(33);
        fruit.setMaxWidth(33);
        return fruit;
    }

}
