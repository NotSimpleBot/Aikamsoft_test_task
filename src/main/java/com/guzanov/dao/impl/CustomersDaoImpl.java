package com.guzanov.dao.impl;

import com.guzanov.connection.JdbcConnection;
import com.guzanov.dao.CustomersDao;
import com.guzanov.entity.Customer;
import com.guzanov.entity.Product;
import com.guzanov.entity.Purchase;
import com.guzanov.helpers.ErrorInJson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс-реализация интерфейса CustomersDao.
 * <p>
 * Содержит бизнес-логику процесса взаимодействия с БД (с таблицей 'customers').
 */
public class CustomersDaoImpl implements CustomersDao {
    private final Connection connection;

    public CustomersDaoImpl() {
        this.connection = JdbcConnection.getConnection();
    }


    @Override
    public List<Purchase> getAllCustomersByDate(String date) {
        ProductDaoImpl productDao = new ProductDaoImpl();
        List<Purchase> purchases = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * " +
                    "FROM purchases " +
                    "WHERE data = '" + date + "';");
            while (resultSet.next()) {
                Customer customer = getCustomerById(resultSet.getInt("customer_id"));
                Product product = productDao.getProductById(resultSet.getInt("product_id"));
                Purchase purchase =
                        new Purchase(customer, product, resultSet.getDate("data"));
                purchases.add(purchase);
            }
            statement.close();
        } catch (SQLException e) {
            ErrorInJson.writeError(e);
        }
        return purchases;
    }

    @Override
    public List<Customer> getAllCustomersByLastName(String lastName) {
        List<Customer> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * " +
                    "FROM customers " +
                    "WHERE last_name='" + lastName + "'");
            while (resultSet.next()) {
                Customer customer =
                        new Customer(resultSet.getString("first_name").trim(), resultSet.getString("last_name").trim());
                result.add(customer);
            }
            statement.close();
        } catch (SQLException e) {
            ErrorInJson.writeError(e);
        }
        return result;
    }

    @Override
    public List<Customer> getAllCustomersByProductMinTimes(String productName, int minTimes) {
        List<Customer> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT customers.first_name, customers.last_name " +
                    "FROM customers " +
                    "JOIN purchases " +
                    "ON customers.id = purchases.customer_id " +
                    "JOIN products " +
                    "ON products.id = purchases.product_id " +
                    "WHERE products.product_name = '" + productName + "' " +
                    "GROUP BY customers.first_name, customers.last_name " +
                    "HAVING count(*) > " + minTimes + ";");
            while (resultSet.next()) {
                Customer customer =
                        new Customer(resultSet.getString("first_name").trim(), resultSet.getString("last_name").trim());
                result.add(customer);
            }
            statement.close();
        } catch (SQLException e) {
            ErrorInJson.writeError(e);
        }
        return result;
    }

    @Override
    public List<Customer> getAllCustomersByAmountInIntervalMinAndMax(int minExpenses, int maxExpenses) {
        List<Customer> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT customers.first_name, customers.last_name " +
                    "FROM customers " +
                    "JOIN purchases " +
                    "ON customers.id = purchases.customer_id " +
                    "JOIN products " +
                    "ON products.id = purchases.product_id " +
                    "GROUP BY customers.first_name, customers.last_name " +
                    "HAVING sum(products.cost) BETWEEN " + minExpenses + " AND " + maxExpenses + ";");
            while (resultSet.next()) {
                Customer customer =
                        new Customer(resultSet.getString("first_name").trim(), resultSet.getString("last_name").trim());
                result.add(customer);
            }
            statement.close();
        } catch (SQLException e) {
            ErrorInJson.writeError(e);
        }

        return result;
    }

    @Override
    public List<Customer> getAllBadCustomersByCountLessThen(int badCustomers) {
        List<Customer> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT customers.first_name, customers.last_name, count(*) " +
                    "FROM customers " +
                    "JOIN purchases " +
                    "ON customers.id = purchases.customer_id " +
                    "JOIN products " +
                    "ON products.id = purchases.product_id " +
                    "GROUP BY customers.first_name, customers.last_name " +
                    "ORDER BY count(*) " +
                    "LIMIT " + badCustomers + ";");
            while (resultSet.next()) {
                Customer customer =
                        new Customer(resultSet.getString("first_name").trim(), resultSet.getString("last_name").trim());
                result.add(customer);
            }
            statement.close();
        } catch (SQLException e) {
            ErrorInJson.writeError(e);
        }
        return result;
    }

    @Override
    public Customer getCustomerById(int customer_id) {
        Customer customer = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * " +
                    "FROM customers " +
                    "WHERE id = " + customer_id);
            while (resultSet.next()) {
                customer =
                        new Customer(resultSet.getString("first_name").trim(), resultSet.getString("last_name").trim());
            }
            statement.close();
        } catch (SQLException e) {
            ErrorInJson.writeError(e);
        }
        return customer;
    }


}
