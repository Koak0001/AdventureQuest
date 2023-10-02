package ItemPack;

public class Food extends Item{
    private int healthMod;
    private String effect;


    public Food(String itemName, String itemDescription, int healthMod, String effect) {
        super(itemName, itemDescription);
        this.healthMod = Math.min(5, healthMod);
        this.effect = effect;
    }
}