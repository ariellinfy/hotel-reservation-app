package model;
import java.util.Date;
public class Driver {

    public static void main (String[] args) {
        Customer customer = new Customer("Ariel", "Lin", "ariel@gmail.com");
        System.out.println(customer);

        Room room = new Room("120", 0.0, RoomType.SINGLE);
        System.out.println(room);

        Reservation reservation = new Reservation(customer, room, new Date(), new Date());
        System.out.println(reservation);
    }
}
