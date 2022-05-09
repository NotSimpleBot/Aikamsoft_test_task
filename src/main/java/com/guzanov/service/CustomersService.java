package com.guzanov.service;

import com.guzanov.criterias.Criterias;
import com.guzanov.criterias.target.BadCustomersLessCriteria;
import com.guzanov.criterias.target.CustomersByLastNameCriteria;
import com.guzanov.criterias.target.CustomersProductAmountBetweenCriteria;
import com.guzanov.criterias.target.CustomersProductCountMoreCriteria;
import com.guzanov.deserialized_objects.ResultJsonObjectOperationSearch;
import com.guzanov.deserialized_objects.ResultJsonObjectOperationStat;
import com.guzanov.entity.Customer;

import java.util.List;

/**
 * Сервис для работы с Покупателями.
 */
public interface CustomersService {
    /**
     * Возвращает результирующий объект для записи в файл, поиск в БД на основе переданных критериев.
     * <p>
     * Актуален для операции Operation.SEARCH.
     *
     * @param criterias Критерии, на их основе осуществляется выборка в БД
     * @return Результирующий объект для записи в файл
     */
    ResultJsonObjectOperationSearch<Customer> getAllCustomersListsByCriteriaOperationSearch(Criterias[] criterias);

    /**
     * Возвращает результирующий объект для записи в файл, поиск в БД на основе переданных критериев.
     * <p>
     * Актуален для операции Operation.STAT.
     *
     * @param criterias Критерии, на их основе осуществляется выборка в БД
     * @return Результирующий объект для записи в файл
     */
    ResultJsonObjectOperationStat getAllCustomersListsByCriteriaOperationStat(Criterias[] criterias);

    /**
     * Возвращает список покупателей на основе конерктного критерия.
     * <p>
     * Критерий - Фамилия покупателя.
     *
     * @param criteria Критерий, на основе которго осуществляется выборка в БД
     */
    List<Customer> getAllCustomersByLastName(CustomersByLastNameCriteria criteria);

    /**
     * Возвращает список покупателей на основе конерктного критерия.
     * <p>
     * Критерий - название продукта + количество покупок данного продукта у покупателя.
     *
     * @param criteria Критерий, на основе которго осуществляется выборка в БД
     */
    List<Customer> getAllCustomersByProductMinTimes(CustomersProductCountMoreCriteria criteria);

    /**
     * Возвращает список покупателей на основе конерктного критерия.
     * <p>
     * Критерий - сумма за все продукты покупателя в указанном промежутке.
     *
     * @param criteria Критерий, на основе которго осуществляется выборка в БД
     */
    List<Customer> getAllCustomersByAmountInIntervalMinAndMax(CustomersProductAmountBetweenCriteria criteria);

    /**
     * Возвращает список покупателей на основе конерктного критерия.
     * <p>
     * Критерий - указанное число покупателей с минимальным колличеством продуктов в корзине за все время.
     *
     * @param criteria Критерий, на основе которго осуществляется выборка в БД
     */
    List<Customer> getAllBadCustomersByCountLessThen(BadCustomersLessCriteria criteria);
}
