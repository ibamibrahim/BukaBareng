package com.bukantkpd.bukabareng.api.remote;

/**
 * Created by Ibam on 5/25/2017.
 */

import com.bukantkpd.bukabareng.api.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BukBarAPIService {

    @GET("/auth/ret_user")
    Call<UserModel> getAnswers(@Query("email") String email, @Query("password") String password);
}
