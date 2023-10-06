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
}
