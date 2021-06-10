package api;

import model.Customer;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    CustomerService customerService = CustomerService.getInstance();
    ReservationService reservationService = ReservationService.getInstance();

    public Customer getCustomer (String email) {
        return customerService.getCustomer(email);
    }

    public void addRoom (List<Room> rooms) {
        for (Room room : rooms) {
            reservationService.addRoom(room);
        }
    }

    public Collection<Customer> getAllCustomers () {
        return customerService.getAllCustomers();
    }

    public List<Room> getAllRooms () {
        return reservationService.getAllRooms();
    }

    public void displayAllReservations() {
        reservationService.printAllReservation();
    }
}
