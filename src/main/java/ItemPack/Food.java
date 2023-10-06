package ItemPack;

import static ItemPack.ItemType.FOOD;

public class Food extends Item {
    protected final int healthMod;
    protected final String effect;

    public Food(String itemName, String itemDescription, int healthMod, String effect, FoodType foodType) {
        super(itemName, itemDescription, FOOD);
        this.healthMod = healthMod;
        this.effect = effect;
        this.isLiquid = false;
        this.isEdible = true;
        }



    @Override
    public String toString(){
        return "A " + itemName + " it is " + effect;
    }

    @Override
    public int getHealthMod() {
        return healthMod;
    }


}