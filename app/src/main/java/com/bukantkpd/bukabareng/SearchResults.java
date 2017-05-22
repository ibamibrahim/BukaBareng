package com.bukantkpd.bukabareng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity {

    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<SearchResultsItem> searchResultsItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        recyclerView = (RecyclerView) findViewById(R.id.search_results_list_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        searchResultsItemList = new ArrayList<>();

        // creating dummy product list

        String productName = "Nama Barang ke ";
        String initialProductPrice = "60rb";
        String groceryProductPrice = "30rb";
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

        recyclerView.setAdapter(adapter);
    }
}
