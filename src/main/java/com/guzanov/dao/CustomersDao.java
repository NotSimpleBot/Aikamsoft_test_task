package com.guzanov.dao;

import com.guzanov.entity.Customer;
import com.guzanov.entity.Purchase;

import java.util.List;

/**
 * Содержит API для взаимодействия с таблицей 'customers' из базы данных.
 */
public interface CustomersDao {
    /**
     * Возвращает список покупателей/покупок (many-to-many) за указанную дату,
     * метод взаимодействует с БД на основе стандарта JDBC.
     *
     * @param date Дата, за период которой будут находиться покупатели/покупки
     * @return Список покупателей/покупок за указанную дату
     */
    List<Purchase> getAllCustomersByDate(String date);

    /**
     * Возвращает список покупателей с указанной фамилией,
     * метод взаимодействует с БД на основе стандарта JDBC.
     *
     * @param lastName Фамилия, на основе которой осуществляется поиск покупателя
     * @return Список покупателей удовлетворяющих условие
     */
    List<Customer> getAllCustomersByLastName(String lastName);

    /**
     * Возвращает список покупателей, купивших указанный продукт указанное число раз,
     * метод взаимодействует с БД на основе стандарта JDBC.
     *
     * @param productName Название продука
     * @param minTimes    Счетчик
     * @return Список покупателей удовлетворяющих условие
     */
    List<Customer> getAllCustomersByProductMinTimes(String productName, int minTimes);

    /**
     * Возвращает список покупателей суммарная стоимость продуктовой корзины которых - находится в указанном промежутке,
     * метод взаимодействует с БД на основе стандарта JDBC.
     *
     * @param minExpenses Нижняя граница денежной суммы
     * @param maxExpenses Верхняя граница денежной суммы
     * @return Список покупателей удовлетворяющих условие
     */
    List<Customer> getAllCustomersByAmountInIntervalMinAndMax(int minExpenses, int maxExpenses);

    /**
     * Возвращает список покупателей суммарное колличество товаров в корзине которых - минимально,
     * список отсортирован по возрастанию числа продуктов в корзине,
     * метод взаимодействует с БД на основе стандарта JDBC.
     *
     * @param badCustomers Счетчик - сколько покупателей с маленькой корзиной вывести
     * @return Список покупателей удовлетворяющих условие
     */
    List<Customer> getAllBadCustomersByCountLessThen(int badCustomers);

    /**
     * Возвращает покупателя по ID,
     * метод взаимодействует с БД на основе стандарта JDBC.
     *
     * @param customer_id ID покупателя в базе данных
     * @return Покупатель с указанным ID
     */
    Customer getCustomerById(int customer_id);

}
