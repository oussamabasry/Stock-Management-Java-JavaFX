package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Compte implements Serializable {
    private long id;
    private String numero;
    private String nom;
    private String prenom;
    private List<Operation> operations = new ArrayList<>();
    private double solde;

    public Compte(long id, String numero, String nom, String prenom, List<Operation> operations) {
        this.id = id;
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.operations = operations;
        calculerSolde();
    }

    public Compte(String numero, String nom, String prenom, List<Operation> operations) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.operations = operations;
        calculerSolde();
    }

    public void addOperation(Operation operation) {
        this.operations.add(operation);
        calculerSolde();
    }

    public long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
        calculerSolde();
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<Operation> getOperations() {
        return this.operations;
    }

    public void calculerSolde() {
        double sumSolde = 0;
        for (Operation operation : operations) {
            sumSolde += operation.getMontant();
        }
        solde = sumSolde;
    }
}