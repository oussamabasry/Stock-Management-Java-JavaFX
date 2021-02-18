package com.stock.payment.ihm;

import com.stock.customer.dao.Customer;
import com.stock.payment.dao.*;
import com.stock.product.dao.IProductDao;
import com.stock.product.dao.Product;
import com.stock.product.dao.ProductDaoImpl;
import com.stock.sales.dao.*;
import com.stock.sales.ihm.UpDateSaleWindow;
import javafx.collections.FXCollections;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.List;

public class PaymentHandler {
    PaymentWindow paymentWindow = null;

    public PaymentHandler(PaymentWindow paymentWindow) {
        this.paymentWindow = paymentWindow;
    }


    public void displayCheque() {
        if (paymentWindow.cbType.getValue().equals("Cheque")) {
            paymentWindow.paymentDetailsVBox.getChildren().remove(paymentWindow.savePaymentButton);
            try {
                paymentWindow.paymentDetailsVBox.getChildren().addAll(
                        paymentWindow.chequeNumberLabel,
                        paymentWindow.chequeNumberTextField,
                        paymentWindow.dateDeadlineChequeLabel,
                        paymentWindow.chequeDeadLineDatePicker,
                        paymentWindow.cbBank,
                        paymentWindow.nameLabel,
                        paymentWindow.nameTextField,
                        paymentWindow.savePaymentButton
                );
                paymentWindow.chequeNumberTextField.setText("");
                paymentWindow.chequeDeadLineDatePicker.setValue(LocalDate.now());
                paymentWindow.nameTextField.setText("");
                paymentWindow.cbBank.setValue("Attijari");
            } catch (Exception exp) {
            }

        } else {
            paymentWindow.paymentDetailsVBox.getChildren().removeAll(
                    paymentWindow.chequeNumberLabel,
                    paymentWindow.chequeNumberTextField,
                    paymentWindow.dateDeadlineChequeLabel,
                    paymentWindow.chequeDeadLineDatePicker,
                    paymentWindow.cbBank,
                    paymentWindow.nameLabel,
                    paymentWindow.nameTextField
            );
        }
    }

    public void addPayment() {
        IPayment paymentDao = new PaymentDaoImpl();

        if (paymentWindow.cbType.getValue().equals("Cash") && !paymentWindow.paymentAmountTextField.getText().isEmpty() && paymentWindow.paymentDatePicker.getValue() != null) {
            Cash cash = new Cash(
                    Double.valueOf(paymentWindow.paymentAmountTextField.getText()),
                    paymentWindow.paymentDatePicker.getValue(),
                    paymentWindow.cbType.getValue().toString(),
                    paymentWindow.sale
            );
            paymentDao.add(cash);
            upDateListPayment();
            paymentWindow.paymentAmountTextField.setText("");
        }
        if (paymentWindow.cbType.getValue().equals("Cheque") && !paymentWindow.paymentAmountTextField.getText().isEmpty() && paymentWindow.paymentDatePicker.getValue() != null && !paymentWindow.chequeNumberTextField.getText().isEmpty() && paymentWindow.chequeDeadLineDatePicker.getValue() != null && paymentWindow.cbBank.getValue() != null && !paymentWindow.nameTextField.getText().isEmpty()) {
            Cheque cheque = new Cheque(
                    Double.valueOf(paymentWindow.paymentAmountTextField.getText()),
                    paymentWindow.paymentDatePicker.getValue(),
                    paymentWindow.cbType.getValue().toString(),
                    paymentWindow.sale,
                    Integer.parseInt(paymentWindow.chequeNumberTextField.getText()),
                    paymentWindow.nameTextField.getText(),
                    paymentWindow.cbBank.getValue().toString(),
                    paymentWindow.chequeDeadLineDatePicker.getValue()
            );
            paymentDao.add(cheque);
            upDateListPayment();
            paymentWindow.paymentAmountTextField.setText("");
            paymentWindow.chequeNumberTextField.setText("");
            paymentWindow.nameTextField.setText("");
        }
    }

    public void upDateListPayment() {
        IPayment paymentDao = new PaymentDaoImpl();
        paymentWindow.paymentsObservablesList.setAll(paymentDao.getAll(paymentWindow.sale));
        upDatetotalPayments();
    }

