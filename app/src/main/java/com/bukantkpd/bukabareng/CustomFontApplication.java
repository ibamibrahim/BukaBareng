package com.bukantkpd.bukabareng;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Ibam on 5/24/2017.
 */

public class CustomFontApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Montserrat-bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
