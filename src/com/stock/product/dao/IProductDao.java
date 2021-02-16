package com.stock.product.dao;

import com.stock.IDao;

import java.util.List;

public interface IProductDao extends IDao<Product> {
    List<Product> getAll(String keyword);

    List<Product> getAllByCategory(String category);

    List<Product> getAllByCategory(String category, String keyword);
}
