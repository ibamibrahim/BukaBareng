package com.bukantkpd.bukabareng;

/**
 * Created by Ibam on 5/16/2017.
 */

public class SearchResultsItems {

    private String productName;
    private String productGroceryPrice;
    private String productNormalPrice;
    private String productCurrentQtyBuying;
    private int productImage;

    public SearchResultsItems(String productName, String productGroceryPrice, String productNormalPrice, String productCurrentQtyBuying, int productImage) {
        this.productName = productName;
        this.productGroceryPrice = productGroceryPrice;
        this.productNormalPrice = productNormalPrice;
        this.productCurrentQtyBuying = productCurrentQtyBuying;
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductGroceryPrice() {
        return productGroceryPrice;
    }

    public String getProductNormalPrice() {
        return productNormalPrice;
    }

    public String getProductCurrentQtyBuying() {
        return productCurrentQtyBuying;
    }

    public int getProductImage() {
        return productImage;
    }


}
