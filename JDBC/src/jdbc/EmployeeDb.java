/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Employee;

/**
 *
 * @author rzkrn20
 */
public class EmployeeDb {

    private Connection connection;

    public EmployeeDb(Connection connection) {
        this.connection = connection;
    }

    /**
     * SHOW
     *
     * @return List
     */
    public List<Employee> show() {
        List<Employee> employee = new ArrayList<>();
        String query = "SELECT * FROM EMPLOYEE;";
        try {
            ResultSet resultSet = connection
                    .prepareStatement(query)
                    .executeQuery();

            while (resultSet.next()) {
                employee.add(new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), 
                                            resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), 
                                            resultSet.getString(7), resultSet.getDouble(8), resultSet.getDouble(9), 
                                            resultSet.getInt(10), resultSet.getInt(11)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
        public List<Employee> getById(int id) {
        List<Employee> employee = new ArrayList<>();
        String query = "SELECT * FROM EMPLOYEE WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employee.add(new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), 
                                            resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), 
                                            resultSet.getString(7), resultSet.getDouble(8), resultSet.getDouble(9), 
                                            resultSet.getInt(10), resultSet.getInt(11)));
            }
        } catch (SQLException e) {
            e.printStackTrace();//line number yang error juga di print
        }
        return employee;
    }

    /**
     * INSERT insert unique id and name, count is from method count
     *
     * @param employee
     * @return boolean
     */
    public boolean insert(models.Employee employee) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO EMPLOYEE(id, first_name, last_name, email, phone_number, hire_date, " +
                                             "job, salary, comission_pct, manager, department) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getFirst_name());
            preparedStatement.setString(3, employee.getLast_name());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setString(5, employee.getPhone_number());
            preparedStatement.setString(6, employee.getHire_date());
            preparedStatement.setString(7, employee.getJob());
            preparedStatement.setDouble(8, employee.getSalary());
            preparedStatement.setDouble(9, employee.getComission_pct());
            preparedStatement.setInt(10, employee.getManager());
            preparedStatement.setInt(11, employee.getDepartment());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * UPDATE unique name if id is not on the table, its return is true
     *
     * @param id
     * @param employee
     * @return boolean
     */
    public boolean update(models.Employee employee) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE EMPLOYEE SET id = ?, first_name = ?, last_name = ?, email = ?, phone_number = ?, hire_date = ?"
                                                                            + ", job = ?, salary = ?, comission_pct = ?, manager = ?, department = ? WHERE id = ?;");
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getFirst_name());
            preparedStatement.setString(3, employee.getLast_name());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setString(5, employee.getPhone_number());
            preparedStatement.setString(6, employee.getHire_date());
            preparedStatement.setString(7, employee.getJob());
            preparedStatement.setDouble(8, employee.getSalary());
            preparedStatement.setDouble(9, employee.getComission_pct());
            preparedStatement.setInt(10, employee.getManager());
            preparedStatement.setInt(11, employee.getDepartment());
            preparedStatement.setInt(12, employee.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * DELETE delete by id
     *
     * @param id
     * @return boolean
     */
    public boolean delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM EMPLOYEE WHERE id = ? ");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * CHECK ID or EMAIL or PHONE_NUMBER from user 
     * purpose -> to make sure no double id or email or phone_number
     *
     * @param id
     * @param email
     * @param phone_number
     * @return boolean
     */

    public boolean checkIdName(int id, String email, String phone_number) {

        String query = "SELECT * FROM EMPLOYEE WHERE id = ?||email = ?||phone_number = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone_number);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
       /**
        * CHECK job from user purpose -> to make sure the job is exist in job table
        * @param job
        * @return boolean
        */
    public boolean checkJob(String job) {

        String query = "SELECT * FROM JOB WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, job);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * CHECK manager from user purpose -> to make sure the manager id is exist in "employee id"
     * @param manager
     * @return boolean
     */
    public boolean checkManager(int manager) {

        String query = "SELECT * FROM EMPLOYEE WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, manager);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * CHECK department from user purpose -> to make sure the department is exist in department table
     * @param department
     * @return boolean
     */
    public boolean checkDepartment(int department) {

        String query = "SELECT * FROM DEPARTMENT WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, department);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
