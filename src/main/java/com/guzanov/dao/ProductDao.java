package com.guzanov.dao;

import com.guzanov.entity.Product;
/**
 * Содержит API для взаимодействия с таблицей 'products' из базы данных.
 */
public interface ProductDao {
    /**
     * Возвращает продукт по ID,
     * метод взаимодействует с БД на основе стандарта JDBC.
     *
     * @param product_id ID продукта в базе данных
     * @return Продукт с указанным ID
     */
    Product getProductById(int product_id);
}
