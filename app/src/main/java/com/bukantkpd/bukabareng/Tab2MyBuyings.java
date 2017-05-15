package com.bukantkpd.bukabareng;

/**
 * Created by Ibam on 5/16/2017.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tab2MyBuyings extends  Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2_my_buyings, container, false);
        return rootView;
    }
}
