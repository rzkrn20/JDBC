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
import models.Location;

/**
 *
 * @author rzkrn20
 */
public class LocationDb {

    private Connection connection;

    public LocationDb(Connection connection) {
        this.connection = connection;
    }

    /**
     * SHOW
     *
     * @return List
     */
    public List<Location> show() {
        List<Location> location = new ArrayList<>();
        String query = "SELECT * FROM LOCATION";
        try {
            ResultSet resultSet = connection
                    .prepareStatement(query)
                    .executeQuery();

            while (resultSet.next()) {
                location.add(new Location(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)
                                        , resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return location;
    }
    public List<Location> getById(int id) {
        List<Location> location = new ArrayList<>();
        String query = "SELECT * FROM LOCATION WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                location.add(new Location(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)
                                        , resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();//line number yang error juga di print
        }
        return location;
    }
    /**
     * INSERT insert unique id and name, count is from method count
     *
     * @param location
     * @return boolean
     */
    public boolean insert(models.Location location) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO LOCATION(id, street_address, postal_code, city, state_province, country) VALUES(?,?,?,?,?,?)");
            preparedStatement.setInt(1, location.getId());
            preparedStatement.setString(2, location.getStreet_address());
            preparedStatement.setString(3, location.getPostal_code());
            preparedStatement.setString(4, location.getCity());
            preparedStatement.setString(5, location.getState_province());
            preparedStatement.setString(6, location.getCountry());
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
     * @param location
     * @return boolean
     */
    public boolean update(models.Location location) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE LOCATION SET id = ?, street_address = ?, postal_code = ?, city = ?, state_province = ?, country = ? WHERE id = ?;");
            preparedStatement.setInt(1, location.getId());
            preparedStatement.setString(2, location.getStreet_address());
            preparedStatement.setString(3, location.getPostal_code());
            preparedStatement.setString(4, location.getCity());
            preparedStatement.setString(5, location.getState_province());
            preparedStatement.setString(6, location.getCountry());
            preparedStatement.setInt(7, location.getId());
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM LOCATION WHERE id = ? ");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * CHECK ID from user purpose -> to make sure no double id
     *
     * @param id
     * @return boolean
     */

    public boolean checkIdName(int id) {

        String query = "SELECT * FROM LOCATION WHERE id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
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
     * CHECK country from user purpose -> to make sure the country is exist in country table
     *
     * @param country
     * @return boolean
     */
    public boolean checkCountry(String country) {

        String query = "SELECT * FROM COUNTRY WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, country);
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
