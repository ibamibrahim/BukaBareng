package com.bukantkpd.bukabareng.fragments;

/**
 * Created by Ibam on 5/16/2017.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bukantkpd.bukabareng.R;
import com.bukantkpd.bukabareng.activities.SearchResultsActivity;

public class Tab1SearchMenu extends Fragment implements View.OnClickListener{

    Button searchButton;
    EditText searchQuery;
    String query;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1_search_menu, container, false);
        searchButton = (Button) rootView.findViewById(R.id.search_button_view);
        searchQuery  = (EditText) rootView.findViewById(R.id.search_query_view);

        searchButton.setOnClickListener(this);

        query = searchQuery.getText().toString();

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.search_button_view:

                if(query == ""){
                    Toast.makeText(getActivity(), "Masukkan query pencarian!", Toast.LENGTH_SHORT).show();
                } else {

                }
                    Intent intent = new Intent(getActivity(), SearchResultsActivity.class);
                    intent.putExtra("searchQuery", query);

                    startActivity(intent);
                break;
        }
    }

    /**
     * source: http://stackoverflow.com/questions/18711433/
     */
}
