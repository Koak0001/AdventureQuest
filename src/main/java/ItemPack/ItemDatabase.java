package ItemPack;
import java.util.ArrayList;

import static ItemPack.FoodType.FOOD;

public class ItemDatabase {
    public ArrayList<Item> items = new ArrayList<>();

    public ItemDatabase() {
        //int itemCount = items.size();
        //System.out.println("Number of items in the ArrayList: " + itemCount);
        //8Food and 12Potion 9Weapon 9 Misc
        items.add(new MeleeWeapon("Frostbite Sword", "A finely-crafted longsword with an icy blue hilt. \nIts blade, etched with frost patterns, feels unnaturally cold to the touch.", 6, "Cold as death itself."));
        items.add(new MeleeWeapon("Spiteful Axe", "A wicked battle axe with a malevolent aura, its razor-sharp blade hungers for blood.", 2, "Hungers for vengeance."));
        items.add(new MeleeWeapon("Crimson Firebrand", "A fiery sword forged from the essence of fire, granting temporary invulnerability to flames.", 5, "Burning with fury."));
        items.add(new MeleeWeapon("Aerial Dagger", "A slender, translucent dagger with an ethereal glow. \nIt can bypass physical defenses and phase through armor.", 3, "Dances on the wind."));
        items.add(new MeleeWeapon("Crimson Warhammer", "A massive warhammer imbued with fiery enchantments, offering temporary invulnerability to flames.", 5, "Wields the fires of hell."));
        items.add(new RangedWeapon("Whisperwind Bow", "A bow with an exquisite silk quiver, its arrows imbued with wind magic for unparalleled accuracy.", 7, "Whispers death from afar.", 15, false));
        items.add(new RangedWeapon("Heartwood Bow", "A sturdy bow with a beautifully crafted quiver. \nIts arrows are imbued with a faint wind magic, making them incredibly accurate.", 7, "Swift as the wind.", 10,false));
        items.add(new MeleeWeapon("Frostbite Rapier", "A slender rapier with an icy blue hilt and a blade etched in frost patterns, chilling to the touch.", 6, "Cuts through the cold night."));
        items.add(new MeleeWeapon("Echoing Blade of Shadows", "A wickedly curved short sword with an ebony hilt. \nIt has the power to summon shadowy duplicates of the wielder to confuse and disorient foes.", 8, "Shadows everywhere!"));


        items.add(new Food("Hearty Stew", "a bowl of hearty stew, packed with chunks of meat and vegetables.", 3, "savory and filling.", FOOD));
        items.add(new Food("Sweet berry Pie", "a freshly baked pie with a sweet berry filling.", 2, "deliciously sweet and tangy.", FOOD));
        items.add(new Food("Spicy Curry", "a plate of spicy curry with fragrant spices and tender meat.", 4, "fiery and flavorful.", FOOD));
        items.add(new Food("Crispy Bacon", "crisp strips of bacon cooked to perfection.", 2, "salty and satisfying.", FOOD));
        items.add(new Food("Roasted Chestnuts", "warm and roasted chestnuts, seasoned with a hint of salt.", 1, "nutty and comforting.", FOOD));
        items.add(new Food("Delicious Apple", "a juicy red apple, perfectly ripe and sweet.", -75, "a cursed apple! It turns to maggots in your mouth.", FOOD));
        items.add(new Food("Sizzling Sausage", "a sizzling hot sausage with a smoky aroma.", 2, "juicy and flavorful.", FOOD));
        items.add(new Food("Velvety Chocolate Truffle", "a decadent chocolate truffle with a silky smooth texture.", 3, "indulgently rich and sweet.", FOOD));
/*8f*/  items.add(new Potion("Stale Ale", "A mug of ale that has gone stale, leaving a bitter aftertaste.", -2, "Unpleasant and flat."));
/*12p*/  items.add(new Potion("Muddy Water", "A brackish cup of water from a stagnant source, best avoided if possible.", -1, "Murky and unpalatable."));
        items.add(new Potion("Bitter Brew", "A bitter concoction that makes your tongue recoil upon tasting it.", -3, "Acrid and foul."));
        items.add(new Potion("Vinegar Wine", "A bottle of wine that has turned to vinegar, souring the palate.", -4, "Sharp and vinegary."));
        items.add(new Potion("Rotten Grog", "A foul-smelling concoction that should only be consumed in the direst of circumstances.", -5, "Repulsive and nauseating."));
        items.add(new Potion("Fine Wine", "A bottle of fine wine, smooth and flavorful.", 2, "Rich and delightful."));
        items.add(new Potion("Refreshing Ale", "A crisp and refreshing ale, perfect for quenching your thirst.", 1, "Effervescent and satisfying."));
        items.add(new Potion("Mead", "A traditional honey mead, sweet and comforting.", 2, "Golden and soothing."));
        items.add(new Potion("Golden Honey Mead", "a golden, sweet mead in a polished wooden tankard.", 3, "rich and honey-sweet."));
        items.add(new Potion("Refreshing Lemonade", "a glass of ice-cold lemonade with a citrus zing.", 2, "cool and invigorating."));
        items.add(new Potion("Healing Elixir", "A vial of healing elixir that mends wounds and restores vitality.", 15, "Restorative and life-affirming."));
        items.add(new Potion("Greater Healing Potion", "A potent potion that can mend severe injuries and rejuvenate the spirit.", 20, "Powerful and life-saving."));

        //Miscellaneous Items
        items.add(new Misc("Antique Lamp", "A dusty, brass lamp with intricate engravings, ready to light the darkest corners of your adventure."));
        items.add(new Misc("Mysterious Locket", "A delicate locket with a faded portrait inside, hinting at a hidden story waiting to be uncovered."));
        items.add(new Misc("Shimmering Crystal", "A radiant crystal that shimmers with an otherworldly glow, casting colorful reflections on nearby surfaces."));
        items.add(new Misc("Rune-etched Key", "An ornate key with ancient runes etched into its surface, hinting at long-forgotten secrets."));
        items.add(new Misc("Traveler's Journal", "A weathered journal filled with scribbled notes, sketches, and cryptic maps from a previous adventurer."));
        items.add(new Misc("Enchanted Quill", "A quill that never runs out of ink, perfect for recording your own tales of heroism."));
        items.add(new Misc("Dragon Scale", "A gleaming scale from a vanquished dragon, said to possess mystical properties."));
        items.add(new Misc("Ethereal Compass", "A small compass that always points toward your next destination, no matter how obscure."));
        items.add(new Misc("Lucky Rabbit's Foot", "A preserved rabbit's foot that, despite its origins, is believed to bring good luck to its owner."));
    }
}
