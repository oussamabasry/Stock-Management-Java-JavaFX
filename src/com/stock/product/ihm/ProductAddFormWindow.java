package com.stock.product.ihm;

import com.stock.product.dao.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProductAddFormWindow {
    protected IProductDao productDAO = new ProductDaoImpl();
    protected ICategoryDao categoryDao = new CategoryDaoImpl();
    protected ProductsListWindow productsListWindow = ProductsListHandler.getListHandler();
    protected ProductsAddAndUpdateHandler handler = new ProductsAddAndUpdateHandler(this);
    protected VBox root = new VBox();
    protected Scene scene = new Scene(root);
    protected Stage window = new Stage();
    protected Label titleLabel = new Label("  New Product");
    protected Label designationLabel = new Label("Designation:");
    protected TextField designationTextField = new TextField();
    protected Label priceLabel = new Label("Price:");
    protected TextField priceTextField = new TextField();
    protected Label quantityLabel = new Label("Quantity:");
    protected TextField quantityTextField = new TextField();
    protected Label categoryLabel = new Label("Category");
    protected ChoiceBox<Category> categoryChoiceBox = new ChoiceBox<>();
    protected Label dateLabel = new Label("Date:");
    protected DatePicker dateDatePicker = new DatePicker();
    protected HBox buttonsBox = new HBox();
    protected Button confirmButton = new Button("Add");
    protected Button cancelButton = new Button("Cancel");
    protected ObservableList<Category> categories = FXCollections.observableArrayList();
    protected ObservableList<Product> productObservableList = FXCollections.observableArrayList();

    protected void initWindow() {
        window.setScene(scene);
        window.setWidth(600);
        window.setHeight(530);
        window.setTitle("New Product");

    }

    protected void addNodesToWindow() {
        root.getChildren().add(titleLabel);
        root.getChildren().addAll(designationLabel, designationTextField);
        root.getChildren().addAll(priceLabel, priceTextField);
        root.getChildren().addAll(quantityLabel, quantityTextField);
        root.getChildren().addAll(categoryLabel, categoryChoiceBox);
        root.getChildren().addAll(dateLabel, dateDatePicker);
        buttonsBox.getChildren().addAll(confirmButton, cancelButton);
        root.getChildren().add(buttonsBox);
    }

    protected void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");
        root.getStyleClass().add("addProductRoot");
        titleLabel.getStyleClass().add("labelTitle");
        titleLabel.setPadding(new Insets(20, 20, 20, 20));
        titleLabel.setMaxWidth(window.getWidth());
        designationLabel.getStyleClass().add("labelForm");
        priceLabel.getStyleClass().add("labelForm");
        quantityLabel.getStyleClass().add("labelForm");
        dateLabel.getStyleClass().add("labelForm");
        categoryLabel.getStyleClass().add("labelForm");
        root.setSpacing(15);
        root.setPadding(new Insets(20, 20, 20, 20));
        buttonsBox.setSpacing(20);
        confirmButton.getStyleClass().add("buttonsAddProduct");
        cancelButton.getStyleClass().add("buttonsAddProduct");
    }

    protected void addEvents() {
        cancelButton.setOnAction(event -> window.close());
        confirmButton.setOnAction(event -> {
            handler.addProduct();

        });
    }

    protected void addItemsToChoiceBox() {
        handler.updateChoiceBoxCategories();
        categoryChoiceBox.setItems(categories);
        categoryChoiceBox.setValue(categories.get(0));
    }

    public ProductAddFormWindow() {
        initWindow();
        addItemsToChoiceBox();
        addStylesToNodes();
        addNodesToWindow();
        addEvents();
        window.show();
    }
}

