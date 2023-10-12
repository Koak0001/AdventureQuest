package MapPack;
import ItemPack.*;
import RoomPack.Room;

import java.util.ArrayList;

public class Map {

    private ArrayList<Room> rooms;
    private Room startingRoom;


    private Room westRoom;
    private Room eastRoom;
    private Room southRoom;
    private Room northRoom;


    public Map() {
        initializeMap();
    }


    private void initializeMap() {
        ItemDatabase itemDatabase = new ItemDatabase();
        rooms = new ArrayList<>();



        // Initialize rooms
        rooms.add(new Room("Room 1 - the Entry Hall\n", "A grand chamber with faded tapestries that once depicted heroic battles.\nThe once-marble floor is now cracked and worn, and a chandelier hangs precariously from the ceiling.\n"));
        rooms.add(new Room("Room 2 - Hallway of Echoes\n", "A long, narrow corridor lined with dusty suits of armor that stand like silent sentinels.\nTorches, long extinguished, still line the walls, casting eerie shadows.\n"));
        rooms.add(new Room("Room 3 - Forgotten Smithy\n", "A blacksmith's workshop, filled with rusted tools and broken anvils.\nThe air is thick with the scent of old metalwork, and the forge lies cold and lifeless.\n"));
        rooms.add(new Room("Room 4 - Guard Room of Yore\n", "The remains of a guard room, with ancient weapons rusting on the racks.\nFaded banners hang from the walls, bearing the insignia of a long-lost kingdom.\n"));
        rooms.add(new Room("Room 5 - Throne Room of Decay\n", "A decaying chamber where a tarnished throne sits atop a dais.\nTattered banners hang from the walls, and the ceiling is adorned with faded frescoes.\n"));
        rooms.add(new Room("Room 6 - Shrine of the Forgotten Gods\n", "An ornate room dedicated to long-forgotten deities.\nBroken statues lie toppled on the floor, and the once-sacred altar is now covered in spiderwebs.\n"));
        rooms.add(new Room("Room 7 - Macabre Art Room\n", "A chilling gallery displaying gruesome paintings and sculptures.\nThe artwork seems to come to life in the dim light, evoking a sense of dread.\n"));
        rooms.add(new Room("Room 8 - Chamber of Whispers\n", "A small, eerie room filled with tattered curtains that billow mysteriously in the stagnant air.\nFaint whispers seem to emanate from the walls themselves, carrying ancient secrets.\n"));
        rooms.add(new Room("Room 9 - Library of Lost Knowledge\n", "Rows of rotting bookshelves filled with disintegrating tomes.\nMotes of dust dance in the faint rays of light filtering through boarded-up windows.\n"));
        startingRoom = rooms.get(0);

        Enemy enemy1 = new Enemy("ork", "big green and mean", 50, (Weapon) itemDatabase.items.get(44), 5);
        Enemy enemy2 = new Enemy("gnoll", "a snarling gnoll", 50, (Weapon) itemDatabase.items.get(45), 2);
        Enemy enemy3 = new Enemy("troll", "a hulking brute that's not too bright", 60, (Weapon) itemDatabase.items.get(46), 4);
        Enemy enemy4 = new Enemy("kobold", "a tiny menace, cunning and quick", 40, (Weapon) itemDatabase.items.get(47), 3);
        Enemy enemy5 = new Enemy("ogre", "a massive creature with enormous strength", 80, (Weapon) itemDatabase.items.get(48), 7);
        Enemy enemy6 = new Enemy("naga", "a serpent-bodied creature with piercing eyes", 55, (Weapon) itemDatabase.items.get(49), 4);
        Enemy enemy7 = new Enemy("wraith", "an ethereal being that saps the life out of its victims", 45, (Weapon) itemDatabase.items.get(50), 5);
        Enemy enemy8 = new Enemy("minotaur", "a half-man, half-bull monster that charges with rage", 70, (Weapon) itemDatabase.items.get(51), 6);


        // Room enemies
        rooms.get(1).addEnemy(enemy1);
        rooms.get(1).addEnemy(enemy2);
        rooms.get(3).addEnemy(enemy3);
        rooms.get(3).addEnemy(enemy4);
        rooms.get(4).addEnemy(enemy5);
        rooms.get(7).addEnemy(enemy7);
        rooms.get(6).addEnemy(enemy6);
        rooms.get(6).addEnemy(enemy8);
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy1);
        enemies.add(enemy2);


        // Room connectors and Room items:

        rooms.get(0).setEastRoom(rooms.get(1));
        rooms.get(0).setSouthRoom(rooms.get(3));

        rooms.get(1).setEastRoom(rooms.get(2));
        rooms.get(1).setWestRoom(rooms.get(0));

        rooms.get(2).setSouthRoom(rooms.get(5));
        rooms.get(2).setWestRoom(rooms.get(1));

        rooms.get(3).setNorthRoom(rooms.get(0));
        rooms.get(3).setSouthRoom(rooms.get(6));

        rooms.get(4).setSouthRoom(rooms.get(7));

        rooms.get(5).setNorthRoom(rooms.get(2));
        rooms.get(5).setSouthRoom(rooms.get(8));

        rooms.get(6).setNorthRoom(rooms.get(3));
        rooms.get(6).setEastRoom(rooms.get(7));

        rooms.get(7).setNorthRoom(rooms.get(4));
        rooms.get(7).setEastRoom(rooms.get(8));
        rooms.get(7).setWestRoom(rooms.get(6));

        rooms.get(8).setNorthRoom(rooms.get(5));
        rooms.get(8).setWestRoom(rooms.get(7));


        Room room1 = rooms.get(0);
        Room room2 = rooms.get(1);
        Room room3 = rooms.get(2);
        Room room4 = rooms.get(3);
        Room room5 = rooms.get(4);
        Room room6 = rooms.get(5);
        Room room7 = rooms.get(6);
        Room room8 = rooms.get(7);
        Room room9 = rooms.get(8);


        int[] itemIndices1 = {0, 1, 5, 9, 14, 15, 17, 18, 27, 28, 29};
        int[] itemIndices2 = {2, 7, 10, 16, 19, 25, 26};
        int[] itemIndices3 = {3, 9, 20, 30};
        int[] itemIndices4 = {23, 31};
        int[] itemIndices5 = {4, 6, 8, 32};
        int[] itemIndices6 = {24, 33};
        int[] itemIndices7 = {11, 12, 34};
        int[] itemIndices8 = {10, 22, 35};
        int[] itemIndices9 = {4, 13, 21, 36};

        //  items to rooms using indices
        addItemsToRoom(itemIndices1, room1, itemDatabase);
        addItemsToRoom(itemIndices2, room2, itemDatabase);
        addItemsToRoom(itemIndices3, room3, itemDatabase);
        addItemsToRoom(itemIndices4, room4, itemDatabase);
        addItemsToRoom(itemIndices5, room5, itemDatabase);
        addItemsToRoom(itemIndices6, room6, itemDatabase);
        addItemsToRoom(itemIndices7, room7, itemDatabase);
        addItemsToRoom(itemIndices8, room8, itemDatabase);
        addItemsToRoom(itemIndices9, room9, itemDatabase);
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

    public Room getStartingRoom() {
        return startingRoom;
    }
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    private void addItemsToRoom(int[] itemIndices, Room room, ItemDatabase itemDatabase) {
        for (int index : itemIndices) {
            Item item = itemDatabase.items.get(index);
            room.addItem(item);
        }
    }
}
















