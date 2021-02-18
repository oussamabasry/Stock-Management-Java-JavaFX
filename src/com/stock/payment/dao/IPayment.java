package com.stock.payment.dao;


import com.stock.sales.dao.Sale;

import java.util.List;

public interface IPayment  {
    void add(Payment payment);
    void delete(Payment payment);
    List<Payment> getAll(Sale sale);
    void update(Payment payment);
}
