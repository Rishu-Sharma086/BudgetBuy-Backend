package com.example.demo.model;

public class AnalyticsResponse {

    private int budget;
    private int spent;
    private int saved;

    private double avgRating;

    private int totalProducts;

    private double budgetUtilization;

    private int minimumRequiredBudget;

    private int extraBudgetNeeded;

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getSpent() {
        return spent;
    }

    public void setSpent(int spent) {
        this.spent = spent;
    }

    public int getSaved() {
        return saved;
    }

    public void setSaved(int saved) {
        this.saved = saved;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public double getBudgetUtilization() {
        return budgetUtilization;
    }

    public void setBudgetUtilization(double budgetUtilization) {
        this.budgetUtilization = budgetUtilization;
    }

    public int getMinimumRequiredBudget() {
        return minimumRequiredBudget;
    }

    public void setMinimumRequiredBudget(int minimumRequiredBudget) {
        this.minimumRequiredBudget = minimumRequiredBudget;
    }

    public int getExtraBudgetNeeded() {
        return extraBudgetNeeded;
    }

    public void setExtraBudgetNeeded(int extraBudgetNeeded) {
        this.extraBudgetNeeded = extraBudgetNeeded;
    }
// getters setters
}