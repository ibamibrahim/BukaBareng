package com.bukantkpd.bukabareng;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Ibam on 5/24/2017.
 */

public class EdittextMontserrat extends EditText {

    public EdittextMontserrat(Context context) {
        super(context);
    init(); }

    public EdittextMontserrat(Context context, AttributeSet attrs) {
        super(context, attrs);
    init(); }

    public EdittextMontserrat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    init();}

    public EdittextMontserrat(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    init(); }

    public void init(){
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat.ttf");
        setTypeface(tf);
    }

}
