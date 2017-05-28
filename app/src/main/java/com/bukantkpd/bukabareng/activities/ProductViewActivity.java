package com.bukantkpd.bukabareng.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bukantkpd.bukabareng.R;
import com.bukantkpd.bukabareng.api.model.ProductModel;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductViewActivity extends AppCompatActivity {

    TextView viewName;
    TextView viewPrice;
    TextView viewLowPrice;
    TextView viewWeight;
    TextView viewRemaining;
    TextView viewDeadline;
    TextView viewDescription;
    ImageView viewImage;

    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        isLoggedIn();

        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("product");
        ProductModel item = gson.fromJson(strObj, ProductModel.class);

        String productName= item.getName();
        String normalPrice = NumberFormat.getNumberInstance(Locale.GERMAN).format(item
                .getPrice());
        String lowerPrice = NumberFormat.getNumberInstance(Locale.GERMAN).format(item
                .getLowerPrice());
        String imageUrl = item.getImage();
        String weight = "Berat: " + item.getWeight() +  " gram";
        String remainingPc = (item.getLowerBound() - item.getQuantity()) + " barang lagi.";

        DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY-MM-DD");
        DateTime currentDate = new DateTime();
        DateTime deadlineDate = DateTime.parse(item.getDeadline(), formatter);
        int days = Days.daysBetween(deadlineDate, currentDate).getDays();

        String deadlineText = days +" hari lagi";
        String description = item.getDesc();
        description = Html.fromHtml(description).toString();
        String productID = item.getProductId();

        viewName = (TextView) findViewById(R.id.product_detal_name_view);
        viewPrice = (TextView) findViewById(R.id.product_price_view);
        viewLowPrice = (TextView) findViewById(R.id.product_detail_lower_price_view);
        viewWeight = (TextView) findViewById(R.id.product_weight_detail_view);
        viewRemaining = (TextView) findViewById(R.id.product_minimumqty_detail_view);
        viewDeadline = (TextView) findViewById(R.id.product_deadline_detail_view);
        viewDescription = (TextView) findViewById(R.id.produc_description_view);
        viewImage = (ImageView) findViewById(R.id.product_image_view);

        viewName.setText(productName);
        viewPrice.setText(normalPrice);
        viewLowPrice.setText(lowerPrice);
        viewPrice.setPaintFlags(viewPrice.getPaintFlags() | Paint
                .STRIKE_THRU_TEXT_FLAG);
        viewWeight.setText(weight);
        viewRemaining.setText(remainingPc);
        viewDeadline.setText(deadlineText);
        viewDescription.setText(description);


        Picasso.with(viewImage.getContext()).load(imageUrl).into(viewImage);

        Toast.makeText(this, item.getMassdropId()+"", Toast.LENGTH_SHORT).show();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_product_view);
        toolbar.setTitle(productName);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        toolbar.setNavigationIcon(R.drawable.arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
