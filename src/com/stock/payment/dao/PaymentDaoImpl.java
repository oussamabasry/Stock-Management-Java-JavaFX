package com.stock.payment.dao;

import com.stock.AbstractDao;
import com.stock.product.dao.Category;
import com.stock.product.dao.Product;
import com.stock.sales.dao.ISale;
import com.stock.sales.dao.Sale;
import com.stock.sales.dao.SaleDaoImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl extends AbstractDao implements IPayment {
    @Override
    public void add(Payment payment) {
        PreparedStatement pst;
        String sql;
        if (payment instanceof Cheque) {
            Cheque cheque = (Cheque) payment;
            sql = "insert into cheque (amountCheque, dateCheque, typePayment, saleId, chequeNumber, owner, bank, deadline) values (?, ?, ? ,? ,?, ?, ?, ?)";
            try {
                pst = connection.prepareStatement(sql);
                pst.setDouble(1, cheque.getAmount());
                pst.setDate(2, Date.valueOf(cheque.getDate()));
                pst.setString(3, cheque.getType());
                pst.setLong(4, cheque.getSale().getId());
                pst.setInt(5, cheque.getChequeNumber());
                pst.setString(6, cheque.getOwner());
                pst.setString(7, cheque.getBank());
                pst.setDate(8, Date.valueOf(cheque.getDeadline()));
                pst.execute();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        } else if (payment instanceof Cash) {
            Cash cash = (Cash) payment;
           String sql1 = "insert into cash (amountCash, date, type, saleId) values (?, ?, ? ,? )";
            try {
                pst = connection.prepareStatement(sql1 );
                pst.setDouble(1, cash.getAmount());
                pst.setDate(2, Date.valueOf(cash.getDate()));
                pst.setString(3, cash.getType());
                pst.setLong(4, cash.getSale().getId());
                pst.execute();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

    }

    @Override
    public void delete(Payment payment) {
        PreparedStatement pst;
        String sql;
        if(payment instanceof Cheque){
            sql = "delete from cheque where idCheque = ?";
            try {
                pst = connection.prepareStatement(sql);
                pst.setLong(1, payment.getId());
                pst.execute();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }else if(payment instanceof Cash){
            sql = "delete from cash where idCash = ?";
            try {
                pst = connection.prepareStatement(sql);
                pst.setLong(1, payment.getId());
                pst.execute();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

    }

    @Override
    public void update(Payment payment) {
        PreparedStatement pst;
        String sql;

        if(payment instanceof  Cheque){
            sql = "update cheque set amountCheque = ?, dateCheque = ?, typePayment = ?, saleId = ?, chequeNumber = ?, owner = ?, bank = ?, deadline=? where idCheque = ?";
            try {
                pst = connection.prepareStatement(sql);
                pst.setDouble(1, payment.getAmount());
                pst.setDate(2, Date.valueOf(payment.getDate()));
                pst.setString(3, payment.getType());
                pst.setLong(4, payment.getSale().getId());
                pst.setInt(5, ((Cheque) payment).getChequeNumber());
                pst.setString(6, ((Cheque) payment).getOwner());
                pst.setString(7, ((Cheque) payment).getBank());
                pst.setDate(8, Date.valueOf(((Cheque) payment).getDeadline()));
                pst.setLong(9, payment.getId());
                pst.execute();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }else if(payment instanceof Cash){
            sql = "update cash set amountCash = ?, date = ?, type = ?, saleId = ? where idCash = ?";
            try {
                pst = connection.prepareStatement(sql);
                pst.setDouble(1, payment.getAmount());
                pst.setDate(2, Date.valueOf(payment.getDate()));
                pst.setString(3, payment.getType());
                pst.setLong(4, payment.getSale().getId());
                pst.setLong(5, payment.getId());
                pst.execute();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

    }


    @Override
    public List<Payment> getAll(Sale sale) {
        List<Payment> payments = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        Cheque cheque;
        Cash cash;

        String sql = "SELECT * FROM cheque where saleId = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, sale.getId());
            rs = pst.executeQuery();

            while (rs.next()) {
               cheque = new Cheque(rs.getLong("idCheque"),rs.getDouble("amountCheque"),rs.getDate("dateCheque").toLocalDate(), rs.getString("typePayment"),sale, rs.getInt("chequeNumber"), rs.getString("owner"), rs.getString("bank"), rs.getDate("deadline").toLocalDate());
                payments.add(cheque);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        sql = "SELECT * FROM cash where saleId = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, sale.getId());
            rs = pst.executeQuery();

            while (rs.next()) {
                cash = new Cash(rs.getLong("idCash"),rs.getDouble("amountCash"),rs.getDate("date").toLocalDate(), rs.getString("type"),sale);
                payments.add(cash);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return payments;
    }

}
