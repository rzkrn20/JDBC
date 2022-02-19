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
import models.Department;

/**
 *
 * @author rzkrn20
 */
public class DepartmentDb {

    private Connection connection;

    public DepartmentDb(Connection connection) {
        this.connection = connection;
    }

    /**
     * SHOW
     *
     * @return List
     */
    public List<Department> show() {
        List<Department> department = new ArrayList<>();
        String query = "SELECT * FROM DEPARTMENT";
        try {
            ResultSet resultSet = connection
                    .prepareStatement(query)
                    .executeQuery();

            while (resultSet.next()) {
                department.add(new Department(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }
    public List<Department> getById(int id) {
        List<Department> department = new ArrayList<>();
        String query = "SELECT * FROM DEPARTMENT WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                department.add(new Department(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();//line number yang error juga di print
        }
        return department;
    }
    /**
     * INSERT insert unique id and name, count is from method count
     *
     * @param department
     * @return boolean
     */
    public boolean insert(models.Department department) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO DEPARTMENT(id, name, manager, location) VALUES(?,?,?,?)");
            preparedStatement.setInt(1, department.getId());
            preparedStatement.setString(2, department.getName());
            preparedStatement.setInt(3, department.getManager());
            preparedStatement.setInt(4, department.getLocation());
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
     * @param department
     * @return boolean
     */
    public boolean update(models.Department department) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE DEPARTMENT SET id = ?, name = ?, manager = ?, location = ? WHERE id = ?;");
            preparedStatement.setInt(1, department.getId());
            preparedStatement.setString(2, department.getName());
            preparedStatement.setInt(3, department.getManager());
            preparedStatement.setInt(4, department.getLocation());
            preparedStatement.setInt(5, department.getId());
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM DEPARTMENT WHERE id = ? ");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * CHECK ID or NAME from user purpose -> to make sure no double id or name
     *
     * @param id
     * @param name
     * @return boolean
     */

    public boolean checkIdName(int id, String name) {

        String query = "SELECT * FROM DEPARTMENT WHERE id = ?||name = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
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
     * CHECK location from user purpose -> to make sure the location is exist in location table
     *
     * @param location
     * @return boolean
     */
    public boolean checkLocation(int location) {

        String query = "SELECT * FROM LOCATION WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, location);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
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
}
