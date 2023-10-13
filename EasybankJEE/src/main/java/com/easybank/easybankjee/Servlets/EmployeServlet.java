package com.easybank.easybankjee.Servlets;

import com.easybank.easybankjee.Dao.EmployeDao;
import com.easybank.easybankjee.Dto.Employe;
import com.easybank.easybankjee.Implimentation.EmployeImpl;
import com.easybank.easybankjee.Service.EmployeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "EmployeServlet", value = "/EmployeServlet")
public class EmployeServlet extends HttpServlet {
    EmployeImpl employeimp = new EmployeImpl();
    EmployeService employeService = new EmployeService(employeimp);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {
        String action = request.getParameter("action");
        if (action == null){
            action = "list";
        }
        switch (action) {
            case "add":
                ajouterEmploye(request, response);
                break;
            case "list":
                afficherEmploye(request, response);
                break;
            case "supprimer":
                supprimerEmploye(request, response);
                break;
            case "edit":
                editEmploye(request, response);
                break;
            case "update":
                updateEmploye(request, response);
                break;
            default:
                afficherEmploye(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    private void ajouterEmploye(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        //String matricule = request.getParameter("matricule");
        int matricule = Integer.parseInt(request.getParameter("matricule"));
        LocalDate dateRecrutement = LocalDate.parse(request.getParameter("daterecrutement"));
        String email = request.getParameter("email");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        LocalDate dateNaissance = LocalDate.parse(request.getParameter("datenaissance"));
        String telephone = request.getParameter("telephone");
        String adress = request.getParameter("adress");

        Employe employe = new Employe( nom,  prenom,  dateNaissance,  telephone,  adress,  matricule,  dateRecrutement, email);
        Optional<Employe> addEmploye = employeService.Ajouter(employe);
        if (addEmploye.isPresent()){
            response.sendRedirect(request.getContextPath() + "/EmployeServlet?action=list");
        } else {
            response.sendRedirect("/EmployeServlet?error=ajoute-failed");
        }
    }

    private void afficherEmploye(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        List<Employe> employes = employeService.listEmploye();
        request.setAttribute("employes",employes);
        request.getRequestDispatcher("/index.jsp").forward(request,response);

    }


    private void supprimerEmploye(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int matricule = Integer.parseInt(request.getParameter("matricule"));;
        if (matricule != 0){
            int deleted = employeService.supprimer(matricule);
            if (deleted > 0){
                response.sendRedirect(request.getContextPath() + "/EmployeServlet?action=list");
            }else {
                response.sendRedirect("/EmployeServlet?error=delete-failed");
            }
        }else {
            response.sendRedirect(request.getContextPath() + "/EmployeServlet?action=list");

        }
    }

    private void editEmploye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int matricule = Integer.parseInt(request.getParameter("matricule"));;
        if (matricule > 0) {
            Optional<Employe> employeFind = employeService.chercher(matricule);
            if (!employeFind.isEmpty()) {
                request.setAttribute("employe", employeFind.get());
                System.out.println(employeFind);
                request.getRequestDispatcher("/Edit.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/EmployeServlet?action=list");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/EmployeServlet?action=list");
        }
    }



    private void updateEmploye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int matricule = Integer.parseInt(request.getParameter("matricule"));
        LocalDate dateRecrutement = LocalDate.parse(request.getParameter("daterecrutement"));
        String email = request.getParameter("email");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        LocalDate dateNaissance = LocalDate.parse(request.getParameter("datenaissance"));
        String telephone = request.getParameter("telephone");
        String adress = request.getParameter("adress");
        Employe employe = new Employe( nom,  prenom,  dateNaissance,  telephone,  adress,  matricule,  dateRecrutement, email);
        Optional<Employe> addEmploye = employeService.modifier(employe);
        if (addEmploye.isPresent()){
            response.sendRedirect(request.getContextPath() + "/EmployeServlet?action=list");
        } else {
            response.sendRedirect("/EmployeServlet?error=update-failed");
        }
    }




}