package AdvPack;
import MapPack.Map;
import PlayerPack.Player;

public class Adventure {
    private Map map;
    private Player player;

    public Adventure(Map map) {
        this.map = map;
        this.player = new Player(map); // Initialize player
    }

    public void newGame() {
        map = new Map();
        player = new Player(map);
    }



    }

