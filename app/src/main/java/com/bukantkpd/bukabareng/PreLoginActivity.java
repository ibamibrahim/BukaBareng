package com.bukantkpd.bukabareng;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

        loginButton.setOnClickListener(this);
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
}
