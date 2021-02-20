package com.stock.sales.ihm;

import com.stock.customer.dao.Customer;
import com.stock.product.dao.*;
import com.stock.sales.dao.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class UpDateSaleWindow {
    protected UpDateSaleHandler handler = new UpDateSaleHandler(this);
    protected SaleManagementHandler saleManagementHandler;
    protected VBox root = new VBox();
    protected Scene scene = new Scene(root);
    protected Stage window = new Stage();
    protected Label titleLabel = new Label("  Up Date BL");
    protected Label customerLabel = new Label("Customer");
    protected ChoiceBox<Customer> customerChoiceBox = new ChoiceBox<>();
    protected Label dateLabel = new Label("Date:");
    protected DatePicker dateDatePicker = new DatePicker();
    protected HBox buttonsBox = new HBox();
    protected Button confirmButton = new Button("Up Date");
    protected Button cancelButton = new Button("Cancel");
    protected ObservableList<Customer> customers = FXCollections.observableArrayList();
    protected Sale sale;

    protected void initWindow() {
        window.setScene(scene);
        window.setWidth(500);
        window.setHeight(330);
        window.setTitle("Up Date BL");
    }

    protected void addNodesToWindow() {
        root.getChildren().add(titleLabel);
        root.getChildren().addAll(dateLabel, dateDatePicker, customerLabel, customerChoiceBox);
        buttonsBox.getChildren().addAll(confirmButton, cancelButton);
        root.getChildren().add(buttonsBox);
    }

    protected void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");
        root.getStyleClass().add("addProductRoot");
        titleLabel.getStyleClass().add("labelTitle");
        titleLabel.setPadding(new Insets(20, 20, 20, 20));
        titleLabel.setMaxWidth(window.getWidth());
        dateLabel.getStyleClass().add("labelForm");
        customerLabel.getStyleClass().add("labelForm");
        root.setSpacing(15);
        root.setPadding(new Insets(20, 20, 20, 20));
        buttonsBox.setSpacing(20);
        confirmButton.getStyleClass().add("buttonsAddProduct");
        cancelButton.getStyleClass().add("buttonsAddProduct");
    }

    protected void addEvents() {
        cancelButton.setOnAction(event -> window.close());
        confirmButton.setOnAction(event -> {
            handler.updateBl();
        });

    }

    protected void addItemsToChoiceBox() {
        handler.updateChoiceBoxCustomers();
    }

    public UpDateSaleWindow(Sale sale, SaleManagementHandler newBlHandler) {
        this.sale = sale;
        this.saleManagementHandler = newBlHandler;
        this.dateDatePicker.setValue(sale.getDate());
        initWindow();
        addItemsToChoiceBox();
        addStylesToNodes();
        addNodesToWindow();
        addEvents();
        window.show();
    }
}

