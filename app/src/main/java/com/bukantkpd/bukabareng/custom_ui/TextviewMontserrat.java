package com.bukantkpd.bukabareng.custom_ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Ibam on 5/24/2017.
 */

public class TextviewMontserrat extends TextView {
    public TextviewMontserrat(Context context) {
        super(context);
    init(); }

    public TextviewMontserrat(Context context, AttributeSet attrs) {
        super(context, attrs);
    init(); }

    public TextviewMontserrat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    init(); }

    public TextviewMontserrat(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    init(); }

    public void init(){
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat.ttf");
        setTypeface(tf);
    }
}
