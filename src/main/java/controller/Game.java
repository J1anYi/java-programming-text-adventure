package controller;

import model.character.Protagonist;
import service.scene.Scene;

import java.io.IOException;
import java.util.Scanner;

public class Game {

    GameRepository gameRepository;
    public static Scanner scanner = new Scanner(System.in);
    private Scene currentScene;
    private Protagonist currentProtagonist;

    public Game() {
        gameRepository = new GameRepository();

        start();
    }

    public void start() {
        System.out.println("----------------Game started----------------");
        this.currentScene = new Scene();
        this.currentProtagonist = new Protagonist();
        load();
    }

    public void load() {
        System.out.println("----------------Game loaded----------------");
        try {
            gameRepository.loadAllResource();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        System.out.println("----------------Game running----------------");
        while (true) {
            System.out.println("---------------------------------------------------");
            System.out.println("Please write your next action: ");
            System.out.println("1. Go to the next scene");
            System.out.println("2. View Protagonist Panel");
            System.out.println("3. Exit the game");

            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    System.out.println("Please write the scene number you want to go: ");
                    int sceneNumber = scanner.nextInt();
                    Scene nextScene = currentScene.act(sceneNumber);
                    if (nextScene != null) {
                        this.currentScene = nextScene;
                    }else {
                        System.out.println("Invalid scene number");
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
}
