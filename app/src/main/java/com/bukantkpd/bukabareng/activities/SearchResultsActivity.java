package com.bukantkpd.bukabareng.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bukantkpd.bukabareng.R;
import com.bukantkpd.bukabareng.activities.ProductViewActivity;
import com.bukantkpd.bukabareng.adapters_and_items.SearchResultsAdapter;
import com.bukantkpd.bukabareng.adapters_and_items.SearchResultsItem;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity implements SearchResultsAdapter.SearchResultsClickListener{

    RecyclerView recyclerView;
    private SearchResultsAdapter adapter;
    private List<SearchResultsItem> searchResultsItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        isLoggedIn();

        recyclerView = (RecyclerView) findViewById(R.id.search_results_list_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        searchResultsItemList = new ArrayList<>();

        // creating dummy product list

        String productName = "Jersey bola murah~";
        String initialProductPrice = "60rb";
        String groceryProductPrice = "Rp 30.000";
        String boughtQty = "10 orang beli bareng!";
        int productImage = R.drawable.dummy_loading;
        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
        String deadline = "7 hari lagi";

        for (int i = 0; i < 20; i++){
            SearchResultsItem newItem = new SearchResultsItem();

            newItem.setProductName(productName);
            newItem.setProductNormalPrice(initialProductPrice);
            newItem.setProductGroceryPrice(groceryProductPrice);
            newItem.setProductCurrentQtyBuying(boughtQty);
            newItem.setProductImage(productImage);
            newItem.setProductDescription(description);
            newItem.setDeadline(deadline);

            searchResultsItemList.add(newItem);
        }

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
}
