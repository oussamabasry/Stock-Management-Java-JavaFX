package com.stock.payment.dao;

import com.stock.sales.dao.Sale;

import java.time.LocalDate;

public class Cash extends Payment{
    public Cash(long id, double amount, LocalDate date, String type, Sale sale) {
        super(id, amount, date, type, sale);
    }

    public Cash(double amount, LocalDate date, String type, Sale sale) {
        super(amount, date, type, sale);
    }
}
