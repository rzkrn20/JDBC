/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author rzkrn20
 */
public class Country {
    private String id,name;
    private int region;

    public Country(String id, String name, int region) {
        this.id = id;
        this.name = name;
        this.region = region;
    }

    @Override
    public String toString() {
        return "Id: " + id
            + "| Name: " + name
            + "\t| Region: " + region;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }
    
}
