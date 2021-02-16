package com.stock.product.dao;

import com.stock.AbstractDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl extends AbstractDao implements IProductDao {
    @Override
    public void add(Product product) {
        PreparedStatement pst;
        String sql = "insert into product (designation, quantity, price, date, categoryid) values (?, ?, ? ,? ,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, product.getDesignation());
            pst.setInt(2, product.getQuantity());
            pst.setDouble(3, product.getPrice());
            pst.setDate(4, Date.valueOf(product.getDate()));
            pst.setLong(5, product.getCategory().getId());
            pst.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    @Override
    public void delete(long id) {
        PreparedStatement pst;
        String sql = "delete from product where id = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            pst.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        PreparedStatement pst;
        String sql = "update product set designation = ?, quantity = ?, price = ?, date = ?, categoryid = ? where id = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, product.getDesignation());
            pst.setInt(2, product.getQuantity());
            pst.setDouble(3, product.getPrice());
            pst.setDate(4, Date.valueOf(product.getDate()));
            pst.setLong(5, product.getCategory().getId());
            pst.setLong(6, product.getId());
            pst.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public Product getOne(long id) {
        PreparedStatement pst;
        ResultSet rs;
        Product product = null;
        Category category;

        String sql = "SELECT * FROM product LEFT JOIN category ON product.categoryId = category.id where product.id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                category = new Category(rs.getLong("categoryId"), rs.getString("title"));
                product = new Product(rs.getLong("id"), rs.getString("designation"), rs.getInt("quantity"), rs.getDouble("price"), rs.getDate("date").toLocalDate(), category);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        Product product;
        Category category;


        String sql = "SELECT * FROM product LEFT JOIN category ON product.categoryId = category.id";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                category = new Category(rs.getLong("categoryId"), rs.getString("title"));
                product = new Product(rs.getLong("id"), rs.getString("designation"), rs.getInt("quantity"), rs.getDouble("price"), rs.getDate("date").toLocalDate(), category);
                products.add(product);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getAll(String keyword) {
        List<Product> products = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        Product product;
        Category category;

        String sql = "SELECT * FROM product LEFT JOIN category ON product.categoryId = category.id where designation LIKE ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                category = new Category(rs.getLong("categoryId"), rs.getString("title"));
                product = new Product(rs.getLong("id"), rs.getString("designation"), rs.getInt("quantity"), rs.getDouble("price"), rs.getDate("date").toLocalDate(), category);
                products.add(product);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getAllByCategory(String categoryName) {
        List<Product> products = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        Product product;
        Category category;

        /// String sql = "select * from product where designation LIKE ?";
        String sql = "SELECT * FROM product LEFT JOIN category ON product.categoryId = category.id where category.title = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, categoryName);
            rs = pst.executeQuery();
            while (rs.next()) {
                category = new Category(rs.getLong("categoryId"), rs.getString("title"));
                product = new Product(rs.getLong("id"), rs.getString("designation"), rs.getInt("quantity"), rs.getDouble("price"), rs.getDate("date").toLocalDate(), category);
                products.add(product);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getAllByCategory(String categoryName, String keyword) {
        List<Product> products = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        Product product;
        Category category;

        /// String sql = "select * from product where designation LIKE ?";
        String sql = "SELECT * FROM product LEFT JOIN category ON product.categoryId = category.id where category.title = ? And product.designation LIKE ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, categoryName);
            pst.setString(2, "%" + keyword + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                category = new Category(rs.getLong("categoryId"), rs.getString("title"));
                product = new Product(rs.getLong("id"), rs.getString("designation"), rs.getInt("quantity"), rs.getDouble("price"), rs.getDate("date").toLocalDate(), category);
                products.add(product);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return products;
    }


}


