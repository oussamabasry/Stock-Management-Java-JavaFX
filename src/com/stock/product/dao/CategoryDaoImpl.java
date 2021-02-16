package com.stock.product.dao;

import com.stock.AbstractDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends AbstractDao implements ICategoryDao {


    public void add(Category category) {
        PreparedStatement pst;
        String sql = "insert into category (title) values (?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, category.getTitle());
            pst.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void delete(long id) {
        PreparedStatement pst;

        String sql = "delete from category where id = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            pst.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public Category getOne(long id) {
        PreparedStatement pst;
        ResultSet rs;
        Category category = null;
        IProductDao productDao = new ProductDaoImpl();
        List<Product> listProduct ;

        String sql = "select * from category where id = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            if (rs.next()){
                category = new Category(rs.getLong("id"), rs.getString("title"));
                listProduct = productDao.getAllByCategory(rs.getString("title"));
                category.setListProducts(listProduct);
            }


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return category;
    }

    public void update(Category category) {

        PreparedStatement pst;
        String sql = "update category set title = ? where id = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, category.getTitle());
            pst.setLong(2, category.getId());
            pst.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        Category category;
        IProductDao productDao = new ProductDaoImpl();
        List<Product> listProduct ;

        String sql = "select * from category";

        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                category = new Category(rs.getLong("id"), rs.getString("title"));
                listProduct = productDao.getAllByCategory(rs.getString("title"));
                category.setListProducts(listProduct);
                categories.add(category);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return categories;
    }

    public List<Category> getAll(String key) {
        List<Category> categories = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        Category category;
        IProductDao productDao = new ProductDaoImpl();
        List<Product> listProduct ;

        String sql = "select * from category where title LIKE ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, "%" + key + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                category = new Category(rs.getLong("id"), rs.getString("title"));
                listProduct = productDao.getAllByCategory(rs.getString("title"));
                category.setListProducts(listProduct);
                categories.add(category);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return categories;
    }


}
