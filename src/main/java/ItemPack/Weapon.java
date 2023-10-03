package ItemPack;
public class Weapon extends Item{
    private final int damage;

    public Weapon(String itemName, String itemDescription, int damage){
        super(itemName, itemDescription, ItemType.WEAPON);
        this.damage = damage;
    }
}
