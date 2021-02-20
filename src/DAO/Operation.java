package DAO;

import DAO.Compte;

import java.io.Serializable;
import java.time.LocalDate;

public class Operation implements Serializable {
    private long id;
    private LocalDate date;
    private double montant;
    private String type;
    private Compte compte;

    public Operation(long id, LocalDate date, double montant, String type, Compte compte) {
        this.id = id;
        this.date = date;
        this.montant = montant;
        this.type = type;
        this.compte = compte;
    }

    public Operation(LocalDate date, double montant, String type, Compte compte) {
        this.date = date;
        this.montant = montant;
        this.type = type;
        this.compte = compte;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getMontant() {
        return montant;
    }

    public String getType() {
        return type;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
}
