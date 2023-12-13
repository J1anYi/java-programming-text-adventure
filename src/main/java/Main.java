import controller.Game;
import controller.GameRepository;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Game game =  Game.getInstance();
        game.run();
    }
}
