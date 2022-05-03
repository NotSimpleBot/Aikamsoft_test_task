package com.guzanov.dao;

import com.guzanov.entity.Customer;
import com.guzanov.entity.Purchase;

import java.util.List;

public interface CustomersDao {
    List<Purchase> getAllCustomersByDate(String date);

    List<Customer> getAllCustomersByLastName(String lastName);

    List<Customer> getAllCustomersByProductMinTimes(String productName, int minTimes);

    List<Customer> getAllCustomersByAmountInIntervalMinAndMax(int minExpenses, int maxExpenses);

    List<Customer> getAllBadCustomersByCountLessThen(int badCustomers);

    Customer getCustomerById(int customer_id);

}
