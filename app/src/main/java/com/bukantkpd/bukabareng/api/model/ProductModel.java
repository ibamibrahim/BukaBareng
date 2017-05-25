package com.bukantkpd.bukabareng.api.model;

/**
 * Created by Ibam on 5/25/2017.
 */

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductModel {

    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("seller_name")
    @Expose
    private String sellerName;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("lower_price")
    @Expose
    private Integer lowerPrice;
    @SerializedName("lower_bound")
    @Expose
    private Integer lowerBound;
    @SerializedName("is_mass_drop")
    @Expose
    private Boolean isMassDrop;
    @SerializedName("deadline")
    @Expose
    private String deadline;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getLowerPrice() {
        return lowerPrice;
    }

    public void setLowerPrice(Integer lowerPrice) {
        this.lowerPrice = lowerPrice;
    }

    public Integer getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(Integer lowerBound) {
        this.lowerBound = lowerBound;
    }

    public Boolean getIsMassDrop() {
        return isMassDrop;
    }

    public void setIsMassDrop(Boolean isMassDrop) {
        this.isMassDrop = isMassDrop;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
