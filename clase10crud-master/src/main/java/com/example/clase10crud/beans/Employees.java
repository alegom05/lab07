package com.example.clase10crud.beans;

import java.sql.Date;

public class Employees {
        private int employeeId;
        private String fullNameEmployee;
        private String email;
        private String password;
        private String phoneNumber;
        private Date hireDate;
        private String jobId;
        private double salary;
        private double comissionPct;
        private int managerId;
        private int departmentId;
        private int enabled;

        public int getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(int employeeId) {
            this.employeeId = employeeId;
        }

        public String getFullNameEmployee() {
            return fullNameEmployee;
        }

        public void setFullNameEmployee(String fullNameEmployee) {
            this.fullNameEmployee = fullNameEmployee;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Date getHireDate() {
            return hireDate;
        }

        public void setHireDate(Date hireDate) {
            this.hireDate = hireDate;
        }

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public double getComissionPct() {
            return comissionPct;
        }

        public void setComissionPct(double comissionPct) {
            this.comissionPct = comissionPct;
        }

        public int getManagerId() {
            return managerId;
        }

        public void setManagerId(int managerId) {
            this.managerId = managerId;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public int getEnabled() {
            return enabled;
        }

        public void setEnabled(int enabled) {
            this.enabled = enabled;
        }
    }


