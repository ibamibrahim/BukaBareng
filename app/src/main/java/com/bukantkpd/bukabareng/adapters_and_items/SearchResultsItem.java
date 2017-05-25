package com.bukantkpd.bukabareng.adapters_and_items;

/**
 * Created by Ibam on 5/22/2017.
 */

public class SearchResultsItem {

    private String productName;
    private String productGroceryPrice;
    private String productNormalPrice;
    private String productCurrentQtyBuying;
    private String productDescription;
    private int productImage;
    private String deadline;

    public SearchResultsItem(){}

    public SearchResultsItem(String deadline, String productCurrentQtyBuying, String productDescription, String productGroceryPrice, int productImage, String productName, String productNormalPrice) {
        this.deadline = deadline;
        this.productCurrentQtyBuying = productCurrentQtyBuying;
        this.productDescription = productDescription;
        this.productGroceryPrice = productGroceryPrice;
        this.productImage = productImage;
        this.productName = productName;
        this.productNormalPrice = productNormalPrice;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getProductCurrentQtyBuying() {
        return productCurrentQtyBuying;
    }

    public void setProductCurrentQtyBuying(String productCurrentQtyBuying) {
        this.productCurrentQtyBuying = productCurrentQtyBuying;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductGroceryPrice() {
        return productGroceryPrice;
    }

    public void setProductGroceryPrice(String productGroceryPrice) {
        this.productGroceryPrice = productGroceryPrice;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNormalPrice() {
        return productNormalPrice;
    }

    public void setProductNormalPrice(String productNormalPrice) {
        this.productNormalPrice = productNormalPrice;
    }
}
