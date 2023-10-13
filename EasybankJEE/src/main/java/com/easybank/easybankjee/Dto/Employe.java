package com.easybank.easybankjee.Dto;

import java.time.LocalDate;
import java.util.List;

public class Employe extends Person{

    private int matricule;
    private LocalDate dateRecrutement;


    public Employe(String nom, String prenom, LocalDate dateNaissance, String telephone, String adress, int matricule, LocalDate dateRecrutement, String email) {
        super(nom, prenom, dateNaissance, telephone, adress, email);
        this.matricule = matricule;
        this.dateRecrutement = dateRecrutement;

    }


    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public LocalDate getDateRecrutement() {
        return dateRecrutement;
    }

    public void setDateRecrutement(LocalDate dateRecrutement) {
        this.dateRecrutement = dateRecrutement;
    }
}

