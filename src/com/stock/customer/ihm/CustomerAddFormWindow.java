package com.stock.customer.ihm;

import com.stock.customer.dao.Customer;
import com.stock.customer.dao.CustomerDaoImpl;
import com.stock.customer.dao.ICustomerDao;
import com.stock.product.dao.*;
import com.stock.product.ihm.ProductsAddAndUpdateHandler;
import com.stock.product.ihm.ProductsListHandler;
import com.stock.product.ihm.ProductsListWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerAddFormWindow {
    protected ICustomerDao customerDao = new CustomerDaoImpl();
    protected ICategoryDao categoryDao = new CategoryDaoImpl();
    private CustomerAddHandler handler = new CustomerAddHandler(this);
    protected ProductsListWindow productsListWindow = ProductsListHandler.getListHandler();
    protected  VBox root = new VBox();
    protected Scene scene = new Scene(root);
    protected Stage window = new Stage();
    protected Label titleLabel = new Label("  New Customer");
    protected Label firstNameLabel = new Label("First Name:");
    protected TextField firstNameTextField = new TextField();
    protected Label lastNameLabel = new Label("Last Name:");
    protected TextField lastNameTextField = new TextField();
    protected Label telLabel = new Label("Tel:");
    protected TextField telTextField = new TextField();
    protected Label emailLabel = new Label("Email");
    protected TextField emailTextField = new TextField();
    protected Label addressLabel = new Label("Address:");
    protected TextField addressTextField = new TextField();
    protected HBox buttonsBox = new HBox();
    protected Button confirmButton = new Button("Add");
    protected Button cancelButton = new Button("Cancel");
    protected ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();


    protected void initWindow() {
        window.setScene(scene);
        window.setWidth(600);
        window.setHeight(530);
        window.setTitle("New Product");

    }

    protected void addNodesToWindow() {
        root.getChildren().add(titleLabel);
        root.getChildren().addAll(firstNameLabel, firstNameTextField);
        root.getChildren().addAll(lastNameLabel, lastNameTextField);
        root.getChildren().addAll(telLabel, telTextField);
        root.getChildren().addAll(emailLabel, emailTextField);
        root.getChildren().addAll(addressLabel, addressTextField);
        buttonsBox.getChildren().addAll(confirmButton, cancelButton);
        root.getChildren().add(buttonsBox);
    }

    protected void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");
        root.getStyleClass().add("addProductRoot");
        titleLabel.getStyleClass().add("labelTitle");
        titleLabel.setMaxWidth(window.getWidth());
        titleLabel.setPadding(new Insets(20, 20, 20, 20));
        firstNameLabel.getStyleClass().add("labelForm");
        lastNameLabel.getStyleClass().add("labelForm");
        telLabel.getStyleClass().add("labelForm");
        emailLabel.getStyleClass().add("labelForm");
        addressLabel.getStyleClass().add("labelForm");
        root.setSpacing(15);
        root.setPadding(new Insets(20, 20, 20, 20));
        buttonsBox.setSpacing(20);
        confirmButton.getStyleClass().add("buttonsAddProduct");
        cancelButton.getStyleClass().add("buttonsAddProduct");
    }

    protected void addEvents() {
        cancelButton.setOnAction(event -> window.close());
        confirmButton.setOnAction(event -> {
            if (!firstNameTextField.getText().isEmpty() && !lastNameTextField.getText().isEmpty() && !telTextField.getText().isEmpty() && !emailTextField.getText().isEmpty() && !addressTextField.getText().isEmpty()) {
                Customer customer = new Customer(
                        firstNameTextField.getText(),
                        lastNameTextField.getText(),
                        telTextField.getText(),
                        emailTextField.getText(),
                        addressTextField.getText()
                );
                handler.addCustomer(customer);
                window.close();
            }

        });
      /*  window.setOnCloseRequest(event -> {
            event.consume();
        });*/
    }



    public CustomerAddFormWindow() {
        initWindow();
        addStylesToNodes();
        addNodesToWindow();
        addEvents();
        window.show();
    }
}

