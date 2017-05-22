package com.bukantkpd.bukabareng;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Ibam on 5/22/2017.
 */

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>{

    private List<SearchResultsItem> searchResultsData;
    private Context context;

    public SearchResultsAdapter (List<SearchResultsItem> srd, Context c){
        searchResultsData = srd;
        context = c;
    }

    @Override
    public SearchResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_results, parent, false);


        return new SearchResultsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultsAdapter.ViewHolder holder, int position) {
        SearchResultsItem item = searchResultsData.get(position);

        holder.productName.setText(item.getProductName());
        holder.description.setText(item.getProductDescription());
        holder.productImage.setImageResource(item.getProductImage());
        holder.productNormalPrice.setText(item.getProductNormalPrice());
        holder.productGroceryPrice.setText(item.getProductGroceryPrice());
        holder.deadline.setText(item.getDeadline());
        holder.productCurrentQtyBuying.setText(item.getProductCurrentQtyBuying());
    }

    @Override
    public int getItemCount() {
        return searchResultsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView productName;
        public TextView productGroceryPrice;
        public TextView productNormalPrice;
        public TextView productCurrentQtyBuying;
        public ImageView productImage;
        public TextView description;
        public TextView deadline;

        public ViewHolder(View itemView){
            super(itemView);

            productName = (TextView) itemView.findViewById(R.id.product_name_search_view);
            description = (TextView) itemView.findViewById(R.id.product_description_search_view);
            productGroceryPrice = (TextView) itemView.findViewById(R.id.grocery_price_search_view);
            productNormalPrice = (TextView) itemView.findViewById(R.id.initial_price_search_view);
            productImage = (ImageView) itemView.findViewById(R.id.search_results_image_view);
            productCurrentQtyBuying = (TextView) itemView.findViewById(R.id.collective_buying_amount_search_view);
            deadline = (TextView) itemView.findViewById(R.id.deadline_search_view);


        }

    }
}