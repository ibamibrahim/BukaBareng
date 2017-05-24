package com.bukantkpd.bukabareng;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button loginButton;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.ttf");


        loginButton = (Button) findViewById(R.id.login_button_view);
        username = (EditText) findViewById(R.id.username_view);
        password = (EditText) findViewById(R.id.password_view);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login_button_view:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Log.d("DEBUG"," Login pressed");
                break;
        }
    }
}
