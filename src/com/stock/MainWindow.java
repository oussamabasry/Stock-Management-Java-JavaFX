package com.stock;

import com.stock.customer.ihm.CustomerAddFormWindow;
import com.stock.customer.ihm.CustomerListWindow;
import com.stock.product.ihm.CategoryAddFormWindow;
import com.stock.product.ihm.CategoryListWindow;
import com.stock.product.ihm.ProductAddFormWindow;
import com.stock.product.ihm.ProductsListWindow;
import com.stock.sales.ihm.SaleHomeWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow extends Application {
    private final BorderPane root = new BorderPane();
    private final Scene scene = new Scene(root);
    MenuItem addProductMenuItem = new MenuItem("New Product");
    MenuItem listProductsMenuItem = new MenuItem("List Products");
    MenuItem addCategoryMenuItem = new MenuItem("New Category");
    MenuItem listCategoriesMenuItem = new MenuItem("List Categories");
    MenuItem addCustomerMenuItem = new MenuItem("New Customer");
    MenuItem listCustomerMenuItem = new MenuItem("List Customers");
    MenuItem salesHomeMenuItem = new MenuItem("Sales Management");
    MenuItem helpMenuItem = new MenuItem("?");

    private void createMenu() {
        MenuBar menuBar = new MenuBar();
        Menu productMenu = new Menu("Products");
        Menu customerMenu = new Menu("Customers");
        Menu saleMenu = new Menu("Sales");
        Menu paymentMenu = new Menu("Payments");
        Menu inventoryMenu = new Menu("Inventory");
        Menu helpMenu = new Menu("Help");
        menuBar.getMenus().addAll(productMenu, customerMenu, saleMenu, paymentMenu, inventoryMenu, helpMenu);
        productMenu.getItems().addAll(addProductMenuItem, listProductsMenuItem, addCategoryMenuItem, listCategoriesMenuItem);
        customerMenu.getItems().addAll(addCustomerMenuItem, listCustomerMenuItem);
        saleMenu.getItems().addAll(salesHomeMenuItem);
        helpMenu.getItems().addAll(helpMenuItem);
        root.setTop(menuBar);
    }

    private void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");
        root.getStyleClass().add("mainWindow");
    }

    private void addEvents() {
        addProductMenuItem.setOnAction(event -> new ProductAddFormWindow());
        listProductsMenuItem.setOnAction(event -> new ProductsListWindow());
        addCategoryMenuItem.setOnAction(event -> new CategoryAddFormWindow());
        listCategoriesMenuItem.setOnAction(event -> new CategoryListWindow());
        addCustomerMenuItem.setOnAction(event -> new CustomerAddFormWindow());
        listCustomerMenuItem.setOnAction(event -> new CustomerListWindow());
        salesHomeMenuItem.setOnAction(event -> new SaleHomeWindow());
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) {
        createMenu();
        addEvents();
        addStylesToNodes();
        window.setScene(scene);
        window.setTitle("Stock Management");
        window.setWidth(1100);
        window.setHeight(650);
        window.show();
    }
}