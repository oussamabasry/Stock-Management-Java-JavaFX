package com.stock.product.ihm;

import com.stock.product.dao.Category;
import com.stock.product.dao.CategoryDaoImpl;
import com.stock.product.dao.ICategoryDao;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CategoryAddFormWindow {

    protected ICategoryDao categoryDao = new CategoryDaoImpl();
    protected VBox root = new VBox();
    protected Scene scene = new Scene(root);
    protected Stage window = new Stage();
    protected Label titleLabel = new Label("  New Category");
    protected Label categoryTitleLabel = new Label("Title:");
    protected TextField categoryTitleTextField = new TextField();
    protected HBox buttonsBox = new HBox();
    protected Button categoryConfirmButton = new Button("Add");
    protected Button categoryCancelButton = new Button("Cancel");


    protected void initWindow() {
        window.setScene(scene);
        window.setWidth(500);
        window.setHeight(270);
        window.setTitle("New Category");
    }

    protected void addNodesToWindow() {
        root.getChildren().add(titleLabel);
        root.getChildren().addAll(categoryTitleLabel, categoryTitleTextField);
        buttonsBox.getChildren().addAll(categoryConfirmButton, categoryCancelButton);
        root.getChildren().add(buttonsBox);
    }

    protected void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");
        titleLabel.getStyleClass().add("labelTitle");
        titleLabel.setMaxWidth(window.getWidth());
        titleLabel.setPadding(new Insets(10, 10, 10, 10));
        categoryTitleLabel.getStyleClass().add("labelForm");
        root.setSpacing(30);
        root.setPadding(new Insets(20, 20, 20, 20));
        buttonsBox.setSpacing(20);
        categoryConfirmButton.getStyleClass().add("buttonsAddProduct");
        categoryCancelButton.getStyleClass().add("buttonsAddProduct");
    }

    protected void addEvents() {
        categoryCancelButton.setOnAction(event -> window.close());
        categoryConfirmButton.setOnAction(event -> {
            if (!categoryTitleTextField.getText().isEmpty()) {
                Category category = new Category(
                        categoryTitleTextField.getText()
                );
                categoryDao.add(category);
                window.close();
            }

        });
      /*  window.setOnCloseRequest(event -> {
            event.consume();
        });*/
    }

    public CategoryAddFormWindow() {
        initWindow();
        addStylesToNodes();
        addNodesToWindow();
        addEvents();
        window.show();
    }
}
