package ui;
import java.util.Scanner;

public class HotelApplication {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.onMainMenu();
    }
}
