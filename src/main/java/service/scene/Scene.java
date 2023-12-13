package service.scene;

import controller.Game;

import java.util.List;
import java.util.Map;
import model.character.Character;
import service.event.AttackEvent;

public class Scene {

    private String description;
    private List<Scene> nextScenes;
    private List<Character> characters;

    public Scene() {
    }

    public Scene act() {
        System.out.println("Please choice the event number you want to do: ");
        System.out.println("1. Go to the next scene");
        System.out.println("2. attack monster");
        int choice = Game.scanner.nextInt();
        switch (choice) {
            case 1:
                Scene nextScene = choiceScene();
                return nextScene;
            case 2:
                choiceCharacter();
                break;
            default:
                break;
        }

        return null;
    }

    // list next scenes
    private void listNextScenes() {
        System.out.println("Please choice the scene number you want to go: ");
        for (int i = 0; i < nextScenes.size(); i++) {
            System.out.println(i + ". " + nextScenes.get(i).getDescription());
        }
    }

    private Scene choiceScene() {
        listNextScenes();
        int choice = Game.scanner.nextInt();

        // check if the choice is valid
        if (choice < 0 || choice >= nextScenes.size()) {
            System.out.println("Invalid scene number");
            return null;
        }

        // go to the next scene
        Scene nextScene = nextScenes.get(choice);

        return nextScene;
    }

    // list all characters in this scene
    private void listCharacters() {
        System.out.println("Characters in this scene: ");
        for (int i = 0; i < characters.size(); i++) {
            System.out.println(i + ". " + characters.get(i).getDescription());
        }
    }

    private Character choiceCharacter() {
        listCharacters();
        int choice = Game.scanner.nextInt();

        // check if the choice is valid
        if (choice < 0 || choice >= characters.size()) {
            System.out.println("Invalid character number");
            return null;
        }

        // go to the next scene
        Character character = characters.get(choice);

        // check if the character is dead
        if (character.getHealth() <= 0) {
            System.out.println("The character is dead");
            // remove the dead character
            characters.remove(character);
            return null;
        }

        // default all characters are monster
        System.out.println("You are attacking " + character.getDescription());
        new AttackEvent(Game.getInstance().getCurrentProtagonist(), character).trigger();

        // check if the character is dead
        if (character.getHealth() <= 0) {
            System.out.println("The " +  character.getDescription() + " is dead");
            // remove the dead character
            characters.remove(character);
        }

        return character;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Scene> getNextScenes() {
        return nextScenes;
    }

    public void setNextScenes(List<Scene> nextScenes) {
        this.nextScenes = nextScenes;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    @Override
    public String toString() {
        return "Scene{" +
                "description='" + description + '\'' +
                ", nextScenes=" + nextScenes +
                ", characters=" + characters +
                '}';
    }
}
