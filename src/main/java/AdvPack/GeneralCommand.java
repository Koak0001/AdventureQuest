package AdvPack;

public enum GeneralCommand {

    HELP {
        @Override
        public void execute(String[] inputTokens, Adventure adventure) {
            adventure.help();
        }
    },
    LOOK {
        @Override
        public void execute(String[] inputTokens, Adventure adventure) {
            adventure.look();
        }
    },
    EAT {
        @Override
        public void execute(String[] inputTokens, Adventure adventure) {
            adventure.eatOrDrink(inputTokens[1], true);
        }
    },
    DRINK {
        @Override
        public void execute(String[] inputTokens, Adventure adventure) {
            adventure.eatOrDrink(inputTokens[1], false);
        }
    },
    INVENTORY {
        @Override
        public void execute(String[] inputTokens, Adventure adventure) {
            adventure.displayInventory();
        }
    },
    HEALTH {
        @Override
        public void execute(String[] inputTokens, Adventure adventure) {
            adventure.displayHealth();
        }
    },
    QUIVER {
        @Override
        public void execute(String[] inputTokens, Adventure adventure) {
            // Assuming adventure has the method to display quiver
            adventure.displayQuiver();
        }
    },
    EQUIP {
        @Override
        public void execute(String[] inputTokens, Adventure adventure) {
            adventure.equipWeapon(inputTokens[1]);
        }
    },
    UNEQUIP {
        @Override
        public void execute(String[] inputTokens, Adventure adventure) {
            adventure.unEquipWeapon();
        }
    };

    public abstract void execute(String[] inputTokens, Adventure adventure);
}