package com.stock.product.ihm;

import com.stock.product.dao.Category;
import com.stock.product.dao.CategoryDaoImpl;
import com.stock.product.dao.ICategoryDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CategoryListWindow {
    private ICategoryDao categoryDao = new CategoryDaoImpl();
    private VBox root = new VBox();
    private HBox searchCategoriesHBox = new HBox();
    private HBox crudActionsHBox = new HBox();
    private Label searchProductLabel = new Label("Search a category:");
    private TextField searchCategoryTextField = new TextField();
    private Button searchCategoryButton = new Button("Search");
    private Button displayAllCategoriesButton = new Button("Display all categories");
    private Button upDateCategoryButton = new Button("Update category");
    private Button deleteCategoryButton = new Button("Delete category");
    private Button displayProductsButton = new Button("Display products");
    private Scene scene = new Scene(root);
    private Stage window = new Stage();
    private Label titleLabel = new Label("  List of categories");
    private TableView<Category> categoriesListTableView = new TableView<>();
    private TableColumn<Category, Long> categoryIdTableColumn = new TableColumn<>("ID");
    private TableColumn<Category, String> categoryTitleTableColumn = new TableColumn<>("Title");
    ObservableList<Category> data;

    private void initWindow() {
        window.setScene(scene);
        window.setWidth(800);
        window.setHeight(600);
        window.setTitle("List categories");
        categoriesListTableView.setEditable(true);
    }

    private void addNodesToWindow() {
        root.getChildren().add(titleLabel);
        searchCategoriesHBox.getChildren().addAll(searchProductLabel, searchCategoryTextField, searchCategoryButton);
        root.getChildren().add(searchCategoriesHBox);
        crudActionsHBox.getChildren().addAll(displayAllCategoriesButton, upDateCategoryButton, deleteCategoryButton, displayProductsButton);
        root.getChildren().add(crudActionsHBox);
        categoriesListTableView.getColumns().addAll(
                categoryIdTableColumn,
                categoryTitleTableColumn
        );
        root.getChildren().add(categoriesListTableView);
    }

    private void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");
        titleLabel.getStyleClass().add("labelTitle");
        titleLabel.setPadding(new Insets(20, 20, 20, 20));
        titleLabel.setMaxWidth(window.getWidth());
        root.setSpacing(20);
        root.setPadding(new Insets(20, 20, 20, 20));
        searchCategoriesHBox.setSpacing(20);
        crudActionsHBox.setSpacing(20);
        root.setPadding(new Insets(20, 20, 20, 20));
    }

    private void addEvents() {
        searchCategoryButton.setOnAction(event -> {
            if (!searchCategoryTextField.getText().isEmpty()) {
                data = FXCollections.observableArrayList(categoryDao.getAll(searchCategoryTextField.getText()));
                categoriesListTableView.setItems(data);
            }
        });
        displayAllCategoriesButton.setOnAction(event -> this.updateListProductsView());
        deleteCategoryButton.setOnAction(event -> {
            Category category = categoriesListTableView.getSelectionModel().getSelectedItem();
            if (category != null) {
                categoryDao.delete(category.getId());
                this.updateListProductsView();
            }
        });
        upDateCategoryButton.setOnAction(event -> {
            Category category = categoriesListTableView.getSelectionModel().getSelectedItem();
            if (category != null) {
                CategoryUpdateFormWindow categoryUpdateFormWindow = new CategoryUpdateFormWindow(category, data, categoriesListTableView);
                this.updateListProductsView();
            }
        });
        displayProductsButton.setOnAction(event -> {
            Category category = categoriesListTableView.getSelectionModel().getSelectedItem();
            if (category != null) {
                CategoryProductsWindow categoryProductsWindow = new CategoryProductsWindow(category);
            }
        });
    }

    public void updateListProductsView() {
        data = FXCollections.observableArrayList(categoryDao.getAll());
        categoriesListTableView.setItems(data);
    }

    public void configTableView() {
        double width = window.getWidth() - 50;
        categoryIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        categoryIdTableColumn.setMinWidth(width / 2);
        categoryTitleTableColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        categoryTitleTableColumn.setMinWidth(width / 2);
        this.updateListProductsView();
    }

    public CategoryListWindow() {
        initWindow();
        addStylesToNodes();
        configTableView();
        addNodesToWindow();
        addEvents();
        window.show();
    }
}
