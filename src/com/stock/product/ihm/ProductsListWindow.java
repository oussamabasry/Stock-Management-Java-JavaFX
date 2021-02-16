package com.stock.product.ihm;

import com.stock.product.dao.IProductDao;
import com.stock.product.dao.Product;
import com.stock.product.dao.ProductDaoImpl;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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
import java.util.ArrayList;

public class ProductsListWindow {
    protected  IProductDao productDAO = new ProductDaoImpl();
    protected ProductsListHandler handler = new ProductsListHandler(this);
    protected VBox root = new VBox();
    protected HBox searchProductsHBox = new HBox();
    protected HBox crudActionsHBox = new HBox();
    protected HBox totalHBox = new HBox();
    protected Label totalLabel = new Label("Total: ");
    protected Label totalValueLabel = new Label("0.0");
    protected Label searchProductLabel = new Label("Search a product:");
    protected TextField searchProductTextField = new TextField();
    protected Button searchProductButton = new Button("Search");
    protected Button displayAllProductsButton = new Button("Display all products");
    protected Button upDateProductsButton = new Button("Update product");
    protected Button deleteProductsButton = new Button("Delete product");
    protected Scene scene = new Scene(root);
    protected Stage window = new Stage();
    protected Label titleLabel = new Label("  List of products");
    protected TableView<Product> productsTableView = new TableView<>();
    protected TableColumn<Product, Long> idColumn = new TableColumn<>("ID");
    protected TableColumn<Product, String> designationColumn = new TableColumn<>("Designation");
    protected TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
    protected TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
    protected TableColumn<Product, String> categoryColumn = new TableColumn<>("Category");
    protected TableColumn<Product, LocalDate> dateColumn = new TableColumn<>("Date");
    protected TableColumn<Product, Double> totalColumn = new TableColumn<>("Total");
    protected ObservableList<Product> productsObservablesList =  FXCollections.observableArrayList();


    protected void initWindow() {
        window.setScene(scene);
        window.setWidth(1000);
        window.setHeight(650);
        window.setTitle("List products");
        productsTableView.setEditable(true);
    }

    protected void addNodesToWindow() {
        root.getChildren().add(titleLabel);
        searchProductsHBox.getChildren().addAll(searchProductLabel, searchProductTextField, searchProductButton);
        root.getChildren().add(searchProductsHBox);
        crudActionsHBox.getChildren().addAll(displayAllProductsButton, upDateProductsButton, deleteProductsButton);
        root.getChildren().add(crudActionsHBox);
        root.getChildren().add(productsTableView);
        totalHBox.getChildren().addAll(totalLabel, totalValueLabel);
        root.getChildren().add(totalHBox);
    }


    protected   void addColumnToTableview(){
        productsTableView.getColumns().addAll(
                idColumn,
                designationColumn,
                quantityColumn,
                priceColumn,
                categoryColumn,
                totalColumn,
                dateColumn

        );
        productsTableView.setItems(productsObservablesList);
    }


    protected void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");
        titleLabel.getStyleClass().add("labelTitle");
        titleLabel.setPadding(new Insets(20, 20, 20, 20));
        totalLabel.getStyleClass().add("labelTotal");
        totalValueLabel.getStyleClass().add("labelTotal");
        titleLabel.setMaxWidth(window.getWidth());
        totalHBox.getStyleClass().add("boxTotal");
        totalHBox.setPadding(new Insets(8, 8, 8, 8));
        root.setSpacing(20);
        searchProductsHBox.setSpacing(20);
        crudActionsHBox.setSpacing(20);
        root.setPadding(new Insets(20, 20, 20, 20));
    }

    protected void addEvents() {

        searchProductButton.setOnAction(event -> {
            if (!searchProductTextField.getText().isEmpty()) {
              //  productsObservablesList = FXCollections.observableArrayList(productDAO.getAll(searchProductTextField.getText()));
              //  productsTableView.setItems(productsObservablesList);
                handler.updateListProductsViewByKeyword(searchProductTextField.getText());
            }
        });
        displayAllProductsButton.setOnAction(event -> handler.updateAllListProductsView());
        deleteProductsButton.setOnAction(event -> {
            Product product = productsTableView.getSelectionModel().getSelectedItem();
            if(product != null) {
                productDAO.delete(product.getId());
                handler.updateAllListProductsView();
            }
        });
        upDateProductsButton.setOnAction(event -> {
            Product product = productsTableView.getSelectionModel().getSelectedItem();
            if(product != null)
                 new productUpdateFormWindow(product,"fromProduct", handler);
        });
    }


    public void configTableView() {

        idColumn.setCellValueFactory(new PropertyValueFactory<Product, Long>("id"));
        idColumn.setPrefWidth(100);
        designationColumn.setCellValueFactory(new PropertyValueFactory<Product,String>("designation"));
        designationColumn.setPrefWidth(250);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        quantityColumn.setPrefWidth(125);
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        priceColumn.setPrefWidth(125);
        categoryColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product , String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Product , String> param) {
                return new SimpleObjectProperty<>(param.getValue().getCategory().getTitle());

            }
        });

        categoryColumn.setPrefWidth(100);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setPrefWidth(120);
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        totalColumn.setPrefWidth(122);
    }

    public ProductsListWindow() {
        initWindow();
        addStylesToNodes();
        addColumnToTableview();
        configTableView();
        handler.updateAllListProductsView();
        addNodesToWindow();
        addEvents();
        window.show();
    }


}

