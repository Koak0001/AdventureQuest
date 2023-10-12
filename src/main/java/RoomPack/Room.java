package RoomPack;

import ItemPack.Item;
import MapPack.Enemy;

import java.util.ArrayList;

public class Room {
    public final String roomName;
    public final String roomDesc;
    public Room northRoom;
    public Room eastRoom;
    public Room southRoom;
    public Room westRoom;
    public boolean visited;
    private final ArrayList<Item> items;
    private final ArrayList<Enemy> enemies;


    public Room(String roomName, String roomDesc) {
        this.roomName = roomName;
        this.roomDesc = roomDesc;
        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.northRoom = null;
        this.eastRoom = null;
        this.southRoom = null;
        this.westRoom = null;
        this.visited = false;

    }



    public void setNorthRoom(Room room) {
        this.northRoom = room;
    }
    public void setEastRoom(Room room) {
        this.eastRoom = room;
    }
    public void setSouthRoom(Room room)  {
        this.southRoom = room;
    }
    public void setWestRoom(Room room) {
        this.westRoom = room;
    }


    public String getRoomName() {
        return roomName;
    }
    public String getRoomDesc() {
        return roomDesc;
    }

    public boolean isVisited() {
        return visited;
    }
    public void setVisited() {
        this.visited = true;
    }


    public String toString() {
        return "You are in" + roomName + " \n" +  roomDesc;}

    public ArrayList<Item> getItems() {
        return items;
    }
    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

    public void addItem(Item roomItem)  {
        if (roomItem != null) {
            items.add(roomItem);
        }
    }
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }


}

