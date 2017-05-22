package com.bukantkpd.bukabareng;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ibam on 5/16/2017.
 */

/* https://www.youtube.com/watch?v=gGFvbvkZiMs */

public class MyBuyingsAdapter extends RecyclerView.Adapter<MyBuyingsAdapter.ViewHolder>{

    private List<MyBuyingsItem> myBuyingsItemsa;
    private Context context;

    public MyBuyingsAdapter(List<MyBuyingsItem> srd, Context c){
        myBuyingsItemsa = srd;
        context = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_buyings, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyBuyingsItem item = myBuyingsItemsa.get(position);

        holder.productName.setText(item.getProductName());
        holder.productImage.setImageResource(item.getProductImage());
        holder.productCurrentQtyBuying.setText(item.getProductCurrentQtyBuying());
        holder.productGroceryPrice.setText(item.getProductGroceryPrice());
    }

    @Override
    public int getItemCount() {
        return myBuyingsItemsa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView productName;
        public  TextView productGroceryPrice;
        public  TextView productNormalPrice;
        public  TextView productCurrentQtyBuying;
        public  ImageView productImage;

        public ViewHolder(View itemView){
            super(itemView);

            productName = (TextView) itemView.findViewById(R.id.product_name_view);
            productGroceryPrice = (TextView) itemView.findViewById(R.id.produc_price_view);
            productCurrentQtyBuying = (TextView) itemView.findViewById(R.id.product_bought_qty_view);
            productImage = (ImageView) itemView.findViewById(R.id.product_image_view);

        }

    }
}
