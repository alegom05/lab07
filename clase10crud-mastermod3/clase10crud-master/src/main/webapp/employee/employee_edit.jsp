<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="employees" type="com.example.clase10crud.beans.Employees" scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
              crossorigin="anonymous">
        <title>Editar un empleado</title>
    </head>
    <body>
        <div class='container'>
            <h1 class='mb-3'>Editar un empleado</h1>
            <form method="post" action="<%=request.getContextPath()%>/home?action=e">
                <div class="mb-3">
                    <input type="hidden" class="form-control" name="employee_id" value="<%=employees.getEmployeeId()%>">
                </div>
                <div class="mb-3">
                    <label>Nombre completo</label>
                    <input type="text" class="form-control" name="full_name_employee" value="<%=employees.getFullNameEmployee()%>">
                </div>
                <div class="mb-3">
                    <label>Email</label>
                    <input type="text" class="form-control" name="email" value="<%=employees.getEmail()%>">
                </div>
                <div class="mb-3">
                    <label>Fecha de contrato</label>
                    <input type="text" class="form-control" name="hire_date" value="<%=employees.getHireDate()%>">
                </div>
                <div class="mb-3">
                    <label>Salario</label>
                    <input type="text" class="form-control" name="salary" value="<%=employees.getSalary()%>">
                </div>
                <a href="<%=request.getContextPath()%>/home" class="btn btn-danger">Regresar</a>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
                crossorigin="anonymous"></script>
    </body>
</html>

