package com.stock.sales.dao;

import com.stock.AbstractDao;
import com.stock.customer.dao.Customer;
import com.stock.product.dao.Category;
import com.stock.product.dao.Product;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandLineDaoImpl extends AbstractDao implements ICommandLineDao {
    @Override
    public void add(CommandLine commandLine) {
        PreparedStatement pst;
        String sql = "insert into commandLine (commandQuantity, deliverySheetId, productId) values (?, ?, ?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, commandLine.getQuantity());
            pst.setLong(2, commandLine.getSale().getId());
            pst.setLong(3, commandLine.getProduct().getId());
            pst.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        PreparedStatement pst;
        String sql = "delete from commandLine where commandLineId = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            pst.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void update(CommandLine commandLine) {
        PreparedStatement pst;
        String sql = "update commandLine set commandQuantity = ?, deliverySheetId = ?, productId = ? where commandLineId = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, commandLine.getQuantity());
            pst.setLong(2, commandLine.getSale().getId());
            pst.setLong(3, commandLine.getProduct().getId());
            pst.setLong(4, commandLine.getId());
            pst.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public CommandLine getOne(long id) {
        PreparedStatement pst;
        ResultSet rs;
        CommandLine commandLine = null;
        Product product;
        Customer customer;
        Sale deliverySheet;
        Category category;
        String sql = "SELECT * FROM commandLine LEFT JOIN product ON commandLine.productId= product.id " +
                "LEFT JOIN deliverySheet dS on dS.id = commandLine.deliverySheetId " +
                "LEFT JOIN category c on c.id = product.categoryid " +
                "LEFT JOIN customer c2 on c2.id = dS.customerId where commandLine.commandLineId = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                category = new Category(rs.getLong("categoryId"), rs.getString("title"));
                product = new Product(rs.getLong("productId"), rs.getString("designation"), rs.getInt("quantity"), rs.getDouble("price"), rs.getDate("date").toLocalDate(), category);
                customer = new Customer(rs.getLong("customerId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("tel"), rs.getString("email"), rs.getString("address"));
                deliverySheet = new Sale(rs.getLong("deliverySheetId"), rs.getDate("deliveryDate").toLocalDate(), customer);
                commandLine = new CommandLine(rs.getLong("commandLineId"), rs.getInt("commandQuantity"), deliverySheet, product);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return commandLine;
    }

    @Override
    public List<CommandLine> getAll() {
        List<CommandLine> commandLines = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        CommandLine commandLine;
        Sale deliverySheet;
        Product product;
        Customer customer;
        Category category;
        String sql = "SELECT * FROM commandLine LEFT JOIN product ON commandLine.productId= product.id " +
                "LEFT JOIN deliverySheet dS on dS.id = commandLine.deliverySheetId " +
                "LEFT JOIN category c on c.id = product.categoryid " +
                "LEFT JOIN customer c2 on c2.id = dS.customerId";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                category = new Category(rs.getLong("categoryId"), rs.getString("title"));
                product = new Product(rs.getLong("productId"), rs.getString("designation"), rs.getInt("quantity"), rs.getDouble("price"), rs.getDate("date").toLocalDate(), category);
                customer = new Customer(rs.getLong("customerId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("tel"), rs.getString("email"), rs.getString("address"));
                deliverySheet = new Sale(rs.getLong("deliverySheetId"), rs.getDate("deliveryDate").toLocalDate(), customer);
                commandLine = new CommandLine(rs.getLong("commandLineId"), rs.getInt("commandQuantity"), deliverySheet, product);
                commandLines.add(commandLine);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return commandLines;
    }

    public List<CommandLine> getAll(long deliverySheetId) {
        List<CommandLine> commandLines = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        CommandLine commandLine;
        Sale deliverySheet;
        Product product;
        Customer customer;
        Category category;
        String sql = "SELECT * FROM commandLine LEFT JOIN product ON commandLine.productId= product.id " +
                "LEFT JOIN deliverySheet dS on dS.id = commandLine.deliverySheetId " +
                "LEFT JOIN category c on c.id = product.categoryid " +
                "LEFT JOIN customer c2 on c2.id = dS.customerId where deliverySheetId = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, deliverySheetId);
            rs = pst.executeQuery();
            while (rs.next()) {
                category = new Category(rs.getLong("categoryId"), rs.getString("title"));
                product = new Product(rs.getLong("productId"), rs.getString("designation"), rs.getInt("quantity"), rs.getDouble("price"), rs.getDate("date").toLocalDate(), category);
                customer = new Customer(rs.getLong("customerId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("tel"), rs.getString("email"), rs.getString("address"));
                deliverySheet = new Sale(rs.getLong("deliverySheetId"), rs.getDate("deliveryDate").toLocalDate(), customer);
                commandLine = new CommandLine(rs.getLong("commandLineId"), rs.getInt("commandQuantity"), deliverySheet, product);
                commandLines.add(commandLine);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return commandLines;
    }
}