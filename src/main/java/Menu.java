import db.DBView;
import model.User;

import java.util.Scanner;

public class Menu {

    DBView dbView = new DBView();
    private Scanner scanner = new Scanner(System.in);
    private boolean isComplete = false;

    public void run() {
        showWelcomeMessage();
        while (!isComplete) {
            String s = scanner.nextLine();
            switch (s) {
                case "q":
                    isComplete = true;
                    break;
                case "start":
                    writeAllCommands();
                    break;
                case "1":
                    addNewUser();
                    break;
                case "2":
                    showAllUsers();
                    break;
                case "3":
                    showOlderUsers();
                    break;
                case "4":
                    deleteUserById();
                    break;
                case "5":
                    updateUserData();
                    break;
                case "6":
                    showUserInfoById();
                    break;
                case "7":
                    showUserInfoByFirstName();
                    break;
            }
        }
    }

    private void showAllUsers() {
        dbView.getAllUsers();
        System.out.println("\nChoose command which you want to execute: " +
                "\nWrite \"start\" to show list of commands");
    }

    private void addNewUser() {
        System.out.println("Please input the first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Please input the last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Please input the age: ");
        int age = scanner.nextInt();

        User user = new User(firstName, lastName, age);

        dbView.addUser(user);
        System.out.println("New user was successfully added.");

        System.out.println("\nChoose command which you want to execute: " +
                "\nWrite \"start\" to show list of commands");
    }

    private void updateUserData() {
        System.out.println("Please input id of user which data you want to update: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Incorrect input data. You should input digit value");
            scanner.nextLine();
        }
        int userId = scanner.nextInt();

        System.out.println("Please input new first  name of user which data you want to update: ");
        String fName = scanner.next();
        System.out.println("Please input new last name of user which data you want to update: ");
        String lName = scanner.next();
        System.out.println("Please input new age of user which data you want to update: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Incorrect input data. You should input digit value");
            scanner.nextLine();
        }
        int age = scanner.nextInt();

        User user = new User(fName, lName, age);

        dbView.updateUserDataById(userId, user);
        System.out.println("User" + userId + "was successfully updated.");

        System.out.println("\nChoose command which you want to execute: " +
                "\nWrite \"start\" to show list of commands");
    }

    private void deleteUserById() {
        System.out.println("Please input the id of user which you want to delete: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Incorrect input data. Please check your input value and try again");
            scanner.next();
        }
        int userId = scanner.nextInt();
        dbView.deleteUserById(userId);
        System.out.println("User with id " + userId + " was successfully deleted.");

        System.out.println("\nChoose command which you want to execute: " +
                "\nWrite \"start\" to show list of commands");
    }

    private void showUserInfoById() {
        System.out.println("Please input the id of user whose information you want to see: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Incorrect input data. Please check your input value and try again");
            scanner.next();
        }
        int userId = scanner.nextInt();
        if (dbView.getUserById(userId) != null) {
            System.out.println(dbView.getUserById(userId));
        } else {
            System.out.println("User doesn't exist!");
        }
        System.out.println("\nChoose command which you want to execute: " +
                "\nWrite \"start\" to show list of commands");
    }

    private void showUserInfoByFirstName() {
        System.out.println("Please input name of user whose information you want to see: ");

        String firstName = scanner.nextLine();
        if (dbView.getUserByFirstName(firstName) != null) {
            System.out.println(dbView.getUserByFirstName(firstName));
        } else {
            System.out.println("User doesn't exist!");
        }
        System.out.println("\nChoose command which you want to execute: " +
                "\nWrite \"start\" to show list of commands");
    }

    private void showOlderUsers() {
        System.out.println("Please input the age of users which you want to show: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Incorrect input data. You should input digit value.");
            scanner.next();
        }
        int age = scanner.nextInt();
        dbView.getOlderUsersThan(age);
        System.out.println("\nChoose command which you want to execute: " +
                "\nWrite \"start\" to show list of commands");
    }

    private void writeAllCommands() {
        System.out.println("\nChoose command which you want to execute:");
        System.out.println("1 - Add new user");
        System.out.println("2 - Show all users");
        System.out.println("3 - Show all older users");
        System.out.println("4 - Delete user");
        System.out.println("5 - Update user");
        System.out.println("6 - Find user by id");
        System.out.println("7 - Find user by firstName");
        System.out.println("\nPress \"q\" to exit.");
    }

    private void showWelcomeMessage() {
        System.out.println("Hi! This is user management application. You can use it for manage all your users.");
        writeAllCommands();
    }
}