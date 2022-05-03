package com.guzanov.service;

import com.guzanov.criterias.Criterias;
import com.guzanov.criterias.target.BadCustomersLessCriteria;
import com.guzanov.criterias.target.CustomersByLastNameCriteria;
import com.guzanov.criterias.target.CustomersProductAmountBetweenCriteria;
import com.guzanov.criterias.target.CustomersProductCountMoreCriteria;
import com.guzanov.entity.Customer;
import com.guzanov.deserialized_objects.ResultJsonObjectOperationSearch;
import com.guzanov.deserialized_objects.ResultJsonObjectOperationStat;

import java.util.List;

public interface CustomersService {
    ResultJsonObjectOperationSearch<Customer> getAllCustomersListsByCriteriaOperationSearch(Criterias[] criterias);

    ResultJsonObjectOperationStat getAllCustomersListsByCriteriaOperationStat(Criterias[] criterias);

    List<Customer> getAllCustomersByLastName(CustomersByLastNameCriteria criteria);

    List<Customer> getAllCustomersByProductMinTimes(CustomersProductCountMoreCriteria criteria);

    List<Customer> getAllCustomersByAmountInIntervalMinAndMax(CustomersProductAmountBetweenCriteria criteria);

    List<Customer> getAllBadCustomersByCountLessThen(BadCustomersLessCriteria criteria);
}
