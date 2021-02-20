package com.stock.customer.ihm;

import com.stock.customer.dao.Customer;


public class CustomerUpdateFormWindow extends CustomerAddFormWindow {
    private Customer customerUpdate;
    private CustomerListHandler handler;

    public CustomerUpdateFormWindow(Customer customer, CustomerListHandler handler) {
        this.customerUpdate = customer;
        this.handler = handler;
        this.titleLabel.setText("Update customer");
        this.window.setTitle("Update customer");
        this.confirmButton.setText("Update");
        this.firstNameTextField.setText(customer.getFirstName());
        this.lastNameTextField.setText(customer.getLastName());
        this.telTextField.setText(customer.getTel());
        this.emailTextField.setText(customer.getEmail());
        this.addressTextField.setText(customer.getAddress());
    }

    @Override
    protected void addEvents() {
        cancelButton.setOnAction(event -> window.close());
        confirmButton.setOnAction(event -> {
            if (!firstNameTextField.getText().isEmpty() && !lastNameTextField.getText().isEmpty() && !telTextField.getText().isEmpty() && !emailTextField.getText().isEmpty() && !addressTextField.getText().isEmpty()) {
                Customer customer = new Customer(
                        customerUpdate.getId(),
                        firstNameTextField.getText(),
                        lastNameTextField.getText(),
                        telTextField.getText(),
                        emailTextField.getText(),
                        addressTextField.getText()
                );
                customerDao.update(customer);
                handler.updateListCustomersView();
                window.close();
            }
        });
    }
}
