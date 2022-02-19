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
import jdbc.JobDb;
import tools.ConnectDb;
import models.Job;

/**
 *
 * @author rzkrn20
 */
public class MenuJob extends MenuAbstract {

    static ConnectDb connection = new ConnectDb();
    static MainMenu mainMenu = new MainMenu();
    static JobDb jobDb = new JobDb(connection.getConnection());
    static Scanner scan = new Scanner(System.in);
    static int pilihan;
    static String id, title, pause;
    static double min_salary, max_salary;

    @Override
    public void menu() {
        System.out.print("""
                         
                         
                           [JOB MENU]
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
                           
                              [JOB]
                           ---------------""");
        List<Job> sortedJob = jobDb.show().stream()
                .sorted(Comparator.comparing(Job::getId))
                .collect(Collectors.toList());

        sortedJob.forEach(System.out::println);

    }

    @Override
    public void menuInsert() {
        System.out.print("""
                         INSERT DATA
                         id: """);
        id = scan.nextLine();
        System.out.print("title: ");
        title = scan.nextLine();
        System.out.print("min_salary: ");
        min_salary = scan.nextDouble();
        scan.nextLine();
        System.out.print("max_salary: ");
        max_salary = scan.nextDouble();
        scan.nextLine();
        if (jobDb.checkIdName(id, title)) {
            Job job = new Job(id, title, min_salary, max_salary);
            if (jobDb.insert(job)) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }
        } else {
            System.out.println("ID/TITLE is already in the database.");
        }
    }

    @Override
    public void menuUpdate() {
        System.out.print("\n/*Please make sure that the Id is on the list.*/\nUPDATE DATA\nId: ");
        id = scan.nextLine();

        List<Job> jobById = jobDb.getById(id).stream()
                .collect(Collectors.toList());

        jobById.forEach(System.out::println);
        if (jobById.isEmpty()) {
            System.out.println("No data with that ID");
        } else {
            System.out.print("\ntitle: ");
            title = scan.nextLine();
            System.out.print("min_salary: ");
            min_salary = scan.nextDouble();
            scan.nextLine();
            System.out.print("max_salary: ");
            max_salary = scan.nextDouble();
            scan.nextLine();

            Job job = new Job(id, title, min_salary, max_salary);
            if (jobDb.checkIdName("", title)) {
                if (jobDb.update(job)) {
                    System.out.println("Data updated successfully.");
                } else {
                    System.out.println("Failed to update data.");
                }
            } else {
                System.out.println("ID is already in the database.");
            }
        }
    }

    @Override
    public void menuDelete() {
        System.out.print("\nDELETE DATA\nId: ");
        id = scan.nextLine();
        List<Job> jobById = jobDb.getById(id).stream()
                .collect(Collectors.toList());

        if (jobById.isEmpty()) {
            System.out.println("No data with that ID");
        } else {
            if (jobDb.delete(id)) {
                System.out.println("Data deleted successfully.");
            } else {
                System.out.println("Failed to delete data.");
            }
        }
    }
}
