package com.stock.sales.dao;

import com.stock.customer.dao.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sale {
    private long id;
    private LocalDate date;
    private double total;
    private Customer customer;
    private List<CommandLine> commandLines = new ArrayList<>();

    public Sale(long id, LocalDate date, Customer customer) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        calculTotal();
    }

    public Sale(LocalDate date, Customer customer) {
        this.date = date;
        this.customer = customer;
        calculTotal();
    }
    public Sale() {
    }
    public  void addCommandLine(CommandLine commandLine){
        commandLines.add(commandLine);
    }
    public  void deleteCommandLine(CommandLine commandLine){
        commandLines.remove(commandLine);
    }
    private  void calculTotal(){
        for (CommandLine c : commandLines)
            total+= c.getSubtotal();
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCommandLines(List<CommandLine> commandLines) {
        this.commandLines = commandLines;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getTotal() {
        return total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<CommandLine> getCommandLines() {
        return commandLines;
    }

    public void setId(long id) {
        this.id = id;
    }
}