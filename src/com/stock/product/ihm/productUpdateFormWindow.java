package com.stock.product.ihm;


import com.stock.product.dao.Product;



public class productUpdateFormWindow extends ProductAddFormWindow {

    private Product productUpdate;
    private String from;
    ProductsListHandler handler;

    public productUpdateFormWindow(Product product, String from, ProductsListHandler handler){
        this.productUpdate = product;
        this.from = from;
        this.handler = handler;
        this.titleLabel.setText("Update product");
        this.window.setTitle("Update product");
        this.confirmButton.setText("Update");
        this.designationTextField.setText(product.getDesignation());
        this.quantityTextField.setText(String.valueOf(product.getQuantity()));
        this.priceTextField.setText(String.valueOf(product.getPrice()));
        this.dateDatePicker.setValue(product.getDate());
    }


    @Override
    protected void addEvents() {
        cancelButton.setOnAction(event -> window.close());
        confirmButton.setOnAction(event -> {
            if (!designationTextField.getText().isEmpty() && !priceTextField.getText().isEmpty() && !quantityTextField.getText().isEmpty() && dateDatePicker.getValue() != null) {
                Product product = new Product(
                        productUpdate.getId(),
                        designationTextField.getText(),
                        Integer.parseInt(quantityTextField.getText()),
                        Double.parseDouble(priceTextField.getText()),
                        dateDatePicker.getValue(),
                        categoryChoiceBox.getSelectionModel().getSelectedItem()
                );
                productDAO.update(product);
                if(from.equals("fromProduct")) handler.updateAllListProductsView();
                else if(from != null) handler.updateListProductsViewByCategory(from);

                window.close();
            }

        });
    }


}
