package ItemPack;

import java.util.ArrayList;

public class ItemDatabase {

    public ArrayList<Item> items = new ArrayList<>() {

        Item item1 = new Item("Frostbite Blade:\n ", "A finely-crafted longsword with an icy blue hilt. \nIts blade, etched with frost patterns, feels unnaturally cold to the touch.");
        Item item2 = new Item("Shadowcloak of the Whispering Night:\n ", "A hooded cloak that seems to absorb light. \nWhen worn, it grants the ability to move silently and blend into shadows.");
        Item item3 = new Item("Crimson Elixir:\n ", "A small vial containing a red potion with a swirling, misty interior. \nWhen consumed, it grants temporary invulnerability to fire.");
        Item item4 = new Item("Aetherial Dagger:\n ", "A slender, translucent dagger with an ethereal glow. \nIt can bypass physical defenses and phase through armor.");
        Item item5 = new Item("Tome of Forgotten Incantations:\n ", "An ancient, leather-bound tome filled with long-lost spells and rituals. \nReading it bestows forbidden knowledge of the arcane arts.");
        Item item6 = new Item("Soul-Stealer's Amulet:\n ", "A dark amulet adorned with a black gemstone. \nWhen worn, it allows the wearer to steal a fraction of an opponent's life force upon landing a critical blow.");
        Item item7 = new Item("Whisperwind Quiver:\n", "A beautifully crafted quiver that never runs out of arrows. \nIts arrows are imbued with a faint wind magic, making them incredibly accurate.");
        Item item8 = new Item("Elixir of Time's Embrace:\n", "A delicate glass flask containing a shimmering potion. \nWhen consumed, it grants the drinker a brief glimpse into the future, allowing them to foresee impending danger.");
        Item item9 = new Item("Echoing Blade of Shadows:\n", "A wickedly curved shortsword with an ebony hilt. \nIt has the power to summon shadowy duplicates of the wielder to confuse and disorient foes.");


    };


    public void addItem(Item item) {
        items.add(item);
    }
}
