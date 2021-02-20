package com.stock.product.ihm;

import com.stock.product.dao.Category;
import com.stock.product.dao.Product;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;

public class CategoryProductsWindow extends ProductsListWindow {
    Category category;

    public CategoryProductsWindow(Category category) {
        this.category = category;
        titleLabel.setText("Products of " + category.getTitle() + " category");
        handler.updateListProductsViewByCategory(category.getTitle());
        titleLabel.getStyleClass().add("labelTitle");
        titleLabel.setPadding(new Insets(20, 20, 20, 20));
        searchProductLabel.getStyleClass().add("labelForm");
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

    @Override
    protected void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");
        titleLabel.getStyleClass().add("labelTitle");
        titleLabel.setMaxWidth(window.getWidth());
        root.setSpacing(20);
        searchProductsHBox.setSpacing(20);
        crudActionsHBox.setSpacing(20);
        root.setPadding(new Insets(20, 20, 20, 20));
    }

    @Override
    protected void addEvents() {
        searchProductButton.setOnAction(event -> {
            if (!searchProductTextField.getText().isEmpty()) {
                handler.updateListProductsViewByCategoryKeyword(category.getTitle(), searchProductTextField.getText());
                productsTableView.setItems(productsObservablesList);
            }
        });
        displayAllProductsButton.setOnAction(event -> handler.updateListProductsViewByCategory(category.getTitle()));
        deleteProductsButton.setOnAction(event -> {
            Product product = productsTableView.getSelectionModel().getSelectedItem();
            if (product != null) {
                productDAO.delete(product.getId());
                handler.updateListProductsViewByCategory(category.getTitle());
            }
        });
        upDateProductsButton.setOnAction(event -> {
            Product product = productsTableView.getSelectionModel().getSelectedItem();
            if (product != null) {
                new productUpdateFormWindow(product, category.getTitle(), handler);
            }
        });
    }
}
