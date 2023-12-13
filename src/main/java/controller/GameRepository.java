package controller;

import model.Magic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.character.Monster;
import model.goods.Armor;
import model.goods.Goods;
import model.goods.Weapon;
import service.scene.Scene;
import service.scene.TownScene;

public class GameRepository {

    // Singleton pattern
    private static GameRepository instance = null;
    private List<Magic> magics;
    private List<Goods> goods;
    private List<Armor> armors;
    private List<Weapon> weapons;
    private List<Monster> monsters;
    private List<Scene> scenes;
    private List<TownScene> townScenes;

    private GameRepository() {
        // Initialize lists
        magics = new ArrayList<>();
    }

    // Methods to access the lists
    public List<Magic> getMagics() {
        return magics;
    }

    public void loadAllResource() throws IOException {
        System.out.println("Loading all resources...");
        loadMagics("magics.json");
        System.out.println("Magics loaded!");
        displayMagics();

        loadGoods("goods.json");
        System.out.println("Goods loaded!");
        displayGoods();

        loadArmors("armors.json");
        System.out.println("Armors loaded!");
        displayArmors();

        loadWeapons("weapons.json");
        System.out.println("Weapons loaded!");
        displayWeapons();

        loadMonsters("monsters.json");
        System.out.println("Monsters loaded!");
        displayMonsters();

        // load scenes
        loadScenes("scenes.json");
        System.out.println("Scenes loaded!");

        loadTownScenes("town_scenes.json");
        System.out.println("TownScenes loaded!");

        // connect scenes after loading
        connectScenes();
        // display scenes will cause stack overflow
//        displayScenes();
//        displayTownScenes();

        System.out.println("All resources loaded!");
    }


    // Methods to load magical items from JSON, copy chatGPT
    public void loadMagics(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(jsonFilePath).getFile());
        magics = objectMapper.readValue(file, new TypeReference<List<Magic>>() {});
    }

    public void loadGoods(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(jsonFilePath).getFile());
        goods = objectMapper.readValue(file, new TypeReference<List<Goods>>() {});
    }

    public void loadArmors(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(jsonFilePath).getFile());
        armors = objectMapper.readValue(file, new TypeReference<List<Armor>>() {});
    }

    public void loadWeapons(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(jsonFilePath).getFile());
        weapons = objectMapper.readValue(file, new TypeReference<List<Weapon>>() {});
    }

    public void loadMonsters(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(jsonFilePath).getFile());
        monsters = objectMapper.readValue(file, new TypeReference<List<Monster>>() {});

        // set weapon and armor for each monster
        for (Monster monster : monsters) {
            loadArmorsAndWeaponsForMonsters(monster);
            loadDropItemsForMonster(monster);
        }
    }

    private void loadArmorsAndWeaponsForMonsters(Monster monster) {
        for (Weapon weapon : weapons) {
            if (weapon.getDescription().contains(monster.getWeapon().getDescription())) {
                // deep copy weapon to monster
                try {
                    Weapon weaponCopy = (Weapon) weapon.clone();
                    weaponCopy.setAmount(1);
                    monster.setWeapon(weaponCopy);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        for (Armor armor : armors) {
            if (armor.getDescription().contains(monster.getArmor().getDescription())) {
                // deep copy armor to monster
                try {
                    Armor armorCopy = (Armor) armor.clone();
                    armorCopy.setAmount(1);
                    monster.setArmor(armorCopy);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void loadDropItemsForMonster(Monster monster) {
        List<Goods> dropItems = new ArrayList<>();
        for (Goods monsterDrop : monster.getDropItems()) {
            for (Goods good : goods) {
                if (good.getDescription().contains(monsterDrop.getDescription())) {
                    // deep copy good to monster
                    try {
                        Goods goodCopy = (Goods) good.clone();
                        goodCopy.setAmount(1);
                        dropItems.add(goodCopy);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        monster.setDropItems(dropItems);
    }

    private void loadScenes(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(jsonFilePath).getFile());
        scenes = objectMapper.readValue(file, new TypeReference<List<Scene>>() {});
    }

    private void loadTownScenes(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(jsonFilePath).getFile());
        townScenes = objectMapper.readValue(file, new TypeReference<List<TownScene>>() {});
    }

    private void connectScenes() {
        // connect scenes
        for (Scene scene : scenes) {
            List<Scene> removeScenes = new ArrayList<>();
            List<Scene> addScenes = new ArrayList<>();
            for (Scene nextScene : scene.getNextScenes()) {
                for (Scene scene1 : scenes) {
                    if (scene1.getDescription().contains(nextScene.getDescription())) {
                        removeScenes.add(nextScene);
                        addScenes.add(scene1);
                        break;
                    }
                 }
                for (TownScene townScene : townScenes) {
                    if (townScene.getDescription().contains(nextScene.getDescription())) {
                        removeScenes.add(nextScene);
                        addScenes.add(townScene);
                        break;
                    }
                }
            }
            scene.getNextScenes().removeAll(removeScenes);
            scene.getNextScenes().addAll(addScenes);
        }

        // connect town scenes
        for (TownScene townScene : townScenes) {
            List<Scene> removeScenes = new ArrayList<>();
            List<Scene> addScenes = new ArrayList<>();
            for (Scene nextScene : townScene.getNextScenes()) {
                for (Scene scene1 : scenes) {
                    if (scene1.getDescription().contains(nextScene.getDescription())) {
                        removeScenes.add(nextScene);
                        addScenes.add(scene1);
                        break;
                    }
                }
                for (TownScene townScene1 : townScenes) {
                    if (townScene1.getDescription().contains(nextScene.getDescription())) {
                        removeScenes.add(nextScene);
                        addScenes.add(townScene1);
                        break;
                    }
                }
            }
            townScene.getNextScenes().removeAll(removeScenes);
            townScene.getNextScenes().addAll(addScenes);
        }
    }

    public void displayMagics() {
        System.out.println("Magics:");
        for (Magic magic : magics) {
            System.out.println(magic);
        }
    }

    public void displayGoods() {
        System.out.println("Goods:");
        for (Goods good : goods) {
            System.out.println(good);
        }
    }

    public void displayArmors() {
        System.out.println("Armors:");
        for (Armor armor : armors) {
            System.out.println(armor);
        }
    }

    public void displayWeapons() {
        System.out.println("Weapons:");
        for (Weapon weapon : weapons) {
            System.out.println(weapon);
        }
    }

    public void displayMonsters() {
        System.out.println("Monsters:");
        for (Monster monster : monsters) {
            System.out.println(monster);
        }
    }

    public void displayScenes() {
        System.out.println("Scenes:");
        for (Scene scene : scenes) {
            System.out.println(scene);
        }
    }

    public void displayTownScenes() {
        System.out.println("TownScenes:");
        for (TownScene townScene : townScenes) {
            System.out.println(townScene);
        }
    }

    public static void main(String[] args) {
        GameRepository gameRepository = new GameRepository();
        try {
            gameRepository.loadAllResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameRepository getInstance() {
        if (instance == null) {
            instance = new GameRepository();
        }
        return instance;
    }
}
