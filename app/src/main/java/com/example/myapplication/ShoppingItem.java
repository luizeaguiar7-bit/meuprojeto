package com.example.myapplication;

public class ShoppingItem {
    private static long nextId = 1;
    private long id;
    private String description;
    private int quantity;

    public ShoppingItem(String description, int quantity) {
        this.id = nextId++;
        this.description = description;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}