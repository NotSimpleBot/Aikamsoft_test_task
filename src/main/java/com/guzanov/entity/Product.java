package com.guzanov.entity;

public class Product {
    private String productName;
    private int cost;


    public Product() {
    }

    public Product(String name, int cost) {
        this.productName = name;
        this.cost = cost;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" + productName +
                ", " + cost +
                '}';
    }
}
