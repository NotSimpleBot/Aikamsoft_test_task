package com.guzanov.service;

import com.guzanov.Operation;
import com.guzanov.ResultJsonObject;
import com.guzanov.criterias.Criterias;
import com.guzanov.criterias.target.BadCustomersLessCriteria;
import com.guzanov.criterias.target.CustomersByLastNameCriteria;
import com.guzanov.criterias.target.CustomersProductAmountBetweenCriteria;
import com.guzanov.criterias.target.CustomersProductCountMoreCriteria;
import com.guzanov.entity.Customer;

import java.util.List;

public interface CustomersService {
    ResultJsonObject getAllCustomersListsByCriterias(Operation operation, Criterias[] criterias);

    List<Customer> getAllCustomersByLastName(CustomersByLastNameCriteria criteria);

    List<Customer> getAllCustomersByProductMinTimes(CustomersProductCountMoreCriteria criteria);

    List<Customer> getAllCustomersByAmountInIntervalMinAndMax(CustomersProductAmountBetweenCriteria criteria);

    List<Customer> getAllBadCustomersByCountLessThen(BadCustomersLessCriteria criteria);
}
