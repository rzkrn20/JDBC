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
import jdbc.LocationDb;
import tools.ConnectDb;
import models.Location;

/**
 *
 * @author rzkrn20
 */
public class MenuLocation extends MenuAbstract {

    static ConnectDb connection = new ConnectDb();
    static MainMenu mainMenu = new MainMenu();
    static LocationDb locationDb = new LocationDb(connection.getConnection());
    static Scanner scan = new Scanner(System.in);
    static int pilihan, id;
    static String street_address, postal_code, city, state_province, country, pause;

    @Override
    public void menu() {
        System.out.print("""
                         
                         
                           [LOCATION MENU]
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
                           
                              [LOCATION]
                           ---------------""");
        List<Location> sortedLocation = locationDb.show().stream()
                .sorted(Comparator.comparing(Location::getId))
                .collect(Collectors.toList());

        sortedLocation.forEach(System.out::println);

    }

    @Override
    public void menuInsert() {
        System.out.print("""
                         INSERT DATA
                         id: """);
        id = scan.nextInt();
        scan.nextLine();
        System.out.print("street_address: ");
        street_address = scan.nextLine();
        System.out.print("postal_code: ");
        postal_code = scan.nextLine();
        System.out.print("city: ");
        city = scan.nextLine();
        System.out.print("state_province: ");
        state_province = scan.nextLine();
        System.out.print("country: ");
        country = scan.nextLine();
        if (locationDb.checkIdName(id)) {
            if (locationDb.checkCountry(country)) {
                Location location = new Location(id, street_address, postal_code, city, state_province, country);
                if (locationDb.insert(location)) {
                    System.out.println("Data inserted successfully.");
                } else {
                    System.out.println("Failed to insert data.");
                }
            } else {
                System.out.println("There's no COUNTRY with that id");
            }
        } else {
            System.out.println("ID is already in the database.");
        }
    }

    @Override
    public void menuUpdate() {
        System.out.print("\n/*Please make sure that the Id is on the list.*/\nUPDATE DATA\nId: ");
        id = scan.nextInt();
        scan.nextLine();

        List<Location> locationById = locationDb.getById(id).stream()
                .collect(Collectors.toList());

        locationById.forEach(System.out::println);
        if (locationById.isEmpty()) {
            System.out.println("No data with that ID");
        } else {
            System.out.print("\nstreet_address: ");
            street_address = scan.nextLine();
            System.out.print("postal_code: ");
            postal_code = scan.nextLine();
            System.out.print("city: ");
            city = scan.nextLine();
            System.out.print("state_province: ");
            state_province = scan.nextLine();
            System.out.print("country: ");
            country = scan.nextLine();

            Location location = new Location(id, street_address, postal_code, city, state_province, country);

            if (locationDb.checkCountry(country)) {
                if (locationDb.update(location)) {
                    System.out.println("Data updated successfully.");
                } else {
                    System.out.println("Failed to update data.");
                }
            } else {
                System.out.println("There's no COUNTRY with that id");
            }
        }
    }

    @Override
    public void menuDelete() {
        System.out.print("\nDELETE DATA\nId: ");
        id = scan.nextInt();
        List<Location> locationById = locationDb.getById(id).stream()
                .collect(Collectors.toList());

        if (locationById.isEmpty()) {
            System.out.println("No data with that ID");
        } else {
            if (locationDb.delete(id)) {
                System.out.println("Data deleted successfully.");
            } else {
                System.out.println("Failed to delete data.");
            }
        }
    }
}
