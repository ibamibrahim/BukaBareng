package com.bukantkpd.bukabareng.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bukantkpd.bukabareng.R;
import com.bukantkpd.bukabareng.api.model.UserModel;
import com.bukantkpd.bukabareng.api.remote.ApiUtils;
import com.bukantkpd.bukabareng.api.remote.BukBarAPIService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button loginButton;
    EditText username, password;
    String usernameText, passwordText;


    SharedPreferences sharedPreference;
    SharedPreferences.Editor sharedPreferenceEditor;

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
        sharedPreference = this.getSharedPreferences("bukabareng", Context.MODE_PRIVATE);
        sharedPreferenceEditor = sharedPreference.edit();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login_button_view:
                usernameText = username.getText().toString();
                passwordText = password.getText().toString();

                if(usernameText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(this, "Email dan password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    /*AsyncTaskRunner runner = new AsyncTaskRunner(this);
                    runner.execute(usernameText, passwordText);
                    */
                    login(usernameText, passwordText);
                    Log.d("DEBUG", " Login pressed");
                    break;
                }
        }
    }


    private void login(String username, String password){

        bbasService.getUsersDetail(username, password).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if(response.isSuccessful()){

                    String result = new Gson().toJson(response.body());
                    Log.d("LOGIN RESULT", result);

                    String token = response.body().getToken();
                    String username = response.body().getUserName();
                    String email = response.body().getEmail();
                    //int userId = response.body().getId();
                    boolean isLoggedIn = true;

                    Toast.makeText(LoginActivity.this, token, Toast.LENGTH_SHORT).show();;/*
                    sharedPreferenceEditor.putString("token", token);
                    sharedPreferenceEditor.putString("username", username);
                    sharedPreferenceEditor.putString("email", email);
                    sharedPreferenceEditor.putInt("userId", userId);
                    sharedPreferenceEditor.putBoolean("isLoggedIn", isLoggedIn);

                    sharedPreferenceEditor.commit();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);*/
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(LoginActivity.this, "Username atau password salah!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String>{
        ProgressDialog pd;

        public AsyncTaskRunner(LoginActivity login){
            pd = new ProgressDialog(login);
        }

        @Override
        protected void onPreExecute() {
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setMessage("Login...");

            pd.show();

        }


        @Override
        protected String doInBackground(String... params) {

            login(params[0], params[1]);

            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
        }
    }
}
