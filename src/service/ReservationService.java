package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    private static Map<String, IRoom> roomList = new HashMap<String, IRoom>();
    private static Set<Reservation> reservationList = new HashSet<Reservation>();

    public static void addRoom (IRoom room) {
        roomList.put(room.getRoomNumber(), room);
    }

    public static Collection<IRoom> findRooms (Date checkInDate, Date checkOutDate) {
        List<String> bookedRoomNumbers = new ArrayList<String>();
        List<IRoom> availableRooms = new ArrayList<IRoom>();

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

    public static IRoom getARoom (String roomId) {
        return roomList.get(roomId);
    }

    public static Reservation reserveARoom (Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservationList.add(newReservation);
        return newReservation;
    }

    public static Collection<Reservation> getCustomerReservations (Customer customer) {
        List<Reservation> customerReservations = new ArrayList<Reservation>();

        for (Reservation reservation : reservationList) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }

    public static Collection<IRoom> getAllRooms () {
        return roomList.values();
    }

    public static void printAllReservation () {
        for (Reservation reservation : reservationList) {
            System.out.println(reservation);
        }
    }
}
