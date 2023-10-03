package UserInterfacePack;
import ItemPack.ItemDatabase;
import AdvPack.Adventure;
import ItemPack.Item;
import ItemPack.Food;
import ItemPack.Potion;
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
            String input = userInput.replace("go ", "").replace("around", "").replace("for", "").replace("move", "go").replace("display","").replace("\\s+", " ").replace("show", "").replace("the", "").trim();
            String[] inputTokens = input.split("\\s+", 2);
            boolean validCommandProcessed = false;
            switch (input) {
                case "north", "n" -> {
                    player.setRequestRoom(player.getPlayerLocation().getNorthRoom());
                    moveTo(player.getRequestRoom());
                    validCommandProcessed = true;
                }
                case "south", "s" -> {
                    player.setRequestRoom(player.getPlayerLocation().getSouthRoom());
                    moveTo(player.getRequestRoom());
                    validCommandProcessed = true;
                }
                case "west", "w" -> {
                    player.setRequestRoom(player.getPlayerLocation().getWestRoom());
                    moveTo(player.getRequestRoom());
                    validCommandProcessed = true;
                }
                case "east", "e" -> {
                    player.setRequestRoom(player.getPlayerLocation().getEastRoom());
                    moveTo(player.getRequestRoom());
                    validCommandProcessed = true;
                }
                case "help" -> {
                    help();
                    validCommandProcessed = true;
                }
                case "exit" -> {
                    exit = true; System.exit(0);
                    validCommandProcessed = true;
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
            System.out.println("Look around, you see a paths leading ");
             if (player.getPlayerLocation().northRoom != null){
                 System.out.println("North");
             }if (player.getPlayerLocation().eastRoom != null){
                 System.out.println("East");
             }if (player.getPlayerLocation().southRoom != null){
                 System.out.println("South");
             }if (player.getPlayerLocation().westRoom != null){
                 System.out.println("West");}
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
        public void takeItemFromRoom (String itemName){
            Room currentRoom = player.getPlayerLocation();
            itemName = itemName.trim().toLowerCase();
            Item foundItem = null;
            for (Item item : currentRoom.getItems()) {
                if (item.getItemName().toLowerCase().replaceAll("\\s+", "").contains(itemName.replaceAll("\\s+", ""))) {
                    foundItem = item;
                    break;
                }
            }
            if (foundItem != null) {
                player.getInventory().add(foundItem);
                currentRoom.getItems().remove(foundItem);
                System.out.println("You picked up " + foundItem.getItemName() + " \n" + foundItem.getItemDescription());
            } else {
                System.out.println("Item not found in the room.");
            }
        }
    public void eatFood(String itemName) {
        Room currentRoom = player.getPlayerLocation();
        itemName = itemName.trim().toLowerCase();
        Item foodToEat = null;
        for (Item food : player.getInventory()) {
            if (food.getItemName().toLowerCase().replaceAll("\\s+", "").contains(itemName.replaceAll("\\s+", ""))) {
                foodToEat = food;
                break;
            }
        }
        if (foodToEat != null) {
            if (foodToEat instanceof Food) {
                int currentHealth = player.getHealth();
                int healthMod = ((Food) foodToEat).getHealthMod();
                Food food = (Food) foodToEat;
                System.out.println("You consume " + food.getItemName() + "\nIt is " + food.getEffect());
                player.setHealth(currentHealth + healthMod);
                System.out.println(player.getHealth());
                gameOverCheck();
            } else {
                System.out.println("This is not edible.");
            }
            player.getInventory().remove(foodToEat);
        } else {
            System.out.println("Item not found in your inventory.");
        }
    }

        public void removeItemFromInventory (String itemName){
        Room currentRoom = player.getPlayerLocation();
        itemName = itemName.trim().toLowerCase();
        Item item = null;
        for (Item inventoryItem : player.getInventory()) {
            if (inventoryItem.getItemName().toLowerCase().replaceAll("\\s+", "").contains(itemName.replaceAll("\\s+", ""))) {
                item = inventoryItem;
                break;
            }
        }
            if (item != null) {
                player.getInventory().remove(item);
                currentRoom.addItem(item);
                System.out.println("You dropped " + item.getItemName());
            } else {
                System.out.println("Item not found in inventory.");
            }
        }

        public void moveTo(Room requestedRoom) {
            if (requestedRoom == null) {
                System.out.println();
                System.out.println("There's nothing in that direction");
        } else if (!requestedRoom.isVisited()) {
                int currentHealth = player.getHealth();
                System.out.println("You move forward through the keep");
                requestedRoom.setVisited();
                player.setPlayerLocation(requestedRoom);
                System.out.println(player.getPlayerLocation().getRoomDesc());
                player.setHealth(currentHealth - 1);
                System.out.println( "The way through the ruins is tiring ");
                System.out.println("You have " + player.getHealth() + " health points.");
                map.setCurrentRoom(player.getPlayerLocation());
                gameOverCheck();
            } else {
                int currentHealth = player.getHealth();
                player.setPlayerLocation(requestedRoom);
                player.setHealth(currentHealth - 1);
                System.out.println( "The way through the ruins is tiring ");
                System.out.println("You have " + player.getHealth() + " health points.");
                System.out.println("You return to " + player.getPlayerLocation().getRoomName());
                map.setCurrentRoom(player.getPlayerLocation());
                gameOverCheck();
           }
    }
}

