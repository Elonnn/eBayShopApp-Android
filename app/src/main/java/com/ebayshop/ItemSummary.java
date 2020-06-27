package com.ebayshop;

public class ItemSummary {
    private String title;
    private String imageURL;
    private String productID;
    private String price;
    private String shippingCost;
    private String condition;
    private Boolean isTopRated;

    public String getProductID() {
        return productID;
    }

    public String getPrice() {
        return price;
    }

    public String getShippingCost() {
        return shippingCost;
    }

    public String getCondition() {
        return condition;
    }

    public Boolean getTopRated() {
        return isTopRated;
    }

    public String getTitle() {
        return title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public ItemSummary(String title, String imageURL) {
        this.title = title;
        this.imageURL = imageURL;
    }
}
