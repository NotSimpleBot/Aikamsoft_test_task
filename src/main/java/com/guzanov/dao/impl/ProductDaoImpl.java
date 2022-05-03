package com.guzanov.dao.impl;

import com.guzanov.connection.JdbcConnection;
import com.guzanov.dao.ProductDao;
import com.guzanov.entity.Product;
import com.guzanov.helpers.ErrorInJson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDaoImpl implements ProductDao {
    private final Connection connection;

    public ProductDaoImpl() {
        this.connection = JdbcConnection.getConnection();
    }

    @Override
    public Product getProductById(int product_id) {
        Product product = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * " +
                    "FROM products " +
                    "WHERE id = " + product_id);
            while (resultSet.next()) {
                product =
                        new Product(resultSet.getString("product_name").trim(), resultSet.getInt("cost"));
            }
            statement.close();
        } catch (SQLException e) {
            ErrorInJson.writeError(e);
        }
        return product;
    }
}
