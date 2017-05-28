package com.bukantkpd.bukabareng.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bukantkpd.bukabareng.R;
import com.bukantkpd.bukabareng.api.model.CreateTransactionResponseModel;
import com.bukantkpd.bukabareng.api.model.UserBuyingModel;
import com.bukantkpd.bukabareng.api.model.UserDetailModel;
import com.bukantkpd.bukabareng.api.remote.ApiUtils;
import com.bukantkpd.bukabareng.api.remote.BukBarAPIService;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {

    TextView productName;
    TextView singlePriceView;
    TextView quantitiyView;
    TextView totalPriceView;
    TextView userBalanceView;
    TextView remainingBalanceView;
    Button finalPayBUtton;
    BukBarAPIService bbasService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        SharedPreferences preferences = getSharedPreferences("bukabareng", Context.MODE_PRIVATE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_payment_view);
        toolbar.setTitle("Pembayaran " + "Baju Persib");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        toolbar.setNavigationIcon(R.drawable.arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bbasService = ApiUtils.getBBASService();
        Gson gson = new Gson();
        // get product to be paid
        String strObj = getIntent().getStringExtra("payment");
        final UserBuyingModel payProduct = gson.fromJson(strObj, UserBuyingModel.class);
        // get user detail
        String userObj = preferences.getString("userDetail", null);
        final UserDetailModel user = gson.fromJson(userObj, UserDetailModel.class);

        productName = (TextView) findViewById(R.id.payment_product_name);
        singlePriceView = (TextView) findViewById(R.id.payment_single_price);
        quantitiyView = (TextView) findViewById(R.id.payment_qty);
        totalPriceView = (TextView) findViewById(R.id.payment_total_price);
        userBalanceView = (TextView) findViewById(R.id.payment_user_balance);
        remainingBalanceView = (TextView) findViewById(R.id.payment_remaining_balance);

        productName.setText(payProduct.getNamaBarang());
        singlePriceView.setText("Rp " + NumberFormat.getNumberInstance(Locale.GERMAN).format(payProduct.getHargaBarang()));
        quantitiyView.setText(payProduct.getQuantity()+" ");
        final int totalPrice = payProduct.getQuantity() * payProduct.getHargaBarang();
        totalPriceView.setText("Rp " + NumberFormat.getNumberInstance(Locale.GERMAN).format(totalPrice));

        userBalanceView.setText("Rp " + NumberFormat.getNumberInstance(Locale.GERMAN).format(user
                .getBalance()));
        int remaining = user.getBalance() - totalPrice;
        remainingBalanceView.setText("Rp " + NumberFormat.getNumberInstance(Locale.GERMAN).format
                (remaining));

        finalPayBUtton = (Button)findViewById(R.id.final_pay_button);

        if(remaining < 0){
            remainingBalanceView.setTextColor(Color.parseColor("#c0392b"));
            finalPayBUtton.setEnabled(false);
        } else {
            finalPayBUtton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pay(payProduct.getIdTransaksi(), totalPrice);
                }
            });
        }
    }

    private void pay(int idTransaksi, int harga){
        String tId = idTransaksi+"";
        String payAmount = harga+"";
        Toast.makeText(this, tId+payAmount, Toast.LENGTH_SHORT).show();
        bbasService.payTransaction(tId, payAmount).enqueue(new Callback<CreateTransactionResponseModel>() {
            @Override
            public void onResponse(Call<CreateTransactionResponseModel> call, Response<CreateTransactionResponseModel> response) {
                try {
                    if (response.body().getStatus().equals("succes")) {
                        Toast.makeText(PaymentActivity.this, "Barang berhasil dibayar!", Toast
                                .LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(PaymentActivity.this, "Pembayaran gagal, coba lagi!", Toast
                                .LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    Toast.makeText(PaymentActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateTransactionResponseModel> call, Throwable t) {
                Toast.makeText(PaymentActivity.this, "Request timeout, coba lagi", Toast
                        .LENGTH_SHORT).show();
            }
        });
    }
}
