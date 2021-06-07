package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    public Collection<IRoom> findARoom (Date checkIn, Date checkOut) {
        return ReservationService.findRooms(checkIn, checkOut);
    }

    public Customer getCustomer (String email) {
        return CustomerService.getCustomer(email);
    }

    public void createACustomer (String email, String firstName, String lastName) {
        CustomerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom (String roomNumber) {
        return ReservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom (String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = getCustomer(customerEmail);
        return ReservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations (String customerEmail) {
        Customer customer = getCustomer(customerEmail);
        return ReservationService.getCustomerReservations(customer);
    }
}
