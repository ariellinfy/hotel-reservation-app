package ui;

import api.HotelResource;

import java.util.Scanner;

public class HotelApplication {

    public static void main(String[] args) {
        onMainMenu();
    }

    public static void onMainMenu () {
        boolean onMainMenu = true;
        int selection = 0;
        Scanner mainMenuScanner = new Scanner(System.in);
        while (onMainMenu) {
            MainMenu.mainMenu();
            selection = Integer.parseInt(mainMenuScanner.nextLine());
            try {
                switch (selection) {
                    case 1:
                        onMainMenu = false;
                    case 2:
                        onMainMenu = false;
                    case 3:
                        onMainMenu = false;
                        MainMenu.createAnAccount();
                        break;
                    case 4:
                        onMainMenu = false;
                        AdminMenu.adminMenu();
                        break;
                    case 5:
                        System.out.println("\nThank you for using our booking service, see you next time!");
                        onMainMenu = false;
                        break;
                    default:
                        System.out.println("\nError: option not existed, please enter a valid number\n");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("\nError: invalid input\n");
                System.out.println(ex.getLocalizedMessage());
                System.out.println(ex);
            }
        }
        mainMenuScanner.close();
    }
}
