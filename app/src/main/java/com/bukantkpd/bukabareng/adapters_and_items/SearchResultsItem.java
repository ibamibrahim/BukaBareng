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
    private String productImage;
    private String deadline;
    private String weight;
    boolean isMassDrop;
    private String productId;
    private String city;


    public SearchResultsItem(String city, String deadline, boolean isMassDrop, String productCurrentQtyBuying, String productDescription, String productGroceryPrice, String productId, String productImage, String productName, String productNormalPrice, String weight) {
        this.city = city;
        this.deadline = deadline;
        this.isMassDrop = isMassDrop;
        this.productCurrentQtyBuying = productCurrentQtyBuying;
        this.productDescription = productDescription;
        this.productGroceryPrice = productGroceryPrice;
        this.productId = productId;
        this.productImage = productImage;
        this.productName = productName;
        this.productNormalPrice = productNormalPrice;
        this.weight = weight;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public boolean isMassDrop() {
        return isMassDrop;
    }

    public void setMassDrop(boolean massDrop) {
        isMassDrop = massDrop;
    }


    public SearchResultsItem(){
        productName = ""; productGroceryPrice = ""; productNormalPrice = ""; productCurrentQtyBuying = "";
        productDescription = ""; productImage = ""; deadline = ""; weight = ""; city = ""; isMassDrop= false;
        productId = "";
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

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
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

    public String getWeight(){
        return this.weight;
    }
    public void setWeight(int weight){
        this.weight = weight + " gram";
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
