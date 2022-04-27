package com.guzanov.dao;

import com.guzanov.entity.Customer;

import java.util.List;

public interface CustomersDao {
    List<Customer> getAllCustomersByLastName(String lastName);

    List<Customer> getAllCustomersByProductMinTimes(String productName, int minTimes);

    List<Customer> getAllCustomersByAmountInIntervalMinAndMax(int minExpenses, int maxExpenses);

    List<Customer> getAllBadCustomersByCountLessThen(int badCustomers);
}
