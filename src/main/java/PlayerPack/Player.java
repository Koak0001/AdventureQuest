package PlayerPack;
import MapPack.Map;
import RoomPack.Room;

public class Player {
    private final Map map;
    private Room requestRoom;

    public Player(Map map) {
        this.map = map;
    }

    public void moveTo(Room requestRoom) {
        this.requestRoom = requestRoom;
        if (requestRoom == null) {
            System.out.println("There is no room in that direction.");
        } else if (!requestRoom.isVisited()) {
            requestRoom.setVisited();
            map.setCurrentRoom(requestRoom);
            System.out.println(map.getCurrentRoom().getRoomDesc());
        } else {
            map.setCurrentRoom(requestRoom);
            System.out.println("You return to " + map.getCurrentRoom().getRoomName());
        }
    }

    public void setRequestRoom(Room requestRoom) {
        this.requestRoom = requestRoom;
    }

    public Room getRequestRoom() {
        return requestRoom;
    }
}












