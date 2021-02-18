package com.stock.payment.dao;

import com.stock.sales.dao.Sale;

import java.time.LocalDate;

public class Cheque extends Payment{
    private int chequeNumber;
    private String owner;
    private String bank;
    private  LocalDate deadline;

    public Cheque(long id, double amount, LocalDate date, String type, Sale sale, int chequeNumber, String owner, String bank, LocalDate deadline) {
        super(id, amount, date, type, sale);
        this.chequeNumber = chequeNumber;
        this.owner = owner;
        this.bank = bank;
        this.deadline = deadline;
    }

    public Cheque(double amount, LocalDate date, String type, Sale sale, int chequeNumber, String owner, String bank, LocalDate deadline) {
        super(amount, date, type, sale);
        this.chequeNumber = chequeNumber;
        this.owner = owner;
        this.bank = bank;
        this.deadline = this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getChequeNumber() {
        return chequeNumber;
    }

    public String getOwner() {
        return owner;
    }

    public String getBank() {
        return bank;
    }

    public void setChequeNumber(int chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
