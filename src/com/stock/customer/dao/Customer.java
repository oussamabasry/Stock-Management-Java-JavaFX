package com.stock.customer.dao;

import com.stock.sales.dao.Sale;
import com.stock.sales.dao.Sale;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private long id;
    private String firstName;
    private String lastName;
    private String tel;
    private String email;
    private String address;
    private List<Sale> salesList = new ArrayList<>();

    public Customer(long id, String firstName, String lastName, String tel, String email, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tel = tel;
        this.email = email;
        this.address = address;
    }

    public Customer(String firstName, String lastName, String tel, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tel = tel;
        this.email = email;
        this.address = address;
    }

    public void addSale(Sale sale) {
        salesList.add(sale);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Sale> getSalesList() {
        return salesList;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
