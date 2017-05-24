package com.bukantkpd.bukabareng;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Ibam on 5/24/2017.
 */

public class ButtonMontserrat extends Button {
    public ButtonMontserrat(Context context) {
        super(context);
        init();
    }

    public ButtonMontserrat(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ButtonMontserrat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ButtonMontserrat(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Bold.ttf");
        setTypeface(tf);
    }
}
