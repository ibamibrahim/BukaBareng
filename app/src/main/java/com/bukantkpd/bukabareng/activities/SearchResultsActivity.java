package com.bukantkpd.bukabareng.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bukantkpd.bukabareng.R;
import com.bukantkpd.bukabareng.activities.ProductViewActivity;
import com.bukantkpd.bukabareng.adapters_and_items.SearchResultsAdapter;
import com.bukantkpd.bukabareng.adapters_and_items.SearchResultsItem;
import com.bukantkpd.bukabareng.api.model.ProductModel;
import com.bukantkpd.bukabareng.api.model.SearchResultListModel;
import com.bukantkpd.bukabareng.api.remote.ApiUtils;
import com.bukantkpd.bukabareng.api.remote.BukBarAPIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultsActivity extends AppCompatActivity implements SearchResultsAdapter.SearchResultsClickListener{

    RecyclerView recyclerView;
    private SearchResultsAdapter adapter;
    private BukBarAPIService bbasService;
    String query, token;
    SharedPreferences sharedPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        isLoggedIn();

        bbasService = ApiUtils.getBBASService();


        adapter = new SearchResultsAdapter(this, new ArrayList<ProductModel>(0));

        recyclerView = (RecyclerView) findViewById(R.id.search_results_list_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        sharedPreference = this.getSharedPreferences("bukabareng", Context.MODE_PRIVATE);
        query = getIntent().getStringExtra("searchQuery");
        token = sharedPreference.getString("token", null);
        Log.d("Search Query & token: ", query + " " + token);

        getProducts(query, token);

    }

    @Override
    public void onBuyButtonClicked(View view, int position) {
        Intent intent = new Intent(this, ProductViewActivity.class);
        startActivity(intent);
    }


    private void isLoggedIn(){
        SharedPreferences sp = this.getSharedPreferences("bukabareng", Context.MODE_PRIVATE);
        boolean isLoggedIn = sp.getBoolean("isLoggedIn", false);

        if(!isLoggedIn){
            Intent intent = new Intent(this, PreLoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void getProducts(String query, String token){
        bbasService.getProductSearch(query, token).enqueue(new Callback<SearchResultListModel>() {
            @Override
            public void onResponse(Call<SearchResultListModel> call, Response<SearchResultListModel> response) {
                if(response.isSuccessful()){
                    adapter.updateList(response.body().getProductsList());
                    Log.d("Search Result Act", "post loaded from API");
                } else {
                    int statusCode = response.code();
                    Log.d("ERROR API", statusCode+"");
                }
            }

            @Override
            public void onFailure(Call<SearchResultListModel> call, Throwable t) {
                t.printStackTrace();
                Log.d("MainActivity", "error loading from API");
            }
        });
    }
}
