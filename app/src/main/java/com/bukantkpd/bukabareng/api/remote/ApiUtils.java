package com.bukantkpd.bukabareng.api.remote;

/**
 * Created by Ibam on 5/25/2017.
 */

public class ApiUtils {

    public static final String BASE_URL = "https://powerful-taiga-29434.herokuapp.com/";

    public static BukBarAPIService getBBASService() {
        return RetrofitClient.getClient(BASE_URL).create(BukBarAPIService.class);
    }
}
