package UserInterfacePack;
import ItemPack.*;
import AdvPack.Adventure;
import MapPack.Map;
import PlayerPack.Player;
import RoomPack.Room;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    public Adventure adventure;
    public Player player;
    public Map map;
    public ItemDatabase itemDatabase;


    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
        this.map = new Map();
        this.player = new Player(map);
        this.itemDatabase = new ItemDatabase();
        player.setHealth(100);

        play();
    }

    public void play() {
        adventure.newGame();
        // player.newPlayer();
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Welcome to the game you are standing in " + map.getCurrentRoom().getRoomName() + map.getCurrentRoom().getRoomDesc());
        player.getPlayerLocation().setVisited();
        System.out.println("Start by going south or east ");
        System.out.println("Type help for help");
        while (!exit) {
            String userInput = keyboard.nextLine().toLowerCase();
            String input = userInput.replace("go", "").replace("around", "").replace("for", "").replace("move", "").replace("display","").replace("\\s+", " ").replace("room","").replace("I","").replace("show", "").replace("the", "").trim();
            String[] inputTokens = input.split("\\s+", 2);
            boolean validCommandProcessed = false;
            Room targetRoom = null; // Initialize targetRoom to null

            switch (input) {
                case "north", "n" -> {
                    targetRoom = player.getPlayerLocation().getNorthRoom();
                }
                case "south", "s" -> {
                    targetRoom = player.getPlayerLocation().getSouthRoom();
                }
                case "west", "w" -> {
                    targetRoom = player.getPlayerLocation().getWestRoom();
                }
                case "east", "e" -> {
                    targetRoom = player.getPlayerLocation().getEastRoom();
                }
                case "help" -> {
                    help();
                    validCommandProcessed = true;
                }
                case "exit", "quit" -> {
                    exit = true;
                    System.exit(0);
                }
                case "look" -> {
                    look();
                    validCommandProcessed = true;
                }
                case "search" -> {
                    search();
                    validCommandProcessed = true;
                }
                case "inventory" -> {
                    displayInventory();
                    validCommandProcessed = true;
                }
                case "healthbar", "health" -> {
                    displayHealth();
                    validCommandProcessed = true;
                }
            }
            if ((inputTokens[0].equals("north") || inputTokens[0].equals("south") || inputTokens[0].equals("east") || inputTokens[0].equals("west")) && targetRoom == null) {
                System.out.println("There's nothing that way");
                validCommandProcessed = true;
            } else if (targetRoom != null) {
                player.setRequestRoom(targetRoom);
                moveTo(player.getRequestRoom());
                validCommandProcessed = true;
                }
            if (!validCommandProcessed && inputTokens.length == 2) {
                String itemName = inputTokens[1].toLowerCase().trim();
                if (inputTokens[0].equals("take") || inputTokens[0].equals("grab") || inputTokens[0].equals("pickup")) {
                    takeOrDropItem(itemName);
                } if (inputTokens[0].equals("drop") || inputTokens[0].equals("leave") || inputTokens[0].equals("dump")) {
                    takeOrDropItem(itemName);
                } if (inputTokens[0].equals("eat") ||inputTokens[0].equals("drink") ){
                    eatFood(itemName);
                }
            } else if (!validCommandProcessed) {
                System.out.println("Unknown input");
            }
        }
    }
        public void help () {
            System.out.println("You are standing in " + map.getCurrentRoom().getRoomName());
            System.out.println("In this game you may move in 4 directions");
            System.out.println("North, South, East, West");
            System.out.println("Type 'Look around' to see which directions you may move to");
            System.out.println("'Search' to search the room.");
            System.out.println("'Health', or 'Display Healthbar' to see current Health Points");
            System.out.println("'Inventory' to view your inventory.");
            System.out.println("If you wish to take or drop an item, simply input take or drop, and the item's name");
            System.out.println("And use 'eat' and 'drink' for food and drinks");
            System.out.println("Type exit to exit the game");
        }
        public void look () {
            System.out.println("You are standing in " + player.getPlayerLocation().getRoomName() + player.getPlayerLocation().getRoomDesc());
            System.out.println("Looking around, you see paths leading... ");
             if (player.getPlayerLocation().northRoom != null){
                 System.out.println("... North");
             }if (player.getPlayerLocation().eastRoom != null){
                 System.out.println("... East");
             }if (player.getPlayerLocation().southRoom != null){
                 System.out.println("... South");
             }if (player.getPlayerLocation().westRoom != null){
                 System.out.println("... West");}
            }
        public void displayHealth() {
            System.out.println("You have " + player.getHealth() + " health points.");
            if (player.getHealth() >0 && player.getHealth() <= 25){
                System.out.println("You are severely wounded, you should rest and tend to your wounds");
            }
            if (player.getHealth() >25 && player.getHealth() <= 50){
                System.out.println("You are injured, be wary");
            }
            if (player.getHealth() >50 && player.getHealth() <= 75){
                System.out.println("You've suffered minor wounds");
            }
            if (player.getHealth() >75){
                System.out.println("You're in good form, fight on!");}
            }
        public void gameOverCheck() {
        if (player.getHealth() <= 0){
            System.out.println("Death comes for us all.");
            System.out.println("Game Over!");
            System.exit(0);}
        if (player.getHealth() == 100)
        {System.out.println("You are sated and at full health");}
        }
        public void search () {
            System.out.println("You search the room for treasures.");
            System.out.println();
            List<Item> itemsInRoom = map.getCurrentRoom().getItems();
            if (itemsInRoom != null && !itemsInRoom.isEmpty()) {
                for (Item item : itemsInRoom) {
                    System.out.println("You find " + item.getItemName());}
            } else {
                System.out.println("This room is void food or treasures");}
                }
        public void displayInventory () {
            System.out.println("You search your bag... ");
            if (player.inventory.isEmpty()) {
                System.out.println(" ... It remains empty.");
            } else {
                System.out.println(".. And find its contents within. Just as you left them: ");
                for (Item item : player.inventory) {
                    System.out.println(item.getItemName() + " \n" + item.getItemDescription());}
                 }
            }
        public void takeOrDropItem (String itemName) {
            Room currentRoom = player.getPlayerLocation();
            itemName = itemName;
            Item foundItem = null;
            for (Item item : currentRoom.getItems()) {
                if (item.getItemName().toLowerCase().replaceAll("\\s+", "").contains(itemName.replaceAll("\\s+", ""))) {
                    foundItem = item;
                    break;
                }
            }
            if (foundItem != null) {
                player.inventory.add(foundItem);
                currentRoom.getItems().remove(foundItem);
                System.out.println("You picked up " + foundItem.getItemName() + " \n" + foundItem.getItemDescription());
            } else{
                for (Item item : player.inventory) {
                    if (item.getItemName().toLowerCase().replaceAll("\\s+", "").contains(itemName.replaceAll("\\s+", ""))) {
                        foundItem = item;
                        currentRoom.addItem(foundItem);
                        player.inventory.remove(foundItem);
                        System.out.println("You dropped " + foundItem.getItemName());
                        break;
                }
            }
        }
    }
    public void eatFood(String itemName) {
        boolean foundFood = false;

        // Iterate through the player's inventory
        for (Item item : player.getInventory()) {
            if (item instanceof Food) {
                Food food = (Food) item;
                String foodName = food.getItemName().toLowerCase().replaceAll("\\s+", "");

                if (foodName.contains(itemName.replaceAll("\\s+", ""))) {
                    int currentHealth = player.getHealth();
                    int healthMod = food.getHealthMod();

                    System.out.println("You consume " + food.getItemName() + "\nIt is " + food.getEffect());
                    player.setHealth(currentHealth + healthMod);
                    System.out.println(player.getHealth());
                    player.getInventory().remove(food); // Remove the consumed food
                    foundFood = true;
                    break; // Exit the loop once food is found and consumed
                }
            }
        }

        if (!foundFood) {
            System.out.println("Item not found in your inventory or not edible.");
        }
    }
