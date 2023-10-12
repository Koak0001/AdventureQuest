package UserInterfacePack;
import AdvPack.CombatCommand;
import AdvPack.GeneralCommand;
import ItemPack.*;
import AdvPack.Adventure;
import MapPack.*;
import PlayerPack.Player;
import RoomPack.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    public Adventure adventure;
    private Scanner keyboard;
    private Room currentRoom;


    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
        Player player = adventure.getPlayer();
        adventure.getPlayer().setHealth(100);
        this.keyboard = new Scanner(System.in);
        play();
    }

    public void play() {
        adventure.newGame();

        boolean exit = false;
        boolean inCombat = adventure.isInCombat();
        boolean playerTurn = adventure.isPlayerTurn();
        boolean turn1 = adventure.isTurn1();

        currentRoom = adventure.getCurrentRoom();
        Player player = adventure.getPlayer();
        currentRoom.setVisited();
        System.out.println(currentRoom);

        System.out.println("Let the Adventure begin!");
        System.out.println(); //Linebreak
        System.out.println("Type 'look around' to see where you might go.");
        System.out.println(); //Linebreak
        System.out.println("Type help for more input options.");
        adventure.endCombat();
        while (!exit) {
            String userInput = keyboard.nextLine();
            adventure.handleUserInput(userInput);
            String input = adventure.getInput();
            String[] inputTokens = adventure.getInputTokens();
            System.out.println("Input: ");
            System.out.println("InputTokens: " + Arrays.toString(inputTokens));
            System.out.println("You are currently in: " + adventure.getCurrentRoom().getRoomName());

            boolean validInput = false;

            if (adventure.isInCombat()) {
                CombatCommand combatCommand = null;

                if (inputTokens[0].equalsIgnoreCase("attack")) {
                    combatCommand = CombatCommand.ATTACK;
                } else {
                    try {
                        combatCommand = CombatCommand.valueOf(inputTokens[0].toUpperCase());
                    } catch (IllegalArgumentException e) {
                    }
                }

                if (combatCommand != null) {
                    combatCommand.execute(inputTokens, adventure, currentRoom);
                    validInput = true;
                }
            }


            if (!validInput) {
                GeneralCommand generalCommand = null;
                try {
                    generalCommand = GeneralCommand.valueOf(inputTokens[0].toUpperCase());
                } catch (IllegalArgumentException e) {

                }

                if (generalCommand != null) {
                    generalCommand.execute(inputTokens, adventure);
                    validInput = true;
                }
            }


            if (!validInput && inputTokens.length == 2) {
                switch (inputTokens[0]) {
                    case "take" -> {
                        String itemName = inputTokens[1];
                        adventure.takeOrDropItem(itemName, false);
                        validInput = true;
                    }
                    case "drop" -> {
                        String itemName = inputTokens[1];
                        adventure.takeOrDropItem(itemName, true);
                        validInput = true;
                    }
                }
            }
            currentRoom = adventure.getCurrentRoom();

            // Commands exclusive for outside combat
            if (!validInput && !adventure.isInCombat()) {
                switch (input) {
                    case "north", "n" -> {
                        adventure.moveTo(currentRoom.northRoom);
                        validInput = true;
                    }
                    case "south", "s" -> {
                        adventure.moveTo(currentRoom.southRoom);
                        validInput = true;
                    }
                    case "west", "w" -> {
                        adventure.moveTo(currentRoom.westRoom);
                        validInput = true;
                    }
                    case "east", "e" -> {
                        adventure.moveTo(currentRoom.eastRoom);
                        validInput = true;
                    }
                    case "help" -> {
                        adventure.help();
                        validInput = true;
                    }
                    case "exit", "quit" -> {
                        exit = true;
                        System.exit(0);
                    }
                    case "look" -> {
                        adventure.look();
                        validInput = true;
                    }
                    case "search" -> {
                        adventure.search();
                        validInput = true;
                    }
                    case "healthbar", "health" -> {
                        adventure.displayHealth();
                        validInput = true;
                    }
                    case "unequip" -> {
                        adventure.unEquipWeapon();
                        validInput = true;
                    }
                    case "inventory", "bag" -> {
                        adventure.displayInventory();
                        validInput = true;
                    }
                    default -> System.out.println("input not recognized");
                }
            }
        }
    }
}

//    private Adventure getAdventure() {
//        return adventure;
//    }

