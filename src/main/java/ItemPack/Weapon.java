package ItemPack;

import static ItemPack.ItemType.WEAPON;

public abstract class Weapon extends Item {
    protected final int damage;
    protected final String ability;
    protected int ammo;
    protected final WeaponType weaponType;
    protected boolean outOfAmmo;

    public Weapon(String itemName, String itemDescription, int damage, String ability, WeaponType weaponType){
        super(itemName, itemDescription, WEAPON);
        this.damage = damage;
        this.ability = ability;
        this.outOfAmmo = false;
        this.weaponType = weaponType;
        this.isEquippable = true;

    }

    public boolean isEquippable(){
        return true;
    }
    public int getDamage() {
        return damage;
    }
    public String getAbility(){return ability;}

    public int getAmmo(){
        return ammo;
    }
    public boolean isOutOfAmmo() {

        return outOfAmmo;
    }
    @Override
    public String toString(){
        return itemDescription;
    }

    public void setAmmo(int newAmmo) {
        ammo = newAmmo;
        if (newAmmo == 0) {
            outOfAmmo = true;
        }
    }

    public boolean isRanged() {
        return this instanceof RangedWeapon;
    }

}
