package com.guzanov.entity;

import java.util.Date;

public class Purchase {
    private Customer customer;
    private Product product;
    private Date date;


    public Purchase() {
    }

    public Purchase(Customer customer, Product product, Date date) {
        this.customer = customer;
        this.product = product;
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Purchase{" + customer +
                ", " + product +
                ", " + date +
                '}';
    }
}
