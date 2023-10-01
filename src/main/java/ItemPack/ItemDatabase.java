package ItemPack;

import java.util.ArrayList;

public class ItemDatabase {
    public ArrayList<Item> items = new ArrayList<>();

    public ItemDatabase() {

        items.add(new Item("Frostbite Blade\n", "A finely-crafted longsword with an icy blue hilt. \nIts blade, etched with frost patterns, feels unnaturally cold to the touch."));
        items.add(new Item("Shadow-cloak of the Whispering Night \n ", "A hooded cloak that seems to absorb light. \nWhen worn, it grants the ability to move silently and blend into shadows."));
        items.add(new Item("Crimson Elixir\n", "A small vial containing a red potion with a swirling, misty interior. \nWhen consumed, it grants temporary invulnerability to fire."));
        items.add(new Item("Aerial Dagger\n", "A slender, translucent dagger with an ethereal glow. \nIt can bypass physical defenses and phase through armor."));
        items.add(new Item("Tome of Forgotten Incantations\n", "An ancient, leather-bound tome filled with long-lost spells and rituals. \nReading it bestows forbidden knowledge of the arcane arts."));
        items.add(new Item("Soul-Stealer's Amulet\n", "A dark amulet adorned with a black gemstone. \nWhen worn, it allows the wearer to steal a fraction of an opponent's life force upon landing a critical blow."));
        items.add(new Item("Whisper-wind Quiver\n", "A beautifully crafted quiver that never runs out of arrows. \nIts arrows are imbued with a faint wind magic, making them incredibly accurate."));
        items.add(new Item("Elixir of Time's Embrace\n", "A delicate glass flask containing a shimmering potion. \nWhen consumed, it grants the drinker a brief glimpse into the future, allowing them to foresee impending danger."));
        items.add(new Item("Echoing Blade of Shadows\n", "A wickedly curved short sword with an ebony hilt. \nIt has the power to summon shadowy duplicates of the wielder to confuse and disorient foes."));
    }
}
