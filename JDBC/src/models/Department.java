/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author rzkrn20
 */
public class Department {

    private int id;
    private String name;
    private int manager, location;

    public Department(int id, String name, int manager, int location) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.location = location;
    }

    @Override
    public String toString() {
        return "id: " + id + "| name: " + name + "\t| manager:" + manager + "\t| location:" + location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
    
}
