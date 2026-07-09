package com.example.demo.model;

import java.util.List;

public class Response {

    private int totalCost;

    private List<Product> selectedItems;

    // 🤖 AI suggestion
    private String suggestion;

    private List<Product> missingProducts;

    private AnalyticsResponse analytics;

    public AnalyticsResponse getAnalytics() {
        return analytics;
    }

    public void setAnalytics(AnalyticsResponse analytics) {
        this.analytics = analytics;
    }



    // 🔥 Default constructor
    public Response() {}

    // 🔥 Constructor
    public Response(int totalCost,
                    List<Product> selectedItems,
                    String suggestion) {

        this.totalCost = totalCost;

        this.selectedItems = selectedItems;

        this.suggestion = suggestion;
    }

    // 🔥 Getter

    public List<Product> getMissingProducts() {
        return missingProducts;
    }

    public void setMissingProducts(List<Product> missingProducts) {
        this.missingProducts = missingProducts;
    }
    public int getTotalCost() {
        return totalCost;
    }

    public List<Product> getSelectedItems() {
        return selectedItems;
    }

    public String getSuggestion() {
        return suggestion;
    }

    // 🔥 Setter
    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public void setSelectedItems(List<Product> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}