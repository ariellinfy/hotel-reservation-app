package ui;

import api.AdminResource;
import model.*;

import java.util.*;
import java.util.regex.Pattern;

public class AdminMenu {
    AdminResource adminResource = new AdminResource();
    Scanner scanner = HotelApplication.scanner;

    public void adminMenu () {
        List<String> adminMenuList = new ArrayList<>();
        adminMenuList.add("\nAdmin Menu");
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
    }

    public void onAdminMenu () {
        boolean backToMainMenu = false;
        while (!backToMainMenu) {
            adminMenu();
            try {
                int selection = Integer.parseInt(scanner.nextLine());
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
                        backToMainMenu = true;
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

    public void seeAllCustomers () {
        Collection<Customer> allCustomers = adminResource.getAllCustomers();
        if (allCustomers.size() > 0) {
            for (Customer customer : allCustomers) {
                System.out.println(customer);
            }
        } else if (allCustomers.size() == 0) {
            System.out.println("No customers.");
        }
    }

    public void seeAllRooms () {
        List<Room> allRooms = adminResource.getAllRooms();
        if (allRooms.size() > 0) {
            for (Room room : allRooms) {
                System.out.println(room);
            }
        } else if (allRooms.size() == 0) {
            System.out.println("No rooms.");
        }
    }

    public void seeAllReservations () {
        adminResource.displayAllReservations();
    }

    public void addARoom () {
        boolean backToAdminMenu = false;
        int i = 1;
        String roomNumber = null;
        Double price = null;
        String numberRegex = "^\\d+$";
        RoomType roomType = null;
        String roomTypeRegex = "^[1|2]$";
        String confirmation;
        String confirmRegex = "^[y|n]$";
        Pattern pattern;
        while (!backToAdminMenu) {
            try {
                switch (i) {
                    case 1:
                        System.out.println("Enter Room Number: ");
                        pattern = Pattern.compile(numberRegex);
                        if (scanner.hasNextLine()) {
                            roomNumber = scanner.nextLine();
                            if (!pattern.matcher(roomNumber).matches()) {
                                System.out.println("Error, invalid room number, please try again.\n");
                            } else {
                                i++;
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Enter Price per night: ");
                        pattern = Pattern.compile(numberRegex);
                        if (scanner.hasNextLine()) {
                            String priceInput = scanner.nextLine();
                            if (!pattern.matcher(priceInput).matches()) {
                                System.out.println("Error, invalid price, please try again.\n");
                            } else {
                                price = Double.parseDouble(priceInput);
                                i++;
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Enter Room Type: 1 for single bed, 2 for double bed");
                        pattern = Pattern.compile(roomTypeRegex);
                        if (scanner.hasNextLine()) {
                            String roomTypeInput = scanner.nextLine();
                            if (!pattern.matcher(roomTypeInput).matches()) {
                                System.out.println("Error, invalid room type, please try again.\n");
                            } else {
                                if (roomTypeInput.equals("1")) {
                                    roomType = RoomType.SINGLE;
                                } else if (roomTypeInput.equals("2")) {
                                    roomType = RoomType.DOUBLE;
                                }
                                Room newRoom;
                                if (price == 0.0) {
                                    newRoom = new FreeRoom(roomNumber, price, roomType);
                                } else {
                                    newRoom = new Room(roomNumber, price, roomType);
                                }
                                List<Room> roomsToAdd = new ArrayList<>();
                                roomsToAdd.add(newRoom);
                                adminResource.addRoom(roomsToAdd);
                                i++;
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Would you like to add another room? y/n");
                        pattern = Pattern.compile(confirmRegex);
                        if (scanner.hasNextLine()) {
                            confirmation = scanner.nextLine().toLowerCase();
                            if (!pattern.matcher(confirmation).matches()) {
                                System.out.println("Please enter Y (Yes) or N (No)\n");
                            } else {
                                if (confirmation.equals("y")) {
                                    i = 1;
                                } else if (confirmation.equals("n")) {
                                    backToAdminMenu = true;
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

    public void addTestData () {
        Random randGenerator = new Random();
        double[] priceList = {0.0, 100.0, 120.0, 125.0, 135.0, 145.0, 150.0, 175.0, 200.0, 250.0};
        List<Room> roomsToAdd = new ArrayList<>();
        String roomNumber = "99";
        Double price;
        RoomType roomType;

        List<Room> allRooms = adminResource.getAllRooms();
        if (allRooms.size() > 0) {
            for (IRoom room : allRooms) {
                if (Integer.parseInt(room.getRoomNumber()) > Integer.parseInt(roomNumber)) {
                    roomNumber = room.getRoomNumber();
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            Room newRoom;
            int randomPrice = randGenerator.nextInt(priceList.length);
            float randomType = randGenerator.nextFloat();
            roomNumber = String.valueOf(Integer.parseInt(roomNumber) + 1);
            price = priceList[randomPrice];
            if (randomType <= 0.5) {
                roomType = RoomType.SINGLE;
            } else {
                roomType = RoomType.DOUBLE;
            }

            if (price == 0.0) {
                newRoom = new FreeRoom(roomNumber, price, roomType);
            } else {
                newRoom = new Room(roomNumber, price, roomType);
            }
            roomsToAdd.add(newRoom);
        }
        adminResource.addRoom(roomsToAdd);
    }
}
