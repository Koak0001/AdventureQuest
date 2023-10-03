package ItemPack;

public class Item {
    private final String itemName;
    private final String itemDescription;
    private final ItemType itemType;

    public Item(String itemName, String itemDescription, ItemType itemType) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public ItemType getItemType() {
        return itemType;
    }
    public String getEffect() {
        return null;
    }

    public int getHealthMod() {
        return 0;
    }
}
