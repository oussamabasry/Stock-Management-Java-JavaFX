package com.stock.sales.dao;


import com.mysql.jdbc.Statement;
import com.stock.AbstractDao;
import com.stock.customer.dao.Customer;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleDaoImpl extends AbstractDao implements ISale {
    @Override
    public void add(Sale sale) {
        PreparedStatement pst;
        String sql = "insert into deliverySheet (deliveryDate, customerId) values (?, ?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setDate(1, Date.valueOf(sale.getDate()));
            pst.setLong(2, sale.getCustomer().getId());
            pst.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public long addDelivery(Sale sale) {

        long generatedKey = 0;
        String sql = "insert into deliverySheet (deliveryDate) values (?)";
        PreparedStatement pst;
        try {
            pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setDate(1, null);
            pst.execute();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return generatedKey;
    }

    @Override
    public void delete(long id) {
        PreparedStatement pst;
        String sql = "delete from deliverySheet where id = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            pst.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void update(Sale sale) {
        PreparedStatement pst;
        String sql = "update deliverySheet set deliveryDate = ?, customerId = ? where id = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setDate(1, Date.valueOf(sale.getDate()));
            pst.setLong(2, sale.getCustomer().getId());
            pst.setLong(3, sale.getId());
            pst.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public Sale getOne(long id) {
        PreparedStatement pst;
        ResultSet rs;
        Sale sale = null;
        Customer customer;

        String sql = "SELECT * FROM deliverySheet LEFT JOIN customer ON deliverySheet.customerId = customer.id where deliverySheet.id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                customer = new Customer(rs.getLong("customerId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("tel"), rs.getString("email"), rs.getString("address"));
                sale = new Sale(rs.getLong("id"), rs.getDate("deliveryDate").toLocalDate(), customer);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return sale;
    }

    @Override
    public List<Sale> getAll() {
        return null;
    }

    public List<Sale> getAll(long idCustomer) {
        List<Sale> sales = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        Sale deliverySheet;
        Customer customer;
        String sql = "SELECT * FROM deliverySheet LEFT JOIN customer c on deliverySheet.customerId = c.id where deliverySheet.customerId = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, idCustomer);
            rs = pst.executeQuery();
            while (rs.next()) {
                customer = new Customer(rs.getLong("customerId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("tel"), rs.getString("email"), rs.getString("address"));
                deliverySheet = new Sale(rs.getLong("id"), rs.getDate("deliveryDate").toLocalDate(), customer);
                sales.add(deliverySheet);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return sales;
    }
}
