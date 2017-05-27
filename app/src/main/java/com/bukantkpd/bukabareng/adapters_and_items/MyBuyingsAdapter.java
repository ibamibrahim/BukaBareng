package com.bukantkpd.bukabareng.adapters_and_items;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bukantkpd.bukabareng.R;
import com.bukantkpd.bukabareng.activities.PaymentActivity;
import com.bukantkpd.bukabareng.activities.ProductViewActivity;

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
       // Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat.ttf");

        holder.productName.setText(item.getProductName());
        holder.productImage.setImageResource(item.getProductImage());
        holder.productCurrentQtyBuying.setText(item.getProductCurrentQtyBuying());
        holder.productGroceryPrice.setText(item.getProductGroceryPrice());
        holder.payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PaymentActivity.class);
                context.startActivity(intent);
            }
        });
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
        public Button payButton;

        public ViewHolder(View itemView){
            super(itemView);

            productName = (TextView) itemView.findViewById(R.id.product_name_view);
            productGroceryPrice = (TextView) itemView.findViewById(R.id.produc_price_view);
            productCurrentQtyBuying = (TextView) itemView.findViewById(R.id.product_bought_qty_view);
            productImage = (ImageView) itemView.findViewById(R.id.product_image_view);
            payButton = (Button) itemView.findViewById(R.id.pay_button_view);

        }

    }
}
