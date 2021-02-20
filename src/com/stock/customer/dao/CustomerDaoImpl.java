package com.stock.customer.dao;

import com.stock.AbstractDao;
import com.stock.product.dao.Category;
import com.stock.product.dao.Product;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl extends AbstractDao implements ICustomerDao {
    @Override
    public void add(Customer customer) {
        PreparedStatement pst;
        String sql = "insert into customer (firstName, lastName, tel, email, address) values (?, ?, ? ,? ,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, customer.getFirstName());
            pst.setString(2, customer.getLastName());
            pst.setString(3, customer.getTel());
            pst.setString(4, customer.getEmail());
            pst.setString(5, customer.getAddress());
            pst.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        PreparedStatement pst;
        String sql = "delete from customer where id = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            pst.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {
        PreparedStatement pst;
        String sql = "update customer set firstName = ?, lastName = ?, tel = ?, email = ?, address = ? where id = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, customer.getFirstName());
            pst.setString(2, customer.getLastName());
            pst.setString(3, customer.getTel());
            pst.setString(4, customer.getEmail());
            pst.setString(5, customer.getAddress());
            pst.setLong(6, customer.getId());
            pst.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public Customer getOne(long id) {
        PreparedStatement pst;
        ResultSet rs;
        Customer customer = null;
        String sql = "SELECT * FROM customer where customer.id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                customer = new Customer(rs.getLong("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("tel"), rs.getString("email"), rs.getString("address"));
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        Customer customer;
        String sql = "SELECT * FROM customer";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                customer = new Customer(rs.getLong("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("tel"), rs.getString("email"), rs.getString("address"));
                customers.add(customer);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return customers;
    }

    public List<Customer> getAll(String keyword) {
        List<Customer> customers = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        Customer customer;
        String sql = "SELECT * FROM customer  where customer.firstName LIKE ? Or customer.lastName Like ? ";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                customer = new Customer(rs.getLong("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("tel"), rs.getString("email"), rs.getString("address"));
                customers.add(customer);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return customers;
    }
}
