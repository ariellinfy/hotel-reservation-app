package api;

import model.*;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class HotelResource {
    CustomerService customerService = CustomerService.getInstance();
    ReservationService reservationService = ReservationService.getInstance();

    public List<Room> findARoom (Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }

    public List<Room> freeOrPaidRooms (Date checkIn, Date checkOut, RoomPrice priceType) {
        return reservationService.freeOrPaidRooms(checkIn, checkOut, priceType);
    }

    public Customer getCustomer (String email) {
        return customerService.getCustomer(email);
    }

    public void createACustomer (String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom (String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom (String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations (String customerEmail) {
        Customer customer = getCustomer(customerEmail);
        return reservationService.getCustomerReservations(customer);
    }
}
