package ui;

import api.HotelResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainMenu {
    HotelResource hotelResource = new HotelResource();
    Scanner scanner = HotelApplication.scanner;

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

    public void findAndReserveARoom () {
    }

    public void seeMyReservation () {
    }

    public void createAnAccount () {
        boolean validInput = false;
        int i = 1;
        String email = "";
        String emailRegex = "^(.+)@(.+).(.+)$";
        String firstName = "";
        String firstNameRegex = "^[a-zA-Z ,.'-]+$";
        String lastName = "";
        String lastNameRegex = "^[a-zA-Z,.'-]+$";
        Pattern pattern;
        while (!validInput) {
            try {
                switch (i) {
                    case 1:
                        System.out.println("Enter Email format: name@domain.com");
                        pattern = Pattern.compile(emailRegex);
                        if (scanner.hasNextLine()) {
                            email = scanner.nextLine();
                            if (!pattern.matcher(email).matches()) {
                                System.out.println("Error, invalid email, please try again\n");
                            } else {
                                i++;
                            }
                        }
                        break;
                    case 2:
                        System.out.println("First Name: ");
                        pattern = Pattern.compile(firstNameRegex);
                        if (scanner.hasNextLine()) {
                            firstName = scanner.nextLine();
                            if (!pattern.matcher(firstName).matches()) {
                                System.out.println("Error, invalid first name, please try again\n");
                            } else {
                                i++;
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Last Name: ");
                        pattern = Pattern.compile(lastNameRegex);
                        if (scanner.hasNextLine()) {
                            lastName = scanner.nextLine();
                            if (!pattern.matcher(lastName).matches()) {
                                System.out.println("Error, invalid last name, please try again\n");
                            } else {
                                validInput = true;
                            }
                        }
                        break;
                    default:
                        break;
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
