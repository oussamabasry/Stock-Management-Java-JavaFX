package com.stock.payment.dao;

import DAO.CompteSocket;
import DAO.ConfirmationResponse;


import java.io.*;
import java.net.Socket;

public class SocketConnection {
    private Socket socketEnd2 = null;
    private static final int port = 3333;
    private InputStream input = null;
    private OutputStream output = null;
    private ConfirmationResponse confirmationResponse;

    public SocketConnection() {
        try {
            socketEnd2 = new Socket("127.0.0.1", port);
            input = socketEnd2.getInputStream();
            output = socketEnd2.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendInfosOperation(CompteSocket compteSocket) {
        try {
            ObjectOutputStream oo = new ObjectOutputStream(output);
            oo.writeObject(compteSocket);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void receveConfirmation() {
        try {
            System.out.println("hi");
            ObjectInputStream oi = new ObjectInputStream(input);
            try {
                ConfirmationResponse confirmationResponseInfo = (ConfirmationResponse) oi.readObject();
                this.confirmationResponse = confirmationResponseInfo;
                System.out.println("Confirmation Info: \n Compte: " + confirmationResponseInfo.isExistenceAccount() + "\n montant :" + confirmationResponseInfo.isValideAmount());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConfirmationResponse getConfirmationResponse() {
        return confirmationResponse;
    }
}
