package ItemPack;

public abstract class Item {
    protected final String itemName;
    protected final String itemDescription;
    protected final ItemType itemType;
    public boolean isLiquid = false;
    public boolean isEdible = false;
    public boolean isEquippable = false;


    public Item(String itemName, String itemDescription, ItemType itemType) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemType = itemType;
    }



    @Override
    public String toString(){
        return "The " + itemName + " \n" + itemDescription + " \n";
    }
    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getHealthMod() {
        return 0;
    }


}
