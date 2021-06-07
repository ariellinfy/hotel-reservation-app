package model;

public class FreeRoom extends Room {

    public FreeRoom(String roomNumber, Double price, RoomType enumeration) {
        super(roomNumber, price, enumeration);
        setRoomPrice(0.0);
    }

    @Override
    public String toString() {
        return "Room Number: " + this.getRoomNumber() + ", " + this.getRoomTypeToString() + ", Room Price: Free";
    }
}
