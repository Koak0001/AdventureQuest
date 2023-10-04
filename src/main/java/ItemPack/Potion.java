package ItemPack;

import static ItemPack.FoodType.POTION;

public class Potion extends Food {
    public Potion(String itemName, String itemDescription, int healthMod, String effect) {
        super(itemName, itemDescription, healthMod, effect, POTION);
    }

    @Override
    public FoodType getFoodType() {
        return POTION;
    }
}


