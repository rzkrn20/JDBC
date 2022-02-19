/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author rzkrn20
 */
public class Employee {
    private int id;
    private String first_name, last_name, email, phone_number;
    private String hire_date;
    private String job;
    private double salary, comission_pct;
    private int manager, department;

    public Employee(int id, String first_name, String last_name, String email, String phone_number, String hire_date, String job, double salary, double comission_pct, int manager, int department) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.hire_date = hire_date;
        this.job = job;
        this.salary = salary;
        this.comission_pct = comission_pct;
        this.manager = manager;
        this.department = department;
    }

    @Override
    public String toString() {
        return "id: " + id + "| first_name: " + first_name + "\t| last_name: " + last_name + "\t| email: " 
                + email + "\t| phone_number: " + phone_number + "\t| hire_date: " + hire_date + "\t| job: " 
                + job + "\t| salary=" + salary + "\t| comission_pct: " + comission_pct + "\t| manager: " 
                + manager + "\t| department: " + department ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getComission_pct() {
        return comission_pct;
    }

    public void setComission_pct(double comission_pct) {
        this.comission_pct = comission_pct;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }
    
}
