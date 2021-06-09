package ui;

import api.HotelResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainMenu {
    HotelResource hotelResource = new HotelResource();

    public void mainMenu () {
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

    public void createAnAccount (Scanner scanner) {
        boolean validEmail = false;
        boolean validFirstName = false;
        boolean validLastName = false;
        String email = "";
        String firstName = "";
        String lastName = "";
        while (!validEmail) {
            System.out.println("Enter Email format: name@domain.com");
            try {
                String emailRegex = "^(.+)@(.+).(.+)$";
                Pattern pattern = Pattern.compile(emailRegex);
                email = scanner.nextLine();
                if (!pattern.matcher(email).matches()) {
                    System.out.println("Error, invalid email, please try again\n");
                } else {
                    validEmail = true;
                }
            } catch (Exception ex) {
                ex.getLocalizedMessage();
            }
        }
        while (!validFirstName) {
            System.out.println("First Name: ");
            firstName = scanner.nextLine();
            try {
                String firstNameRegex = "^[a-zA-Z ,.'-]+$";
                Pattern pattern = Pattern.compile(firstNameRegex);
                if (!pattern.matcher(firstName).matches()) {
                    System.out.println("Error, invalid first name, please try again\n");
                } else {
                    validFirstName = true;
                }
            } catch (Exception ex) {
                ex.getLocalizedMessage();
            }
        }
        while (!validLastName) {
            System.out.println("Last Name: ");
            lastName = scanner.nextLine();
            try {
                String lastNameRegex = "^[a-zA-Z,.'-]+$";
                Pattern pattern = Pattern.compile(lastNameRegex);
                if (!pattern.matcher(lastName).matches()) {
                    System.out.println("Error, invalid last name, please try again\n");
                } else {
                    validLastName = true;
                }
            } catch (Exception ex) {
                ex.getLocalizedMessage();
            }
        }
        hotelResource.createACustomer(email, firstName, lastName);
        System.out.println("\nNew account has been created: ");
        System.out.println(hotelResource.getCustomer(email) + "\n");
    }
}
