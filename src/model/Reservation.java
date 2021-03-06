package model;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.US);

    public Reservation (Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "\nReservation" + "\n" +
                customer.getFirstName() + " " + customer.getLastName() + "\n" +
                "Room: " + room.getRoomNumber() + " - " + room.getRoomTypeToString() + "\n" +
                "Price: $" + room.getRoomPrice() + " price per night" + "\n" +
                "CheckIn Date: " + dateFormat.format(checkInDate) + "\n" +
                "CheckOut Date: " + dateFormat.format(checkOutDate);
    }
}
