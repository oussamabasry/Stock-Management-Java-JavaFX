package com.stock.payment.dao;

import com.stock.sales.dao.Sale;

import java.time.LocalDate;

public class Payment {
 protected long id;
 protected double amount;
 protected LocalDate date;
 protected String type;
 protected Sale sale;

    public Payment(long id, double amount, LocalDate date, String type, Sale sale) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.sale = sale;
    }

    public Payment(double amount, LocalDate date, String type, Sale sale) {
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.sale = sale;
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getType() {
        return type;
    }


    public Sale getSale() {
        return sale;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