//        public void removeItemFromInventory (String itemName){
//        Room currentRoom = player.getPlayerLocation();
//        itemName = itemName.trim().toLowerCase();
//        Item item = null;
//        for (Item inventoryItem : player.getInventory()) {
//            if (inventoryItem.getItemName().toLowerCase().replaceAll("\\s+", "").contains(itemName.replaceAll("\\s+", ""))) {
//                item = inventoryItem;
//                break;
//            }
//        }
//            if (item != null) {
//                player.getInventory().remove(item);
//                currentRoom.addItem(item);
//                System.out.println("You dropped " + item.getItemName());
//            } else {
//                System.out.println("Item not found in inventory.");
//            }
//        }
        public void moveTo(Room requestedRoom) {
            if (requestedRoom == null) {
                System.out.println("There's nothing in that direction");
        } else if (!requestedRoom.isVisited()) {
                requestedRoom.setVisited();
                player.setPlayerLocation(requestedRoom);
                moveTax();
                System.out.println(player.getPlayerLocation().getRoomDesc());
            } else {
                player.setPlayerLocation(requestedRoom);
                moveTax();
            }
        }
        public void moveTax() {
            int currentHealth = player.getHealth();
            map.setCurrentRoom(player.getPlayerLocation());
            System.out.println( "The way through the ruins is a gruelling task ");
            player.setHealth(currentHealth - 1)
            ;System.out.println("You have " + player.getHealth() + " health points.");
            System.out.println(player.getPlayerLocation().getRoomName());
            gameOverCheck();
    }


}

