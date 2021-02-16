package com.stock.customer.ihm;


import com.stock.customer.dao.Customer;
import com.stock.customer.dao.CustomerDaoImpl;
import com.stock.customer.dao.ICustomerDao;


import java.util.List;

public class CustomerListHandler {
    CustomerListWindow customerListWindow = null;

    public CustomerListHandler(CustomerListWindow customerListWindow){
        this.customerListWindow = customerListWindow;
    }


    public  void updateListCustomersView(){
        ICustomerDao customerDao = new CustomerDaoImpl();
        List<Customer> list = customerDao.getAll();
        customerListWindow.customerObservablesList.setAll(list);
        getTotalCustomers();
    }

    public  void updateCustomerByKeyword(String keyword){
        ICustomerDao customerDao = new CustomerDaoImpl();
        List<Customer> list = customerDao.getAll(keyword);
        customerListWindow.customerObservablesList.setAll(list);
        getTotalCustomers();
    }

    public void getTotalCustomers(){
        int count = 0;
        for ( Customer c : customerListWindow.customerObservablesList) count++;
        customerListWindow.numberValueLabel.setText(String.valueOf(count));
    }

}
