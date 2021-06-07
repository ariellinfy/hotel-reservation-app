package ui;

import api.HotelResource;

import java.util.Scanner;

public class HotelApplication {

    public static void main(String[] args) {
        boolean programActive = true;
        Scanner scanner = new Scanner(System.in);
        MainMenu.mainMenu();
        int selection = Integer.parseInt(scanner.nextLine());
        while (programActive) {
            try {
                switch (selection) {
                    case 1:
                    case 2:
                    case 3:
                        MainMenu.createAnAccount();
                        MainMenu.mainMenu();
                        break;
                    case 4:
                        AdminMenu.adminMenu();
                        break;
                    case 5:
                        System.out.println("\nThank you for using our booking service, see you next time!");
                        programActive = false;
                        break;
                    default:
                        System.out.println("\nError: option not existed, please enter a valid number\n");
                        MainMenu.mainMenu();
                        break;
                }
            } catch (Exception ex) {
                System.out.println("\nError: invalid input\n");
                MainMenu.mainMenu();
            } finally {
                scanner.close();
            }
        }
    }
}
