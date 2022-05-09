package com.guzanov.entity;

import java.util.Objects;

/**
 * Класс-сущность (Entity), для получения/изменения данных в таблице базы данных.
 * <p>
 * Данная модель содержит поля идентичные столбцам таблицы 'customers'.
 */
public class Customer {
    private String firstName;
    private String lastName;


    public Customer() {
    }

    public Customer(String name, String surname) {
        this.firstName = name;
        this.lastName = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "{" +
                firstName.trim() + " " +
                lastName.trim() +
                '}';
    }
}
