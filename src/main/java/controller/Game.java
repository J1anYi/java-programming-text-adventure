package controller;

import model.character.Protagonist;
import service.scene.Scene;

import java.io.IOException;
import java.util.Scanner;

public class Game {

    // Singleton pattern
    private static Game instance = null;
    private GameRepository gameRepository;
    public static Scanner scanner = new Scanner(System.in);
    private Scene currentScene;
    private Protagonist currentProtagonist;

    private Game() {
        gameRepository = GameRepository.getInstance();
        start();
    }

    public void start() {
        System.out.println("----------------Game started----------------");
        load();
    }

    public void load() {
        System.out.println("----------------Game loaded----------------");
        try {
            gameRepository.loadAllResource();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // set current scene and protagonist
        for (Scene scene : GameRepository.getInstance().getTownScenes()) {
            if (scene.getDescription().equals("Newbie Village")) {
                this.currentScene = scene;
                break;
            }
        }
        this.currentProtagonist = chooseProtagonist();
    }

    public void run() {
        System.out.println("----------------Game running----------------");
        while (true) {
            System.out.println("---------------------------------------------------");
            System.out.println("Current scene: " + currentScene.getDescription());
            System.out.println("Current protagonist: " + currentProtagonist.getDescription());
            System.out.println("Please write your next action: ");
            System.out.println("1. Walk around in the current scene");
            System.out.println("2. View Protagonist Panel");
            System.out.println("3. Exit the game");

            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    System.out.println("Walking around in this scene: ");
                    Scene nextScene = currentScene.act();
                    if (nextScene != null) {
                        this.currentScene = nextScene;
                    }
                    break;
                case 2:
                    System.out.println("Protagonist Panel");
                    System.out.println(currentProtagonist);

                    break;
                case 3:
                    exit();
                    break;
                default:
                    System.out.println("Invalid action");
                    break;
            }
        }
    }

    public void exit() {
        System.out.println("----------------Game ended----------------");
        System.exit(0);
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    public Protagonist getCurrentProtagonist() {
        return currentProtagonist;
    }

    public void setCurrentProtagonist(Protagonist currentProtagonist) {
        this.currentProtagonist = currentProtagonist;
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }

        return instance;
    }


    private Protagonist chooseProtagonist() {
        System.out.println("---------------------------------------------------");
        System.out.println("Please choose your protagonist: ");
        // list all protagonist
        for (int i = 0; i < GameRepository.getInstance().getProtagonists().size(); i++) {
            System.out.println(i + ". " + GameRepository.getInstance().getProtagonists().get(i));
        }
        int choice = scanner.nextInt();

        return GameRepository.getInstance().getProtagonists().get(choice);
    }
}
