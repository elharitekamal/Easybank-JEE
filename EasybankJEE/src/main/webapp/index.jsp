<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Clients</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/stylesheet.css">

    <link rel="stylesheet" href="sweetalert2.min.css">

</head>
<body>
<div class="container-lg">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8"><h2>Employes <b>Details</b></h2></div>
                    <a href="Ajout.jsp"><button type="button" class="btn btn-info add-new" id="showPopupButton" ><i class="fa fa-plus"></i> Add New</button></a>
                </div>
            </div>
        </div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Matricule</th>
                <th>Prenom</th>
                <th>Nom</th>
                <th>Telephone</th>
                <th>Date de naissance</th>
                <th>Date de recrutement</th>
                <th>Email</th>
                <th>Adresse</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${employes}" var="employe">
                <tr>
                    <td>${employe.matricule}</td>
                    <td>${employe.prenom}</td>
                    <td>${employe.nom}</td>
                    <td>${employe.telephone}</td>
                    <td>${employe.dateNaissance}</td>
                    <td>${employe.dateRecrutement}</td>
                    <td>${employe.email}</td>
                    <td>${employe.adress}</td>
                    <td>
                        <a class="edit"><i class="material-icons" onclick="showUpdateAlert('${employe.matricule}')">&#xE254;</i></a>
                        <a class="delete"><i class="material-icons" onclick="showDeleteAlert('${employe.matricule}')">&#xE872;</i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
<script>
    function showUpdateAlert(employeMatricule) {
        Swal.fire({
            title: 'Do you want to save the changes?',
            showDenyButton: true,
            showCancelButton: true,
            confirmButtonText: 'Save',
            denyButtonText: `Don't save`,
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {

                window.location.href = "${pageContext.request.contextPath}/EmployeServlet?action=edit&matricule=" + employeMatricule;

            } else if (result.isDenied) {
                Swal.fire('Changes are not saved', '', 'info')
            }
            });
    }
</script>

<script>
    function showDeleteAlert(employeMatricule) {
        Swal.fire({
            title: 'Do you want to save the changes?',
            showDenyButton: true,
            showCancelButton: true,
            confirmButtonText: 'Save',
            denyButtonText: `Don't save`,
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
             window.location.href = "${pageContext.request.contextPath}/EmployeServlet?action=supprimer&matricule=" + employeMatricule;
            } else if (result.isDenied) {
                Swal.fire('Changes are not saved', '', 'info')
            }
        })
    }
</script>
<script src="sweetalert2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</body>
</html>