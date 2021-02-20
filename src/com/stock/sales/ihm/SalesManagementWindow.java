package com.stock.sales.ihm;

import com.stock.customer.dao.Customer;
import com.stock.product.dao.Product;
import com.stock.sales.dao.CommandLine;
import com.stock.sales.dao.Sale;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;

public class SalesManagementWindow {
    protected SaleManagementHandler handler = new SaleManagementHandler(this);
    protected VBox root = new VBox();
    protected Scene scene = new Scene(root);
    protected Stage window = new Stage();
    protected HBox actionsHBox = new HBox();
    protected Button saveButton = new Button("Add Sale");
    protected Button exitButton = new Button("Exit");
    protected Button payButton = new Button("Pay");
    protected Button deleteSaleButton = new Button("Delete Sale");
    protected Button upDateSaleButton = new Button("Up Date Sale");
    protected HBox bodyHBox = new HBox();
    protected VBox leftVBox = new VBox();
    protected VBox rightVBox = new VBox();
    protected VBox saleDetailVBox = new VBox();
    protected Label saleDetailLabel = new Label("Sale Details");
    protected HBox numberSaleHBox = new HBox();
    protected Label numberSaleLabel = new Label("N°Sale: ");
    protected TextField numberSaleTextField = new TextField();
    protected HBox customerHBox = new HBox();
    protected Label customerLabel = new Label("Customer: ");
    protected Label customerNameLabel = new Label("");
    protected HBox dateHBox = new HBox();
    protected Label dateLabel = new Label("Date:    ");
    protected DatePicker dateDatePicker = new DatePicker();
    protected HBox commandLineContainerHBox = new HBox();
    protected VBox commandLineInfosVBox = new VBox();
    protected HBox commandLineActionsHBox = new HBox();
    protected HBox idProductHBox = new HBox();
    protected Label idProductLabel = new Label("ID:               ");
    protected TextField idProductTextField = new TextField();
    protected HBox designProductHBox = new HBox();
    protected Label designProductLabel = new Label("Design:       ");
    protected TextField designProductTextField = new TextField();
    protected HBox priceProductHBox = new HBox();
    protected Label priceProductLabel = new Label("Price:          ");
    protected TextField priceProductTextField = new TextField();
    protected HBox quantityProductHBox = new HBox();
    protected Label quantityProductLabel = new Label("Quantity:    ");
    protected TextField quantityProductTextField = new TextField();
    protected Button addCommandLineButton = new Button("+");
    protected Button deleteCommandLineButton = new Button("-");
    protected VBox listSalesVBox = new VBox();
    protected Label listSalesLabel = new Label("List of Sales: ");
    protected HBox totalHBox = new HBox();
    protected Label totalLabel = new Label("  Total: ");
    protected Label totalValueLabel = new Label("0,00");
    protected Label blTableLabel = new Label("Sales");
    protected VBox tableBlVBox = new VBox();
    protected TableView<Sale> salesTableView = new TableView<>();
    protected TableColumn<Sale, Long> saleIdColumn = new TableColumn<>("ID");
    protected TableColumn<Sale, String> saleDateColumn = new TableColumn<>("Date");
    protected TableColumn<Sale, String> saleCustomerColumn = new TableColumn<>("Customer");
    protected ObservableList<Sale> salesObservablesList = FXCollections.observableArrayList();
    protected VBox tableProductsVBox = new VBox();
    protected TableView<Product> productsTableView = new TableView<>();
    protected TableColumn<Product, Long> productIdColumn = new TableColumn<>("ID");
    protected TableColumn<Product, String> productDesignationColumn = new TableColumn<>("Designation");
    protected TableColumn<Product, Double> productPriceColumn = new TableColumn<>("Price");
    protected ObservableList<Product> productsObservablesList = FXCollections.observableArrayList();
    protected TableView<CommandLine> commandLineTableView = new TableView<>();
    protected TableColumn<CommandLine, Long> commandLineIdColumn = new TableColumn<>("ID");
    protected TableColumn<CommandLine, String> commandLineDesignationColumn = new TableColumn<>("Designation");
    protected TableColumn<CommandLine, Double> commandLinePriceColumn = new TableColumn<>("Price");
    protected TableColumn<CommandLine, Integer> commandLineQuantityColumn = new TableColumn<>("Quantity");
    protected TableColumn<CommandLine, Double> commandLineSubtotalColumn = new TableColumn<>("Subtotal");
    protected ObservableList<CommandLine> commandLinesObservablesList = FXCollections.observableArrayList();
    protected Sale sale = new Sale();
    protected VBox commandLineTableVBox = new VBox();
    protected Label commandLineTableLabel = new Label("Command lines");
    Customer customer;

