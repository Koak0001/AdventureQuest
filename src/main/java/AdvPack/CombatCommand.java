package AdvPack;

import MapPack.Enemy;
import RoomPack.Room;

import java.util.List;

public enum CombatCommand {
    ATTACK {
        @Override
        public void execute(String[] inputTokens, Adventure adventure, Room currentRoom) {
            List<Enemy> enemiesInRoom = adventure.getCurrentRoom().getEnemies();
//              debug lines
//            System.out.println("Attempting attack in: " + currentRoom.getRoomName());
//            System.out.println("Enemies in room: " + enemiesInRoom);
            if (!enemiesInRoom.isEmpty()) {
                if (inputTokens.length == 1) {
                    //trigger for input of only "attack", we target the first enemy in the List.
                    adventure.attack(enemiesInRoom.get(0));
                } else { //else, if the line is 2 input tokens, we namecheck token 2 at integer 1, with current enemy List
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
