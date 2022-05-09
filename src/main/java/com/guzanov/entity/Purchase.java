package com.guzanov.entity;

import java.util.Date;
import java.util.Objects;
/**
 * Класс-сущность (Entity), для получения/изменения данных в таблице базы данных.
 * <p>
 * Данная модель содержит поля идентичные столбцам таблицы 'purchases'.
 */
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(customer, purchase.customer) && Objects.equals(product, purchase.product) && Objects.equals(date, purchase.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, product, date);
    }

    @Override
    public String toString() {
        return "Purchase{" + customer +
                ", " + product +
                ", " + date +
                '}';
    }
}
