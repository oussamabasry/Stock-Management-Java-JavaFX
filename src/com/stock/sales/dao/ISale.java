package com.stock.sales.dao;

import com.stock.IDao;

import java.util.List;

public interface ISale extends IDao<Sale> {
    long addDelivery(Sale deliverySheet);
    List<Sale> getAll(long idCustomer);
}
