package com.bukantkpd.bukabareng;

import android.app.Application;


/**
 * Created by Ibam on 5/24/2017.
 */

public class CustomFontApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String fontLocation = "fonts/Montserrat-Bold.ttf";
        FontOverride.setDefaultFont(this, "DEFAULT", fontLocation);
        FontOverride.setDefaultFont(this, "MONOSPACE", fontLocation);
        FontOverride.setDefaultFont(this, "SERIF", fontLocation);
        FontOverride.setDefaultFont(this, "SANS_SERIF", fontLocation);
    }
}
