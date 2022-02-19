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
import models.Country;

/**
 *
 * @author rzkrn20
 */
public class CountryDb {

    private Connection connection;

    public CountryDb(Connection connection) {
        this.connection = connection;
    }

    /**
     * SHOW
     *
     * @return List
     */
    public List<Country> show() {
        List<Country> country = new ArrayList<>();
        String query = "SELECT * FROM COUNTRY";
        try {
            ResultSet resultSet = connection
                    .prepareStatement(query)
                    .executeQuery();

            while (resultSet.next()) {
                country.add(new Country(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }

    public List<Country> getById(String id) {
        List<Country> country = new ArrayList<>();
        String query = "SELECT * FROM COUNTRY WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                country.add(new Country(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();//line number yang error juga di print
        }
        return country;
    }

    /**
     * INSERT insert unique id and name, count is from method count
     *
     * @param country
     * @return boolean
     */
    public boolean insert(models.Country country) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO COUNTRY(id, name, region) VALUES(?,?, ?)");
            preparedStatement.setString(1, country.getId());
            preparedStatement.setString(2, country.getName());
            preparedStatement.setInt(3, country.getRegion());
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
     * @param country
     * @return boolean
     */
    public boolean update(models.Country country) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE COUNTRY SET id = ?, name = ?, region = ? WHERE id = ?;");
            preparedStatement.setString(1, country.getId());
            preparedStatement.setString(2, country.getName());
            preparedStatement.setInt(3, country.getRegion());
            preparedStatement.setString(4, country.getId());
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM COUNTRY WHERE id = ? ");
            preparedStatement.setString(1, id);
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
    public boolean checkIdName(String id, String name) {

        String query = "SELECT * FROM COUNTRY WHERE id = ? || name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
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
     * CHECK region from user purpose -> to make sure the region is exist in
     * region table
     *
     * @param region
     * @return boolean
     */

    public boolean checkRegion(int region) {

        String query = "SELECT * FROM REGION WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, region);
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
     * get the id. purpose -> for the update count
     *
     * @param id
     * @return int
     */
    public int getId(String id) {
        int idReg;
        String query = "SELECT region FROM country WHERE id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idReg = resultSet.getInt(1);
                return idReg;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Update Count in REGION table
     *
     * @param region
     */
    public void countUpdate(int region) {
        int count;
        String query = "UPDATE REGION SET count = (SELECT COUNT(*) FROM COUNTRY WHERE region= ?) WHERE id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, region);
            preparedStatement.setInt(2, region);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
