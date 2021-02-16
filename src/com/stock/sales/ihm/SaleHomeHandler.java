package com.stock.sales.ihm;

import com.stock.customer.dao.Customer;
import com.stock.customer.dao.CustomerDaoImpl;
import com.stock.customer.dao.ICustomerDao;


import java.util.List;

public class SaleHomeHandler {
    SaleHomeWindow saleHomeWindow = null;

    public SaleHomeHandler(SaleHomeWindow saleHomeWindow){
        this.saleHomeWindow = saleHomeWindow;
    }


    public  void updateListCustomersView(){
        ICustomerDao customerDao = new CustomerDaoImpl();
        List<Customer> list = customerDao.getAll();
        saleHomeWindow.customerObservablesList.setAll(list);
        getTotalCustomers();
    }

    public  void updateCustomerByKeyword(String keyword){
        ICustomerDao customerDao = new CustomerDaoImpl();
        List<Customer> list = customerDao.getAll(keyword);
        saleHomeWindow.customerObservablesList.setAll(list);
        getTotalCustomers();
    }

    public void getTotalCustomers(){
        int count = 0;
        for ( Customer c : saleHomeWindow.customerObservablesList) count++;
        saleHomeWindow.numberValueLabel.setText(String.valueOf(count));
    }

    public void addBl(){
        Customer customer = saleHomeWindow.customerTableView.getSelectionModel().getSelectedItem();
        new SalesManagementWindow(customer);
    }

}
