package com.bukantkpd.bukabareng.fragments;

/**
 * Created by Ibam on 5/16/2017.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bukantkpd.bukabareng.R;

public class Tab3MyAccount extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3_my_account, container, false);
        return rootView;
    }
}
