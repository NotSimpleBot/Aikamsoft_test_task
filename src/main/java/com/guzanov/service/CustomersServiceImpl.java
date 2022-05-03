package com.guzanov.service;

import com.guzanov.helpers.Operation;
import com.guzanov.criterias.Criterias;
import com.guzanov.criterias.target.*;
import com.guzanov.dao.CustomersDao;
import com.guzanov.dao.impl.CustomersDaoImpl;
import com.guzanov.deserialized_objects.ResultJsonObjectOperationSearch;
import com.guzanov.deserialized_objects.ResultJsonObjectOperationStat;
import com.guzanov.entity.Customer;
import com.guzanov.entity.Purchase;

import java.text.SimpleDateFormat;
import java.util.*;

public class CustomersServiceImpl implements CustomersService {
    private final CustomersDao CUSTOMERS_DAO;

    public CustomersServiceImpl() {
        this.CUSTOMERS_DAO = new CustomersDaoImpl();
    }


    @Override
    public ResultJsonObjectOperationSearch<Customer> getAllCustomersListsByCriteriaOperationSearch(Criterias[] criterias) {
        ResultJsonObjectOperationSearch<Customer> resultJsonObject =
                new ResultJsonObjectOperationSearch<>(Operation.SEARCH, criterias.length);
        ResultJsonObjectOperationSearch.MyEntityOperationSearch<Customer> entity;

        for (Criterias c : criterias) {
            Class<? extends Criterias> clazz = c.getClass();
            entity = new ResultJsonObjectOperationSearch.MyEntityOperationSearch<>(c);
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
    public ResultJsonObjectOperationStat getAllCustomersListsByCriteriaOperationStat(Criterias[] criterias) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int totalDays = 0;
        Set<ResultJsonObjectOperationStat.MyEntryOperationStat> resultSet =
                new LinkedHashSet<>();
        for (Criterias c : criterias) {
            if (c.getClass() == CustomersBetweenTwoDatesCriteria.class) {
                Calendar start_date = Calendar.getInstance();
                Calendar end_date = Calendar.getInstance();
                start_date.setTime(((CustomersBetweenTwoDatesCriteria) c).getStartDate());
                end_date.setTime(((CustomersBetweenTwoDatesCriteria) c).getEndDate());


                List<Purchase> purchases;
                List<ResultJsonObjectOperationStat.MyEntryOperationStat> interList;


                while (!start_date.after(end_date)) {
                    int day = start_date.get(Calendar.DAY_OF_WEEK);
                    if ((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY)) {
                        purchases =
                                CUSTOMERS_DAO.getAllCustomersByDate(dateFormat.format(start_date.getTime()));
                        interList =
                                new ArrayList<>();
                        for (Purchase p : purchases) {
                            Customer customer = p.getCustomer();
                            String fullName = customer.getLastName() + " " + customer.getFirstName();
                            List<ResultJsonObjectOperationStat.MyEntryOperationStat.InnerEntryProductInfo> innerEntryProductInfo = new ArrayList<>();
                            innerEntryProductInfo.add(new ResultJsonObjectOperationStat.MyEntryOperationStat.InnerEntryProductInfo(p.getProduct().getProductName(), p.getProduct().getCost()));
                            interList.add(new ResultJsonObjectOperationStat.MyEntryOperationStat(fullName, innerEntryProductInfo, 0));
                        }
                        resultSet.addAll(interList);
                        for (ResultJsonObjectOperationStat.MyEntryOperationStat out : resultSet) {
                            for (ResultJsonObjectOperationStat.MyEntryOperationStat in : interList) {
                                if (out.equals(in)) {
                                    out.getPurchases().add(new ResultJsonObjectOperationStat.MyEntryOperationStat.InnerEntryProductInfo(in.getPurchases().get(0).getName(), in.getPurchases().get(0).getExpenses()));
                                }
                            }
                        }
                        totalDays++;
                    }
                    start_date.add(Calendar.DATE, 1);
                }
            }
        }
        List<ResultJsonObjectOperationStat.MyEntryOperationStat> sortedResult = new ArrayList<>(resultSet);
        Collections.sort(sortedResult);
        return new ResultJsonObjectOperationStat(Operation.STAT, totalDays, sortedResult);
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
