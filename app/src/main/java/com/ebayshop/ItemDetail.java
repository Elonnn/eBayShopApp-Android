package com.ebayshop;

import java.util.ArrayList;
import java.util.Map;

public class ItemDetail {
    private ArrayList<String> pictureURLs;

    private ArrayList<String> brand;
    private String subtitle;
    private Map<String, String> specifications;

    private Map<String, String> sellerInfo;
    private Map<String, String> returnPolicies;

    public ArrayList<String> getPictureURLs() {
        return pictureURLs;
    }

    public ArrayList<String> getBrand() {
        return brand;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Map<String, String> getSpecifications() {
        return specifications;
    }

    public Map<String, String> getSellerInfo() {
        return sellerInfo;
    }

    public Map<String, String> getReturnPolicies() {
        return returnPolicies;
    }
}
