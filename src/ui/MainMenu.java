package ui;

import api.HotelResource;
import model.*;

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
            try {
                int selection = Integer.parseInt(scanner.nextLine());
                switch (selection) {
                    case 1:
                        findAndReserveARoom();
                        break;
                    case 2:
                        seeMyReservation();
                        break;
                    case 3:
                        createAnAccount(1, null);
                        break;
                    case 4:
                        adminMenu.onAdminMenu();
                        break;
                    case 5:
                        System.out.println("\nThank you for using our booking service, see you next time!");
                        exitProgram = true;
                        break;
                    default:
                        System.out.println("\nError: option not existed, please enter a valid number.");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("\nError: invalid input.");
            }
        }
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
        String numberRegex = "^\\d+$";
        RoomPrice priceType = null;
        String priceTypeRegex = "^[f|p|a]$";
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
                                System.out.println("Error, invalid date input, please try again.\n");
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
                                System.out.println("Error, invalid date input, please try again.\n");
                            } else {
                                String[] subString = checkOut.split("-", 3);
                                calendar.set(Integer.parseInt(subString[0]), Integer.parseInt(subString[1])-1, Integer.parseInt(subString[2]), 0, 0, 0);
                                checkOutDate = calendar.getTime();
                                if (checkOutDate.getTime() <= checkInDate.getTime()) {
                                    System.out.println("Error, CheckOut date must be greater than CheckIn date, please try again.\n");
                                } else {
                                    i++;
                                }
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Filter room price by entering: F for free rooms, P for paid rooms, A for all rooms");
                        pattern = Pattern.compile(priceTypeRegex);
                        if (scanner.hasNextLine()) {
                            String option = scanner.nextLine().toLowerCase();
                            if (!pattern.matcher(option).matches()) {
                                System.out.println("Error, invalid input, please try again.\n");
                            } else {
                                if (option.equals("f")) {
                                    priceType = RoomPrice.FREE;
                                } else if (option.equals("p")) {
                                    priceType = RoomPrice.PAID;
                                } else if (option.equals("a")) {
                                    priceType = RoomPrice.BOTH;
                                }
                                List<Room> availableRooms = hotelResource.freeOrPaidRooms(checkInDate, checkOutDate, priceType);
                                if (availableRooms.size() > 0) {
                                    for (IRoom room : availableRooms) {
                                        System.out.println(room);
                                    }
                                    i = 5;
                                } else {
                                    i = 4;
                                }
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Rooms aren't available for your preferred dates, we would now search the recommended rooms on alternatives date.");
                        System.out.println("Tell us how many days out the room recommendation should search? Example enter 7 for adding 7 days to your checkin and chechout dates.");
                        pattern = Pattern.compile(numberRegex);
                        if (scanner.hasNextLine()) {
                            String addition = scanner.nextLine();
                            if (!pattern.matcher(addition).matches()) {
                                System.out.println("Error, invalid input, please try again.\n");
                            } else {
                                calendar.setTime(checkInDate);
                                calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(addition));
                                checkInDate = calendar.getTime();
                                calendar.setTime(checkOutDate);
                                calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(addition));
                                checkOutDate = calendar.getTime();
                                List<Room> availableRooms = hotelResource.freeOrPaidRooms(checkInDate, checkOutDate, priceType);
                                if (availableRooms.size() > 0) {
                                    for (IRoom room : availableRooms) {
                                        System.out.println(room);
                                    }
                                    i++;
                                } else {
                                    System.out.println("Sorry, no rooms are available at this time, please try different set of dates or come back another time as there may have more rooms opened for booking, thank you for your interest and hope to see you back.");
                                    backToMainMenu = true;
                                }
                            }
                        }
                        break;
                    case 5:
                        System.out.println("\nWould you like to book a room? y/n");
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
                    case 6:
                        System.out.println("Do you have an account with us? y/n");
                        pattern = Pattern.compile(confirmRegex);
                        if (scanner.hasNextLine()) {
                            confirmation = scanner.nextLine().toLowerCase();
                            if (!pattern.matcher(confirmation).matches()) {
                                System.out.println("Please enter Y (Yes) or N (No)\n");
                            } else {
                                if (confirmation.equals("n")) {
                                    email = createAnAccount(1, null);
                                } else {
                                    System.out.println("Enter Email format: name@domain.com");
                                    pattern = Pattern.compile(emailRegex);
                                    if (scanner.hasNextLine()) {
                                        email = scanner.nextLine();
                                        if (!pattern.matcher(email).matches()) {
                                            System.out.println("Error, invalid email, please try again.\n");
                                        } else {
                                            if (hotelResource.getCustomer(email) == null) {
                                                System.out.println("Error, account not existed, let's create an account.\n");
                                                createAnAccount(2, email);
                                            }
                                        }
                                    }
                                }
                                i++;
                            }
                        }
                        break;
                    case 7:
                        System.out.println("What room number would you like to reserve?");
                        pattern = Pattern.compile(numberRegex);
                        if (scanner.hasNextLine()) {
                            roomNumber = scanner.nextLine();
                            if (!pattern.matcher(roomNumber).matches()) {
                                System.out.println("Error, invalid input, please try again.\n");
                            } else {
                                IRoom room = hotelResource.getRoom(roomNumber);
                                if (room == null) {
                                    System.out.println("This room is not available, please try a different room.\n");
                                } else {
                                    Reservation newReservation = hotelResource.bookARoom(email, room, checkInDate, checkOutDate);
                                    if (newReservation == null) {
                                        System.out.println("This room is not available, please try a different room.\n");
                                    } else {
                                        System.out.println(newReservation);
                                        backToMainMenu = true;
                                    }
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
        boolean backToMainMenu = false;
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        while (!backToMainMenu) {
            System.out.println("Enter Email format: name@domain.com");
            if (scanner.hasNextLine()) {
                String email = scanner.nextLine();
                if (!pattern.matcher(email).matches()) {
                    System.out.println("Error, invalid email, please try again.\n");
                } else {
                    if (hotelResource.getCustomer(email) == null) {
                        System.out.println("Error, account not existed, please create an account to use the booking service.");
                    } else {
                        Collection<Reservation> myReservations = hotelResource.getCustomerReservations(email);
                        if (myReservations.size() > 0) {
                            for (Reservation reservation : myReservations) {
                                System.out.println(reservation);
                            }
                        } else if (myReservations.size() == 0) {
                            System.out.println("No reservations.");
                        }
                    }
                    backToMainMenu = true;
                }
            }
        }
    }

    public String createAnAccount (int i, String email) {
        boolean validInput = false;
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
                                System.out.println("Error, invalid email, please try again.\n");
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
                                System.out.println("Error, invalid first name, please try again.\n");
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
                                System.out.println("Error, invalid last name, please try again.\n");
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
        System.out.println("\nYour account: ");
        System.out.println(hotelResource.getCustomer(email) + "\n");
        return email;
    }
}