    protected void initWindow() {
        window.setScene(scene);
        window.setWidth(1400);
        window.setHeight(700);
        window.setTitle("Sales");
        productsTableView.setEditable(true);
    }

    protected void addNodesToWindow() {
        actionsHBox.getChildren().addAll(saveButton, upDateSaleButton, deleteSaleButton, payButton, exitButton);
        root.getChildren().add(actionsHBox);
        numberSaleHBox.getChildren().addAll(numberSaleLabel, numberSaleTextField);
        customerHBox.getChildren().addAll(customerLabel, customerNameLabel);
        dateHBox.getChildren().addAll(dateLabel, dateDatePicker);
        saleDetailVBox.getChildren().addAll(saleDetailLabel, numberSaleHBox, customerHBox, dateHBox);
        totalHBox.getChildren().addAll(totalLabel, totalValueLabel);
        listSalesVBox.getChildren().addAll(listSalesLabel, salesTableView);
        idProductHBox.getChildren().addAll(idProductLabel, idProductTextField);
        designProductHBox.getChildren().addAll(designProductLabel, designProductTextField);
        priceProductHBox.getChildren().addAll(priceProductLabel, priceProductTextField);
        quantityProductHBox.getChildren().addAll(quantityProductLabel, quantityProductTextField);
        commandLineInfosVBox.getChildren().addAll(idProductHBox, designProductHBox, priceProductHBox, quantityProductHBox);
        commandLineActionsHBox.getChildren().addAll(addCommandLineButton, deleteCommandLineButton);
        commandLineContainerHBox.getChildren().addAll(commandLineInfosVBox, commandLineActionsHBox);
        commandLineTableVBox.getChildren().addAll(commandLineTableLabel, totalHBox, commandLineTableView);
        tableProductsVBox.getChildren().add(productsTableView);
        rightVBox.getChildren().addAll(listSalesVBox, commandLineTableVBox);
        leftVBox.getChildren().addAll(saleDetailVBox, commandLineContainerHBox, tableProductsVBox);
        bodyHBox.getChildren().addAll(leftVBox, rightVBox);
        root.getChildren().add(bodyHBox);
    }

    protected void addColumnToBlTableview() {
        salesTableView.getColumns().addAll(
                saleIdColumn,
                saleDateColumn,
                saleCustomerColumn
        );
        salesTableView.setItems(salesObservablesList);
    }

    protected void addColumnToProductTableview() {
        productsTableView.getColumns().addAll(
                productIdColumn,
                productDesignationColumn,
                productPriceColumn
        );
        productsTableView.setItems(productsObservablesList);
    }

    protected void addColumnToCommandLineTableview() {
        commandLineTableView.getColumns().addAll(
                commandLineIdColumn,
                commandLineDesignationColumn,
                commandLinePriceColumn,
                commandLineQuantityColumn,
                commandLineSubtotalColumn
        );
        commandLineTableView.setItems(commandLinesObservablesList);
    }

