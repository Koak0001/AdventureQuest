package MapPack;
import ItemPack.Food;
import ItemPack.Item;
import ItemPack.ItemDatabase;
import RoomPack.Room;

import java.util.ArrayList;

public class Map {
    private Room currentRoom;
    private Room requestRoom;
    private ArrayList<Item> items;
    public Map() {
        initializeMap();
    }
    private void initializeMap() {
        ItemDatabase itemDatabase = new ItemDatabase(); // Initialize your item database
        items = new ArrayList<>(); // Initialize the ArrayList to store items in rooms


        // Room overview
        Room room1 = new Room("Room 1 - the Entry Hall\n", "A grand chamber with faded tapestries that once depicted heroic battles.\nThe once-marble floor is now cracked and worn, and a chandelier hangs precariously from the ceiling.\n");
        Room room2 = new Room("Room 2 - Hallway of Echoes\n", "A long, narrow corridor lined with dusty suits of armor that stand like silent sentinels.\nTorches, long extinguished, still line the walls, casting eerie shadows.\n");
        Room room3 = new Room("Room 3 - Forgotten Smithy\n", "A blacksmith's workshop, filled with rusted tools and broken anvils.\nThe air is thick with the scent of old metalwork, and the forge lies cold and lifeless.\n");
        Room room4 = new Room("Room 4 - Guard Room of Yore\n", "The remains of a guard room, with ancient weapons rusting on the racks.\nFaded banners hang from the walls, bearing the insignia of a long-lost kingdom.\n");
        Room room5 = new Room("Room 5 - Throne Room of Decay\n", "A decaying chamber where a tarnished throne sits atop a dais.\nTattered banners hang from the walls, and the ceiling is adorned with faded frescoes.\n");
        Room room6 = new Room("Room 6 - Shrine of the Forgotten Gods\n", "An ornate room dedicated to long-forgotten deities.\nBroken statues lie toppled on the floor, and the once-sacred altar is now covered in spiderwebs.\n");
        Room room7 = new Room("Room 7 - Macabre Art Room\n", "A chilling gallery displaying gruesome paintings and sculptures.\nThe artwork seems to come to life in the dim light, evoking a sense of dread.\n");
        Room room8 = new Room("Room 8 - Chamber of Whispers\n", "A small, eerie room filled with tattered curtains that billow mysteriously in the stagnant air.\nFaint whispers seem to emanate from the walls themselves, carrying ancient secrets.\n");
        Room room9 = new Room("Room 9 - Library of Lost Knowledge\n", "Rows of rotting bookshelves filled with disintegrating tomes.\nMotes of dust dance in the faint rays of light filtering through boarded-up windows.\n" );

        // Room connectors and Room items:

        room1.setEastRoom(room2);
        room1.setSouthRoom(room4);
        //
        room2.setEastRoom(room3);
        room2.setWestRoom(room1);
        //
        room3.setSouthRoom(room6);
        room3.setWestRoom(room2);
        //
        room4.setNorthRoom(room1);
        room4.setSouthRoom(room7);
        //
        room5.setSouthRoom(room8);
        //
        room6.setNorthRoom(room3);
        room6.setSouthRoom(room9);
        //
        room7.setNorthRoom(room4);
        room7.setEastRoom(room8);
        //
        room8.setNorthRoom(room5);
        room8.setEastRoom(room9);
        room8.setWestRoom(room7);
        //
        room9.setNorthRoom(room6);
        room9.setWestRoom(room8);

        currentRoom = room1;

        Item item1 = itemDatabase.items.get(0);
        room1.addItem(item1);
        Item item2 = itemDatabase.items.get(1);
        room2.addItem(item2);
        Item item3 = itemDatabase.items.get(2);
        room8.addItem(item3);
        Item item4 = itemDatabase.items.get(3);
        room3.addItem(item4);
        Item item5 = itemDatabase.items.get(4);
        room7.addItem(item5);
        Item item6 = itemDatabase.items.get(5);
        room7.addItem(item6);
        Item item7 = itemDatabase.items.get(6);
        room5.addItem(item7);
        Item item8 = itemDatabase.items.get(7);
        room5.addItem(item8);
        Item item9 = itemDatabase.items.get(8);
        room5.addItem(item9);

        //Food
        Food food1 = (Food) itemDatabase.items.get(9);
        room1.addItem(food1);
        Food food2 = (Food) itemDatabase.items.get(10);
        room2.addItem(food2);
        Food food3 = (Food) itemDatabase.items.get(11);
        room3.addItem(food3);
        Food food4 = (Food) itemDatabase.items.get(12);
        room9.addItem(food4);
        Food food5 = (Food) itemDatabase.items.get(13);
        room9.addItem(food5);
        Food food6 = (Food) itemDatabase.items.get(14);
        room7.addItem(food6);
        Food food7 = (Food) itemDatabase.items.get(15);
        room1.addItem(food7);
        Food food8 = (Food) itemDatabase.items.get(16);
        room2.addItem(food8);
        Food food9 = (Food) itemDatabase.items.get(17);
        room1.addItem(food9);


    }

    // Getter for the current room
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }
    public Room getCurrentRoom() {
        return currentRoom;}

    public void setRequestRoom(Room requestRoom) {
        this.requestRoom = requestRoom;
    }

    public Room getRequestRoom() {
        return requestRoom;
    }
}





