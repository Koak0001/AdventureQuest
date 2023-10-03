package ItemPack;


import java.util.ArrayList;
public class ItemDatabase {
    public ArrayList<Item> items = new ArrayList<>();

    public ItemDatabase() {

        items.add(new Weapon("Frostbite Sword\n", "A finely-crafted longsword with an icy blue hilt. \nIts blade, etched with frost patterns, feels unnaturally cold to the touch.", 6));
        items.add(new Weapon("Shadow-cloak of the Whispering Night\n ", "A hooded cloak that seems to absorb light. \nWhen worn, it grants the ability to move silently and blend into shadows.",2));
        items.add(new Weapon("Crimson Elixir\n", "A small vial containing a red potion with a swirling, misty interior. \nWhen consumed, it grants temporary invulnerability to fire.", 5));
        items.add(new Weapon("Aerial Dagger\n", "A slender, translucent dagger with an ethereal glow. \nIt can bypass physical defenses and phase through armor.", 3));
        items.add(new Weapon("Tome of Forgotten Incantations\n", "An ancient, leather-bound tome filled with long-lost spells and rituals. \nReading it bestows forbidden knowledge of the arcane arts.",3));
        items.add(new Weapon("Soul-Stealer's Amulet\n", "A dark amulet adorned with a black gemstone. \nWhen worn, it allows the wearer to steal a fraction of an opponent's life force upon landing a critical blow.",5));
        items.add(new Weapon("Whisper-wind Quiver\n", "A beautifully crafted quiver that never runs out of arrows. \nIts arrows are imbued with a faint wind magic, making them incredibly accurate.", 7));
        items.add(new Weapon("Elixir of Time's Embrace\n", "A delicate glass flask containing a shimmering potion. \nWhen consumed, it grants the drinker a brief glimpse into the future, allowing them to foresee impending danger.",6));
        items.add(new Weapon("Echoing Blade of Shadows\n", "A wickedly curved short sword with an ebony hilt. \nIt has the power to summon shadowy duplicates of the wielder to confuse and disorient foes.", 8));

    //Food Items: Starts with int[9] for stew.
        items.add(new Food("Hearty Stew", "a bowl of hearty stew, packed with chunks of meat and vegetables.", 3, "savory and filling."));
        items.add(new Food("Sweet berry Pie", "a freshly baked pie with a sweet berry filling.", 2, "deliciously sweet and tangy."));
        items.add(new Food("Spicy Curry", "a plate of spicy curry with fragrant spices and tender meat.", 4, "fiery and flavorful."));
        items.add(new Food("Crispy Bacon", "crisp strips of bacon cooked to perfection.", 2, "salty and satisfying."));
        items.add(new Potion("Golden Honey Mead", "a golden, sweet mead in a polished wooden tankard.", 3, "rich and honey-sweet."));
        items.add(new Food("Roasted Chestnuts", "warm and roasted chestnuts, seasoned with a hint of salt.", 1, "nutty and comforting."));
        items.add(new Food("Delicious Apple", "a juicy red apple, perfectly ripe and sweet.", -75, "a cursed apple! It turns to maggots in your mouth."));
        items.add(new Food("Sizzling Sausage", "a sizzling hot sausage with a smoky aroma.", 2, "juicy and flavorful."));
        items.add(new Food("Velvety Chocolate Truffle", "a decadent chocolate truffle with a silky smooth texture.", 3, "indulgently rich and sweet."));
        items.add(new Potion("Refreshing Lemonade", "a glass of ice-cold lemonade with a citrus zing.", 2, "cool and invigorating."));

    }
}
