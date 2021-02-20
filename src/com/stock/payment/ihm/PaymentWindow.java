package com.stock.payment.ihm;


import com.stock.payment.dao.Payment;
import com.stock.sales.dao.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class PaymentWindow {

    protected PaymentHandler handler = new PaymentHandler(this);
    protected VBox root = new VBox();
    protected Scene scene = new Scene(root);
    protected Stage window = new Stage();
    protected HBox actionsHBox = new HBox();
    protected Button addNewPaymentButton = new Button("Add Payment");

    protected Button deletePaymentButton = new Button("Delete");
    protected Button upDateButton = new Button("Up Date");
    protected HBox bodyHBox = new HBox();
    protected VBox leftVBox = new VBox();
    protected VBox rightVBox = new VBox();
    protected VBox saleDetailVBox = new VBox();
    protected Label saleDetailLabel = new Label("Sale Details");


    protected HBox customerHBox = new HBox();
    protected Label customerLabel = new Label("Customer: ");
    protected Label customerNameLabel = new Label("");

    protected HBox dateHBox = new HBox();
    protected Label dateLabel = new Label("Date: ");
    protected Label dateValueLabel = new Label("");

    protected HBox numberSaleHBox = new HBox();
    protected Label numberSaleLabel = new Label("N°Sale: ");
    protected Label numberSaleValueLabel = new Label("");

    protected HBox totalSaleHBox = new HBox();
    protected Label totalSaleLabel = new Label("Total: ");
    protected Label totalSaleValueLabel = new Label();

    protected HBox totalPaidSaleHBox = new HBox();
    protected Label totalPaidSaleLabel = new Label("Total Paid: ");
    protected Label totalPaidSaleValueLabel = new Label();

    protected HBox restSaleHBox = new HBox();
    protected Label restSaleLabel = new Label("Rest: ");
    protected Label restSaleValueLabel = new Label();

    protected VBox paymentDetailsVBox = new VBox();
    protected Label paymentDetailsLabel = new Label("payment Details: ");
    protected HBox totalHBox = new HBox();
    protected Label totalLabel = new Label("  Total: ");
    protected Label totalValueLabel = new Label("0,00");

    protected VBox tablepaymentVBox = new VBox();
    protected TableView<Payment> paymentsTableView = new TableView<>();
    protected TableColumn<Payment, Long> paymentIdColumn = new TableColumn<>("ID");
    protected TableColumn<Payment, Double> paymentAmountColumn = new TableColumn<>("Amount");
    protected TableColumn<Payment, LocalDate> paymentDateColumn = new TableColumn<>("Date");
    protected TableColumn<Payment, String> paymentTypeColumn = new TableColumn<>("Type");
    protected TableColumn<Payment, Integer> paymentChequeNumberColumn = new TableColumn<>("N°Cheque");
    protected TableColumn<Payment, String> paymentOwnerColumn = new TableColumn<>("Owner");
    protected TableColumn<Payment, String> paymentBankColumn = new TableColumn<>("Bank");
    protected ObservableList<Payment> paymentsObservablesList = FXCollections.observableArrayList();

    protected Label paymentDateLabel = new Label("Date: ");
    protected DatePicker paymentDatePicker = new DatePicker();
    protected Label paymentAmountLabel = new Label("Amount: ");
    protected TextField paymentAmountTextField = new TextField();
    protected Label paymentTypeLabel = new Label("Type: ");
    ChoiceBox cbType = new ChoiceBox(FXCollections.observableArrayList(
            "Cash", "Cheque", "Bank card")
    );
    protected Label chequeNumberLabel = new Label("Cheque Number: ");
    protected TextField chequeNumberTextField = new TextField();
    protected Label dateDeadlineChequeLabel = new Label("Date deadline: ");
    protected DatePicker chequeDeadLineDatePicker = new DatePicker();
    ChoiceBox cbBank = new ChoiceBox(FXCollections.observableArrayList(
            "Attijari", "CIH", "Societé generale")
    );
    protected Label nameLabel = new Label("Name");
    protected TextField nameTextField = new TextField();

    protected Button savePaymentButton = new Button("Save");

    protected Button upDatePaymentButton = new Button("UpDate payment");

    protected Label accountNumberLabel = new Label("Account Number");
    protected TextField accountNumberTextField = new TextField();

    protected Sale sale = new Sale();
    protected double total;


    protected void initWindow() {
        window.setScene(scene);
        window.setWidth(1400);
        window.setHeight(700);
        window.setTitle("Payments");
        paymentsTableView.setEditable(true);
    }

    protected void addNodesToWindow() {
        actionsHBox.getChildren().addAll(addNewPaymentButton, upDateButton, deletePaymentButton);
        root.getChildren().add(actionsHBox);

        numberSaleHBox.getChildren().addAll(numberSaleLabel, numberSaleValueLabel);
        customerHBox.getChildren().addAll(customerLabel, customerNameLabel);
        dateHBox.getChildren().addAll(dateLabel, dateValueLabel);
        totalSaleHBox.getChildren().addAll(totalSaleLabel, totalSaleValueLabel);
        totalPaidSaleHBox.getChildren().addAll(totalPaidSaleLabel, totalPaidSaleValueLabel);
        restSaleHBox.getChildren().addAll(restSaleLabel, restSaleValueLabel);
        saleDetailVBox.getChildren().addAll(
                saleDetailLabel,
                customerHBox,
                numberSaleHBox,
                dateHBox,
                totalSaleHBox,
                totalPaidSaleHBox,
                restSaleHBox
        );

        totalHBox.getChildren().addAll(totalLabel, totalValueLabel);
        paymentDetailsVBox.getChildren().addAll(
                paymentDetailsLabel,
                paymentDateLabel,
                paymentDatePicker,
                paymentAmountLabel,
                paymentAmountTextField,
                paymentTypeLabel,
                cbType,
                savePaymentButton
        );

        tablepaymentVBox.getChildren().add(paymentsTableView);

        rightVBox.getChildren().addAll(paymentDetailsVBox);
        leftVBox.getChildren().addAll(saleDetailVBox, tablepaymentVBox);

        bodyHBox.getChildren().addAll(leftVBox, rightVBox);
        root.getChildren().add(bodyHBox);

    }

    protected void addColumnToPaymentsTableview() {
        paymentsTableView.getColumns().addAll(
                paymentIdColumn,
                paymentAmountColumn,
                paymentDateColumn,
                paymentTypeColumn,
                paymentChequeNumberColumn,
                paymentOwnerColumn,
                paymentBankColumn
        );

        paymentsTableView.setItems(paymentsObservablesList);
    }


    protected void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");
        actionsHBox.setSpacing(20);
        saleDetailVBox.getStyleClass().add("box");
        saleDetailVBox.setSpacing(10);
        saleDetailVBox.setPadding(new Insets(10, 10, 10, 10));
        saleDetailVBox.setMinWidth(window.getWidth() / 1.8);
        saleDetailLabel.getStyleClass().add("labelTitle");
        saleDetailLabel.setMinWidth(window.getWidth() / 2.6);
        saleDetailLabel.setPadding(new Insets(15, 15, 15, 15));

        bodyHBox.setSpacing(20);

        paymentDetailsLabel.getStyleClass().add("labelTitle");
        paymentDetailsVBox.getStyleClass().add("box");
        paymentDetailsLabel.setMinWidth(window.getWidth() / 3);
        paymentDetailsLabel.setPadding(new Insets(15, 15, 15, 15));
        paymentDetailsVBox.setMinWidth(window.getWidth() / 3);
        paymentDetailsVBox.setPadding(new Insets(15, 15, 15, 15));
        paymentDetailsVBox.setSpacing(10);

        totalLabel.getStyleClass().add("totalLabel");
        totalValueLabel.getStyleClass().add("totalLabel");

        leftVBox.setSpacing(20);

        root.setSpacing(30);
        root.setPadding(new Insets(20, 20, 20, 20));
    }

    public void clearContainers() {
        root.getChildren().clear();
        actionsHBox.getChildren().clear();
        numberSaleHBox.getChildren().clear();
        customerHBox.getChildren().clear();
        dateHBox.getChildren().clear();
        totalSaleHBox.getChildren().clear();
        totalPaidSaleHBox.getChildren().clear();
        restSaleHBox.getChildren().clear();
        saleDetailVBox.getChildren().clear();
        totalHBox.getChildren().clear();
        paymentDetailsVBox.getChildren().clear();
        tablepaymentVBox.getChildren().clear();
        rightVBox.getChildren().clear();
        leftVBox.getChildren().clear();
        bodyHBox.getChildren().clear();
        root.getChildren().clear();
        paymentAmountTextField.setText("");
        paymentDatePicker.setValue(LocalDate.now());
    }

    protected void addEvents() {

        addNewPaymentButton.setOnAction(event -> {
            cbType.setValue("Cash");
            clearContainers();
            addNodesToWindow();
        });

        cbType.setOnAction(event -> {
            handler.displayCheque();
        });
        savePaymentButton.setOnAction(event -> {
            handler.addPayment();
        });
        upDateButton.setOnAction(event -> {
            handler.upDatePayment();
        });
        upDatePaymentButton.setOnAction(event -> {
            handler.upDatePaymentInfo();
        });
        deletePaymentButton.setOnAction(event -> {
            handler.deletePayment();
        });
    }

    public void configPaymentsTableView() {
        paymentIdColumn.setCellValueFactory(new PropertyValueFactory<Payment, Long>("id"));
        paymentIdColumn.setPrefWidth(60);
        paymentAmountColumn.setCellValueFactory(new PropertyValueFactory<Payment, Double>("amount"));
        paymentAmountColumn.setPrefWidth(140);
        paymentDateColumn.setCellValueFactory(new PropertyValueFactory<Payment, LocalDate>("date"));
        paymentDateColumn.setPrefWidth(140);
        paymentTypeColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("type"));
        paymentTypeColumn.setPrefWidth(100);
        paymentChequeNumberColumn.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("chequeNumber"));
        paymentChequeNumberColumn.setPrefWidth(100);
        paymentOwnerColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("owner"));
        paymentOwnerColumn.setPrefWidth(140);
        paymentBankColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("bank"));
        paymentBankColumn.setPrefWidth(120);
    }

    public PaymentWindow(Sale sale) {
        this.sale = sale;
        customerNameLabel.setText(this.sale.getCustomer().getFirstName() + " " + this.sale.getCustomer().getLastName());
        dateValueLabel.setText(sale.getDate().toString());
        numberSaleValueLabel.setText(String.valueOf(sale.getId()));
        totalSaleValueLabel.setText(String.valueOf(sale.getTotal()));
        cbType.setValue("Cash");
        cbBank.setValue("Attijari");
        handler.upDateListPayment();
        chequeDeadLineDatePicker.setValue(LocalDate.now());
        paymentDatePicker.setValue(LocalDate.now());
        initWindow();
        addStylesToNodes();
        addColumnToPaymentsTableview();
        configPaymentsTableView();
        addNodesToWindow();
        addEvents();
        window.show();
    }
}
