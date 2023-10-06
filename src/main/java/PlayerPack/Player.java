package PlayerPack;
import ItemPack.Item;
import ItemPack.Weapon;
import MapPack.Map;
import RoomPack.Room;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final Map map;

    //    private Room requestRoom;
    private Room playerLocation;
    private Weapon equippedWeapon;
    boolean hasEquippedWeapon;
    private int health;
    public List<Item> inventory;


    public Player(Map map) {
        this.map = map;
        this.inventory = new ArrayList<>();
        this.playerLocation = map.getCurrentRoom();
        this.health = Math.min(100, health);
        this.equippedWeapon = null;
        this.hasEquippedWeapon = false;
    }

    public void setPlayerLocation(Room playerLocation) {
        this.playerLocation = playerLocation;
    }

    public Room getPlayerLocation() {
        return playerLocation;
    }

//    public void setRequestRoom(Room requestRoom) {
//        this.requestRoom = requestRoom;
//    }
//
//    public Room getRequestRoom() {
//        return requestRoom;
//    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void add(Item item) {
        if (item != null) {
            inventory.add(item);
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.min(100, health);
    }

    public void setEquippedWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public boolean hasEquippedWeapon() {
        return hasEquippedWeapon;
    }

    public void setHasEquippedWeapon(boolean hasEquippedWeapon) {
        this.hasEquippedWeapon = hasEquippedWeapon;
    }


    public int attack() {

             if (hasEquippedWeapon && !equippedWeapon.isRanged()) {
                int weaponDamage = 0;
                int strike = 1;
                weaponDamage = getEquippedWeapon().getDamage();
                strike = 1 + weaponDamage;
                return strike;
            }
            if (hasEquippedWeapon && equippedWeapon.isRanged() && !equippedWeapon.isOutOfAmmo()) {
                int arrows = getEquippedWeapon().getAmmo();
                int weaponDamage = 0;
                int shot = 0;
                weaponDamage = getEquippedWeapon().getDamage();
                shot = weaponDamage;
                equippedWeapon.setAmmo(arrows - 1);
                return shot;
            }
            if (hasEquippedWeapon && equippedWeapon.isRanged() && equippedWeapon.isOutOfAmmo()) {
            System.out.println();
            int strike = 0;
            return strike;
            }
            else {
                return 1;
            }

    }
}














