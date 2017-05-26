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
    private List<SearchResultsItem> searchResultsItemList;

    private BukBarAPIService bbasService;
    String query, token;
    SharedPreferences sharedPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        isLoggedIn();

        bbasService = ApiUtils.getBBASService();

        recyclerView = (RecyclerView) findViewById(R.id.search_results_list_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        sharedPreference = this.getSharedPreferences("bukabareng", Context.MODE_PRIVATE);

        query = getIntent().getStringExtra("searchQuery");
        token = sharedPreference.getString("token", null);

        Log.d("Search Query & token: ", query + " " + token);

        searchResultsItemList = getProducts(query, token);

        adapter = new SearchResultsAdapter(searchResultsItemList, this);
        adapter.setSearchResultsOnClickListener(this);

        recyclerView.setAdapter(adapter);
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

    private ArrayList<SearchResultsItem> getProducts(String query, String token){
        final ArrayList<SearchResultsItem> result = new ArrayList<>();

        bbasService.getProductSearch(query, token).enqueue(new Callback<SearchResultListModel>() {
            @Override
            public void onResponse(Call<SearchResultListModel> call, Response<SearchResultListModel> response) {

                List<ProductModel> apiProductListResult;

                apiProductListResult = response.body().getProductsList();
                SearchResultsItem temp;

                for(ProductModel product : apiProductListResult){

                    String productNormalPrice = product.getPrice()+"";
                    String productGroceryPrice = product.getLowerPrice()+"";
                    String sellerLocation = product.getCity();
                    String imageUrl = product.getImage();
                    String description = product.getDesc();
                    String productId = product.getProductId();
                    String minimumBuying = product.getLowerBound()+"";
                    String deadline = product.getDeadline();
                    String weight = product.getWeight()+"";
                    String productName = "Dummy name";
                    boolean isMassDrop; String currentQtyBuying;

                    try{
                        isMassDrop = product.getIsMassDrop();
                        currentQtyBuying = product.getQuantity()+"";
                    } catch (Exception e){

                    } finally {
                        isMassDrop = false;
                        currentQtyBuying = -1+"";
                    }

                    temp = new SearchResultsItem(sellerLocation, deadline, isMassDrop, currentQtyBuying, description, productGroceryPrice, productId, imageUrl, productName, productNormalPrice, weight);

                    result.add(temp);

                    Log.d("Tambah barang: ", productId);
                }
            }

            @Override
            public void onFailure(Call<SearchResultListModel> call, Throwable t) {

            }
        });

        return result;
    }
}
