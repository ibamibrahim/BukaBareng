package com.bukantkpd.bukabareng.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bukantkpd.bukabareng.R;
import com.chabbal.slidingdotsplash.SlidingSplashView;

public class PreLoginActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    ViewPager imageSlide;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);
        SlidingSplashView splashView  = (SlidingSplashView) findViewById(R.id.splash);
        splashView.addOnPageChangeListener(this);

        loginButton = (Button) findViewById(R.id.login_pre_button);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat.ttf");
        loginButton.setTypeface(custom_font);
        loginButton.setOnClickListener(this);

        isLoggedIn();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("OnPageScrolled", String.valueOf(position));
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("OnPageSelected", String.valueOf(position));

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d("PageScrollStateChanged", String.valueOf(state));

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login_pre_button:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void isLoggedIn(){
        SharedPreferences sp = this.getSharedPreferences("bukabareng", Context.MODE_PRIVATE);
        boolean isLoggedIn = sp.getBoolean("isLoggedIn", false);

        if(isLoggedIn){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
