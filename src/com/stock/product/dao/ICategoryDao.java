package com.stock.product.dao;

import com.stock.IDao;

import java.util.List;

public interface ICategoryDao extends IDao<Category> {
    List<Category> getAll(String keyword);
}
