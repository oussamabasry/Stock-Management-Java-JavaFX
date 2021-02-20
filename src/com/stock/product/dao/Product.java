package com.stock.product.dao;

import com.stock.sales.dao.CommandLine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private long id;
    private String designation;
    private int quantity;
    private double price;
    private LocalDate date;
    private double total;
    private Category category;
    private List<CommandLine> commandLines = new ArrayList<>();

    public Product(long id, String designation, int quantity, double price, LocalDate date, Category category) {
        this.id = id;
        this.designation = designation;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.total = quantity * price;
        this.category = category;
    }

    public Product(String designation, int quantity, double price, LocalDate date, Category category) {
        this.designation = designation;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.category = category;
        this.total = quantity * price;
    }

    public void addCommandLine(CommandLine commandLine) {
        commandLines.add(commandLine);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getTotal() {
        return total;
    }

    public long getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<CommandLine> getCommandLines() {
        return commandLines;
    }
}

