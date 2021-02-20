package com.stock.payment.dao;

import com.stock.sales.dao.Sale;

import java.time.LocalDate;

public class BankCard extends Payment {
    private String accountNumber;

    public BankCard(long id, double amount, LocalDate date, String type, Sale sale, String accountNumber) {
        super(id, amount, date, type, sale);
        this.accountNumber = accountNumber;
    }

    public BankCard(double amount, LocalDate date, String type, Sale sale, String accountNumber) {
        super(amount, date, type, sale);
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
