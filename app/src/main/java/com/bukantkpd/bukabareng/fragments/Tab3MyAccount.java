package com.bukantkpd.bukabareng.fragments;

/**
 * Created by Ibam on 5/16/2017.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bukantkpd.bukabareng.R;
import com.bukantkpd.bukabareng.activities.PreLoginActivity;
import com.bukantkpd.bukabareng.activities.SearchResultsActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Tab3MyAccount extends Fragment implements View.OnClickListener{

    Button logOutButton;
    @Override
    @SuppressWarnings("deprecation")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3_my_account, container, false);

        logOutButton = (Button) rootView.findViewById(R.id.logout_button);

        logOutButton.setOnClickListener(this);

        Drawable redButton = getResources().getDrawable(R.drawable.button_red);
        logOutButton.setBackground(redButton);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.logout_button:

                SharedPreferences sp = this.getActivity().getSharedPreferences("bukabareng", Context.MODE_PRIVATE);
                SharedPreferences.Editor spEditor = sp.edit();

                spEditor.putBoolean("isLoggedIn", false);
                spEditor.commit();

                spEditor.clear();
                spEditor.commit();

                Intent intent = new Intent(getActivity(), PreLoginActivity.class);

                startActivity(intent);
                getActivity().finish();
                break;
        }
    }
}
