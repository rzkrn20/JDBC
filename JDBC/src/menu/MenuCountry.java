/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import tools.MenuAbstract;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import jdbc.CountryDb;
import tools.ConnectDb;
import models.Country;

/**
 *
 * @author rzkrn20
 */
public class MenuCountry extends MenuAbstract {

    static ConnectDb connection = new ConnectDb();
    static MainMenu mainMenu = new MainMenu();
    static CountryDb countryDb = new CountryDb(connection.getConnection());
    static Scanner scan = new Scanner(System.in);
    static int pilihan, region;
    static String id, name, pause;

    @Override
    public void menu() {
        System.out.print("""
                         
                         
                           [COUNTRY MENU]
                           1. Show
                           2. Insert
                           3. Update
                           4. Delete
                           5. HOME
                           Pilihan: """);
        pilihan = scan.nextInt();
        scan.nextLine();
        switch (pilihan) {
            case 1 -> {
                try {
                    menuShow();
                    System.out.println("\nInsert any String after you've DONE reading.");
                    pause = scan.next();
                } catch (Exception e) {
                    System.out.println(e);
                }
                menu();
            }
            case 2 -> {
                try {
                    menuInsert();
                } catch (Exception e) {
                    System.out.println(e);
                }
                menu();
            }
            case 3 -> {
                try {
                    menuUpdate();
                } catch (Exception e) {
                    System.out.println(e);
                }
                menu();
            }
            case 4 -> {
                try {
                    menuShow();
                    menuDelete();

                } catch (Exception e) {
                    System.out.println(e);
                }
                menu();
            }
            case 5 -> {
                mainMenu.menu();
            }
            default -> {
                System.out.println("------------\n[ERROR]Pilihan Tidak Tersedia");
                menu();
            }
        }
    }

    @Override
    public void menuShow() {
        System.out.println("""
                           
                              [COUNTRY]
                           ---------------""");
        List<Country> sortedCountry = countryDb.show().stream()
                .sorted(Comparator.comparing(Country::getId))
                .collect(Collectors.toList());

        sortedCountry.forEach(System.out::println);

    }

    @Override
    public void menuInsert() {
        System.out.print("""
                         INSERT DATA
                         Id: """);
        id = scan.nextLine();
        System.out.print("Name: ");
        name = scan.nextLine();
        System.out.print("Region: ");
        region = scan.nextInt();
        scan.nextLine();
        if (countryDb.checkIdName(id, name)) {
            if (countryDb.checkRegion(region)) {
                Country country = new Country(id, name, region);
                if (countryDb.insert(country)) {
                    System.out.println("Data inserted successfully.");
                } else {
                    System.out.println("Failed to insert data.");
                }
            } else {
                System.out.println("There's no REGION with that id");
            }
        } else {
            System.out.println("NAME/ID is already in the database.");
        }
    }

    @Override
    public void menuUpdate() {
        System.out.print("\n/*Please make sure that the Id is on the list.*/\nUPDATE DATA\nId: ");
        id = scan.nextLine();

        List<Country> countryById = countryDb.getById(id).stream()
                .collect(Collectors.toList());

        countryById.forEach(System.out::println);
        if (countryById.isEmpty()) {
            System.out.println("No data with that ID");
        } else {
            System.out.print("\nNama: ");
            name = scan.nextLine();
            System.out.print("Region: ");
            region = scan.nextInt();
            scan.nextLine();
            Country country = new Country(id, name, region);
            if (countryDb.checkIdName("", name)) {
                if (countryDb.checkRegion(region)) {
                    if (countryDb.update(country)) {
                        System.out.println("Data updated successfully.");
                    } else {
                        System.out.println("Failed to update data.");
                    }
                } else {
                    System.out.println("There's no REGION with that id");
                }
            } else {
                System.out.println("NAME is already in the database.");
            }
        }
    }

    @Override
    public void menuDelete() {
        System.out.print("\nDELETE DATA\nId: ");
        id = scan.nextLine();
        
        List<Country> countryById = countryDb.getById(id).stream()
                .collect(Collectors.toList());
        
        if (countryById.isEmpty()) {
            System.out.println("No data with that ID");
        } else {
            region = countryDb.getId(id);
            if (countryDb.delete(id)) {
                System.out.println("Data deleted successfully.");
                countryDb.countUpdate(region);
            } else {
                System.out.println("Failed to delete data.");
            }
        }
    }
}
