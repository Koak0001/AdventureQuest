package ItemPack;
import static ItemPack.WeaponType.MELEEWEAPON;
public class MeleeWeapon extends Weapon {
    public MeleeWeapon(String itemName, String itemDescription, int damage, String ability) {
        super(itemName, itemDescription, damage, ability, MELEEWEAPON);

    }
    @Override
    public boolean isRanged() {
        return false;
    }


    public String toString(){
        return " a strike with " + itemName + " \n" + ability;
    }
    @Override
    public boolean isEquippable(){
        return true;
    }
}
