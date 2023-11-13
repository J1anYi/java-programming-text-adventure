import controller.Game;
import controller.GameRepository;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        try {
            GameRepository gameRepository = new GameRepository();
            gameRepository.loadAllResource();
            System.out.println(gameRepository.getMagics());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
