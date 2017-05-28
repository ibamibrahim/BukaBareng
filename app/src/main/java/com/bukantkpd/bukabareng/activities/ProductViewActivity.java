package com.bukantkpd.bukabareng.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bukantkpd.bukabareng.R;
import com.bukantkpd.bukabareng.api.model.CreateUserResponseModel;
import com.bukantkpd.bukabareng.api.model.ProductModel;
import com.bukantkpd.bukabareng.api.model.UserDetailModel;
import com.bukantkpd.bukabareng.api.remote.ApiUtils;
import com.bukantkpd.bukabareng.api.remote.BukBarAPIService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewActivity extends AppCompatActivity {

    TextView viewName;
    TextView viewPrice;
    TextView viewLowPrice;
    TextView viewWeight;
    TextView viewRemaining;
    TextView viewDeadline;
    TextView viewDescription;
    ImageView viewImage;
    Button buyButton;
    BukBarAPIService bbasService;

    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        Gson gson = new Gson();

        isLoggedIn();

        bbasService = ApiUtils.getBBASService();

        SharedPreferences preferences = getSharedPreferences("bukabareng",
                Context
                        .MODE_PRIVATE);
        String userObj = preferences.getString("userDetail", null);
        final UserDetailModel user = gson.fromJson(userObj, UserDetailModel.class);

        String strObj = getIntent().getStringExtra("product");
        final ProductModel item = gson.fromJson(strObj, ProductModel.class);

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

        buyButton = (Button) findViewById(R.id.buy_button_product_view);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ProductViewActivity
                        .this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_buy, null);
                final EditText quantity = (EditText) dialogView.findViewById(R.id.quantitiy_buy);
                Button buyButtonFinal = (Button) dialogView.findViewById(R.id.buy_button_final);

                dialogBuilder.setView(dialogView);
                final AlertDialog dialog = dialogBuilder.create();
                dialog.show();

                buyButtonFinal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String quantityStr = quantity.getText().toString();
                        if(quantityStr.equals("")){
                            Toast.makeText(ProductViewActivity.this, "Jumlah tidak boleh kosong!",
                                    Toast
                                    .LENGTH_SHORT).show();
                        } else {
                            try {
                                int quantityInt = Integer.parseInt(quantityStr);
                                String productID = item.getProductId();
                                String userID = user.getUserId() + "";
                                String belibarengID = item.getMassdropId() + "";

                                bbasService.createTransaction(userID, belibarengID, quantityInt+"",
                                        productID).enqueue(new Callback<CreateUserResponseModel>() {
                                    @Override
                                    public void onResponse(Call<CreateUserResponseModel> call, Response<CreateUserResponseModel> response) {
                                        try{
                                            String status = response.body().getStatus();
                                            dialog.dismiss();
                                            Toast.makeText(ProductViewActivity.this, "Sukses " +
                                                    "membeli barang!",
                                                    Toast.LENGTH_SHORT).show();
                                        } catch (Exception e){
                                            Toast.makeText(ProductViewActivity.this, e.getMessage(), Toast
                                                    .LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<CreateUserResponseModel> call, Throwable t) {

                                    }
                                });


                            } catch (NumberFormatException e){
                                Toast.makeText(ProductViewActivity.this, "Masukkan jumlah dalam " +
                                        "bilangan bulat!", Toast.LENGTH_SHORT).show();
                            }

                            }
                    }
                });


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
