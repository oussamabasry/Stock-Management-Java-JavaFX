package com.stock.product.dao;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private long id;
    private String title;
    private List<Product> listProducts = new ArrayList<>();

    public Category(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    public Category(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
