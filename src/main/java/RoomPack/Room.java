package RoomPack;

import ItemPack.Item;

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
    private Room currentRoom;

    public Room(String roomName, String roomDesc) {
        this.roomName = roomName;
        this.roomDesc = roomDesc;
        this.items = new ArrayList<>();
        this.northRoom = null;
        this.eastRoom = null;
        this.southRoom = null;
        this.westRoom = null;
        this.visited = false;
        this.currentRoom = null;

    }

    // Setters

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

   // Getters
    public String getRoomName() {
        return roomName;
    }
    public String getRoomDesc() {
        return roomDesc;
    }



    // Booleans
    public boolean isVisited() {
        return visited;
    }
    public void setVisited() {
        this.visited = true;
    }


    public String toString() {
        return "You are in" + roomName + " \n" +  roomDesc;}


    // Getter for items
    public ArrayList<Item> getItems() {
        return items;
    }

    // Add an item to the room
    public void addItem(Item roomItem)  {
        if (roomItem != null) {
            items.add(roomItem);
        }
    }

    public Room getWestRoom() {
        return westRoom;
    }
    public Room getEastRoom() {return eastRoom;}
    public Room getSouthRoom() {
        return southRoom;
    }
    public Room getNorthRoom() {
        return northRoom;
    }



    }

