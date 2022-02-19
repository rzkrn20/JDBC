/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author rzkrn20
 */
public class Location {
    private int id;
    private String street_address, postal_code, city, state_province, country;

    public Location(int id, String street_address, String postal_code, String city, String state_province, String country) {
        this.id = id;
        this.street_address = street_address;
        this.postal_code = postal_code;
        this.city = city;
        this.state_province = state_province;
        this.country = country;
    }

    @Override
    public String toString() {
        return "id:" + id + "| street_address:" + street_address + "\t| postal_code:" + postal_code + "\t| city:" 
                + city + "\t| state_province:" + state_province + "\t| country:" + country + '}';
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState_province() {
        return state_province;
    }

    public void setState_province(String state_province) {
        this.state_province = state_province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
}
