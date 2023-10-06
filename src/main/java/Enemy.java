import ItemPack.Weapon;

public class Enemy {
    private String name;
    private String description;
    private int health;

    private Weapon weapon;

    public Enemy (String name, String description, int Health, Weapon weapon) {
        this.name = name;
        this.description = description;
        this.health = 0;
        this.weapon = weapon;


    }
}
