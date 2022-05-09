package com.guzanov.entity;

import java.util.Objects;
/**
 * Класс-сущность (Entity), для получения/изменения данных в таблице базы данных.
 * <p>
 * Данная модель содержит поля идентичные столбцам таблицы 'products'.
 */
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return cost == product.cost && Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, cost);
    }

    @Override
    public String toString() {
        return "Product{" + productName +
                ", " + cost +
                '}';
    }
}
