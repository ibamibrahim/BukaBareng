package com.bukantkpd.bukabareng.adapters_and_items;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.bukantkpd.bukabareng.api.model.ProductModel;
import com.bukantkpd.bukabareng.api.model.UserBuyingListModel;
import com.bukantkpd.bukabareng.api.model.UserBuyingModel;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ibam on 5/16/2017.
 */

/* https://www.youtube.com/watch?v=gGFvbvkZiMs */

public class MyBuyingsAdapter extends RecyclerView.Adapter<MyBuyingsAdapter.ViewHolder>{

    private List<UserBuyingModel> userBuyingList;
    private Context context;

    public MyBuyingsAdapter(List<UserBuyingModel> userBuyingListModels, Context c){
        this.userBuyingList = userBuyingListModels;
        context = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_buyings, parent, false);


        return new ViewHolder(view);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onBindViewHolder(ViewHolder holder, int position) {;
        final UserBuyingModel item = userBuyingList.get(position);

        holder.productName.setText(item.getNamaBarang());

        int quantityBeli =  item.getQuantity();
        int hargaBarang = item.getHargaBarang();
        int totalHarga = quantityBeli*hargaBarang;
        holder.productGroceryPrice.setText("Rp " +
                NumberFormat.getNumberInstance(Locale.GERMAN).format(totalHarga));

        int status = item.getStatus();

        /*
        - transaksi gagal (karena sampai batas waktunya belum mencapai target minimal pembeli) =>0
        - transasi siap dibayarkan (masih dalam batas waktu, sudah mencapai target, siap dibayar) -> 1
        - transaksi menunggu pembeli lagi (masih dalam batas waktu, belum mencapai target) -> 2
        - transaksi lunas (sudah dibayarkan oleh user) -> 3
         */
        Drawable bg;
        switch (status){
            case 0:
                holder.status.setText("Transaksi gagal");
                bg = context.getResources().getDrawable(R.drawable.tag_background_red);
                holder.status.setBackground(bg);
                break;
            case 1:
                holder.status.setText("Siap dibayar");
                bg = context.getResources().getDrawable(R.drawable.tag_background_green);
                holder.status.setBackground(bg);
                break;
            case 2:
                holder.status.setText("Menunggu pembeli lagi");
                bg = context.getResources().getDrawable(R.drawable.tag_background_yellow);
                holder.status.setBackground(bg);
                break;
            case 3:
                holder.status.setText("Transaksi Lunas");
                bg = context.getResources().getDrawable(R.drawable.tag_background_green);
                holder.status.setBackground(bg);
                holder.payButton.setVisibility(View.INVISIBLE);
                break;
        }

        // https://stackoverflow.com/questions/12195588/

        String deliverydate= item.getTanggalPembelian();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(deliverydate);
        } catch (Exception e){
            e.printStackTrace();
        }
        sdf=new SimpleDateFormat("dd-MM-yyyy");

        System.out.println(sdf.format(date));
        holder.buyDate.setText(sdf.format(date).toString());


        Picasso.with(holder.productImage.getContext()).load(item.getGambar()).into(holder
                .productImage);

        holder.payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                Intent intent = new Intent(context, PaymentActivity.class);
                intent.putExtra("payment", gson.toJson(item));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userBuyingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView productName;
        public  TextView productGroceryPrice;
        public  TextView productNormalPrice;
        public  TextView buyDate;
        public  ImageView productImage;
        public Button payButton;
        public TextView status;

        public ViewHolder(View itemView){
            super(itemView);

            productName = (TextView) itemView.findViewById(R.id.product_name_view);
            productGroceryPrice = (TextView) itemView.findViewById(R.id.produc_price_view);
            buyDate = (TextView) itemView.findViewById(R.id.buy_date);
            productImage = (ImageView) itemView.findViewById(R.id.product_image_view);
            payButton = (Button) itemView.findViewById(R.id.pay_button_view);
            status = (TextView) itemView.findViewById(R.id.transaction_status);

        }

    }

    public void updateList(List<UserBuyingModel> userBuying){
        this.userBuyingList = userBuying;
        notifyDataSetChanged();
    }
}
