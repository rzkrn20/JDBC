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
import jdbc.DepartmentDb;
import tools.ConnectDb;
import models.Department;

/**
 *
 * @author rzkrn20
 */
public class MenuDepartment extends MenuAbstract {

    static ConnectDb connection = new ConnectDb();
    static MainMenu mainMenu = new MainMenu();
    static DepartmentDb departmentDb = new DepartmentDb(connection.getConnection());
    static Scanner scan = new Scanner(System.in);
    static int pilihan, id, manager, location;
    static String name, pause;

    @Override
    public void menu() {
        System.out.print("""
                         
                         
                           [DEPARTMENT MENU]
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
                           
                              [DEPARTMENT]
                           ---------------""");
        List<Department> sortedDepartment = departmentDb.show().stream()
                .sorted(Comparator.comparingInt(Department::getId))
                .collect(Collectors.toList());

        sortedDepartment.forEach(System.out::println);

    }

    @Override
    public void menuInsert() {
        System.out.print("""
                         INSERT DATA
                         id: """);
        id = scan.nextInt();
        scan.nextLine();
        System.out.print("name: ");
        name = scan.nextLine();
        System.out.print("manager: ");
        manager = scan.nextInt();
        scan.nextLine();
        System.out.print("location: ");
        location = scan.nextInt();
        scan.nextLine();
        if (departmentDb.checkIdName(id, name)) {
            if (departmentDb.checkManager(manager)) {
                if (departmentDb.checkLocation(location)) {
                    Department department = new Department(id, name, manager, location);
                    if (departmentDb.insert(department)) {
                        System.out.println("Data inserted successfully.");
                    } else {
                        System.out.println("Failed to insert data.");
                    }
                } else {
                    System.out.println("There's no LOCATION with that id");
                }
            } else {
                System.out.println("There's no EMPLOYEE with that id");
            }
        } else {
            System.out.println("ID/NAME is already in the database.");
        }
    }

    @Override
    public void menuUpdate() {
        System.out.print("\n/*Please make sure that the Id is on the list.*/\nUPDATE DATA\nId: ");
        id = scan.nextInt();
        scan.nextLine();

        List<Department> departmentById = departmentDb.getById(id).stream()
                .collect(Collectors.toList());

        departmentById.forEach(System.out::println);
        if (departmentById.isEmpty()) {
            System.out.println("No data with that ID");
        } else {
            System.out.print("\nname: ");
            name = scan.nextLine();
            System.out.print("manager: ");
            manager = scan.nextInt();
            scan.nextLine();
            System.out.print("location: ");
            location = scan.nextInt();
            scan.nextLine();

            Department department = new Department(id, name, manager, location);
            if (departmentDb.checkIdName(0, name)) {
                if (departmentDb.checkManager(manager)) {
                    if (departmentDb.checkLocation(location)) {
                        if (departmentDb.update(department)) {
                            System.out.println("Data updated successfully.");
                        } else {
                            System.out.println("Failed to update data.");
                        }
                    } else {
                        System.out.println("There's no LOCATION with that id");
                    }
                } else {
                    System.out.println("There's no EMPLOYEE with that id");
                }
            } else {
                System.out.println("ID/NAME is already in the database.");
            }
        }
    }

    @Override
    public void menuDelete() {
        System.out.print("\nDELETE DATA\nId: ");
        id = scan.nextInt();
        List<Department> departmentById = departmentDb.getById(id).stream()
                .collect(Collectors.toList());

        if (departmentById.isEmpty()) {
            System.out.println("No data with that ID");
        } else {
            if (departmentDb.delete(id)) {
                System.out.println("Data deleted successfully.");
            } else {
                System.out.println("Failed to delete data.");
            }
        }
    }
}
