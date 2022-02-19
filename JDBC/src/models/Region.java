/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author rzkrn20
 */
public class Region {
    private String name;
    private int id, count;

    public Region(int id, String name, int count) {
        this.name = name;
        this.id = id;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Id: " + id
            + "| Name: " + name
            + "\t| Count: " + count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
}
