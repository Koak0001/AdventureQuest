package ItemPack;

import static ItemPack.ItemType.FOOD;

public class Food extends Item {
    protected final int healthMod;
    protected final String effect;

    public Food(String itemName, String itemDescription, int healthMod, String effect, FoodType foodType) {
        super(itemName, itemDescription, FOOD);
        this.healthMod = healthMod;
        this.effect = effect;
    }

    @Override
    public String getItemName() {
        String superItemName = super.getItemName();
        if (superItemName.startsWith("The ")) {
            superItemName = superItemName.substring(4); // Removes super class prefix
        }
        return "a " + superItemName;
    }
    @Override
    public String getEffect() {
        return effect;}

    @Override
    public int getHealthMod() {
        return healthMod;
    }

   // public FoodType getFoodType() {
      //  return FoodType.FOOD;
  //  }

}