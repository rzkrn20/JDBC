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
import models.Region;

/**
 *
 * @author rzkrn20
 */
public class RegionDb {

    private Connection connection;

    public RegionDb(Connection connection) {
        this.connection = connection;
    }

    /**
     * SHOW format -> toString
     *
     * @return List
     */
    public List<Region> show() {
        List<Region> region = new ArrayList<>();
        String query = "SELECT * FROM REGION";
        try {
            ResultSet resultSet = connection
                    .prepareStatement(query)
                    .executeQuery();

            while (resultSet.next()) {
                region.add(new Region(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }

    public List<Region> getById(int id) {
        List<Region> regions = new ArrayList<>();
        String query = "SELECT * FROM REGION WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                regions.add(new Region(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();//line number yang error juga di print
        }
        return regions;
    }

    /**
     * INSERT insert unique id and name, count is from method count
     *
     * @param region
     * @return boolean
     */
    public boolean insert(models.Region region) {
        int count = count(region.getId());
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO REGION(id, name, count) VALUES(?,?, ?)");
            preparedStatement.setInt(1, region.getId());
            preparedStatement.setString(2, region.getName());
            preparedStatement.setInt(3, count);
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
     * @param region
     * @return boolean
     */
    public boolean update(models.Region region) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE REGION SET id = ?, name = ?, count = (SELECT COUNT(*) FROM COUNTRY WHERE region= ?) WHERE id = ?;");
            preparedStatement.setInt(1, region.getId());
            preparedStatement.setString(2, region.getName());
            preparedStatement.setInt(3, region.getId());
            preparedStatement.setInt(4, region.getId());
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM REGION WHERE id = ? ");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * CHECK ID and NAME from user purpose -> to make sure no double id or name
     *
     * @param id
     * @param name
     * @return boolean
     */
    public boolean checkIdName(int id, String name) {

        String query = "SELECT * FROM REGION WHERE id = ? || name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * MAX ID get the max id purpose -> for the auto-generated id
     *
     * @return int
     */
    public int getMaxId() {
        int maxId;
        String query = "SELECT MAX(id) FROM region;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                maxId = resultSet.getInt(1);
                return maxId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * COUNT region used in country table
     *
     * @param id
     * @return int
     */
    public int count(int id) {
        int count;
        String query = "SELECT COUNT(*) FROM COUNTRY WHERE region= ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
