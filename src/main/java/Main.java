import AdvPack.Adventure;
import MapPack.Map;
import UserInterfacePack.UserInterface;


public class Main {

    public static void main(String[] args) {
        Map map = new Map();
        Adventure adventure = new Adventure(map);
        UserInterface ui = new UserInterface(adventure);
        ui.play();
    }
}
