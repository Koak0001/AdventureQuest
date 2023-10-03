package PlayerPack;
import ItemPack.Item;
import MapPack.Map;
import RoomPack.Room;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final Map map;
    private Room playerLocation;
    private int health;
    public List<Item> inventory;

    public Player(Map map) {
        this.map = map;
        this.inventory = new ArrayList<>();
        this.playerLocation = map.getCurrentRoom();
        this.health = Math.min(100, health);
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
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
}












