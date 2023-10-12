package UserInterfacePack;
import AdvPack.CombatCommand;
import AdvPack.GeneralCommand;
import ItemPack.*;
import AdvPack.Adventure;
import MapPack.*;
import PlayerPack.Player;
import RoomPack.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    public Adventure adventure;
    private Scanner keyboard;
    private Room currentRoom;


    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
        Player player = adventure.getPlayer();
        adventure.getPlayer().setHealth(100);
        this.keyboard = new Scanner(System.in);
        play();
    }

    public void play() {
        adventure.newGame();

        boolean exit = false;
        boolean inCombat = adventure.isInCombat();
        boolean playerTurn = adventure.isPlayerTurn();
        boolean turn1 = adventure.isTurn1();

        currentRoom = adventure.getCurrentRoom();
        Player player = adventure.getPlayer();
        currentRoom.setVisited();
        System.out.println(currentRoom);

        System.out.println("Let the Adventure begin!");
        System.out.println(); //Linebreak
        System.out.println("Type 'look around' to see where you might go.");
        System.out.println(); //Linebreak
        System.out.println("Type help for more input options.");
        adventure.endCombat();
        while (!exit) {
            String userInput = keyboard.nextLine();
            adventure.handleUserInput(userInput);
            String input = adventure.getInput();
            String[] inputTokens = adventure.getInputTokens();
//            Debug lines
//            System.out.println("Input: ");
//            System.out.println("InputTokens: " + Arrays.toString(inputTokens));
//            System.out.println("You are currently in: " + adventure.getCurrentRoom().getRoomName());

            boolean validInput = false;

            if (adventure.isInCombat()) {
                CombatCommand combatCommand = null;

                if (inputTokens[0].equalsIgnoreCase("attack")) {
                    combatCommand = CombatCommand.ATTACK;
                } else {
                    try {
                        combatCommand = CombatCommand.valueOf(inputTokens[0].toUpperCase());
                    } catch (IllegalArgumentException e) {
                    }
                }

                if (combatCommand != null) {
                    combatCommand.execute(inputTokens, adventure, currentRoom);
                    validInput = true;
                }
            }


            if (!validInput) {
                GeneralCommand generalCommand = null;
                try {
                    generalCommand = GeneralCommand.valueOf(inputTokens[0].toUpperCase());
                } catch (IllegalArgumentException e) {

                }

                if (generalCommand != null) {
                    generalCommand.execute(inputTokens, adventure);
                    validInput = true;
                }
            }


            if (!validInput && inputTokens.length == 2) {
                switch (inputTokens[0]) {
                    case "take" -> {
                        String itemName = inputTokens[1];
                        adventure.takeOrDropItem(itemName, false);
                        validInput = true;
                    }
                    case "drop" -> {
                        String itemName = inputTokens[1];
                        adventure.takeOrDropItem(itemName, true);
                        validInput = true;
                    }
                }
            }
            currentRoom = adventure.getCurrentRoom();

            // out of combat commands
            if (!validInput && !adventure.isInCombat()) {
                switch (input) {
                    case "north", "n" -> {
                        adventure.moveTo(currentRoom.northRoom);
                        validInput = true;
                    }
                    case "south", "s" -> {
                        adventure.moveTo(currentRoom.southRoom);
                        validInput = true;
                    }
                    case "west", "w" -> {
                        adventure.moveTo(currentRoom.westRoom);
                        validInput = true;
                    }
                    case "east", "e" -> {
                        adventure.moveTo(currentRoom.eastRoom);
                        validInput = true;
                    }

                    case "exit", "quit" -> {
                        exit = true;
                        System.exit(0);
                    }
                    case "search" -> {
                        adventure.search();
                        validInput = true;
                    }

                    default -> System.out.println("input not recognized");
                }
            }
        }
    }
}