<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    int maxSalary = (int) request.getAttribute("maxSalary");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
    </head>
    <body>
        <h1><%= "Lab7 IWeb" %>
        </h1>
        <br/>
        <h2>
        <a href="home">Lista de empleados</a>
        <%--<h1>El salario máximo de este día desde base de datos es: <%=maxSalary%></h1>--%>
        </h2>
    </body>
</html>