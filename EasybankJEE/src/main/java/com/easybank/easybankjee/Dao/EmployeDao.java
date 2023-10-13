package com.easybank.easybankjee.Dao;

import com.easybank.easybankjee.Dto.Employe;

import java.util.List;
import java.util.Optional;


public interface EmployeDao {
    Optional<Employe> ajouterEmploye(Employe employe);
    int supprimerEmploye(int matricule);
    Optional<Employe> chercherEmploye(int matricule);
    List<Employe> listEmploye();
    Optional<Employe> modifierEmploye(Employe employeModifie);

}