    public void upDatePayment() {
        Payment payment = paymentWindow.paymentsTableView.getSelectionModel().getSelectedItem();
        if (payment != null) {
            if (payment instanceof Cheque) {
                paymentWindow.paymentDatePicker.setValue(payment.getDate());
                paymentWindow.paymentAmountTextField.setText(String.valueOf(payment.getAmount()));
                paymentWindow.cbType.setValue(payment.getType().toString());
                try {
                    paymentWindow.paymentDetailsVBox.getChildren().removeAll(paymentWindow.cbType, paymentWindow.paymentTypeLabel);
                } catch (Exception exp) {
                }

                paymentWindow.chequeNumberTextField.setText(String.valueOf(((Cheque) payment).getChequeNumber()));
                paymentWindow.chequeDeadLineDatePicker.setValue(((Cheque) payment).getDeadline());
                paymentWindow.cbBank.setValue(((Cheque) payment).getBank());
                paymentWindow.nameTextField.setText(((Cheque) payment).getOwner());
                try {
                    paymentWindow.paymentDetailsVBox.getChildren().remove(paymentWindow.savePaymentButton);
                } catch (Exception exp) {
                }
                try {
                    paymentWindow.paymentDetailsVBox.getChildren().remove(paymentWindow.upDatePaymentButton);
                } catch (Exception exp) {
                }
                try {
                    paymentWindow.paymentDetailsVBox.getChildren().add(paymentWindow.upDatePaymentButton);
                } catch (Exception exp) {
                }

            } else if (payment instanceof Cash) {
                paymentWindow.paymentDatePicker.setValue(payment.getDate());
                paymentWindow.paymentAmountTextField.setText(String.valueOf(payment.getAmount()));
                paymentWindow.cbType.setValue(payment.getType().toString());
                try {
                    paymentWindow.paymentDetailsVBox.getChildren().remove(paymentWindow.savePaymentButton);
                } catch (Exception exp) {
                }
                try {
                    paymentWindow.paymentDetailsVBox.getChildren().removeAll(
                            paymentWindow.chequeNumberTextField,
                            paymentWindow.chequeDeadLineDatePicker,
                            paymentWindow.cbBank,
                            paymentWindow.nameTextField,
                            paymentWindow.cbType,
                            paymentWindow.paymentTypeLabel
                    );
                    paymentWindow.paymentDetailsVBox.getChildren().add(paymentWindow.upDatePaymentButton);
                } catch (Exception exp) {
                }
            }
        }
    }

    public void upDatePaymentInfo() {
        IPayment paymentDao = new PaymentDaoImpl();

        if (paymentWindow.cbType.getValue().equals("Cash") && !paymentWindow.paymentAmountTextField.getText().isEmpty() && paymentWindow.paymentDatePicker.getValue() != null) {
            Cash cash = (Cash) paymentWindow.paymentsTableView.getSelectionModel().getSelectedItem();

            cash.setAmount(Double.valueOf(paymentWindow.paymentAmountTextField.getText()));
            cash.setDate(paymentWindow.paymentDatePicker.getValue());

            paymentDao.update(cash);
            paymentWindow.clearContainers();
            paymentWindow.addNodesToWindow();
            upDateListPayment();
        }
        if (paymentWindow.cbType.getValue().equals("Cheque") && !paymentWindow.paymentAmountTextField.getText().isEmpty() && paymentWindow.paymentDatePicker.getValue() != null && !paymentWindow.chequeNumberTextField.getText().isEmpty() && paymentWindow.chequeDeadLineDatePicker.getValue() != null && paymentWindow.cbBank.getValue() != null && !paymentWindow.nameTextField.getText().isEmpty()) {
            Cheque cheque = (Cheque) paymentWindow.paymentsTableView.getSelectionModel().getSelectedItem();

            cheque.setAmount(Double.valueOf(paymentWindow.paymentAmountTextField.getText()));
            cheque.setDate(paymentWindow.paymentDatePicker.getValue());
            cheque.setChequeNumber(Integer.parseInt(paymentWindow.chequeNumberTextField.getText()));
            cheque.setDeadline(paymentWindow.chequeDeadLineDatePicker.getValue());
            cheque.setBank(paymentWindow.cbBank.getValue().toString());
            cheque.setOwner(paymentWindow.nameTextField.getText());
            paymentDao.update(cheque);
            paymentWindow.clearContainers();
            paymentWindow.addNodesToWindow();
            upDateListPayment();
        }
    }

    public void deletePayment() {
        Payment payment = paymentWindow.paymentsTableView.getSelectionModel().getSelectedItem();
        IPayment paymentDao = new PaymentDaoImpl();
        if (payment != null){
            paymentDao.delete(payment);
            upDateListPayment();
        }

    }

    public  void  upDatetotalPayments(){
        double sum = 0;
        for(Payment payment : paymentWindow.paymentsObservablesList)
            sum+= payment.getAmount();
        paymentWindow.totalPaidSaleValueLabel.setText(String.valueOf(sum));
        double rest = paymentWindow.sale.getTotal() - sum;
        paymentWindow.restSaleValueLabel.setText(String.valueOf(rest));
    }
}
