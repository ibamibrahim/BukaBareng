package com.bukantkpd.bukabareng.api.model;

/**
 * Created by Ibam on 5/25/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResultListModel {

    @SerializedName("results")
    @Expose
    private List<ProductModel> results = null;

    public List<ProductModel> getProductsList() {
        return results;
    }

    public void setProductList(List<ProductModel> results) {
        this.results = results;
    }
}
