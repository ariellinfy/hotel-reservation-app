package ui;

import api.HotelResource;
import model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainMenu {
    public static void mainMenu () {
        List<String> mainMenuList = new ArrayList<String>();
        mainMenuList.add("Welcome to the Hotel Reservation Application");
        mainMenuList.add("1. Find and reserve a room");
        mainMenuList.add("2. See my reservations");
        mainMenuList.add("3. Create an account");
        mainMenuList.add("4. Admin");
        mainMenuList.add("5. Exit");
        mainMenuList.add("Please select a number for the menu option: ");

        for (String menuItem : mainMenuList) {
            System.out.println(menuItem);
        }
    }

    public static void createAnAccount () {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter Email format: name@domain.com");
            String email = scanner.nextLine();
            String emailRegex = "^(.+)@(.+).(.+)$";
            Pattern pattern = Pattern.compile(emailRegex);
            if (!pattern.matcher(email).matches()) {
                throw new IllegalArgumentException("Error, invalid email");
            }
            System.out.println("First Name: ");
            String firstName = scanner.nextLine();
            System.out.println("Last Name: ");
            String lastName = scanner.nextLine();
            HotelResource.createACustomer(email, firstName, lastName);
            System.out.println("New account has been created: ");
            System.out.println(HotelResource.getCustomer(email));
        } catch (Exception ex) {
            ex.getLocalizedMessage();
        } finally {
            scanner.close();
        }
    }
}
