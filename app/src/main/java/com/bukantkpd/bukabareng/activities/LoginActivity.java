package com.bukantkpd.bukabareng.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bukantkpd.bukabareng.R;
import com.bukantkpd.bukabareng.api.model.UserModel;
import com.bukantkpd.bukabareng.api.remote.ApiUtils;
import com.bukantkpd.bukabareng.api.remote.BukBarAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button loginButton;
    EditText username, password;
    String usernameText, passwordText;
    private BukBarAPIService bbasService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat.ttf");


        bbasService = ApiUtils.getBBASService();

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

                usernameText = username.getText().toString();
                passwordText = password.getText().toString();

                login(usernameText, passwordText);


                startActivity(intent);
                Log.d("DEBUG"," Login pressed");
                break;
        }
    }

    private void login(String username, String password){

        bbasService.getUsersDetail(username, password).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if(response.isSuccessful()){
                    String token = response.body().getToken();
                    Log.d("token ", token);
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }
}
