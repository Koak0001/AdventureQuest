package MapPack;

import ItemPack.Weapon;

public class Enemy {

    private final String enemyName;

    private final String description;
    private final int unique;

    private int health;
    public final Weapon weapon;
    public boolean equippedWeapon;

    public Enemy(String enemyName, String description, int health, Weapon weapon, int unique) {
        this.enemyName = enemyName;
        this.description = description;
        this.health = health;
        this.weapon = weapon;
        this.unique = unique;
    }
    public String getEnemyName() {
        return enemyName;
    }
    public String getDescription() {
        return description;
    }


    public boolean hasEquippedWeapon()
        {
            return weapon != null;
        }
    public int getUnique() {
        return unique;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getHealth() {
        return health;
    }

    public int setHealth(int health) {
        this.health = health;
        return health;
    }

    public String toString(){
        return "A menacing " + enemyName + " it is " + description + " \n It wields a " +  weapon.getItemDescription();
    }



//    public void enemyAttack() {
//        currentHeroHp =
//        int damage;
//        Weapon weapon = this.weapon;
//        int weaponDamage = weapon.getDamage();
//        int unique = this.unique;
//
//        if (hasEquippedWeapon && !weapon.isRanged()) {
//
//            weaponDamage = weapon.getDamage();
//            damage = 1 + unique + weaponDamage;
//            return damage;
//        }
//        if (hasEquippedWeapon && weapon.isRanged()) {
//
//            damage = weaponDamage;
//            return damage;
//        }
//        if (!hasEquippedWeapon) {
//            System.out.println();
//            damage = 1 + unique;
//            return damage;
//        }
//        else {
//            return 1;
//        }
//    }
}
