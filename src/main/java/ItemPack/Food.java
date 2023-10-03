package ItemPack;
public class Food extends Item{
    private final int healthMod;
    private final String effect;


    public Food(String itemName, String itemDescription, int healthMod, String effect) {
        super(itemName, itemDescription, ItemType.FOOD);
        this.healthMod = healthMod;
        this.effect = effect;
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