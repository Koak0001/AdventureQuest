package PlayerPack;
import ItemPack.Item;
import MapPack.Map;
import RoomPack.Room;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final Map map;
    private Room requestRoom;

    private Room playerLocation;

    public List<Item> inventory;

    public Player(Map map) {
        this.map = map;
        this.inventory = new ArrayList<>();
        this.playerLocation = map.getCurrentRoom();
    }

    public void moveTo(Room requestRoom) {
        this.requestRoom = requestRoom;
        if (requestRoom == null) {
            System.out.println();
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
    public void setPlayerLocation(Room playerLocation) {
        this.playerLocation = playerLocation;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void add(Item item)  {
        if (item != null) {
            inventory.add(item);
        }
    }
}












