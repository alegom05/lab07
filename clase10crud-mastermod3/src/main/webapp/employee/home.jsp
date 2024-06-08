
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.clase10crud.beans.Job" %>
<%@ page import="com.example.clase10crud.beans.Employees" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="lista" scope="request" type="ArrayList<com.example.clase10crud.beans.Employees>" />
<html>
<head>
    <title>Empleados</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="clearfix mt-3 mt-2">
        <a href="<%=request.getContextPath()%>/home">
            <h1 class="float-start link-dark">Lista de empleados</h1>
        </a>
        <a class="btn btn-primary float-end mt-1" href="<%=request.getContextPath() %>/home?action=new">Crear un empleado</a>
    </div>
    <hr/>
    <%--<form method="post" action="<%=request.getContextPath()%>/JobServlet?action=s">
        <div class="form-floating mt-3">
            <input type="text" class="form-control" id="floatingInput"
                   placeholder="Por ID o por nombre" name="textoBuscar" value="<%= request.getAttribute("busqueda") != null ? request.getAttribute("busqueda") : "" %>">
            <label for="floatingInput">Buscar trabajo</label>
        </div>
    </form>--%>
    <table class="table table-striped mt-3">
        <tr class="table-primary">
            <th>Id</th>
            <th>Nombre completo</th>
            <th>Email</th>
            <th>Fecha de contrato</th>
            <th>Salario</th>
            <th>JobId</th>
            <th></th>
        </tr>
        <% for (Employees employees : lista) { %>
        <tr>
            <td><%=employees.getEmployeeId()  %>
            </td>
            <td><%=employees.getFullNameEmployee()%>
            </td>
            <td><%=employees.getEmail()%>
            </td>
            <td><%=employees.getHireDate()%>
            </td>
            <td><%=employees.getSalary()%>
            </td>
            <td><%=employees.getJobId()%>
            </td>
            <td><a class="btn btn-success" href="<%=request.getContextPath()%>/home?action=edit&id=<%= employees.getEmployeeId() %>">Editar</a></td>
            <td><a onclick="return confirm('¿Esta seguro de borrar?')" class="btn btn-danger" href="<%=request.getContextPath()%>/home?action=del&id=<%= employees.getEmployeeId() %>">Borrar</a></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>