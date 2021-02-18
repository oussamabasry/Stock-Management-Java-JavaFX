package com.stock.sales.ihm;

import com.stock.customer.dao.Customer;
import com.stock.payment.ihm.PaymentWindow;
import com.stock.product.dao.IProductDao;
import com.stock.product.dao.Product;
import com.stock.product.dao.ProductDaoImpl;
import com.stock.sales.dao.*;

import java.util.List;

public class SaleManagementHandler {
    SalesManagementWindow salesManagementWindow = null;

    public SaleManagementHandler(SalesManagementWindow salesManagementWindow){
        this.salesManagementWindow = salesManagementWindow;
    }
    public  void updateAllListProductsView(){
        IProductDao productDao = new ProductDaoImpl();
        List<Product> list = productDao.getAll();
        salesManagementWindow.productsObservablesList.setAll(list);
    }
    public  void addProduct(){
        salesManagementWindow.quantityProductTextField.setText("");
        salesManagementWindow.idProductTextField.setText(String.valueOf(salesManagementWindow.productsTableView.getSelectionModel().getSelectedItem().getId()));
        salesManagementWindow.designProductTextField.setText(String.valueOf(salesManagementWindow.productsTableView.getSelectionModel().getSelectedItem().getDesignation()));
        salesManagementWindow.priceProductTextField.setText(String.valueOf(salesManagementWindow.productsTableView.getSelectionModel().getSelectedItem().getPrice()));
        salesManagementWindow.commandLinesObservablesList.clear();
        salesManagementWindow.commandLinesObservablesList.setAll(salesManagementWindow.sale.getCommandLines());
        upDateTotal();
    }
    public void addCommandLineButton(Customer customer){
        CommandLine commandLine;
        salesManagementWindow.commandLinesObservablesList.setAll(salesManagementWindow.sale.getCommandLines());

        if(!salesManagementWindow.idProductTextField.getText().isEmpty() && !salesManagementWindow.designProductTextField.getText().isEmpty() && !salesManagementWindow.priceProductTextField.getText().isEmpty() && !salesManagementWindow.quantityProductTextField.getText().isEmpty()){
            commandLine = new CommandLine(
                    Integer.parseInt(salesManagementWindow.quantityProductTextField.getText()),
                    salesManagementWindow.sale,
                    salesManagementWindow.productsTableView.getSelectionModel().getSelectedItem()
            );
            for(CommandLine c : salesManagementWindow.sale.getCommandLines())
                if(c.getId() == commandLine.getId()){
                    //c.setQuantity(c.getQuantity()+commandLine.getQuantity());
                    c.setQuantity(commandLine.getQuantity());
                    salesManagementWindow.commandLinesObservablesList.setAll(salesManagementWindow.sale.getCommandLines());
                    upDateTotal();
                    return;
                }

            salesManagementWindow.sale.addCommandLine(commandLine);
            salesManagementWindow.sale.setCustomer(customer);
            salesManagementWindow.commandLinesObservablesList.add(commandLine);
            salesManagementWindow.quantityProductTextField.setText("");
            upDateTotal();
        }
    }
    public  void deleteCommandLine(){
        CommandLine commandLine = salesManagementWindow.commandLineTableView.getSelectionModel().getSelectedItem();
        if( commandLine != null){
            salesManagementWindow.commandLinesObservablesList.remove(commandLine);
            salesManagementWindow.sale.deleteCommandLine(commandLine);
            upDateTotal();
        }
    }
    public  void upDateTotal(){
        double total = 0;
        for(CommandLine commandLine : salesManagementWindow.commandLinesObservablesList)
            total+= commandLine.getSubtotal();
        salesManagementWindow.totalValueLabel.setText(String.valueOf(total));
    }

    public  void addDelivery(){
        ICommandLineDao commandLineDao = new CommandLineDaoImpl();
        ISale deliverySheetdao = new SaleDaoImpl();
        for(CommandLine commandLine : salesManagementWindow.sale.getCommandLines())
            commandLineDao.add(commandLine);
        salesManagementWindow.sale.getCommandLines().clear();
        salesManagementWindow.commandLinesObservablesList.clear();
        salesManagementWindow.sale.setCustomer(salesManagementWindow.customer);
        salesManagementWindow.sale.setDate(salesManagementWindow.dateDatePicker.getValue());
        deliverySheetdao.update(salesManagementWindow.sale);
        salesManagementWindow.quantityProductTextField.setText("");
        salesManagementWindow.idProductTextField.setText("");
        salesManagementWindow.designProductTextField.setText("");
        salesManagementWindow.priceProductTextField.setText("");
        getBls();
        getDeliveryIdFromDB();
       // salesManagementWindow.window.close();
    }

    public  void getDeliveryIdFromDB(){
        ISale deliverySheetDao = new SaleDaoImpl();
        long id =deliverySheetDao.addDelivery(new Sale());
        salesManagementWindow.sale.setId(id);
        salesManagementWindow.numberSaleTextField.setText(String.valueOf(id));
    }

    public void  exitDelivery(){
        ISale deliverySheetDoa = new SaleDaoImpl();
        deliverySheetDoa.delete(salesManagementWindow.sale.getId());
        salesManagementWindow.window.close();
    }

    public  void getBls(){
        ISale deliverySheetDao = new SaleDaoImpl();
        salesManagementWindow.salesObservablesList.setAll(deliverySheetDao.getAll(salesManagementWindow.customer.getId()));
    }
    public  void addCommandLines(){
        ICommandLineDao commandLineDao = new CommandLineDaoImpl();
        Sale deliverySheet = salesManagementWindow.salesTableView.getSelectionModel().getSelectedItem();
        if(deliverySheet != null && salesManagementWindow.sale.getCommandLines().size() == 0)
            salesManagementWindow.commandLinesObservablesList.setAll(commandLineDao.getAll(deliverySheet.getId()));
        upDateTotal();
    }

    public  void deleteBl(){
        Sale deliverySheet = salesManagementWindow.salesTableView.getSelectionModel().getSelectedItem();
        ISale deliverySheetDao = new SaleDaoImpl();
        if(deliverySheet != null){
            deliverySheetDao.delete(deliverySheet.getId());
            salesManagementWindow.salesObservablesList.setAll(deliverySheetDao.getAll(salesManagementWindow.customer.getId()));
            salesManagementWindow.commandLinesObservablesList.clear();
            upDateTotal();
        }
    }

    public void upDateBl(){
        Sale sale = salesManagementWindow.salesTableView.getSelectionModel().getSelectedItem();
        if(sale != null){
            new UpDateSaleWindow(sale, salesManagementWindow.handler);
        }
    }

    public  void addPay(){
        Sale sale = salesManagementWindow.salesTableView.getSelectionModel().getSelectedItem();
        ICommandLineDao commandLineDao = new CommandLineDaoImpl();
        if(sale != null && salesManagementWindow.sale.getCommandLines().size() == 0)
            sale.setCommandLines(commandLineDao.getAll(sale.getId()));
        System.out.println(sale.getTotal());
        System.out.println(sale.getTotal());
            new PaymentWindow(sale);

    }

}
