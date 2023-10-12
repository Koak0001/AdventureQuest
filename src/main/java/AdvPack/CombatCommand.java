package AdvPack;

import MapPack.Enemy;
import RoomPack.Room;

import java.util.List;

public enum CombatCommand {
    ATTACK {
        @Override
        public void execute(String[] inputTokens, Adventure adventure, Room currentRoom) {
            List<Enemy> enemiesInRoom = adventure.getCurrentRoom().getEnemies();
//            System.out.println("Attempting attack in: " + currentRoom.getRoomName());
//            System.out.println("Enemies in room: " + enemiesInRoom);
            if (!enemiesInRoom.isEmpty()) {
                if (inputTokens.length == 1) {
//                    System.out.println("Attempting to attack the first enemy.");
                    adventure.attack(enemiesInRoom.get(0));
                } else { // "attack enemyName"
//                    System.out.println("Attempting to attack a specific enemy.");
                    boolean attacked = false;
                    for (Enemy enemy : enemiesInRoom) {
                        if (inputTokens[1].equals(enemy.getEnemyName().toLowerCase())) {
                            adventure.attack(enemy);
                            attacked = true;
                            break;
                        }
                    }
                    if (!attacked) {
                        System.out.println("Invalid enemy name. Choose an available enemy to attack.");
                    }
                }
            } else {
                System.out.println("There are no enemies in the room.");
            }
        }
    },
    FLEE {
        @Override
        public void execute(String[] inputTokens, Adventure adventure, Room currentRoom) {
            adventure.flee();
        }
    },
    INSPECT {
        @Override
        public void execute(String[] inputTokens, Adventure adventure, Room currentRoom) {
            adventure.inspect();
        }
    },
    OPTIONS {
        @Override
        public void execute(String[] inputTokens, Adventure adventure, Room currentRoom) {
            adventure.combatOptions();
        }
    };

    public abstract void execute(String[] inputTokens, Adventure adventure, Room currentRoom);
}
