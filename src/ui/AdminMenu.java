package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    Scanner scanner = HotelApplication.scanner;

    public void adminMenu () {
        List<String> adminMenuList = new ArrayList<String>();
        adminMenuList.add("Admin Menu");
        adminMenuList.add("1. See all Customers");
        adminMenuList.add("2. See all Rooms");
        adminMenuList.add("3. See all Reservations");
        adminMenuList.add("4. Add a Room");
        adminMenuList.add("5. Add test data");
        adminMenuList.add("6. Back to Main Menu");
        adminMenuList.add("Please select a number for the menu option: ");

        for (String menuItem : adminMenuList) {
            System.out.println(menuItem);
        }

        onAdminMenu();
    }

    public void onAdminMenu () {
        boolean onAdminMenu = true;
        while (onAdminMenu) {
            adminMenu();
            int selection = Integer.parseInt(scanner.nextLine());
            try {
                switch (selection) {
                    case 1:
                        seeAllCustomers();
                        break;
                    case 2:
                        seeAllRooms();
                        break;
                    case 3:
                        seeAllReservations();
                        break;
                    case 4:
                        addARoom();
                        break;
                    case 5:
                        addTestData();
                        break;
                    case 6:
                        onAdminMenu = false;
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
    }

    public void seeAllCustomers () {
    }

    public void seeAllRooms () {
    }

    public void seeAllReservations () {
    }

    public void addARoom () {
    }

    public void addTestData () {
    }
}
