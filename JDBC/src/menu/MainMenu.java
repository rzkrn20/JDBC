/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import java.util.Scanner;

/**
 *
 * @author rzkrn20
 */
public class MainMenu {

    public void menu() {
        MenuRegion menuRegion = new MenuRegion();
        MenuCountry menuCountry = new MenuCountry();
        MenuLocation menuLocation = new MenuLocation();
        MenuDepartment menuDepartment = new MenuDepartment();
        MenuJob menuJob = new MenuJob();
        MenuEmployee menuEmployee = new MenuEmployee();
        Scanner scan = new Scanner(System.in);
        System.out.print("""
                         
                         
                           [MENU]
                           1. Region
                           2. Country
                           3. Location
                           4. Department
                           5. Job
                           6. Employee
                           7. Log Out
                           Pilihan: """);
        int pilihan = scan.nextInt();
        switch (pilihan) {
            case 1 -> {
                try {
                    menuRegion.menu();
                } catch (Exception e) {
                    System.out.println(e);
                    menu();
                }
            }
            case 2 -> {
                try {
                    menuCountry.menu();
                } catch (Exception e) {
                    System.out.println(e);
                    menu();
                }
            }

            case 3 -> {
                try {
                    menuLocation.menu();
                } catch (Exception e) {
                    System.out.println(e);
                    menu();
                }
            }

            case 4 -> {
                try {
                    menuDepartment.menu();
                } catch (Exception e) {
                    System.out.println(e);
                    menu();
                }
            }

            case 5 -> {
                try {
                    menuJob.menu();
                } catch (Exception e) {
                    System.out.println(e);
                    menu();
                }
            }

            case 6 -> {
                try {
                    menuEmployee.menu();
                } catch (Exception e) {
                    System.out.println(e);
                    menu();
                }
            }

            case 7 ->
                System.out.println("See you next time :)");
            default -> {
                System.out.println("------------\n[ERROR]Pilihan Tidak Tersedia");
                menu();
            }
        }

    }
}
