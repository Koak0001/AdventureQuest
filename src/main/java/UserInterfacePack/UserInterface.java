package UserInterfacePack;

import AdvPack.Adventure;
import ItemPack.Item;
import ItemPack.Food;
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
        player.setHealth(100);

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
            String input = userInput.replace("go ", "").replace("around", "").replace("for", "").replace("move", "go").replace("\\s+", " ").replace("the", "").trim();
            String[] inputTokens = input.split("\\s+", 2);
            boolean validCommandProcessed = false;
            switch (input) {
                case "north", "n" -> {
                    map.setRequestRoom(map.getCurrentRoom().getNorthRoom());
                    moveTo(map.getRequestRoom());
                    validCommandProcessed = true;
                }
                case "south", "s" -> {
                    map.setRequestRoom(map.getCurrentRoom().getSouthRoom());
                    moveTo(map.getRequestRoom());
                    validCommandProcessed = true;
                }
                case "west", "w" -> {
                    map.setRequestRoom(map.getCurrentRoom().getWestRoom());
                    moveTo(map.getRequestRoom());
                    validCommandProcessed = true;
                }
                case "east", "e" -> {
                    map.setRequestRoom(map.getCurrentRoom().getEastRoom());
                    moveTo(map.getRequestRoom());
                    validCommandProcessed = true;
                }
                case "help" -> {
                    help();
                    validCommandProcessed = true;
                }
                case "exit" -> {
                    exit = true; validCommandProcessed = true;
                }
                case "look" -> {look(); validCommandProcessed = true;}
                case "search", "items" -> {
                    search();
                    validCommandProcessed = true;
                }
                case "inventory" -> {
                    displayInventory();
                    validCommandProcessed = true;
                }
                case "healthbar", "health" -> {displayHealth(); validCommandProcessed = true;}
            }

            if (!validCommandProcessed && inputTokens.length == 2) {
                String itemName = inputTokens[1].toLowerCase().trim();
                if (inputTokens[0].equals("take") || inputTokens[0].equals("grab") || inputTokens[0].equals("pickup")) {
                    takeItemFromRoom(itemName);
                } if (inputTokens[0].equals("drop") || inputTokens[0].equals("leave") || inputTokens[0].equals("dump")) {
                    removeItemFromInventory(itemName);
                } if (inputTokens[0].equals("eat") ||inputTokens[0].equals("drink") ){
                    eatFood(itemName);
                }
            } else if (!validCommandProcessed) {
                System.out.println("Unknown input");
            }
        }
    }


        // Help Method
        public void help () {
            System.out.println("You are standing in " + map.getCurrentRoom().getRoomName());
            System.out.println("In this game you may move in 4 directions");
            System.out.println("North, South, East, West");
            System.out.println("Type 'Look around' to see which directions you may move to");
            System.out.println("'Search' to search the room.");
            System.out.println("'Inventory' to view your inventory.");
            System.out.println("If you wish to take or drop an item, simply input take or drop, and the item's name");
            System.out.println("Type exit to exit the game");
        }

        // Look Method
        public void look () {
            System.out.println("You are standing in " + map.getCurrentRoom().getRoomName() + map.getCurrentRoom().getRoomDesc());
        }
        public void displayHealth() {
            System.out.println("You have " + player.getHealth() + " health points.");
    }
        public void search () {
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
        public void displayInventory () {
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
        public void takeItemFromRoom (String itemName){
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
    public void eatFood(String itemName) {

        Room currentRoom = map.getCurrentRoom();
        itemName = itemName.trim().toLowerCase();
        Item foodToEat = null;

        for (Item item : player.getInventory()) {
            if (item.getItemName().toLowerCase().replaceAll("\\s+", "").equals(itemName.replaceAll("\\s+", ""))) {
                foodToEat = item;
                break;
            }
        }
        if (foodToEat != null) {
            if (foodToEat instanceof Food) {
                int currentHealth = player.getHealth();
                int healthMod = ((Food) foodToEat).getHealthMod();
                System.out.println(player.getHealth());
                player.setHealth(currentHealth + healthMod);
                Food food = (Food) foodToEat;
                System.out.println("You consume a " + food.getItemName() + " it is " + food.getEffect());
                System.out.println();
                System.out.println(player.getHealth());
            } else {
                System.out.println("This item is not food.");
            }
            player.getInventory().remove(foodToEat);
        } else {
            System.out.println("Item not found in your inventory.");
        }
    }

        public void removeItemFromInventory (String itemName){
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

        public void moveTo(Room requestedRoom) {
            if (requestedRoom == null) {
                System.out.println();
                System.out.println(".. But there's nothing in that direction");
        } else if (!requestedRoom.isVisited()) {
                int currentHealth = player.getHealth();
                System.out.println("You move forward through the keep");
                requestedRoom.setVisited();
                map.setCurrentRoom(requestedRoom);
                System.out.println(map.getCurrentRoom().getRoomDesc());
                player.setHealth(currentHealth - 1);
                System.out.println( "The way through the ruins is tiring ");
                System.out.println("You have " + player.getHealth() + " health points.");
            } else {
                int currentHealth = player.getHealth();
                map.setCurrentRoom(requestedRoom);
                player.setHealth(currentHealth - 1);
                System.out.println( "The way through the ruins is tiring ");
                System.out.println("You have " + player.getHealth() + " health points.");
                System.out.println("You return to " + map.getCurrentRoom().getRoomName());
           }

    }
    }

