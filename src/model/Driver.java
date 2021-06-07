package model;
import java.util.Date;
public class Driver {

    public static void main (String[] args) {
        Customer customer = new Customer("Ariel", "Lin", "ariel@gmail.com");
        System.out.println(customer);

        Room room = new Room("120", 0.0, RoomType.SINGLE);
        System.out.println(room);

        Room freeRoom = new FreeRoom("130", 12.0, RoomType.DOUBLE);
        System.out.println(freeRoom);

        Reservation reservation = new Reservation(customer, freeRoom, new Date(), new Date());
        System.out.println(reservation);
    }
}
