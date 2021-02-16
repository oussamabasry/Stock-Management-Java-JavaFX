package com.stock.customer.ihm;

import com.stock.customer.dao.Customer;
import com.stock.customer.dao.CustomerDaoImpl;
import com.stock.customer.dao.ICustomerDao;


public class CustomerAddHandler {
    CustomerAddFormWindow customerAddFormWindow = null;

    public CustomerAddHandler(CustomerAddFormWindow customerAddFormWindow){
        this.customerAddFormWindow = customerAddFormWindow;
    }

    public void addCustomer(Customer customer){
        ICustomerDao customerDao = new CustomerDaoImpl();
        customerDao.add(customer);
    }

}
