package com.stock.sales.dao;

import com.stock.product.dao.Product;

public class CommandLine {
    private long id;
    private int quantity;
    private  double subtotal;
    private Sale sale;
    private Product product;

    public CommandLine(long id, int quantity, Sale sale, Product product) {
        this.id = product.getId();
        this.quantity = quantity;
        this.sale = sale;
        this.product = product;
        this.subtotal = product.getPrice()*quantity;
    }

    public CommandLine(int quantity, Sale sale, Product product) {
        this.id = product.getId();
        this.quantity = quantity;
        this.sale = sale;
        this.product = product;
        this.subtotal = product.getPrice()*quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Sale getSale() {
        return sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateTotal();
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    private  void calculateTotal(){
        this.subtotal = product.getPrice()*quantity;
    }
}
