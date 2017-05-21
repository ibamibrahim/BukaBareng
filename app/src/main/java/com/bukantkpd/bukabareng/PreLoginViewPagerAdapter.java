package com.bukantkpd.bukabareng;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Ibam on 5/21/2017.
 */

public class PreLoginViewPagerAdapter extends PagerAdapter{

    private Context context;
    private LayoutInflater layoutInflater;
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
