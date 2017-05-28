package com.bukantkpd.bukabareng.api.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ibam on 5/25/2017.
 */

public class ApiUtils {

    public static final String BASE_URL = "https://powerful-taiga-29434.herokuapp.com/";
    public static final String BASE_URL_AUTH = "https://api.bukalapak.com/v2/authenticate.json/";
    public static BukBarAPIService getBBASService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(BukBarAPIService.class);
    }

    public static BukBarAPIService getBBASAuthService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_AUTH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(BukBarAPIService.class);
    }
}
