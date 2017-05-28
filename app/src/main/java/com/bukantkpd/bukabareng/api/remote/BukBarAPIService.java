package com.bukantkpd.bukabareng.api.remote;

/**
 * Created by Ibam on 5/25/2017.
 */

import com.bukantkpd.bukabareng.api.model.CreateTransactionResponseModel;
import com.bukantkpd.bukabareng.api.model.CreateUserResponseModel;
import com.bukantkpd.bukabareng.api.model.SearchResultListModel;
import com.bukantkpd.bukabareng.api.model.UserBuyingListModel;
import com.bukantkpd.bukabareng.api.model.UserDetailModel;
import com.bukantkpd.bukabareng.api.model.UserModel;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BukBarAPIService {

    @POST("/auth/ret_user")
    Call<UserModel> getUsersDetail(@Query("email") String email, @Query("password") String password);

    @GET("/get/products")
    Call<SearchResultListModel> getProductSearch(@Query("keyword") String keyword, @Query("token") String token);

    @POST("./")
    Call<UserModel> authUser(@Header("Authorization") String credentials);

    @POST("create/users/")
    Call<CreateUserResponseModel> createUser(@Query("id") int userId, @Query("balance") int
            balance, @Query("username") String username);

    @GET("/get/users/{id}")
    Call<UserDetailModel> getUserDetail(@Path("id") String userId);

    @POST("/create/transactions")
    Call<CreateUserResponseModel> createTransaction(@Query("user_id") String userId, @Query
            ("massdrop_id") String belibarengID, @Query("jumlah") String jumlah, @Query
            ("product_id") String productID);

    @GET("/get/user_transactions/{id}")
    Call<UserBuyingListModel> getUserTransaction(@Path("id") String userID);

    @POST("/create/payments")
    Call<CreateTransactionResponseModel> payTransaction(@Query("transaction_id") String tId,
                                                        @Query("total_payment") String pay);
}
