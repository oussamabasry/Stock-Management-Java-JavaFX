package com.stock.sales.ihm;

import com.stock.customer.dao.Customer;
import com.stock.customer.dao.CustomerDaoImpl;
import com.stock.customer.dao.ICustomerDao;
import com.stock.sales.dao.SaleDaoImpl;
import com.stock.sales.dao.ISale;
import com.stock.sales.dao.Sale;

public class UpDateSaleHandler {
   static UpDateSaleWindow upDateBlWindow = null;

   public UpDateSaleHandler(UpDateSaleWindow upDateBlWindow){
       this.upDateBlWindow = upDateBlWindow;
   }

   public void updateChoiceBoxCustomers(){
       ICustomerDao customerDao = new CustomerDaoImpl();
       upDateBlWindow.customers.setAll(customerDao.getAll());
       upDateBlWindow.customerChoiceBox.setItems(upDateBlWindow.customers);
       for (Customer customer : upDateBlWindow.customers)
           if(customer.getId() == upDateBlWindow.sale.getCustomer().getId()){
               upDateBlWindow.customerChoiceBox.setValue(customer);
               return;
           }
   }

   public  void updateBl(){
       ISale deliverySheetDao = new SaleDaoImpl();
       Sale sale = upDateBlWindow.sale;
       sale.setDate(upDateBlWindow.dateDatePicker.getValue());
       sale.setCustomer(upDateBlWindow.customerChoiceBox.getValue());
       deliverySheetDao.update(sale);
       upDateBlWindow.saleManagementHandler.getBls();
       upDateBlWindow.window.close();
   }

}
