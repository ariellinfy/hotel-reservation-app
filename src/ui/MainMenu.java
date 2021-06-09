package ui;

import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.util.*;
import java.util.regex.Pattern;

public class MainMenu {
    AdminMenu adminMenu = new AdminMenu();
    HotelResource hotelResource = new HotelResource();
    Scanner scanner = HotelApplication.scanner;

    public void mainMenu () {
        List<String> mainMenuList = new ArrayList<>();
        mainMenuList.add("\nWelcome to the Hotel Reservation Application");
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

    public void onMainMenu () {
        boolean exitProgram = false;
        while (!exitProgram) {
            mainMenu();
            int selection = Integer.parseInt(scanner.nextLine());
            try {
                switch (selection) {
                    case 1:
                        findAndReserveARoom();
                        break;
                    case 2:
                        seeMyReservation();
                        break;
                    case 3:
                        createAnAccount();
                        break;
                    case 4:
                        adminMenu.onAdminMenu();
                        break;
                    case 5:
                        System.out.println("\nThank you for using our booking service, see you next time!");
                        exitProgram = true;
                        break;
                    default:
                        System.out.println("\nError: option not existed, please enter a valid number\n");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("\nError: invalid input\n");
                System.out.println(ex.getLocalizedMessage());
            }
        }
        scanner.close();
    }

    public void findAndReserveARoom () {
        boolean backToMainMenu = false;
        int i = 1;
        Date checkInDate = null;
        Date checkOutDate = null;
        String dateRegex = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$";
        Calendar calendar = Calendar.getInstance();
        String confirmation;
        String confirmRegex = "^[y|n]$";
        String email = null;
        String emailRegex = "^(.+)@(.+).(.+)$";
        String roomNumber;
        String roomNumberRegex = "^\\d+$";
        Pattern pattern;
        while (!backToMainMenu) {
            try {
                switch (i) {
                    case 1:
                        System.out.println("Enter CheckIn Date yyyy-mm-dd example 2021-06-01");
                        pattern = Pattern.compile(dateRegex);
                        if (scanner.hasNextLine()) {
                            String checkIn = scanner.nextLine();
                            if (!pattern.matcher(checkIn).matches()) {
                                System.out.println("Error, invalid date input, please try again\n");
                            } else {
                                String[] subString = checkIn.split("-", 3);
                                calendar.set(Integer.parseInt(subString[0]), Integer.parseInt(subString[1])-1, Integer.parseInt(subString[2]), 0, 0, 0);
                                checkInDate = calendar.getTime();
                                i++;
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Enter CheckOut Date yyyy-mm-dd example 2021-06-15");
                        pattern = Pattern.compile(dateRegex);
                        if (scanner.hasNextLine()) {
                            String checkOut = scanner.nextLine();
                            if (!pattern.matcher(checkOut).matches()) {
                                System.out.println("Error, invalid date input, please try again\n");
                            } else {
                                String[] subString = checkOut.split("-", 3);
                                calendar.set(Integer.parseInt(subString[0]), Integer.parseInt(subString[1])-1, Integer.parseInt(subString[2]), 0, 0, 0);
                                checkOutDate = calendar.getTime();
                                Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);
                                for (IRoom room : availableRooms) {
                                    System.out.println(room);
                                }
                                i++;
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Would you like to book a room? y/n");
                        pattern = Pattern.compile(confirmRegex);
                        if (scanner.hasNextLine()) {
                            confirmation = scanner.nextLine().toLowerCase();
                            if (!pattern.matcher(confirmation).matches()) {
                                System.out.println("Please enter Y (Yes) or N (No)\n");
                            } else {
                                if (confirmation.equals("n")) {
                                    backToMainMenu = true;
                                } else {
                                    i++;
                                }
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Do you have an account with us? y/n");
                        pattern = Pattern.compile(confirmRegex);
                        if (scanner.hasNextLine()) {
                            confirmation = scanner.nextLine().toLowerCase();
                            if (!pattern.matcher(confirmation).matches()) {
                                System.out.println("Please enter Y (Yes) or N (No)\n");
                            } else {
                                if (confirmation.equals("n")) {
                                    email = createAnAccount();
                                } else {
                                    System.out.println("Enter Email format: name@domain.com");
                                    pattern = Pattern.compile(emailRegex);
                                    if (scanner.hasNextLine()) {
                                        email = scanner.nextLine();
                                        if (!pattern.matcher(email).matches()) {
                                            System.out.println("Error, invalid email, please try again\n");
                                        }
                                    }
                                }
                                i++;
                            }
                        }
                        break;
                    case 5:
                        System.out.println("What room number would you like to reserve?");
                        pattern = Pattern.compile(roomNumberRegex);
                        if (scanner.hasNextLine()) {
                            roomNumber = scanner.nextLine();
                            if (!pattern.matcher(roomNumber).matches()) {
                                System.out.println("Error, invalid input, please try again\n");
                            } else {
                                IRoom room = hotelResource.getRoom(roomNumber);
                                System.out.println(room);
                                if (room == null) {
                                    System.out.println("This room is not available, please try a different room\n");
                                } else {
                                    Reservation newReservation = hotelResource.bookARoom(email, room, checkInDate, checkOutDate);
                                    System.out.println(newReservation);
                                    backToMainMenu = true;
                                }
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
    }

    public void seeMyReservation () {
        System.out.println("Enter Email format: name@domain.com");
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (scanner.hasNextLine()) {
            String email = scanner.nextLine();
            if (!pattern.matcher(email).matches()) {
                System.out.println("Error, invalid email, please try again\n");
            } else {
                Collection<Reservation> myReservations = hotelResource.getCustomerReservations(email);
                for (Reservation reservation : myReservations) {
                    System.out.println(reservation);
                }
            }
        }
    }

    public String createAnAccount () {
        boolean validInput = false;
        int i = 1;
        String email = null;
        String emailRegex = "^(.+)@(.+).(.+)$";
        String firstName = null;
        String firstNameRegex = "^[a-zA-Z ,.'-]+$";
        String lastName = null;
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
        return email;
    }
}
