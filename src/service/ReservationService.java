package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

public class ReservationService {

    Map<String, Room> roomList = new HashMap<String, Room>();
    Set<Reservation> reservationList = new HashSet<Reservation>();

    public void addRoom (IRoom room) {

    }

    public Collection<IRoom> findRooms (Date checkInDate, Date checkOutDate) {

    }

    public IRoom getARoom (String roodId) {
        return roomList.get(roodId);
    }

    public Reservation reserveARoom (Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservationList.add(newReservation);
        return newReservation;
    }

    public Collection<Reservation> getCustomerReservation (Customer customer) {

    }

    public void printAllReservation () {
        for (Reservation reservation : reservationList) {
            System.out.println(reservation);
        }
    }
}
