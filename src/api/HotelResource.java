package api;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    public Collection<IRoom> findARoom (Date checkIn, Date checkOut) {

    }

    public Customer getCustomer (String email) {

    }

    public void createACustomer (String email, String firstName, String lastName) {

    }

    public IRoom getRoom (String roomNumber) {

    }

    public Reservation bookARoom (String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {

    }

    public Collection<Reservation> getCustomerReservations (String customerEmail) {

    }
}
