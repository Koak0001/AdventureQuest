package UserInterfacePack;
import ItemPack.*;
import ItemPack.ItemType;
import ItemPack.FoodType;
import AdvPack.Adventure;
import MapPack.Map;
import PlayerPack.Player;
import RoomPack.Room;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static ItemPack.FoodType.FOOD;
import static ItemPack.FoodType.POTION;

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
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Let the Adventure begin!");
        System.out.println(map.getCurrentRoom());
        player.getPlayerLocation().setVisited();
        System.out.println("Type 'look around' to see where you might go.");
        System.out.println("Type help for more input options.");
        while (!exit) {
            String userInput = keyboard.nextLine().toLowerCase();
            userInput = userInput.replace("go", "")
                    .replace("around", "")
                    .replace("for", "")
                    .replace("move ", "")
                    .replace("display ", "")
                    .replace("\\s+", " ")
                    .replace("i ", " ")
                    .replace("room", "")
                    .replace("show ", "")
                    .replace("the", "")
                    .trim();
            String input = userInput;
            String[] inputTokens = userInput.split("\\s+", 2);

//            InputTokens results, for debugging purposes.
//            System.out.println("Input: " + userInput);
//            System.out.println("InputTokens: " + Arrays.toString(inputTokens));
            boolean validCommandProcessed = false;
            boolean noPath = false;

            switch (input) {
                case "north", "n" -> {
                    moveTo(map.getCurrentRoom().northRoom);
                    validCommandProcessed = true;
                }
                case "south", "s" -> {
                    moveTo(map.getCurrentRoom().southRoom);
                    validCommandProcessed = true;
                }
                case "west", "w" -> {
                    moveTo(map.getCurrentRoom().westRoom);
                    validCommandProcessed = true;
                }
                case "east", "e" -> {
                    moveTo(map.getCurrentRoom().eastRoom);
                    validCommandProcessed = true;
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
                    if (player.hasEquippedWeapon()){
                    displayInventory();
                    System.out.println("You're wielding " + player.getEquippedWeapon());
                    }
                    else {displayInventory();}
                    validCommandProcessed = true;
                }
                case "quiver" -> {
                    if (player.hasEquippedWeapon() && player.getEquippedWeapon().isRanged()){
                        System.out.println("You have " + player.getEquippedWeapon().getAmmo() + " arrows left in your quiver.");}
                    else{
                        System.out.println("You're not equipped with a ranged weapon.");
                    } validCommandProcessed = true;

                }
                case "attack" -> {
                    int strikeDamage = player.attack();
                    if (player.hasEquippedWeapon() && player.getEquippedWeapon().isRanged() && player.getEquippedWeapon().isOutOfAmmo()){
                        System.out.println("Your quiver is empty!");
                    }if (player.hasEquippedWeapon() && player.getEquippedWeapon().isRanged() && !player.getEquippedWeapon().isOutOfAmmo()){
                        System.out.println("You let loose an arrow with the " + player.getEquippedWeapon().getItemName());
                        System.out.println(player.getEquippedWeapon().getAbility());
                        System.out.println();
                        System.out.println(strikeDamage + " damage!");
                        System.out.println("You have " + player.getEquippedWeapon().getAmmo() + " arrows left in your quiver.");
                    }if (player.hasEquippedWeapon() && !player.getEquippedWeapon().isRanged()){
                        System.out.println("You strike with " + player.getEquippedWeapon().getItemName());
                        System.out.println( strikeDamage + " damage!");//break
                        System.out.println(player.getEquippedWeapon().getAbility());
                    }if (!player.hasEquippedWeapon()){
                        System.out.println("Unarmed, you pose little threat");
                        System.out.println(strikeDamage + " damage.");
                    }
                    validCommandProcessed = true;
                }
                case "healthbar", "health" -> {
                    displayHealth();
                    validCommandProcessed = true;
                }
                case "unequip" -> {
                    unEquipWeapon();
                    validCommandProcessed = true;
                }
            }
            if (!validCommandProcessed && inputTokens.length == 2) {

                String itemName = inputTokens[1].toLowerCase().trim();
                if (inputTokens[0].equals("take") || inputTokens[0].equals("grab") || inputTokens[0].equals("pickup")) {
                    takeOrDropItem(itemName, false);
                }
                if (inputTokens[0].equals("drop") || inputTokens[0].equals("leave") || inputTokens[0].equals("dump")) {
                    takeOrDropItem(itemName, true);
                }
                if (inputTokens[0].equals("eat")) {
                    eatOrDrink(itemName, true);
                }
                if (inputTokens[0].equals("equip")) {
                    setEquippedWeapon(itemName);
                }
                if (inputTokens[0].equals("drink")) {
                    eatOrDrink(itemName, false);
                }
            } else if (!validCommandProcessed) {
                System.out.println("Invalid input");
            }
        }
    }
    public void help() {
        System.out.println("You are currently standing in " + map.getCurrentRoom().getRoomName());
        System.out.println("There you may move move in 4 directions");
        System.out.println("North, South, East, or West");
        System.out.println("Type 'Look around' to see which directions you may move to");
        System.out.println("'Search' to search the room.");
        System.out.println("'Health', or 'Display Healthbar' to see current Health Points");
        System.out.println("'Inventory' to view your inventory.");
        System.out.println("You may 'equip' and 'unequip' found weapons");
        System.out.println("And you have access to a 'quiver' too.");
        System.out.println("If you wish to take or drop an item, simply input take or drop, and the item's name");
        System.out.println("And use 'eat' and 'drink' for food and drinks");
        System.out.println("Type exit to exit the game");
    }
    public void look() {
        System.out.println("You are standing in ");
        System.out.println(map.getCurrentRoom());
        System.out.println("Looking around, you see paths leading... ");
        if (player.getPlayerLocation().northRoom != null) {
            System.out.println("... North ");
        }
        if (player.getPlayerLocation().eastRoom != null) {
            System.out.println("... East");
        }
        if (player.getPlayerLocation().southRoom != null) {
            System.out.println("... South");
        }
        if (player.getPlayerLocation().westRoom != null) {
            System.out.println("... West");
        }
    }
    public void displayHealth() {
        System.out.println("You have " + player.getHealth() + " health points.");
        if (player.getHealth() > 0 && player.getHealth() <= 25) {
            System.out.println("You are gravely wounded, you should rest and tend to your wounds");
        }
        if (player.getHealth() > 25 && player.getHealth() <= 50) {
            System.out.println("You are injured, be wary");
        }
        if (player.getHealth() > 50 && player.getHealth() <= 75) {
            System.out.println("You've suffered some wounds");
        }
        if (player.getHealth() > 75) {
            System.out.println("You're in good form, fight on!");
        }
    }
    public void gameOverCheck() {
        if (player.getHealth() <= 0) {
            System.out.println("Death comes for us all.");
            System.out.println("Game Over!");
            System.exit(0);
        }
        if (player.getHealth() == 100) {
            System.out.println("You are sated and at full health");
        }
    }

    public void moveTo(Room requestedRoom) {
        if (requestedRoom == null) {
            System.out.println();
        } else if (!requestedRoom.isVisited()) {
            requestedRoom.setVisited();
            player.setPlayerLocation(requestedRoom);
            moveTax();
            System.out.println(requestedRoom);
        } else {
            player.setPlayerLocation(requestedRoom);
            System.out.println(requestedRoom.getRoomName());
            moveTax();
        }
    }
    public void moveTax() {
        int currentHealth = player.getHealth();
        map.setCurrentRoom(player.getPlayerLocation());
        System.out.println("Making your way through the ruins is a gruelling task ");
        player.setHealth(currentHealth - 1);
        System.out.println("You have " + player.getHealth() + " health points.");
        gameOverCheck();
    }
        public void search() {
            System.out.println("You search the room for treasures.");
            System.out.println();
            List<Item> itemsInRoom = map.getCurrentRoom().getItems();
            if (itemsInRoom != null && !itemsInRoom.isEmpty()) {
                for (Item item : itemsInRoom) {
                    System.out.println("You find " + item.getItemName());
                }
            } else {
                System.out.println("This room is void food or treasures");
            }
        }

    public void displayInventory() {
        System.out.println("You search your bag... ");
        if (player.inventory.isEmpty()) {
            System.out.println(" ... It remains empty.");
        } else {
            System.out.println(".. And find its contents within. Just as you left them: ");
            for (Item item : player.inventory) {
                System.out.println(item.getItemName() + " , " + item.getItemDescription());
            }
        }
    }
    public void takeOrDropItem(String itemName, boolean inInventory) {
        Room currentRoom = player.getPlayerLocation();
        Item foundItem = null;

        for (Item item : player.getInventory()) {
            if (item.getItemName().toLowerCase().replaceAll("\\s+", "").contains(itemName.toLowerCase().replaceAll("\\s+", ""))) {
                foundItem = item;
                break;
            }
        }
        if (foundItem == null) {
            for (Item item : currentRoom.getItems()) {
                if (item.getItemName().toLowerCase().replaceAll("\\s+", "").contains(itemName.toLowerCase().replaceAll("\\s+", ""))) {
                    foundItem = item;
                    break;
                }
            }
        }
        if (foundItem != null) {
            if (!inInventory) {
                player.getInventory().add(foundItem);
                if (currentRoom.getItems().contains(foundItem)) {
                    currentRoom.getItems().remove(foundItem);
                }
                System.out.println("You picked up " + foundItem.getItemName());
                System.out.println();
            } else {
                if (player.getInventory().contains(foundItem)) {
                    currentRoom.addItem(foundItem);
                    player.getInventory().remove(foundItem);
                    System.out.println("You dropped " + foundItem.getItemName());
                } else {
                    System.out.println("That might not be where you think it is");
                }
            }
        } else {
            System.out.println("Item not found.");
        }
    }
    public void unEquipWeapon() {
        if (player.hasEquippedWeapon()) {
            Item equippedWeapon = player.getEquippedWeapon();
            player.getInventory().add(equippedWeapon); // Add the unequipped weapon back to the inventory
            player.setHasEquippedWeapon(false);
            System.out.println("You've unequipped the " + equippedWeapon.getItemName());
        } else {
            System.out.println("You are not wielding a weapon.");
        }
    }
    public void setEquippedWeapon(String toEquip) {
        if (player.hasEquippedWeapon()) {
            System.out.println("You must unequip your current weapon.");
        } else {
            Weapon equippedWeapon = null;
            for (Item item : player.getInventory()) {
                if (item instanceof Weapon) {
                    Weapon weaponToEquip = (Weapon) item;
                    if (weaponToEquip.getItemName().toLowerCase().replaceAll("\\s+", "").contains(toEquip.replaceAll("\\s+", ""))) {
                        equippedWeapon = weaponToEquip;
                        break;
                    }
                }
            }
            if (equippedWeapon != null) {
                player.getInventory().remove(equippedWeapon);
                player.setEquippedWeapon(equippedWeapon);
                player.setHasEquippedWeapon(true);
                System.out.println("You've equipped " + equippedWeapon.getItemName());
                System.out.println(equippedWeapon.getAbility());
                System.out.println();
            } else {
                System.out.println("No weapon with the name " + toEquip + " found in your inventory.");
            }
        }
    }
    public void eatOrDrink(String itemName, boolean isEating) {
           boolean rations = false;
           for (Item item : player.getInventory()) {
               if (isEating && item.isEdible || !isEating && item.isLiquid) {
                   Item ration = item;
                   String rationName = ration.getItemName().toLowerCase().replaceAll("\\s+", "");

                       if (rationName.contains(itemName.replaceAll("\\s+", ""))) {
                           int currentHealth = player.getHealth();
                           int healthMod = ration.getHealthMod();
                           System.out.println(item);
                           player.setHealth(currentHealth + healthMod);
                           System.out.println("You have " + player.getHealth() + " health points left.");
                           player.getInventory().remove(ration);
                           rations = true;
                           gameOverCheck();
                           break;
                       }
                   }
               }
               if (!rations) {
                   System.out.println("Item not found in your inventory or not edible.");
           }


    }

}