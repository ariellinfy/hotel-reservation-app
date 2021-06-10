package service;

import model.*;

import java.util.*;

public class ReservationService {

    private static ReservationService reservationService = null;

    private Map<String, Room> roomList;
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

    public void addRoom (Room room) {
        roomList.put(room.getRoomNumber(), room);
    }

    private boolean checkDateRange (Reservation reservation, Date checkInDate, Date checkOutDate) {
        return reservation.getCheckInDate().getTime() <= checkInDate.getTime() && checkInDate.getTime() <= reservation.getCheckOutDate().getTime() ||
                reservation.getCheckInDate().getTime() <= checkOutDate.getTime() && checkOutDate.getTime() <= reservation.getCheckOutDate().getTime() ||
                checkInDate.getTime() <= reservation.getCheckInDate().getTime() && reservation.getCheckInDate().getTime() <= checkOutDate.getTime() ||
                checkInDate.getTime() <= reservation.getCheckOutDate().getTime() && reservation.getCheckOutDate().getTime() <= checkOutDate.getTime();
    }

    public List<Room> findRooms (Date checkInDate, Date checkOutDate) {
        List<String> bookedRoomNumbers = new ArrayList<>();
        List<Room> availableRooms = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            if (checkDateRange(reservation, checkInDate, checkOutDate)) {
                bookedRoomNumbers.add(reservation.getRoom().getRoomNumber());
            }
        }
        for (Room room : roomList.values()) {
            if (!bookedRoomNumbers.contains(room.getRoomNumber())) {
                availableRooms.add(room);
            }
        }
        Collections.sort(availableRooms);
        return availableRooms;
    }

    public List<Room> freeOrPaidRooms (Date checkInDate, Date checkOutDate, RoomPrice priceType) {
        List<Room> allAvailableRooms = findRooms(checkInDate, checkOutDate);
        List<Room> chosenRooms = new ArrayList<>();
        for (Room room : allAvailableRooms) {
            if (priceType == RoomPrice.FREE) {
                if (room.getRoomPrice() == 0.0) {
                    chosenRooms.add(room);
                }
            } else if (priceType == RoomPrice.PAID) {
                if (room.getRoomPrice() > 0.0) {
                    chosenRooms.add(room);
                }
            } else if (priceType == RoomPrice.BOTH) {
                chosenRooms = allAvailableRooms;
            }
        }
        return chosenRooms;
    }

    public IRoom getARoom (String roomId) {
        return roomList.get(roomId);
    }

    public Reservation reserveARoom (Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        List<Room> availableRooms = findRooms(checkInDate, checkOutDate);
        if (availableRooms.size() > 0) {
            if (availableRooms.contains(room)) {
                Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
                reservationList.add(newReservation);
                return newReservation;
            }
        }
        return null;
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

    public List<Room> getAllRooms () {
        List<Room> allRooms = new ArrayList<>();
        for (Room room : roomList.values()) {
            allRooms.add(room);
        }
        Collections.sort(allRooms);
        return allRooms;
    }

    public void printAllReservation () {
        if (reservationList.size() > 0) {
            for (Reservation reservation : reservationList) {
                System.out.println(reservation);
            }
        } else if (reservationList.size() == 0) {
            System.out.println("No reservations.");
        }

    }
}
