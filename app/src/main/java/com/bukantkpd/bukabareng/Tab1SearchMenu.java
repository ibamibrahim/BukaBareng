package com.bukantkpd.bukabareng;

/**
 * Created by Ibam on 5/16/2017.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Tab1SearchMenu extends Fragment implements View.OnClickListener{

    Button searchButton;
    EditText searchQuery;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1_search_menu, container, false);
        searchButton = (Button) rootView.findViewById(R.id.search_button_view);
        searchQuery  = (EditText) rootView.findViewById(R.id.search_query_view);

        searchButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.search_button_view:
                Intent intent = new Intent(getActivity(), SearchResults.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * source: http://stackoverflow.com/questions/18711433/
     */
}
