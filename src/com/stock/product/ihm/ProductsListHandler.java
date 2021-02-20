package com.stock.product.ihm;


import com.stock.product.dao.Category;
import com.stock.product.dao.IProductDao;
import com.stock.product.dao.Product;
import com.stock.product.dao.ProductDaoImpl;

import java.util.List;

public class ProductsListHandler {
    static ProductsListWindow productsListWindow = null;

    public ProductsListHandler(ProductsListWindow productsListWindow) {
        this.productsListWindow = productsListWindow;
    }

    public static ProductsListWindow getListHandler() {
        return productsListWindow;
    }

    public void updateAllListProductsView() {
        IProductDao productDao = new ProductDaoImpl();
        List<Product> list = productDao.getAll();
        productsListWindow.productsObservablesList.setAll(list);
        getTotal();
    }

    public void updateListProductsViewByCategory(String category) {
        IProductDao productDao = new ProductDaoImpl();
        List<Product> list = productDao.getAllByCategory(category);
        productsListWindow.productsObservablesList.setAll(list);
        getTotal();
    }

    public void updateListProductsViewByKeyword(String keyword) {
        IProductDao productDao = new ProductDaoImpl();
        List<Product> list = productDao.getAll(keyword);
        productsListWindow.productsObservablesList.setAll(list);
        getTotal();
    }

    public void updateListProductsViewByCategoryKeyword(String category, String keyword) {
        IProductDao productDao = new ProductDaoImpl();
        List<Product> list = productDao.getAllByCategory(category, keyword);
        productsListWindow.productsObservablesList.setAll(list);
        getTotal();
    }

    public void getTotal() {
        double total = 0;
        for (Product p : productsListWindow.productsObservablesList) total += p.getTotal();
        productsListWindow.totalValueLabel.setText(String.valueOf(total) + " DH");
    }
}
