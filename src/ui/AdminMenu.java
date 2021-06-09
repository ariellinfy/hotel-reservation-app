package ui;

import java.util.ArrayList;
import java.util.List;

public class AdminMenu {
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
    }
}
