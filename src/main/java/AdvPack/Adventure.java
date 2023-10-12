package AdvPack;
import ItemPack.*;
import MapPack.*;
import PlayerPack.Player;
import RoomPack.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Adventure {
    private Map map;
    private Room currentRoom;
    private Player player;

    private boolean inCombat;
    private boolean playerTurn;
    private boolean turn1;
    private Room previousRoom;

    private ItemDatabase itemDatabase;
    private Scanner keyboard;
    private String input;
    private String[] inputTokens;

    public Adventure (Map map) {
        this.map = map;
        this.player = new Player(map);
        this.keyboard = new Scanner(System.in);
        this.inputTokens = new String[0];
    }
    public void newGame() {
        this.map = new Map();
        this.currentRoom = map.getStartingRoom();
        this.player = new Player(map);

    }
    public void handleUserInput(String userInput) {

        userInput = userInput.replace("go", "")
                .replace("around", "")
                .replace("for", "")
                .replace("move ", "")
                .replace("display ", "")
                .replace("i ", " ")
                .replace("room", "")
                .replace("show ", "")
                .replace("the", "")
                .replace("grab", "take")
                .replace("pickup", "take")
                .replace("leave", "drop")
                .replace("dump", "drop")
                .replace("shoot", "attack")
                .replaceAll("\\s+", " ")  // This is done only once now
                .trim();

        setInput(userInput);
        inputTokens = userInput.split("\\s+", 2);
    }


    public void setInputTokens(String[] tokens) {
        inputTokens = tokens;
    }

    public String[] getInputTokens() {
        return inputTokens;
    }


    public void setInput(String input) {
        this.input = input;
    }
    public String getInput() {
        return input;
    }



    public Map getMap() {
        return map;
    }
    public Room getPreviousRoom() {
        return previousRoom;
    }
    public void setPreviousRoom(Room previousRoom) {
        this.previousRoom = previousRoom;
    }
    public Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void setCurrentRoom(Room room) {

        currentRoom = room;

        }

    ////////////////////////////////////
    public void attack(Enemy targetEnemy) {
        Player player = getPlayer();
        Weapon weapon = player.getEquippedWeapon();
        int damage = 5;

        if (player.hasEquippedWeapon()) {
            int weaponDamage = weapon.getDamage();

            if (!weapon.isRanged()) {
                damage = 20 + weaponDamage;
            } else if (weapon.isRanged() && !weapon.isOutOfAmmo()) {
                int arrows = player.getEquippedWeapon().getAmmo();
                damage = weaponDamage;
                weapon.setAmmo(arrows - 1);
            } else {
                System.out.println("You're out of ammunition!");
                return;
            }
        }

        String confirmMessage = "You are about to attack the " + targetEnemy.getEnemyName() +
                ". Input 'yes' to confirm, or 'no' to select another target.";
        System.out.println(confirmMessage);
        handleUserInput(keyboard.nextLine());
        String input = getInput();


        if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
            int enemyHp = targetEnemy.getHealth();
            enemyHp -= damage;
            System.out.println("You hit the " + targetEnemy.getEnemyName() + " for " + damage + " damage!");
            targetEnemy.setHealth(enemyHp);

            enemyDeath(targetEnemy);

            if (!getCurrentRoom().getEnemies().isEmpty()) {
                playerTurn = false;
                turn1 = false;
                enemyTurn();
            } else {
                System.out.println("You are victorious"); endCombat();
            }

        } else if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")) {
            System.out.println("Input new action:");
        } else {
            System.out.println("Invalid input. Input 'yes' to confirm, or 'no' to select another target.");

        }
    }


    public void enemyTurn() {
        while (inCombat  && !playerTurn ) {
            ArrayList<Enemy> enemyActual = getCurrentRoom().getEnemies();
            int damage = 0;

            if (enemyActual.isEmpty()) {
                inCombat = false;
                System.out.println("You've won the fight!");
                break;
            } else
                System.out.println(enemyActual.size() + " enemies remain.");
            for (Enemy enemy : enemyActual) {
                System.out.println(enemy.weapon);
                int hp;
                hp = getPlayer().getHealth();
                damage = enemy.weapon.getDamage();
                int newHp = hp - damage;
                getPlayer().setHealth(newHp);
                System.out.println(enemy.getEnemyName() + " attacks for " + damage + " damage!");
                System.out.println("Your health: " + newHp);
                gameOverCheck();

                playerTurn = true;
            }
        }
    }

    public void enemyDeath(Enemy enemy) {
        int hp = enemy.getHealth();
        if (hp <= 0) {
            Item toLoot = enemy.weapon;
            getCurrentRoom().addItem(toLoot);
            getCurrentRoom().getEnemies().remove(enemy);
            System.out.println( "The " + enemy.getEnemyName() + " dies a gruesome death.");
        }
    }

    public void inspect() {
        System.out.println("Inspect method called");
        Room battleRoom = getCurrentRoom();
        ArrayList<Enemy> enemyActual = battleRoom.getEnemies();
        for (Enemy enemy : enemyActual)
            System.out.println(enemy);
    }


    public void combatOptions() {
        System.out.println("Brace yourself! - you can't escape the room in the first round of combat!");
        System.out.println("Until you've slain all enemies, you can only go back the way you came.");
        System.out.println("You've access to your inventory, and items, but once you've attacked your turn ends!");
        System.out.println("To take another look at the enemies before you, try 'inspect''flee' for a quick escape!");

    }

    public void help() {
        System.out.println("You are currently standing in " + getCurrentRoom().getRoomName());
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
        System.out.println(getCurrentRoom());
        System.out.println("Looking around, you see paths leading... ");
        if (getCurrentRoom().northRoom != null) {
            System.out.println("... North ");
        }
        if (getCurrentRoom().eastRoom != null) {
            System.out.println("... East");
        }
        if (getCurrentRoom().southRoom != null) {
            System.out.println("... South");
        }
        if (getCurrentRoom().westRoom != null) {
            System.out.println("... West");
        }
    }

    public void displayHealth() {
        System.out.println("You have " + getPlayer().getHealth() + " health points.");
        if (getPlayer().getHealth() > 0 && getPlayer().getHealth() <= 25) {
            System.out.println("You are gravely wounded, you should rest and tend to your wounds");
        }
        if (getPlayer().getHealth() > 25 && getPlayer().getHealth() <= 50) {
            System.out.println("You are injured, be wary");
        }
        if (getPlayer().getHealth() > 50 && getPlayer().getHealth() <= 75) {
            System.out.println("You've suffered some wounds");
        }
        if (getPlayer().getHealth() > 75) {
            System.out.println("You're in good form, fight on!");
        }
    }

    public void startCombat(Room currentRoom) {
        System.out.println("You're in battle! \nInput 'options' to see the rules of engagement!");
        inCombat = true;
        turn1 = true;
        playerTurn = true;
        ArrayList<Enemy> enemies = getCurrentRoom().getEnemies();
        for (Enemy enemy : enemies)
            System.out.println(enemy);

    }

    public void gameOverCheck() {
        if (getPlayer().getHealth() <= 0) {
            System.out.println("Death comes for us all.");
            System.out.println("Game Over!");
            System.exit(0);
        }
        if (getPlayer().getHealth() == 100) {
            System.out.println("You are sated and at full health");
        }
    }
    public void moveTo(Room requestedRoom) {
        if (requestedRoom == null) {
            System.out.println("There's nothing that way!");
        } else {
            if (inCombat && !turn1) {
                flee();
            }
            if (!requestedRoom.isVisited()) {
                requestedRoom.setVisited();
                System.out.println(requestedRoom);
            } else {
                System.out.println(requestedRoom.getRoomName());
            }
            setPreviousRoom(getCurrentRoom());
            setCurrentRoom(requestedRoom);

            moveTax();
        }
    }
    public void moveTax() {
        int currentHealth = getPlayer().getHealth();
        System.out.println("Making your way through the ruins is a taxing task ");
        getPlayer().setHealth(currentHealth - 1);
        System.out.println("You have " + getPlayer().getHealth() + " health points.");
        if (!getCurrentRoom().getEnemies().isEmpty()) {

            startCombat(getCurrentRoom());
        }
        gameOverCheck();
    }
    public void flee() {
        if (!turn1) {
            setCurrentRoom(getPreviousRoom());
            inCombat = false;
            currentRoom = getPreviousRoom();
        } else {
            System.out.println("You cannot flee in round one");
        }
    }

    public void search() {
        System.out.println("You search the room for treasures.");
        System.out.println();
        List<Item> itemsInRoom = getCurrentRoom().getItems();
        if (itemsInRoom != null && !itemsInRoom.isEmpty()) {
            for (Item item : itemsInRoom) {
                System.out.println("You find " + item.getItemName());
            }
        } else {
            System.out.println("This room is void of food and treasure");
        }
    }

    public void displayInventory() {
        System.out.println("You search your bag... ");
        if (getPlayer().inventory.isEmpty()) {
            System.out.println(" ... It remains empty.");
        } else {
            System.out.println(".. And find its contents within. Just as you left them: ");
            System.out.println();//Linebreak
            List<Item> inventory = getPlayer().getInventory();
            for (Item item : inventory) {
                System.out.println(item);}

            if (getPlayer().hasEquippedWeapon()){
                System.out.println("You're wielding the " + getPlayer().getEquippedWeapon().getItemName()
                        + " \n" + getPlayer().getEquippedWeapon().getItemDescription()  );
            }
            else System.out.println("You should arm yourself.");
        }
    }


    public void takeOrDropItem(String itemName, boolean inInventory) {
        ArrayList<Item> loot = getCurrentRoom().getItems();

        Item foundItem = null;
        for (Item item : player.inventory) {
            if (item.getItemName().toLowerCase().replaceAll("\\s+", " ").contains(itemName)) {
                foundItem = item;
                break;
            }
        }
        if (foundItem == null) {
            for (Item item : loot) {
                if (item.getItemName().toLowerCase().replaceAll("\\s+", " ").contains(itemName)) {
                    foundItem = item;
                    break;
                }
            }
        }
        if (foundItem != null) {
            if (!inInventory && loot.contains(foundItem)) {
                player.inventory.add(foundItem);
                loot.remove(foundItem);
                System.out.println("You picked up " + foundItem.getItemName());
            } else {
                if (inInventory && player.inventory.contains(foundItem)) {
                    loot.add(foundItem);
                    player.inventory.remove(foundItem);
                    System.out.println("You dropped " + foundItem.getItemName());
                } else {
                    System.out.println("What are you looking for?");
                }
            }
        }
    }
    public void unEquipWeapon() {
        if (getPlayer().hasEquippedWeapon()) {
            Item equippedWeapon = getPlayer().getEquippedWeapon();
            getPlayer().getInventory().add(equippedWeapon);
            getPlayer().setHasEquippedWeapon(false);
            System.out.println("You've unequipped the " + equippedWeapon.getItemName());
        } else {
            System.out.println("You are not wielding a weapon.");
        }
    }
    public void equipWeapon(String toEquip) {
        if (getPlayer().hasEquippedWeapon()) {
            System.out.println("You must unequip your current weapon.");
        } else {
            Weapon weapontoEquip = null;
            List<Item> weaponsToRemove = new ArrayList<>(); //created a seperate array for items to remove, in an attempt to avoid and exception error

            for (Item item : getPlayer().getInventory()) {
                if (item.isEquippable && item.getItemName().toLowerCase().contains(toEquip)) {
                    weapontoEquip = (Weapon) item;
                    break;
                }
            }

            if (weapontoEquip != null) {
                getPlayer().setEquippedWeapon(weapontoEquip);
                getPlayer().setHasEquippedWeapon(true);
                System.out.println("You've equipped " + weapontoEquip.getItemName());
                System.out.println(weapontoEquip.getAbility());
                weaponsToRemove.add(weapontoEquip);
            } else {
                System.out.println("No equippable weapon called " + toEquip + " found in your inventory.");
            }

            // Remove weapons after the loop
           getPlayer().getInventory().removeAll(weaponsToRemove);
        }
    }
    public void eatOrDrink(String itemName, boolean isEating) {
        boolean canConsume = false;
        for (Item item : player.inventory) {
            if (isEating && item.isEdible || !isEating && item.isLiquid) {
                Item ration = item;
                String rationName = ration.getItemName().toLowerCase().replaceAll("\\s+", "");

                if (rationName.contains(itemName.replaceAll("\\s+", ""))) {
                    int currentHealth = getPlayer().getHealth();
                    int healthMod = ration.getHealthMod();
                    System.out.println(item);
                    getPlayer().setHealth(currentHealth + healthMod);
                    System.out.println("You have " + getPlayer().getHealth() + " health points left.");
                    getPlayer().getInventory().remove(ration);
                    canConsume = true;
                    gameOverCheck();
                    break;
                }
            }
        }
        if (!canConsume) {
            System.out.println("You're doing it wrong.");
        }
    }
    ////////////////////////////////////////////
    public boolean isInCombat() {
        return inCombat;
    }

    public void endCombat(){
        inCombat = false;
    }
    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public boolean isTurn1() {
        return turn1;
    }


    public ItemDatabase getItemDatabase() {
        return itemDatabase;
    }
    public void setItemDatabase(ItemDatabase itemDatabase) {
        this.itemDatabase = itemDatabase;
    }

    public int enemyAttack(Enemy enemy) {
        int damage = 0;

        if (enemy == null) {
            return damage;
        }
        Weapon eNweapon = enemy.weapon;
        int weaponDamage = eNweapon.getDamage();
        int unique = enemy.getUnique();

        if (enemy.hasEquippedWeapon() && !eNweapon.isRanged()) {
            weaponDamage = eNweapon.getDamage();
            damage = 1 + unique + weaponDamage;
        } else if (enemy.hasEquippedWeapon() && eNweapon.isRanged()) {
            damage = weaponDamage;
        } else if (!enemy.hasEquippedWeapon()) {
            damage = 1 + unique;
        }

        return damage;
        }

    public void displayQuiver() {
        if (player.hasEquippedWeapon() && player.getEquippedWeapon().isRanged()) {
            System.out.println("You have " + player.getEquippedWeapon().getAmmo() + " arrows left in your quiver.");

        } else {
            System.out.println("You're not equipped with a ranged weapon.");

        }
    }
    }




