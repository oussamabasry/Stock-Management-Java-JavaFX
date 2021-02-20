package com.stock.product.ihm;

import com.stock.product.dao.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class CategoryUpdateFormWindow extends CategoryAddFormWindow {
    private Category categoryUpdate;
    private ObservableList data;
    private TableView categoriesListTableView;

    public CategoryUpdateFormWindow(Category category, ObservableList data, TableView categoriesListTableView) {
        this.categoryUpdate = category;
        this.data = data;
        this.categoriesListTableView = categoriesListTableView;
        this.titleLabel.setText("Update category");
        this.window.setTitle("Update category");
        this.categoryConfirmButton.setText("Update");
        this.categoryTitleTextField.setText(category.getTitle());
    }

    @Override
    protected void addEvents() {
        categoryCancelButton.setOnAction(event -> window.close());
        categoryConfirmButton.setOnAction(event -> {
            if (!categoryTitleTextField.getText().isEmpty()) {
                Category category = new Category(
                        categoryUpdate.getId(),
                        categoryTitleTextField.getText()
                );
                categoryDao.update(category);
                data = FXCollections.observableArrayList(categoryDao.getAll());
                categoriesListTableView.setItems(data);
                window.close();
            }

        });
    }
}
