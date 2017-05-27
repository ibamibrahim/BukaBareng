package com.bukantkpd.bukabareng.adapters_and_items;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bukantkpd.bukabareng.R;
import com.bukantkpd.bukabareng.api.model.ProductModel;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by Ibam on 5/22/2017.
 */

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>{

    private List<ProductModel> searchResultsData;
    private Context context;
    private SearchResultsClickListener srcl;

    public SearchResultsAdapter(Context c, List<ProductModel> productModelList, SearchResultsClickListener
            searchResultsClickListener){
        this.context = c;
        this.searchResultsData = productModelList;
        this.srcl = searchResultsClickListener;
    }

    public SearchResultsAdapter(Context c, List<ProductModel> productModelList){
        this.context = c;
        this.searchResultsData = productModelList;
    }

    @Override
    public SearchResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_results, parent, false);


        return new SearchResultsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultsAdapter.ViewHolder holder, final int position) {

        ProductModel item = searchResultsData.get(position);

        holder.productName.setText("Dummy product name");

        holder.description.setText(cutDescripton(item.getDesc()));

        int groceryPrice = item.getLowerPrice();
        groceryPrice = groceryPrice/1000;
        String groceryPriceText = "Rp " + groceryPrice + " rb";
        holder.productGroceryPrice.setText(groceryPriceText);

        int normalPrice = item.getPrice();
        normalPrice = normalPrice/1000;
        String normalPriceText = "Rp " + normalPrice + " rb";

        holder.productNormalPrice.setText(normalPriceText);

        holder.productName.setText(item.getName());

        holder.productNormalPrice.setPaintFlags(holder.productNormalPrice.getPaintFlags() | Paint
                .STRIKE_THRU_TEXT_FLAG);
        if(!item.getIsMassDrop()){
            holder.deadline.setVisibility(View.INVISIBLE);
            holder.productCurrentQtyBuying.setVisibility(View.INVISIBLE);
            holder.isBeliBarengView.setVisibility(View.INVISIBLE);
            holder.buyButton.setBackgroundColor(Color.parseColor("#7f8c8d"));
        } else {
            holder.productCurrentQtyBuying.setText(item.getQuantity()+" pc BeliBareng");

            DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY-MM-DD");
            DateTime currentDate = new DateTime();
            DateTime deadlineDate = DateTime.parse(item.getDeadline(), formatter);
            int days = Days.daysBetween(deadlineDate, currentDate).getDays();
            String deadlineText = days +" hari lagi";

            holder.deadline.setText(deadlineText);

        }


        String imageUrl = item.getImage();
        Picasso.with(holder.productImage.getContext()).load(imageUrl).into(holder
                .productImage);


    }

    @Override
    public int getItemCount() {
        return searchResultsData.size();
    }

    public void updateList(List<ProductModel> productModelList){
        this.searchResultsData = productModelList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView productName;
        public TextView productGroceryPrice;
        public TextView productNormalPrice;
        public TextView productCurrentQtyBuying;
        public ImageView productImage;
        public TextView description;
        public TextView deadline;
        public Button buyButton;
        public CardView cardContainer;
        public TextView isBeliBarengView;

        public ViewHolder(View itemView){
            super(itemView);

            productName = (TextView) itemView.findViewById(R.id.product_name_search_view);
            description = (TextView) itemView.findViewById(R.id.product_description_search_view);
            productGroceryPrice = (TextView) itemView.findViewById(R.id.grocery_price_search_view);
            productNormalPrice = (TextView) itemView.findViewById(R.id.initial_price_search_view);
            productImage = (ImageView) itemView.findViewById(R.id.search_results_image_view);
            productCurrentQtyBuying = (TextView) itemView.findViewById(R.id.collective_buying_amount_search_view);
            deadline = (TextView) itemView.findViewById(R.id.deadline_search_view);
            buyButton = (Button) itemView.findViewById(R.id.buy_button_search_view);
            cardContainer = (CardView) itemView.findViewById(R.id.card_view_search_results);
            isBeliBarengView = (TextView) itemView.findViewById(R.id.isMassDrop_tag);

        }
    }





    // below interface is to create onClick on recycler view item
    // https://stackoverflow.com/questions/24885223/

    public interface SearchResultsClickListener{
        void onBuyButtonClicked(View view, int position);
    }

    public void setSearchResultsOnClickListener(SearchResultsClickListener srcl){
        this.srcl = srcl;
    }

    @SuppressWarnings("deprecation")
    public String cutDescripton(String description){

        description = Html.fromHtml(description).toString();
        String strOut;
        if(description.length() > 100) {
            strOut = description.substring(0, 99) + "...";
        } else {
            return description;
        }

        return strOut;
    }

}