package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    private static ReservationService reservationService = null;

    private Map<String, IRoom> roomList;
    private Set<Reservation> reservationList;

    private ReservationService () {
        roomList = new HashMap<>();
        reservationList = new HashSet<>();
    }

    public static ReservationService getInstance() {
        if (reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom (IRoom room) {
        roomList.put(room.getRoomNumber(), room);
    }

    public Collection<IRoom> findRooms (Date checkInDate, Date checkOutDate) {
        List<String> bookedRoomNumbers = new ArrayList<>();
        List<IRoom> availableRooms = new ArrayList<>();

        for (Reservation reservation : reservationList) {
            if (reservation.getCheckInDate().compareTo(checkInDate) >= 0 && reservation.getCheckOutDate().compareTo(checkOutDate) <= 0) {
                bookedRoomNumbers.add(reservation.getRoom().getRoomNumber());
            }
        }
        for (IRoom room : roomList.values()) {
            if (!bookedRoomNumbers.contains(room.getRoomNumber())) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public IRoom getARoom (String roomId) {
        return roomList.get(roomId);
    }

    public Reservation reserveARoom (Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservationList.add(newReservation);
        return newReservation;
    }

    public Collection<Reservation> getCustomerReservations (Customer customer) {
        List<Reservation> customerReservations = new ArrayList<>();

        for (Reservation reservation : reservationList) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }

    public Collection<IRoom> getAllRooms () {
        return roomList.values();
    }

    public void printAllReservation () {
        for (Reservation reservation : reservationList) {
            System.out.println(reservation + "\n");
        }
    }
}
