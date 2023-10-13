package com.easybank.easybankjee.Service;

import com.easybank.easybankjee.Dao.EmployeDao;
import com.easybank.easybankjee.Dto.Employe;
import com.easybank.easybankjee.Implimentation.EmployeImpl;

import java.util.List;
import java.util.Optional;

public class EmployeService {
    private final EmployeImpl employeImp;
    public EmployeService(EmployeImpl employeImp) {
        this.employeImp = employeImp;
    }
    public Optional<Employe> Ajouter(Employe employe) {
        return employeImp.ajouterEmploye(employe);
    }
    public  List<Employe> listEmploye() { return employeImp.listEmploye();}

    public int supprimer(int marticule){ return employeImp.supprimerEmploye(marticule);}

    public Optional<Employe> chercher(int marticule) {
        return employeImp.chercherEmploye(marticule);
    }

    public Optional<Employe> modifier(Employe employe) {
        return employeImp.modifierEmploye(employe);
    }




}
