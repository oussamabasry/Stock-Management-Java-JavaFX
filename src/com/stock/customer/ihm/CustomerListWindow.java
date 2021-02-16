package com.stock.customer.ihm;

import com.stock.customer.dao.Customer;
import com.stock.customer.dao.CustomerDaoImpl;
import com.stock.customer.dao.ICustomerDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerListWindow {
    protected ICustomerDao customerDao = new CustomerDaoImpl();
    protected CustomerListHandler handler = new CustomerListHandler(this);
    protected VBox root = new VBox();
    protected HBox searchCustomerHBox = new HBox();
    protected HBox crudActionsHBox = new HBox();
    protected HBox numberHBox = new HBox();
    protected Label numberLabel = new Label("Number of customer: ");
    protected Label numberValueLabel = new Label("0");
    protected Label searchCustomerLabel = new Label("Search a customer:");
    protected TextField searchCustomerTextField = new TextField();
    protected Button searchCustomerButton = new Button("Search");
    protected Button displayAllCustomersButton = new Button("Display all customers");
    protected Button upDateCustomerButton = new Button("Update customer");
    protected Button deleteCustomerButton = new Button("Delete customer");
    protected Scene scene = new Scene(root);
    protected Stage window = new Stage();
    protected Label titleLabel = new Label("  List of customer");
    protected TableView<Customer> customerTableView = new TableView<>();
    protected TableColumn<Customer, Long> idColumn = new TableColumn<>("ID");
    protected TableColumn<Customer, String> firstNameColumn = new TableColumn<>("First Name");
    protected TableColumn<Customer, String> lastNameColumn = new TableColumn<>("Last Name");
    protected TableColumn<Customer, String> telColumn = new TableColumn<>("Tel");
    protected TableColumn<Customer, String> emailColumn = new TableColumn<>("Email");
    protected TableColumn<Customer, String> addressColumn = new TableColumn<>("Address");
    protected ObservableList<Customer> customerObservablesList =  FXCollections.observableArrayList();


    protected void initWindow() {
        window.setScene(scene);
        window.setWidth(1175);
        window.setHeight(650);
        window.setTitle("List products");
        customerTableView.setEditable(true);
    }

    protected void addNodesToWindow() {
        root.getChildren().add(titleLabel);
        searchCustomerHBox.getChildren().addAll(searchCustomerLabel, searchCustomerTextField, searchCustomerButton);
        root.getChildren().add(searchCustomerHBox);
        crudActionsHBox.getChildren().addAll(displayAllCustomersButton, upDateCustomerButton, deleteCustomerButton);
        root.getChildren().add(crudActionsHBox);
        root.getChildren().add(customerTableView);
        numberHBox.getChildren().addAll(numberLabel, numberValueLabel);
        root.getChildren().add(numberHBox);
    }
    protected   void addColumnToTableview(){
        customerTableView.getColumns().addAll(
                idColumn,
                firstNameColumn,
                lastNameColumn,
                telColumn,
                emailColumn,
                addressColumn
        );
        customerTableView.setItems(customerObservablesList);
    }
    protected void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");
        titleLabel.getStyleClass().add("labelTitle");
        numberLabel.getStyleClass().add("labelTotal");
        numberValueLabel.getStyleClass().add("labelTotal");
        titleLabel.setMaxWidth(window.getWidth());
        titleLabel.setPadding(new Insets(20, 20, 20, 20));
        numberHBox.getStyleClass().add("boxTotal");
        numberHBox.setPadding(new Insets(8, 8, 8, 8));
        root.setSpacing(20);
        searchCustomerHBox.setSpacing(20);
        crudActionsHBox.setSpacing(20);
        root.setPadding(new Insets(20, 20, 20, 20));
    }

    protected void addEvents() {

        searchCustomerButton.setOnAction(event -> {
            if (!searchCustomerTextField.getText().isEmpty()) {
                handler.updateCustomerByKeyword(searchCustomerTextField.getText());
            }
        });

       displayAllCustomersButton.setOnAction(event -> handler.updateListCustomersView());

        deleteCustomerButton.setOnAction(event -> {
            Customer customer = customerTableView.getSelectionModel().getSelectedItem();
            if(customer != null) {
                customerDao.delete(customer.getId());
                  handler.updateListCustomersView();
            }
        });

        upDateCustomerButton.setOnAction(event -> {
            Customer customer = customerTableView.getSelectionModel().getSelectedItem();
            if(customer != null) {
                 new CustomerUpdateFormWindow(customer, handler);
            }
        });
    }


    public void configTableView() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(100);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameColumn.setPrefWidth(175);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameColumn.setPrefWidth(175);
        telColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
        telColumn.setPrefWidth(175);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setPrefWidth(200);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressColumn.setPrefWidth(308);
    }

    public CustomerListWindow() {
        initWindow();
        addStylesToNodes();
        addColumnToTableview();
        configTableView();
        handler.updateListCustomersView();
        addNodesToWindow();
        addEvents();
        window.show();
    }


}

