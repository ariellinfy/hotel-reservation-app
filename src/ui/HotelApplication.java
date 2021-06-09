package ui;
import java.util.Scanner;

public class HotelApplication {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        AdminMenu adminMenu = new AdminMenu();
        HotelApplication hotelApplication = new HotelApplication();
        hotelApplication.onMainMenu(mainMenu, adminMenu);
    }

    public void onMainMenu (MainMenu mainMenu, AdminMenu adminMenu) {
        boolean onMainMenu = true;
        while (onMainMenu) {
            mainMenu.mainMenu();
            int selection = Integer.parseInt(scanner.nextLine());
            try {
                switch (selection) {
                    case 1:
                        mainMenu.findAndReserveARoom();
                        break;
                    case 2:
                        mainMenu.seeMyReservation();
                        break;
                    case 3:
                        mainMenu.createAnAccount();
                        break;
                    case 4:
                        adminMenu.adminMenu();
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
        scanner.close();
    }
}
