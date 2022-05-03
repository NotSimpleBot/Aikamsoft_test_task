package com.guzanov.dao;

import com.guzanov.entity.Product;

public interface ProductDao {
    Product getProductById(int product_id);
}
