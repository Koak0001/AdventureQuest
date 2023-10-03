package ItemPack;


import java.util.ArrayList;
public class ItemDatabase {
    public ArrayList<Item> items = new ArrayList<>();

    public ItemDatabase() {

        items.add(new Weapon("Frostbite Sword\n", "A finely-crafted longsword with an icy blue hilt. \nIts blade, etched with frost patterns, feels unnaturally cold to the touch.", 6));
        items.add(new Weapon("Spiteful Axe", "A wicked battle axe with a malevolent aura, its razor-sharp blade hungers for blood.", 2));
        items.add(new Weapon("Crimson Firebrand", "A fiery sword forged from the essence of fire, granting temporary invulnerability to flames.", 5));
        items.add(new Weapon("Aerial Dagger\n", "A slender, translucent dagger with an ethereal glow. \nIt can bypass physical defenses and phase through armor.", 3));
        items.add(new Weapon("Crimson Warhammer", "A massive warhammer imbued with fiery enchantments, offering temporary invulnerability to flames.", 5));
        items.add(new Weapon("Whisperwind Bow", "A bow with an endless quiver, its arrows imbued with wind magic for unparalleled accuracy.", 7));
        items.add(new Weapon("Whisper-wind Quiver\n", "A beautifully crafted quiver that never runs out of arrows. \nIts arrows are imbued with a faint wind magic, making them incredibly accurate.", 7));
        items.add(new Weapon("Frostbite Rapier", "A slender rapier with an icy blue hilt and a blade etched in frost patterns, chilling to the touch.", 6));
        items.add(new Weapon("Echoing Blade of Shadows\n", "A wickedly curved short sword with an ebony hilt. \nIt has the power to summon shadowy duplicates of the wielder to confuse and disorient foes.", 8));

    //Food and Potion Items: Starts with int[9] for stew.
        items.add(new Food("Hearty Stew", "a bowl of hearty stew, packed with chunks of meat and vegetables.", 3, "savory and filling."));
        items.add(new Food("Sweet berry Pie", "a freshly baked pie with a sweet berry filling.", 2, "deliciously sweet and tangy."));
        items.add(new Food("Spicy Curry", "a plate of spicy curry with fragrant spices and tender meat.", 4, "fiery and flavorful."));
        items.add(new Food("Crispy Bacon", "crisp strips of bacon cooked to perfection.", 2, "salty and satisfying."));
        items.add(new Food("Roasted Chestnuts", "warm and roasted chestnuts, seasoned with a hint of salt.", 1, "nutty and comforting."));
        items.add(new Food("Delicious Apple", "a juicy red apple, perfectly ripe and sweet.", -75, "a cursed apple! It turns to maggots in your mouth."));
        items.add(new Food("Sizzling Sausage", "a sizzling hot sausage with a smoky aroma.", 2, "juicy and flavorful."));
        items.add(new Food("Velvety Chocolate Truffle", "a decadent chocolate truffle with a silky smooth texture.", 3, "indulgently rich and sweet."));
/*17*/  items.add(new Potion("Stale Ale", "A mug of ale that has gone stale, leaving a bitter aftertaste.", -2, "Unpleasant and flat."));
        items.add(new Potion("Muddy Water", "A brackish cup of water from a stagnant source, best avoided if possible.", -1, "Murky and unpalatable."));
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



    }
}
