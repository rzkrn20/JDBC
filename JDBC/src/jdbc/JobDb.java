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
import models.Job;

/**
 *
 * @author rzkrn20
 */
public class JobDb {

    private Connection connection;

    public JobDb(Connection connection) {
        this.connection = connection;
    }

    /**
     * SHOW
     *
     * @return List
     */
    public List<Job> show() {
        List<Job> job = new ArrayList<>();
        String query = "SELECT * FROM JOB";
        try {
            ResultSet resultSet = connection
                    .prepareStatement(query)
                    .executeQuery();

            while (resultSet.next()) {
                job.add(new Job(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return job;
    }

    public List<Job> getById(String id) {
        List<Job> job = new ArrayList<>();
        String query = "SELECT * FROM JOB WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                job.add(new Job(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();//line number yang error juga di print
        }
        return job;
    }

    /**
     * INSERT insert unique id and name, count is from method count
     *
     * @param job
     * @return boolean
     */
    public boolean insert(models.Job job) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO JOB(id, title, min_salary, max_salary) VALUES(?,?,?,?)");
            preparedStatement.setString(1, job.getId());
            preparedStatement.setString(2, job.getTitle());
            preparedStatement.setDouble(3, job.getMin_salary());
            preparedStatement.setDouble(4, job.getMax_salary());
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
     * @param job
     * @return boolean
     */
    public boolean update(models.Job job) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE JOB SET id = ?, title = ?, min_salary = ?, max_salary = ? WHERE id = ?;");
            preparedStatement.setString(1, job.getId());
            preparedStatement.setString(2, job.getTitle());
            preparedStatement.setDouble(3, job.getMin_salary());
            preparedStatement.setDouble(4, job.getMax_salary());
            preparedStatement.setString(5, job.getId());
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
    public boolean delete(String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM JOB WHERE id = ? ");
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * CHECK ID or TITLE from user purpose -> to make sure no double id or title
     *
     * @param id
     * @param title
     * @return boolean
     */
    public boolean checkIdName(String id, String title) {

        String query = "SELECT * FROM JOB WHERE id = ?||title = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, title);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
