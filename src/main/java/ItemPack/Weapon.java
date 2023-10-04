package ItemPack;
public class Weapon extends Item {
    private final int damage;
    private final String ability;
    boolean equipped;

    public Weapon(String itemName, String itemDescription, int damage, String ability){
        super(itemName, itemDescription, ItemType.WEAPON, null, WeaponType.WEAPON);
        this.damage = damage;
        this.ability = ability;
        equipped = false;

    }
}