//    public void attack(Enemy targetEnemy) {
//        Player player = adventure.getPlayer();
//        Weapon weapon = player.getEquippedWeapon();
//        int damage = 0;
//
//        if (player.hasEquippedWeapon()) {
//            int weaponDamage = weapon.getDamage();
//
//            if (!weapon.isRanged()) {
//                damage = 25 + weaponDamage;
//            } else if (weapon.isRanged() && !weapon.isOutOfAmmo()) {
//                int arrows = player.getEquippedWeapon().getAmmo();
//                damage = weaponDamage;
//                weapon.setAmmo(arrows - 1);
//            } else {
//                // Handle "out of ammo" scenario externally or return a special value/error
//                return;
//            }
//        }
//
//        String confirmMessage = "You are about to attack the " + targetEnemy.getEnemyName() +
//                ". Input 'yes' to confirm, or 'no' to select another target.";
//        adventure.handleUserInput(keyboard.nextLine());
//        String input = adventure.getInput(); // Assuming getInput() is a method in your controller that fetches the processed input
//
//
//        if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
//            int enemyHp = targetEnemy.getHealth();
//            enemyHp -= damage;
//            targetEnemy.setHealth(enemyHp);
//
//            enemyDeath(targetEnemy);
//
//            if (!adventure.getCurrentRoom().getEnemies().isEmpty()) {
//                adventure.endPlayerTurn();
//                adventure.endTurn1();
//                enemyTurn();
//            } else {
//                // Handle "battle won" scenario externally or notify through return value
//            }
//
//        } else if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")) {
//            // Handle "action cancelled" scenario externally or notify through return value
//        } else {
//            String errorMessage = "Invalid input. Input 'yes' to confirm, or 'no' to select another target.";
//            // Handle this error message externally or return this as an error value
//        }
//    }
//
//
//    public void enemyTurn() {
//        while (adventure.isInCombat() && !adventure.isPlayerTurn()) {
//            ArrayList<Enemy> enemyActual = adventure.getCurrentRoom().getEnemies();
//            int damage = 0;
//
//            if (enemyActual.isEmpty()) {
//                adventure.endCombat();
//                System.out.println("You've won the fight!");
//                break;
//            } else
//                System.out.println(enemyActual.size() + " enemies remain.");
//            for (Enemy enemy : enemyActual) {
//                System.out.println(enemy.weapon);
//                int hp;
//                hp = adventure.getPlayer().getHealth();
//                damage = enemy.weapon.getDamage();
//                int newHp = hp - damage;
//                adventure.getPlayer().setHealth(newHp);
//                System.out.println(enemy.getEnemyName() + " attacks for " + damage + " damage!");
//                System.out.println("Your health: " + newHp);
//                gameOverCheck();
//
//                adventure.startPlayerTurn();
//            }
//        }
//    }
//
//    public void enemyDeath(Enemy enemy) {
//        int hp = enemy.getHealth();
//        if (hp <= 0) {
//            Item toLoot = enemy.weapon;
//            adventure.getCurrentRoom().addItem(toLoot);
//            adventure.getCurrentRoom().getEnemies().remove(enemy);
//            System.out.println( "The " + enemy.getEnemyName() + " dies a gruesome death.");
//        }
//    }
//
//    public void inspect() {
//        System.out.println("Inspect method called");
//        Room battleRoom = adventure.getCurrentRoom();
//        ArrayList<Enemy> enemyActual = battleRoom.getEnemies();
//        for (Enemy enemy : enemyActual)
//            System.out.println(enemy);
//    }
//
//
//    public void combatOptions() {
//        System.out.println("Brace yourself! - you can't escape the room in the first round of combat!");
//        System.out.println("Until you've slain all enemies, you can only go back the way you came.");
//        System.out.println("You've access to your inventory, and items, but once you've attacked your turn ends!");
//        System.out.println("To take another look at the enemies before you, try 'inspect''flee' for a quick escape!");
//
//    }
//
//    public void help() {
//        System.out.println("You are currently standing in " + adventure.getCurrentRoom().getRoomName());
//        System.out.println("There you may move move in 4 directions");
//        System.out.println("North, South, East, or West");
//        System.out.println("Type 'Look around' to see which directions you may move to");
//        System.out.println("'Search' to search the room.");
//        System.out.println("'Health', or 'Display Healthbar' to see current Health Points");
//        System.out.println("'Inventory' to view your inventory.");
//        System.out.println("You may 'equip' and 'unequip' found weapons");
//        System.out.println("And you have access to a 'quiver' too.");
//        System.out.println("If you wish to take or drop an item, simply input take or drop, and the item's name");
//        System.out.println("And use 'eat' and 'drink' for food and drinks");
//        System.out.println("Type exit to exit the game");
//    }
//
//    public void look() {
//        System.out.println("You are standing in ");
//        System.out.println(adventure.getCurrentRoom());
//        System.out.println("Looking around, you see paths leading... ");
//        if (adventure.getCurrentRoom().northRoom != null) {
//            System.out.println("... North ");
//        }
//        if (adventure.getCurrentRoom().eastRoom != null) {
//            System.out.println("... East");
//        }
//        if (adventure.getCurrentRoom().southRoom != null) {
//            System.out.println("... South");
//        }
//        if (adventure.getCurrentRoom().westRoom != null) {
//            System.out.println("... West");
//        }
//    }
//
//    public void displayHealth() {
//        System.out.println("You have " + adventure.getPlayer().getHealth() + " health points.");
//        if (adventure.getPlayer().getHealth() > 0 && adventure.getPlayer().getHealth() <= 25) {
//            System.out.println("You are gravely wounded, you should rest and tend to your wounds");
//        }
//        if (adventure.getPlayer().getHealth() > 25 && adventure.getPlayer().getHealth() <= 50) {
//            System.out.println("You are injured, be wary");
//        }
//        if (adventure.getPlayer().getHealth() > 50 && adventure.getPlayer().getHealth() <= 75) {
//            System.out.println("You've suffered some wounds");
//        }
//        if (adventure.getPlayer().getHealth() > 75) {
//            System.out.println("You're in good form, fight on!");
//        }
//    }
//
//    public void startCombat(Room currentRoom) {
//        System.out.println("You're in battle! \nInput 'options' to see the rules of engagement!");
//        adventure.setInCombat();
//        adventure.setTurn1();
//        adventure.startPlayerTurn();
//        ArrayList<Enemy> enemies = adventure.getCurrentRoom().getEnemies();
//        for (Enemy enemy : enemies)
//            System.out.println(enemy);
//
//    }
//
//    public void gameOverCheck() {
//        if (adventure.getPlayer().getHealth() <= 0) {
//            System.out.println("Death comes for us all.");
//            System.out.println("Game Over!");
//            System.exit(0);
//        }
//        if (adventure.getPlayer().getHealth() == 100) {
//            System.out.println("You are sated and at full health");
//        }
//    }
//    public void moveTo(Room requestedRoom) {
//        if (requestedRoom == null) {
//            System.out.println("There's nothing that way!");
//        } else if (requestedRoom != null) {
//            if (adventure.isInCombat() && !adventure.isTurn1()) {
//                flee();
//            }
//            if (!requestedRoom.isVisited()) {
//                requestedRoom.setVisited();
//                System.out.println(requestedRoom);
//            } else {
//                System.out.println(requestedRoom.getRoomName());
//            }
//            adventure.setPreviousRoom(adventure.getCurrentRoom());
//            adventure.setCurrentRoom(requestedRoom);
//            currentRoom = requestedRoom;
//
//            moveTax();
//        }
//    }
//    public void moveTax() {
//        int currentHealth = adventure.getPlayer().getHealth();
//        System.out.println("Making your way through the ruins is a taxing task ");
//        adventure.getPlayer().setHealth(currentHealth - 1);
//        System.out.println("You have " + adventure.getPlayer().getHealth() + " health points.");
//        if (!adventure.getCurrentRoom().getEnemies().isEmpty()) {
//
//            startCombat(adventure.getCurrentRoom());
//        }
//        gameOverCheck();
//    }
//    public void flee() {
//        if (!adventure.isTurn1()) {
//            adventure.setCurrentRoom(adventure.getPreviousRoom());
//            adventure.endCombat();
//            currentRoom = adventure.getPreviousRoom();
//        } else {
//            System.out.println("You cannot flee in round one");
//        }
//    }
//
//    public void search() {
//        System.out.println("You search the room for treasures.");
//        System.out.println();
//        List<Item> itemsInRoom = adventure.getCurrentRoom().getItems();
//        if (itemsInRoom != null && !itemsInRoom.isEmpty()) {
//            for (Item item : itemsInRoom) {
//                System.out.println("You find " + item.getItemName());
//            }
//        } else {
//            System.out.println("This room is void of food and treasure");
//        }
//    }
//
//    public void displayInventory() {
//        System.out.println("You search your bag... ");
//        if (adventure.getPlayer().inventory.isEmpty()) {
//            System.out.println(" ... It remains empty.");
//        } else {
//            System.out.println(".. And find its contents within. Just as you left them: ");
//            System.out.println();//Linebreak
//            List<Item> inventory = adventure.getPlayer().getInventory();
//            for (Item item : inventory) {
//                System.out.println(item);}
//
//                if (adventure.getPlayer().hasEquippedWeapon()){
//                System.out.println("You're wielding the " + adventure.getPlayer().getEquippedWeapon().getItemName()
//                + " \n" + adventure.getPlayer().getEquippedWeapon().getItemDescription()  );
//                }
//                else System.out.println("You should arm yourself.");
//            }
//        }
//
//
//    public void takeOrDropItem(String itemName, boolean inInventory) {
//        ArrayList<Item> loot = adventure.getCurrentRoom().getItems();
//        Player player = this.adventure.getPlayer();
//        Item foundItem = null;
//        for (Item item : player.inventory) {
//            if (item.getItemName().toLowerCase().replaceAll("\\s+", " ").contains(itemName)) {
//                foundItem = item;
//                break;
//            }
//        }
//        if (foundItem == null) {
//            for (Item item : loot) {
//                if (item.getItemName().toLowerCase().replaceAll("\\s+", " ").contains(itemName)) {
//                    foundItem = item;
//                    break;
//                }
//            }
//        }
//        if (foundItem != null) {
//            if (!inInventory && loot.contains(foundItem)) {
//                player.inventory.add(foundItem);
//                loot.remove(foundItem);
//                System.out.println("You picked up " + foundItem.getItemName());
//            } else {
//                if (inInventory && player.inventory.contains(foundItem)) {
//                    loot.add(foundItem);
//                    player.inventory.remove(foundItem);
//                    System.out.println("You dropped " + foundItem.getItemName());
//                } else {
//                    System.out.println("What are you looking for?");
//                }
//            }
//        }
//    }
//    public void unEquipWeapon() {
//        if (adventure.getPlayer().hasEquippedWeapon()) {
//            Item equippedWeapon = adventure.getPlayer().getEquippedWeapon();
//            adventure.getPlayer().getInventory().add(equippedWeapon); // Add the unequipped weapon back to the inventory
//            adventure.getPlayer().setHasEquippedWeapon(false);
//            System.out.println("You've unequipped the " + equippedWeapon.getItemName());
//        } else {
//            System.out.println("You are not wielding a weapon.");
//        }
//    }
//    public void equipWeapon(String toEquip) {
//        if (adventure.getPlayer().hasEquippedWeapon()) {
//            System.out.println("You must unequip your current weapon.");
//        } else {
//            Weapon weapontoEquip = null;
//            List<Item> weaponsToRemove = new ArrayList<>();
//
//            for (Item item : adventure.getPlayer().getInventory()) {
//                if (item.isEquippable && item.getItemName().toLowerCase().contains(toEquip)) {
//                    weapontoEquip = (Weapon) item;
//                    break;
//                }
//            }
//
//            if (weapontoEquip != null) {
//                adventure.getPlayer().setEquippedWeapon(weapontoEquip);
//                adventure.getPlayer().setHasEquippedWeapon(true);
//                System.out.println("You've equipped " + weapontoEquip.getItemName());
//                System.out.println(weapontoEquip.getAbility());
//                weaponsToRemove.add(weapontoEquip); // Add to the list of weapons to remove
//            } else {
//                System.out.println("No equippable weapon called " + toEquip + " found in your inventory.");
//            }
//
//            // Remove the weapons after the loop
//            adventure.getPlayer().getInventory().removeAll(weaponsToRemove);
//        }
//    }
//    public void eatOrDrink(String itemName, boolean isEating) {
//           boolean canConsume = false;
//           for (Item item : adventure.getPlayer().getInventory()) {
//               if (isEating && item.isEdible || !isEating && item.isLiquid) {
//                   Item ration = item;
//                   String rationName = ration.getItemName().toLowerCase().replaceAll("\\s+", "");
//
//                       if (rationName.contains(itemName.replaceAll("\\s+", ""))) {
//                           int currentHealth = adventure.getPlayer().getHealth();
//                           int healthMod = ration.getHealthMod();
//                           System.out.println(item);
//                           adventure.getPlayer().setHealth(currentHealth + healthMod);
//                           System.out.println("You have " + adventure.getPlayer().getHealth() + " health points left.");
//                           adventure.getPlayer().getInventory().remove(ration);
//                           canConsume = true;
//                           gameOverCheck();
//                           break;
//                       }
//                   }
//               }
//               if (!canConsume) {
//                   System.out.println("You're doing it wrong.");
//           }
//        }



