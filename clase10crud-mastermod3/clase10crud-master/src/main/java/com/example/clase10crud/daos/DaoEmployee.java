package com.example.clase10crud.daos;

import com.example.clase10crud.beans.Employees;
import com.example.clase10crud.beans.Job;

import java.sql.*;
import java.util.ArrayList;

public class
DaoEmployee {

    public ArrayList<Employees> listar(){

        ArrayList<Employees> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "select * from employees";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employees employees = new Employees();

                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String fullNameEmployee = firstName + " " + lastName;
                employees.setFullNameEmployee(fullNameEmployee);

                employees.setEmployeeId(rs.getInt("employee_id"));
                employees.setEmail(rs.getString("email"));
                employees.setHireDate(rs.getDate("hire_date"));
                employees.setJobId(rs.getString("job_id"));
                employees.setSalary(Math.round(rs.getDouble("salary")));
                employees.setFullNameEmployee(fullNameEmployee);
                lista.add(employees);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public static Employees buscarPorId(String id){

        Employees employees = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "select * from employees where employee_id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,id);

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    employees = new Employees();

                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String fullNameEmployee = firstName + " " + lastName;
                    employees.setFullNameEmployee(fullNameEmployee);

                    employees.setEmployeeId(rs.getInt(1));
                    employees.setFullNameEmployee(fullNameEmployee);
                    employees.setEmail(rs.getString(4));
                    employees.setHireDate(rs.getDate(7));
                    employees.setJobId(rs.getString(8));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employees;
    }

    public static void actualizar(Employees employees){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "update employees set first_name = ?, last_name = ?, email = ?, hire_date = ?, salary=? where employee_id = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            String fullNameEmployee = employees.getFullNameEmployee().trim();

            String[] nameParts = fullNameEmployee.split(" ");

            String firstName= nameParts[0];
            String lastName= nameParts[1];

            pstmt.setString(1,firstName);
            pstmt.setString(2,lastName);
            pstmt.setString(3,employees.getEmail());
            pstmt.setDate(4,employees.getHireDate());
            pstmt.setDouble(5,employees.getSalary());
            pstmt.setDouble(6,employees.getEmployeeId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void borrar(String employeeId) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        // SQL para actualizar los managers referenciados en departments a NULL
        String updateDepartmentsSql = "UPDATE departments SET manager_id = NULL WHERE manager_id = ?";
        // SQL para eliminar un empleado
        String deleteEmployeeSql = "DELETE FROM employees WHERE employee_id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Desactivar auto-commit para manejar la transacción manualmente
            connection.setAutoCommit(false);

            // Actualizar las referencias en departments
            try (PreparedStatement updatePstmt = connection.prepareStatement(updateDepartmentsSql)) {
                updatePstmt.setString(1, employeeId);
                updatePstmt.executeUpdate();
            }

            // Eliminar el empleado
            try (PreparedStatement deletePstmt = connection.prepareStatement(deleteEmployeeSql)) {
                deletePstmt.setString(1, employeeId);
                deletePstmt.executeUpdate();
            }

            // Hacer commit de la transacción
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-lanzar la excepción para manejarla en otro lugar si es necesario
        }
    }

    public static void crear(String fullNameEmployee, String email, String hireDate, String salary, String jobId){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "insert into employees (first_name, last_name, email, hire_date, salary, job_id) values (?,?,?,?,?,?)";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            String[] nameParts = fullNameEmployee.split(" ", 2);

            String firstName= nameParts[0];
            String lastName= nameParts[1];

            pstmt.setString(1,firstName);
            pstmt.setString(2,lastName);
            pstmt.setString(3,email);
            pstmt.setString(4,hireDate);
            pstmt.setString(5,salary);
            pstmt.setString(6,jobId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*public ArrayList<Employees> buscarIdOrTitle(String name){

        ArrayList<Employees> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "select * from employees where employee_id = ? or lower(concat(first_name,' ',last_name) as full_name) like lower(?);";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,name);
            pstmt.setString(2,"%" + name + "%");

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    Employees employees = new Employees();
                    employees.setEmployeeId(rs.getInt(1));
                    employees.setFullNameEmployee(rs.getString(2));
                    employees.setEmail(rs.getString(3));
                    employees.setHireDate(rs.getDate(4));

                    lista.add(employees);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }*/

}
