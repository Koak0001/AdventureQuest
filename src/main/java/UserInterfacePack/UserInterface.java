package UserInterfacePack;

import AdvPack.Adventure;
import ItemPack.Item;
import MapPack.Map;
import PlayerPack.Player;
import RoomPack.Room;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    public Adventure adventure;
    public Player player;
    public Map map;

    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
        this.map = new Map();
        this.player = new Player(map);
        play();
    }

    public void play() {

        adventure.newGame();
       // player.newPlayer();
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Welcome to the game you are standing in " + map.getCurrentRoom().getRoomName() + map.getCurrentRoom().getRoomDesc());
        System.out.println("Start by going south or east ");
        System.out.println("Type help for help");
        while (!exit) {
            String userInput = keyboard.nextLine().toLowerCase();

            String input = userInput.replace("go", "").replace("around", "").replace("for", "").trim();
            switch (input) {
                case "north", "n" -> {
                    System.out.println("Going North");
                    Room northRoom = map.getCurrentRoom().getNorthRoom();
                    if (northRoom != null) {
                        player.setRequestRoom(northRoom);
                        System.out.println(player.getRequestRoom().getRoomName());
                        player.moveTo(northRoom);
                    } else {
                        System.out.println("There is no room to the north.");
                    }
                }
                case "south", "s" -> {
                    {
                        System.out.println("Going South");
                        Room southRoom = map.getCurrentRoom().getSouthRoom();
                        if (southRoom != null) {
                            player.setRequestRoom(southRoom);
                            System.out.println(player.getRequestRoom().getRoomName());
                            player.moveTo(southRoom);
                        } else {
                            System.out.println("There is no room to the south.");
                        }
                    }
                }
                case "west", "w" -> {
                    System.out.println("Going West");
                    Room westRoom = map.getCurrentRoom().getWestRoom();
                    if (westRoom != null) {
                        player.setRequestRoom(westRoom);
                        System.out.println(player.getRequestRoom().getRoomName());
                        player.moveTo(westRoom);
                    } else {
                        System.out.println("There is no room to the west.");
                    }
                }
                case "east", "e" -> {
                    System.out.println("Going East");
                    Room eastRoom = map.getCurrentRoom().getEastRoom();
                    if (eastRoom != null) {
                        player.setRequestRoom(eastRoom);
                        System.out.println(player.getRequestRoom().getRoomName());
                        player.moveTo(eastRoom);

                    } else {
                        System.out.println("There is no room to the east.");
                    }
                }
                case "look", "look around" -> look();
                case "help" -> help();
                case "exit" -> exit = true;
                case "search", "items" -> search();
                case "inventory" -> displayInventory();
                case "take" -> {
                    System.out.println("Enter the name of the item you wish to take:");
                    String itemName = keyboard.nextLine().toLowerCase();
                    takeItemFromRoom(itemName);
                }
                case "drop" -> {
                    System.out.println("Enter the name of the item you wish to leave behind:");
                    String itemToDrop = keyboard.nextLine().toLowerCase();
                    removeItemFromInventory(itemToDrop);
                }
                default -> System.out.println("Unknown input");
            }
        }
    }

    // Help Method
    public void help() {
        System.out.println("You are standing in " + map.getCurrentRoom().getRoomName());
        System.out.println("In this game you can move in 4 directions");
        System.out.println("North, South, East, West");
        System.out.println("Type 'Look around' to see which directions you may move to");
        System.out.println("'Search' to search the room.");
        System.out.println("'Inventory' to view your inventory.");
        System.out.println("If you wish to take or drop an item, simply input take/drop");
        System.out.println("Type exit to exit the game");
    }

    // Look Method
    public void look() {
        System.out.println("You are standing in " + map.getCurrentRoom().getRoomName() + map.getCurrentRoom().getRoomDesc());
    }
    public void search() {
        System.out.println("You search the room for treasures.");
        System.out.println();
        List<Item> itemsInRoom = map.getCurrentRoom().getItems();
        if (itemsInRoom != null && !itemsInRoom.isEmpty()) {
            for (Item item : itemsInRoom) {
                System.out.println("You have found the " + item.getItemName());
            }
        } else {
            System.out.println("This room is void of treasures");
        }
    }
    public void displayInventory() {
        System.out.println("You search your bag... ");
        if (player.inventory.isEmpty()) {
            System.out.println(" ... It remains empty.");
        } else {
            System.out.println(".. And find your treasures within:");
            for (Item item : player.inventory) {
                System.out.println();
                System.out.println("The " + item.getItemName() + "\n" + item.getItemDescription());
            }
        }
    }
    public void takeItemFromRoom(String itemName) {
        Room currentRoom = map.getCurrentRoom();
        itemName = itemName.trim().toLowerCase();
        Item foundItem = null;
        for (Item item : currentRoom.getItems()) {
            if (item.getItemName().toLowerCase().replaceAll("\\s+", "").equals(itemName.replaceAll("\\s+", ""))) {
                foundItem = item;
                break;
            }
        }
        if (foundItem != null) {
            player.getInventory().add(foundItem);
            currentRoom.getItems().remove(foundItem);
            System.out.println("You picked up the " + foundItem.getItemName() + "\n" + foundItem.getItemDescription());
        } else {
            System.out.println("Item not found in the room.");
        }
    }
    public void removeItemFromInventory(String itemName) {
        Room currentRoom = map.getCurrentRoom();
        itemName = itemName.trim().toLowerCase();
        Item item = null;
        for (Item inventoryItem : player.getInventory()) {
            if (inventoryItem.getItemName().toLowerCase().replaceAll("\\s+", "").equals(itemName.replaceAll("\\s+", ""))) {
                item = inventoryItem;
                break;
            }
        }
        if (item != null) {
            player.getInventory().remove(item);
            currentRoom.addItem(item);
            System.out.println("You dropped " + itemName);
        } else {
            System.out.println("Item not found in your inventory.");
        }
    }
}