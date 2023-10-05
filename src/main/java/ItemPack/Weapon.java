package ItemPack;
public class Weapon extends Item {
    private final int damage;
    private final String ability;
    private boolean isActive;

    public Weapon(String itemName, String itemDescription, int damage, String ability){
        super(itemName, itemDescription, ItemType.WEAPON, null, WeaponType.WEAPON);
        this.damage = damage;
        this.ability = ability;
        isActive = false;

    }
    public int getDamage() {
        return damage;
    }
    public String getAbility(){return ability;}



    public WeaponType getWeaponType() {
        return WeaponType.WEAPON;
    }

    public boolean isActive(Weapon weapon) {
         return isActive = true;
    }
    public boolean inActive() {
        return isActive = false;
    }



}
