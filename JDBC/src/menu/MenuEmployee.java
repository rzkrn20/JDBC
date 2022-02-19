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
import jdbc.EmployeeDb;
import tools.ConnectDb;
import models.Employee;

/**
 *
 * @author rzkrn20
 */
public class MenuEmployee extends MenuAbstract {

    static ConnectDb connection = new ConnectDb();
    static MainMenu mainMenu = new MainMenu();
    static EmployeeDb employeeDb = new EmployeeDb(connection.getConnection());
    static Scanner scan = new Scanner(System.in);
    static int pilihan, id, manager, department;
    static double salary, comission_pct;
    static String first_name, last_name, email, phone_number, job, dateTemp = "00-00-0000", pause;
    static String hire_date;

    @Override
    public void menu() {
        System.out.print("""
                         
                         
                           [EMPLOYEE MENU]
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
                    menuShow();
                    menuInsert();

                } catch (Exception e) {
                    System.out.println(e);
                }
                menu();
            }
            case 3 -> {
                try {
                    menuShow();
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
                           
                              [EMPLOYEE]
                           ---------------""");
        List<Employee> sortedEmployee = employeeDb.show().stream()
                .sorted(Comparator.comparingInt(Employee::getId))
                .collect(Collectors.toList());

        sortedEmployee.forEach(System.out::println);

    }

    @Override
    public void menuInsert() {
        System.out.print("""
                         INSERT DATA
                         id: """);
        id = scan.nextInt();
        scan.nextLine();
        System.out.print("first_name: ");
        first_name = scan.nextLine();
        System.out.print("last_name: ");
        last_name = scan.nextLine();
        System.out.print("email: ");
        email = scan.nextLine();
        System.out.print("phone_number: ");
        phone_number = scan.nextLine();
        System.out.print("hire_date: ");
        hire_date = scan.nextLine();
        System.out.print("job: ");
        job = scan.nextLine();
        System.out.print("salary: ");
        salary = scan.nextDouble();
        System.out.print("comission_pct: ");
        comission_pct = scan.nextDouble();
        System.out.print("manager: ");
        manager = scan.nextInt();
        System.out.print("department: ");
        department = scan.nextInt();
        scan.nextLine();
        if (employeeDb.checkIdName(id, email, phone_number)) {
            if (employeeDb.checkJob(job)) {
                if (employeeDb.checkManager(manager)) {
                    if (employeeDb.checkDepartment(department)) {
                        Employee employee = new Employee(id, first_name, last_name, email, phone_number, hire_date,
                                job, salary, comission_pct, manager, department);
                        if (employeeDb.insert(employee)) {
                            System.out.println("Data inserted successfully.");
                        } else {
                            System.out.println("Failed to insert data.");
                        }
                    } else {
                        System.out.println("There's no DEPARTMENT with that id");
                    }
                } else {
                    System.out.println("There's no MANAGER with that id");
                }
            } else {
                System.out.println("There's no JOB with that id");
            }
        } else {
            System.out.println("ID/EMAIL/PHONE NUMBER is already in the database.");
        }
    }

    @Override
    public void menuUpdate() {
        System.out.print("\n/*Please make sure that the Id is on the list.*/\nUPDATE DATA\nId: ");
        id = scan.nextInt();
        scan.nextLine();

        List<Employee> employeeById = employeeDb.getById(id).stream()
                .collect(Collectors.toList());

        employeeById.forEach(System.out::println);
        if (employeeById.isEmpty()) {
            System.out.println("No data with that ID");
        } else {
            System.out.print("\nfirst_name: ");
            first_name = scan.nextLine();
            System.out.print("last_name: ");
            last_name = scan.nextLine();
            System.out.print("email: ");
            email = scan.nextLine();
            System.out.print("phone_number: ");
            phone_number = scan.nextLine();
            System.out.print("hire_date: ");
            hire_date = scan.nextLine();
            System.out.print("job: ");
            job = scan.nextLine();
            System.out.print("salary: ");
            salary = scan.nextDouble();
            System.out.print("comission_pct: ");
            comission_pct = scan.nextDouble();
            System.out.print("manager: ");
            manager = scan.nextInt();
            System.out.print("department: ");
            department = scan.nextInt();
            scan.nextLine();

            Employee employee = new Employee(id, first_name, last_name, email, phone_number, hire_date,
                    job, salary, comission_pct, manager, department);
            if (employeeDb.checkIdName(0, email, phone_number)) {
                if (employeeDb.checkJob(job)) {
                    if (employeeDb.checkManager(manager)) {
                        if (employeeDb.checkDepartment(department)) {
                            if (employeeDb.update(employee)) {
                                System.out.println("Data updated successfully.");
                            } else {
                                System.out.println("Failed to update data.");
                            }
                        } else {
                            System.out.println("There's no DEPARTMENT with that id");
                        }
                    } else {
                        System.out.println("There's no MANAGER with that id");
                    }
                } else {
                    System.out.println("There's no JOB with that id");
                }
            } else {
                System.out.println("ID/EMAIL/PHONE NUMBER is already in the database.");
            }
        }
    }

    @Override
    public void menuDelete() {
        System.out.print("\nDELETE DATA\nId: ");
        id = scan.nextInt();
        List<Employee> employeeById = employeeDb.getById(id).stream()
                .collect(Collectors.toList());

        if (employeeById.isEmpty()) {
            System.out.println("No data with that ID");
        } else {
            if (employeeDb.delete(id)) {
                System.out.println("Data deleted successfully.");
            } else {
                System.out.println("Failed to delete data.");
            }
        }
    }
}
