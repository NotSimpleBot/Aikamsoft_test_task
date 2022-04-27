package com.guzanov.service;

import com.guzanov.Operation;
import com.guzanov.MyEntity;
import com.guzanov.ResultJsonObject;
import com.guzanov.criterias.Criterias;
import com.guzanov.criterias.target.BadCustomersLessCriteria;
import com.guzanov.criterias.target.CustomersByLastNameCriteria;
import com.guzanov.criterias.target.CustomersProductAmountBetweenCriteria;
import com.guzanov.criterias.target.CustomersProductCountMoreCriteria;
import com.guzanov.dao.CustomersDao;
import com.guzanov.dao.impl.CustomersDaoImpl;
import com.guzanov.entity.Customer;

import java.util.List;

public class CustomersServiceImpl implements CustomersService {
    private final CustomersDao CUSTOMERS_DAO;

    public CustomersServiceImpl() {
        this.CUSTOMERS_DAO = new CustomersDaoImpl();
    }


    @Override
    public ResultJsonObject<Customer> getAllCustomersListsByCriterias(Operation operation, Criterias[] criterias) {
        ResultJsonObject<Customer> resultJsonObject =
                new ResultJsonObject<>(operation, criterias.length);
        MyEntity<Customer> entity;

        for (Criterias c : criterias) {
            Class<? extends Criterias> clazz = c.getClass();
            entity = new MyEntity<>(c);
            if (clazz == CustomersByLastNameCriteria.class) {
                entity.addAll(CUSTOMERS_DAO.getAllCustomersByLastName(((CustomersByLastNameCriteria) c).getLastName()));
            } else {
                if (clazz == CustomersProductCountMoreCriteria.class) {
                    entity.addAll(CUSTOMERS_DAO.getAllCustomersByProductMinTimes(((CustomersProductCountMoreCriteria) c).getProductName(), ((CustomersProductCountMoreCriteria) c).getMinTimes()));
                } else {
                    if (clazz == CustomersProductAmountBetweenCriteria.class) {
                        entity.addAll(CUSTOMERS_DAO.getAllCustomersByAmountInIntervalMinAndMax(((CustomersProductAmountBetweenCriteria) c).getMinExpenses(), ((CustomersProductAmountBetweenCriteria) c).getMaxExpenses()));
                    } else {
                        if (clazz == BadCustomersLessCriteria.class) {
                            entity.addAll(CUSTOMERS_DAO.getAllBadCustomersByCountLessThen(((BadCustomersLessCriteria) c).getBadCustomers()));
                        }
                    }
                }
            }
            resultJsonObject.addMyEntity(entity);
        }
        return resultJsonObject;
    }

    @Override
    public List<Customer> getAllCustomersByLastName(CustomersByLastNameCriteria criteria) {
        return CUSTOMERS_DAO.getAllCustomersByLastName(criteria.getLastName());
    }

    @Override
    public List<Customer> getAllCustomersByProductMinTimes(CustomersProductCountMoreCriteria criteria) {
        return CUSTOMERS_DAO.getAllCustomersByProductMinTimes(criteria.getProductName(), criteria.getMinTimes());
    }

    @Override
    public List<Customer> getAllCustomersByAmountInIntervalMinAndMax(CustomersProductAmountBetweenCriteria criteria) {
        return CUSTOMERS_DAO.getAllCustomersByAmountInIntervalMinAndMax(criteria.getMinExpenses(), criteria.getMaxExpenses());
    }

    @Override
    public List<Customer> getAllBadCustomersByCountLessThen(BadCustomersLessCriteria criteria) {
        return CUSTOMERS_DAO.getAllBadCustomersByCountLessThen(criteria.getBadCustomers());
    }
}
