package com.stock.product.ihm;


import com.stock.product.dao.*;

import java.util.List;

public class ProductsAddAndUpdateHandler {
    ProductAddFormWindow productAddFormWindow = null;

    public ProductsAddAndUpdateHandler(ProductAddFormWindow productAddFormWindow) {
        this.productAddFormWindow = productAddFormWindow;
    }

    public void addProduct() {
        IProductDao productDao = new ProductDaoImpl();
        if (!productAddFormWindow.designationTextField.getText().isEmpty() && !productAddFormWindow.priceTextField.getText().isEmpty() && !productAddFormWindow.quantityTextField.getText().isEmpty() && productAddFormWindow.dateDatePicker.getValue() != null) {
            Product product = new Product(
                    productAddFormWindow.designationTextField.getText(),
                    Integer.parseInt(productAddFormWindow.quantityTextField.getText()),
                    Double.parseDouble(productAddFormWindow.priceTextField.getText()),
                    productAddFormWindow.dateDatePicker.getValue(),
                    productAddFormWindow.categoryChoiceBox.getSelectionModel().getSelectedItem()
            );
            productDao.add(product);
            productAddFormWindow.window.close();
        }
    }

    public void updateChoiceBoxCategories() {
        ICategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> list = categoryDao.getAll();
        productAddFormWindow.categories.setAll(list);
    }

    public void updateAllListProductsView() {
        IProductDao productDao = new ProductDaoImpl();
        List<Product> list = productDao.getAll();
        productAddFormWindow.productObservableList.setAll(list);
    }

    public void updateListProductsViewByCategory(String category) {
        IProductDao productDao = new ProductDaoImpl();
        List<Product> list = productDao.getAllByCategory(category);
        productAddFormWindow.productObservableList.setAll(list);
    }

}
