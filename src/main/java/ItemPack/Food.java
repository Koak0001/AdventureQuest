package ItemPack;

public class Food extends Item{
    private final int healthMod;
    public final String effect;


    public Food(String itemName, String itemDescription, int healthMod, String effect) {
        super(itemName, itemDescription);
        this.healthMod = healthMod;
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }

    public int getHealthMod() {
        return healthMod;
    }
    @Override
    public String getItemName() {
        String superItemName = super.getItemName();
        if (superItemName.startsWith("The ")) {
            superItemName = superItemName.substring(4); // Remove "The " prefix
        }
        return "a " + superItemName;
    }
}