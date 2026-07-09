package com.example.demo.model;

public class Product {

    private int price;
    private String name;
    private int value;
    private String source;
    private String link;
    private double rating;
    private String image;// ⭐ NEW FIELD

    // 🔥 Updated constructor
    public Product(String name,
                   int price,
                   int value,
                   String source,
                   String link,
                   double rating,
                   String image) {

        this.name = name;
        this.price = price;
        this.value = value;
        this.source = source;
        this.link = link;
        this.rating = rating;
        this.image = image;
    }

    // 🔥 Getters & Setters

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}