package model;

public class Room implements IRoom, Comparable<Room> {
    private String roomNumber;
    private Double price;
    private RoomType enumeration;
    private String roomType;
    private boolean isFree;

    public Room (String roomNumber, Double price, RoomType enumeration) {
        super();
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;

        if (enumeration == RoomType.SINGLE) {
            this.roomType = "Single Bed";
        } else if (enumeration == RoomType.DOUBLE) {
            this.roomType = "Double Bed";
        }

        if (price == 0.0) {
            this.isFree = true;
        } else {
            this.isFree = false;
        }
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    public void setRoomPrice(Double price) {
        this.price = price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    public void setRoomType(RoomType enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomTypeToString() {
        return roomType;
    }

    public void setRoomTypeString(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean isFree() {
        return isFree;
    }

    public void setIsFree(boolean isFree) {
        this.isFree = isFree;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + ", " + roomType + ", Room Price: $" + price;
    }

    @Override
    public int compareTo(Room room) {
        return roomNumber.compareTo(room.getRoomNumber());
    }
}
