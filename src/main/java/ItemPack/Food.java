package ItemPack;

public class Food extends Item{
    private final int healthMod;
    public final String effect;


    public Food(String itemName, String itemDescription, int healthMod, String effect) {
        super(itemName, itemDescription);
        this.healthMod = Math.min(5, healthMod);
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }

    public int getHealthMod() {
        return healthMod;

    }
}