    protected void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");
        actionsHBox.setSpacing(20);
        saleDetailVBox.getStyleClass().add("box");
        saleDetailVBox.setSpacing(10);
        saleDetailVBox.setPadding(new Insets(10, 10, 10, 10));
        saleDetailVBox.setMinWidth(window.getWidth() / 2.5);
        saleDetailLabel.getStyleClass().add("labelTitle");
        saleDetailLabel.setMinWidth(window.getWidth() / 2.6);
        saleDetailLabel.setPadding(new Insets(15, 15, 15, 15));
        numberSaleTextField.setEditable(false);
        numberSaleTextField.setDisable(true);
        idProductTextField.setEditable(false);
        idProductTextField.setDisable(true);
        designProductTextField.setEditable(false);
        designProductTextField.setDisable(true);
        priceProductTextField.setEditable(false);
        priceProductTextField.setDisable(true);
        commandLineContainerHBox.getStyleClass().add("box");
        commandLineContainerHBox.setSpacing(10);
        commandLineContainerHBox.setPadding(new Insets(10, 10, 10, 10));
        commandLineContainerHBox.setMinWidth(window.getWidth() / 2.5);
        commandLineActionsHBox.setSpacing(30);
        commandLineActionsHBox.setPadding(new Insets(0, 25, 0, 25));
        addCommandLineButton.getStyleClass().addAll("commandLineButton");
        deleteCommandLineButton.getStyleClass().addAll("commandLineButton");
        listSalesLabel.getStyleClass().add("labelTitle");
        listSalesVBox.getStyleClass().add("box");
        listSalesLabel.setMinWidth(window.getWidth() / 1.8);
        listSalesLabel.setPadding(new Insets(15, 15, 15, 15));
        listSalesVBox.setSpacing(10);
        commandLineTableLabel.getStyleClass().add("labelTitle");
        commandLineTableVBox.getStyleClass().add("box");
        commandLineTableLabel.setMinWidth(window.getWidth() / 1.8);
        commandLineTableLabel.setPadding(new Insets(15, 15, 15, 15));
        commandLineTableVBox.setSpacing(10);
        totalLabel.getStyleClass().add("totalLabel");
        totalValueLabel.getStyleClass().add("totalLabel");
        leftVBox.setSpacing(20);
        root.setSpacing(30);
        root.setPadding(new Insets(20, 20, 20, 20));
    }

    protected void addEvents() {
        window.setOnCloseRequest(event -> {
            event.consume();
        });
        productsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            handler.addProduct();
        });
        addCommandLineButton.setOnAction(event -> {
            handler.addCommandLineButton(customer);
        });
        deleteCommandLineButton.setOnAction(event -> {
            handler.deleteCommandLine();
        });
        salesTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            handler.addCommandLines();
        });
        upDateSaleButton.setOnAction(event -> {
            handler.upDateBl();
        });
        deleteSaleButton.setOnAction(event -> {
            handler.deleteBl();
        });
        saveButton.setOnAction(event -> {
            handler.addDelivery();
        });
        payButton.setOnAction(event -> {
            handler.addPay();
        });
        exitButton.setOnAction(event -> {
            handler.exitDelivery();
        });
    }

    public void configBlTableView() {
        saleIdColumn.setCellValueFactory(new PropertyValueFactory<Sale, Long>("id"));
        saleIdColumn.setPrefWidth(140);
        saleDateColumn.setCellValueFactory(new PropertyValueFactory<Sale, String>("date"));
        saleDateColumn.setPrefWidth(200);
        saleCustomerColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Sale, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Sale, String> param) {
                return new SimpleObjectProperty<>(param.getValue().getCustomer().getFirstName() + " " + param.getValue().getCustomer().getLastName());
            }
        });
        saleCustomerColumn.setPrefWidth(200);
    }

    public void configProductTableView() {
        productIdColumn.setCellValueFactory(new PropertyValueFactory<Product, Long>("id"));
        productIdColumn.setPrefWidth(140);
        productDesignationColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("designation"));
        productDesignationColumn.setPrefWidth(200);
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        productPriceColumn.setPrefWidth(200);
    }

    public void configCommandLineTableView() {
        commandLineIdColumn.setCellValueFactory(new PropertyValueFactory<CommandLine, Long>("id"));
        commandLineIdColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandLine, Long>, ObservableValue<Long>>() {
            @Override
            public ObservableValue<Long> call(TableColumn.CellDataFeatures<CommandLine, Long> param) {
                return new SimpleObjectProperty<>(param.getValue().getProduct().getId());
            }
        });
        commandLineIdColumn.setPrefWidth(100);
        commandLineDesignationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandLine, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CommandLine, String> param) {
                return new SimpleObjectProperty<>(param.getValue().getProduct().getDesignation());
            }
        });
        commandLineDesignationColumn.setPrefWidth(200);
        commandLinePriceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandLine, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<CommandLine, Double> param) {
                return new SimpleObjectProperty<>(param.getValue().getProduct().getPrice());
            }
        });
        commandLinePriceColumn.setPrefWidth(150);
        commandLineQuantityColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandLine, Integer>, ObservableValue<Integer>>() {

            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<CommandLine, Integer> param) {
                return new SimpleObjectProperty<>(param.getValue().getQuantity());
            }
        });
        commandLineQuantityColumn.setPrefWidth(150);
        commandLineSubtotalColumn.setCellValueFactory(new PropertyValueFactory<CommandLine, Double>("subtotal"));
        commandLineSubtotalColumn.setPrefWidth(150);
    }

    public SalesManagementWindow(Customer customer) {
        handler.getDeliveryIdFromDB();
        this.customer = customer;
        customerNameLabel.setText(this.customer.getFirstName() + " " + this.customer.getLastName());
        dateDatePicker.setValue(LocalDate.now());
        initWindow();
        addStylesToNodes();
        addColumnToProductTableview();
        addColumnToCommandLineTableview();
        addColumnToBlTableview();
        configBlTableView();
        configProductTableView();
        configCommandLineTableView();
        handler.updateAllListProductsView();
        handler.getBls();
        addNodesToWindow();
        addEvents();
        window.show();
    }
}
