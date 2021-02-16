package com.stock.customer.dao;

import com.stock.IDao;
import com.stock.product.dao.Product;

import java.util.List;

public interface ICustomerDao extends IDao<Customer> {
    List<Customer> getAll(String keyword);
}
