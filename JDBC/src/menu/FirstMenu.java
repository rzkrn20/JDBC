/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

/**
 *
 * @author rzkrn20
 */
public class FirstMenu {

    static MainMenu mainMenu = new MainMenu();

    public static void main(String[] args) {
        try {
            mainMenu.menu();

        } catch (Exception e) {
            System.out.println(e);
        mainMenu.menu();
        }
    }
}
