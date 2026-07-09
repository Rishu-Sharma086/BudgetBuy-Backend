package com.example.demo.model;

import java.util.List;

public class Request {

    private int budget;
    private List<ItemRequest> items;



    // 🔥 Default constructor (IMPORTANT)
    public Request() {}

    // Parameterized constructor
    public Request(int budget, List<ItemRequest> items) {
        this.budget = budget;
        this.items = items;
    }

    // Getter
    public int getBudget() {
        return budget;
    }



    // Setter
    public void setBudget(int budget) {
        this.budget = budget;
    }



    public List<ItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ItemRequest> items) {
        this.items = items;
    }
}