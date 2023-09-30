package UserInterfacePack;

import AdvPack.Adventure;
import MapPack.Map;
import PlayerPack.Player;
import RoomPack.Room;

import java.util.Scanner;

public class UserInterface {

    public Adventure adventure;
    public Player player;
    public Map map;

    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
        this.map = new Map();
        this.player = new Player(map);
        play();
    }
    public void play() {

        adventure.newGame();
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Welcome to game you are standing in " + map.getCurrentRoom().getRoomName() + map.getCurrentRoom().getRoomDesc());
        System.out.println("Start by going south or east ");
        System.out.println("Type help for help");
        while (!exit) {
            String userInput = keyboard.nextLine().toLowerCase();

            String input = userInput.replace("go", "").replace("around", "").trim();
            switch (input) {
                case "north", "n" -> {
                    System.out.println("Going North");
                    Room northRoom = map.getCurrentRoom().getNorthRoom();
                    if (northRoom != null) {
                        player.setRequestRoom(northRoom);
                        System.out.println(player.getRequestRoom().getRoomName());
                        player.moveTo(northRoom);
                    } else {
                        System.out.println("There is no room to the north.");
                    }
                }
                case "south", "s" -> {
                    {
                        System.out.println("Going South");
                        Room southRoom = map.getCurrentRoom().getSouthRoom();
                        if (southRoom != null) {
                            player.setRequestRoom(southRoom);
                            System.out.println(player.getRequestRoom().getRoomName());
                            player.moveTo(southRoom);
                        } else {
                            System.out.println("There is no room to the south.");
                        }
                    }
                }
                case "west", "w" -> {
                    System.out.println("Going West");
                    Room westRoom = map.getCurrentRoom().getWestRoom();
                    if (westRoom != null) {
                        player.setRequestRoom(westRoom);
                        System.out.println(player.getRequestRoom().getRoomName());
                        player.moveTo(westRoom);
                    } else {
                        System.out.println("There is no room to the west.");
                    }
                }
                case "east", "e" -> {
                    System.out.println("Going East");
                    Room eastRoom = map.getCurrentRoom().getEastRoom();
                    if (eastRoom != null) {
                        player.setRequestRoom(eastRoom);
                        System.out.println(player.getRequestRoom().getRoomName());
                        player.moveTo(eastRoom);

                    } else {
                        System.out.println("There is no room to the east.");
                    }
                }


                case "look", "look around" -> look();
                case "help" -> help();
                case "exit" -> exit = true;
                default -> System.out.println("Unknown input");
            }
        }
    }

    // Help Method
    public void help() {
        System.out.println("You are standing in " + map.getCurrentRoom().getRoomName());
        System.out.println("In this game you can move in 4 directions");
        System.out.println("North, South, East, West");
        System.out.println("Type 'look around to to see which directions you may move to");
        System.out.println("Type exit to exit the game");
    }

    // Look Method
    public void look() {
        System.out.println("You are standing in " + map.getCurrentRoom().getRoomName() + map.getCurrentRoom().getRoomDesc());
    }
}
