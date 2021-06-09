package ui;
import java.util.Scanner;

public class HotelApplication {
    Scanner scanner = new Scanner(System.in);

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
            int selection = scanner.nextInt();
            try {
                switch (selection) {
                    case 1:
                        onMainMenu = false;
                    case 2:
                        onMainMenu = false;
                    case 3:
                        mainMenu.createAnAccount(scanner);
                        break;
                    case 4:
                        onMainMenu = false;
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
