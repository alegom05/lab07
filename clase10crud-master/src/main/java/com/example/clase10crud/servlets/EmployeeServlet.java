package com.example.clase10crud.servlets;

import com.example.clase10crud.beans.Employees;
import com.example.clase10crud.beans.Job;
import com.example.clase10crud.daos.DaoEmployee;
import com.example.clase10crud.daos.JobDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

//  http://localhost:8080/EmployeeServlet -> http://localhost:8080/home
@WebServlet(name = "home", value = "/home")
public class EmployeeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        DaoEmployee daoEmployee = new DaoEmployee();

        switch (action){
            case "lista":
                //saca del modelo
                ArrayList<Employees> list = daoEmployee.listar();

                //mandar la lista a la vista -> job/lista.jsp
                request.setAttribute("lista",list);
                RequestDispatcher rd = request.getRequestDispatcher("employee/home.jsp");
                rd.forward(request,response);
                break;
            case "new":
                request.getRequestDispatcher("employee/employee_new.jsp").forward(request,response);
                break;
            case "edit":
                String id = request.getParameter("id");

                Employees employees = DaoEmployee.buscarPorId(id);

                if(employees != null){
                    request.setAttribute("employees",employees);
                    request.getRequestDispatcher("employee/employee_edit.jsp").forward(request,response);
                }else{
                    response.sendRedirect(request.getContextPath() + "/home");
                }
                break;
            case "del":
                String idd = request.getParameter("id");
                Employees employees1 = DaoEmployee.buscarPorId(idd);

                System.out.println("Eliminando empleado con ID: " + idd); // Agrega esta línea


                if(employees1 != null){
                    try {
                        daoEmployee.borrar(idd);
                    } catch (SQLException e) {
                        System.out.println("Log: excepcion: " + e.getMessage());
                    }
                }
                response.sendRedirect(request.getContextPath() + "/home");
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        DaoEmployee daoEmployee = new DaoEmployee();

        String action = request.getParameter("action") == null ? "crear" : request.getParameter("action");

        switch (action){
            case "crear"://voy a crear un nuevo empleado
                String idEmployee3 = request.getParameter("idEmployee");
                String fullNameEmployee3 = request.getParameter("full_name_employee");
                String email3 = request.getParameter("email");
                String hireDate3 = request.getParameter("hire_date");
                String salary3 = request.getParameter("salary");
                String jobId3 = request.getParameter("jobId");

                boolean isAllValid = true;

                if(isAllValid){

                    Employees employees = DaoEmployee.buscarPorId(idEmployee3);

                    if(employees == null){
                        DaoEmployee.crear(fullNameEmployee3,email3,hireDate3,salary3,jobId3);
                        response.sendRedirect(request.getContextPath() + "/home");
                    }else{
                        request.getRequestDispatcher("employee/employee_new.jsp").forward(request,response);
                    }
                }else{
                    request.getRequestDispatcher("employee/employee_new.jsp").forward(request,response);
                }
                break;
            case "e": //voy a actualizar
                String employeeId4 = request.getParameter("employee_id");
                String fullNameEmployee4 = request.getParameter("full_name_employee");
                String email4 = request.getParameter("email");
                Date hireDate4 = Date.valueOf(request.getParameter("hire_date"));
                Double salary = Double.valueOf(request.getParameter("salary"));

                boolean isAllValid2 = true;

                if(isAllValid2){

                    Employees employees = new Employees();
                    employees.setEmployeeId(Integer.parseInt(employeeId4));
                    employees.setFullNameEmployee(fullNameEmployee4);
                    employees.setEmail(email4);
                    employees.setHireDate(hireDate4);
                    employees.setSalary(salary);

                    DaoEmployee.actualizar(employees);
                    response.sendRedirect(request.getContextPath() + "/home");
                }else{
                    request.setAttribute("employees",DaoEmployee.buscarPorId(employeeId4));
                    request.getRequestDispatcher("employee/employee_edit.jsp").forward(request,response);
                }
                break;
            /*case "s":
                String textBuscar = request.getParameter("textoBuscar");
                ArrayList<Employees> lista = DaoEmployee.buscarPorId(textBuscar);

                request.setAttribute("lista",lista);
                request.setAttribute("busqueda",textBuscar);
                request.getRequestDispatcher("employees/home.jsp").forward(request,response);

                break;*/


        }

    }
}