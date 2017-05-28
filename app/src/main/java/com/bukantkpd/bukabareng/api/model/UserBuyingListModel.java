package com.bukantkpd.bukabareng.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ibam on 5/28/2017.
 */

public class UserBuyingListModel {

    @SerializedName("results")
    @Expose
    private List<UserBuyingModel> results = null;

    public List<UserBuyingModel> getResults() {
        return results;
    }

    public void setResults(List<UserBuyingModel> results) {
        this.results = results;
    }
}
