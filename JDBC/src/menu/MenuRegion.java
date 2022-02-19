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
import jdbc.RegionDb;
import tools.ConnectDb;
import models.Region;

/**
 *
 * @author rzkrn20
 */
public class MenuRegion extends MenuAbstract {

    static ConnectDb connection = new ConnectDb();
    static MainMenu mainMenu = new MainMenu();
    static RegionDb regionDb = new RegionDb(connection.getConnection());
    static Scanner scan = new Scanner(System.in);
    static int pilihan, id, idFix;
    static String name, pause;

    @Override
    public void menu() {
        System.out.print("""
                         
                         
                           [REGION MENU]
                           1. Show
                           2. Insert
                           3. Update
                           4. Delete
                           5. HOME
                           Pilihan: """);
        pilihan = scan.nextInt();
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
                           
                              [REGION]
                           ---------------""");
        List<Region> sortedRegion = regionDb.show().stream()
                .sorted(Comparator.comparingInt(Region::getId))
                .collect(Collectors.toList());

        sortedRegion.forEach(System.out::println);

    }

    @Override
    public void menuInsert() {
        System.out.print("""
                         INSERT DATA
                         /*If you want the id to be auto-generated, please type 0 as the id.*/
                         Id: """);
        id = scan.nextInt();
        scan.nextLine();
        System.out.print("Name: ");
        name = scan.nextLine();
        if (regionDb.checkIdName(id, name)) {
            if (id == 0) {
                int idReg = regionDb.getMaxId();
                idFix = idReg + 1;
            } else {
                idFix = id;
            }
            Region region = new Region(idFix, name, 0);
            if (regionDb.insert(region)) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }
        } else {
            System.out.println("NAME/ID is already in the database.");
        }
    }

    @Override
    public void menuUpdate() {
        System.out.print("\n/*Please make sure that the Id is on the list.*/\nUPDATE DATA\nId: ");
        id = scan.nextInt();
        scan.nextLine();

        List<Region> regionById = regionDb.getById(id).stream()
                .collect(Collectors.toList());

        regionById.forEach(System.out::println);
        if (regionById.isEmpty()) {
            System.out.println("No data with that ID");
        } else {
            System.out.print("\nNama: ");
            name = scan.nextLine();
            Region region = new Region(id, name, 0);
            if (regionDb.checkIdName(0, name)) {
                if (regionDb.update(region)) {
                    System.out.println("Data updated successfully.");
                } else {
                    System.out.println("Failed to update data.");
                }
            } else {
                System.out.println("NAME is already in the database.");
            }
        }
    }

    @Override
    public void menuDelete() {
        System.out.print("\nDELETE DATA\nId: ");
        id = scan.nextInt();

        List<Region> regionById = regionDb.getById(id).stream()
                .collect(Collectors.toList());

        if (regionById.isEmpty()) {
            System.out.println("No data with that ID");
        } else {
            if (regionDb.delete(id)) {
                System.out.println("Data deleted successfully.");
            } else {
                System.out.println("Failed to delete data.");
            }
        }
    }
}
