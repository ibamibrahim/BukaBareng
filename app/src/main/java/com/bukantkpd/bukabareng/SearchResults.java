package com.bukantkpd.bukabareng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<SearchResultsItems> searchResultsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        recyclerView = (RecyclerView) findViewById(R.id.search_results_list_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchResultsData = new ArrayList<>();

        String productName = "Nama Barang ke ";
        int productPrice = 10000;
        int boughtQty = 5;
        int productImage = R.mipmap.ic_launcher;

        for (int i = 0; i < 50; i++){
            productPrice += i*5000;
            boughtQty += i*2;
            SearchResultsItems temp = new SearchResultsItems(productName + i, "Rp " + productPrice, "Rp "
                    + productPrice, boughtQty + " pcs BeliBareng", productImage);

            searchResultsData.add(temp);
        }

        adapter = new SearchResultsAdapter(searchResultsData, this);

        recyclerView.setAdapter(adapter);
    }
}
