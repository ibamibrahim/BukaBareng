package com.bukantkpd.bukabareng.fragments;

/**
 * Created by Ibam on 5/16/2017.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bukantkpd.bukabareng.R;
import com.bukantkpd.bukabareng.adapters_and_items.MyBuyingsAdapter;
import com.bukantkpd.bukabareng.api.model.UserBuyingListModel;
import com.bukantkpd.bukabareng.api.model.UserBuyingModel;
import com.bukantkpd.bukabareng.api.model.UserModel;
import com.bukantkpd.bukabareng.api.remote.ApiUtils;
import com.bukantkpd.bukabareng.api.remote.BukBarAPIService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab2MyBuyings extends  Fragment{

    RecyclerView recyclerView;
    private MyBuyingsAdapter adapter;
    BukBarAPIService bukBarAPIService;
    String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2_my_buyings, container, false);

        bukBarAPIService = ApiUtils.getBBASService();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_buyings_list_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MyBuyingsAdapter(new ArrayList<UserBuyingModel>(0),
                getContext());
        recyclerView.setAdapter(adapter);


        SharedPreferences preferences = this.getActivity().getSharedPreferences("bukabareng",
                Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String strObj = preferences.getString("userObj", null);
        UserModel user = gson.fromJson(strObj, UserModel.class);
        userId = user.getUserId()+"";

        FloatingActionButton fabRefresh = (FloatingActionButton) rootView.findViewById(R.id
                .fab_refresh);
        fabRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserTransaction(userId);
            }
        });
        getUserTransaction(userId);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserTransaction(userId);
    }

    public void getUserTransaction(String userID){
        bukBarAPIService.getUserTransaction(userID).enqueue(new Callback<UserBuyingListModel>() {
            @Override
            public void onResponse(Call<UserBuyingListModel> call, Response<UserBuyingListModel> response) {
                if(response.isSuccessful()){
                    String result = new Gson().toJson(response.body().getResults());
                    Log.d("JSON RESULT", result);
                    adapter.updateList(response.body().getResults());
                    Log.d("user buying Act", "post loaded from API");
                } else {
                    int statusCode = response.code();
                    Log.d("ERROR API", statusCode+"");
                }
            }

            @Override
            public void onFailure(Call<UserBuyingListModel> call, Throwable t) {

            }
        });
    }

}
