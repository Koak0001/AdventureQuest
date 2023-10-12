package ItemPack;

import static ItemPack.WeaponType.RANGEDWEAPON;

public class RangedWeapon extends Weapon {
    public RangedWeapon(String itemName, String itemDescription, int damage, String ability, int ammo, boolean outOfAmmo) {
        super(itemName, itemDescription, damage, ability, RANGEDWEAPON);
        this.ammo = ammo;
        this.outOfAmmo = false;
    }

    @Override
    public boolean isRanged() {
        return true;
    }
    @Override
    public String toString(){
        return " a shot with " + itemName + " \n" + ability;
    }


}



