package controller;

import model.Magic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Task;
import model.character.Monster;
import model.character.Protagonist;
import model.goods.Armor;
import model.goods.Goods;
import model.goods.Weapon;
import service.scene.Scene;
import service.scene.TownScene;
import model.character.Character;

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
    private List<Protagonist> protagonists;

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
        loadTaskGoodsForTownScenes();
        System.out.println("TownScenes loaded!");

        // connect scenes after loading
        connectScenes();
        // load characters for scenes
        loadCharactersForScenes();
        // display scenes will cause stack overflow
//        displayScenes();
//        displayTownScenes();

        loadProtagonists("protagonists.json");
        System.out.println("Protagonists loaded!");
        displayProtagonists();

        System.out.println("All resources loaded!");
    }


    // Methods to load magical items from JSON, copy chatGPT
    public void loadMagics(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // ClassLoader classLoader = getClass().getClassLoader();
        // File file = new File(classLoader.getResource(jsonFilePath).getFile());
        // magics = objectMapper.readValue(file, new TypeReference<List<Magic>>() {});
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath)) {
            if (inputStream != null) {
                magics = objectMapper.readValue(inputStream, new TypeReference<List<Magic>>() {});
            } else {
                throw new IOException("File not found: " + jsonFilePath);
            }
        }
    }

    public void loadGoods(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // ClassLoader classLoader = getClass().getClassLoader();
        // File file = new File(classLoader.getResource(jsonFilePath).getFile());
        // goods = objectMapper.readValue(file, new TypeReference<List<Goods>>() {});
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath)) {
            if (inputStream != null) {
                goods = objectMapper.readValue(inputStream, new TypeReference<List<Goods>>() {});
            } else {
                throw new IOException("File not found: " + jsonFilePath);
            }
        }
    }

    public void loadArmors(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // ClassLoader classLoader = getClass().getClassLoader();
        // File file = new File(classLoader.getResource(jsonFilePath).getFile());
        // armors = objectMapper.readValue(file, new TypeReference<List<Armor>>() {});
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath)) {
            if (inputStream != null) {
                armors = objectMapper.readValue(inputStream, new TypeReference<List<Armor>>() {});
            } else {
                throw new IOException("File not found: " + jsonFilePath);
            }
        }
    }

    public void loadWeapons(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // ClassLoader classLoader = getClass().getClassLoader();
        // File file = new File(classLoader.getResource(jsonFilePath).getFile());
        // weapons = objectMapper.readValue(file, new TypeReference<List<Weapon>>() {});
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath)) {
            if (inputStream != null) {
                weapons = objectMapper.readValue(inputStream, new TypeReference<List<Weapon>>() {});
            } else {
                throw new IOException("File not found: " + jsonFilePath);
            }
        }
    }

    public void loadMonsters(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // ClassLoader classLoader = getClass().getClassLoader();
        // File file = new File(classLoader.getResource(jsonFilePath).getFile());
        // monsters = objectMapper.readValue(file, new TypeReference<List<Monster>>() {});
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath)) {
            if (inputStream != null) {
                monsters = objectMapper.readValue(inputStream, new TypeReference<List<Monster>>() {});
            } else {
                throw new IOException("File not found: " + jsonFilePath);
            }
        }
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
        // ClassLoader classLoader = getClass().getClassLoader();
        // File file = new File(classLoader.getResource(jsonFilePath).getFile());
        // scenes = objectMapper.readValue(file, new TypeReference<List<Scene>>() {});
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath)) {
            if (inputStream != null) {
                scenes = objectMapper.readValue(inputStream, new TypeReference<List<Scene>>() {});
            } else {
                throw new IOException("File not found: " + jsonFilePath);
            }
        }
    }

    private void loadTownScenes(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // ClassLoader classLoader = getClass().getClassLoader();
        // File file = new File(classLoader.getResource(jsonFilePath).getFile());
        // townScenes = objectMapper.readValue(file, new TypeReference<List<TownScene>>() {});
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath)) {
            if (inputStream != null) {
                townScenes = objectMapper.readValue(inputStream, new TypeReference<List<TownScene>>() {});
            } else {
                throw new IOException("File not found: " + jsonFilePath);
            }
        }
    }

    private void loadTaskGoodsForTownScenes() {
        for (TownScene townScene : townScenes) {
            for (Task task : townScene.getTaskboard()) {
                // deep copy goods to task
                for (Goods good : goods) {
                    if (good.getDescription().contains(task.getNeed().getDescription())) {
                        // deep copy good to task
                        try {
                            Goods goodCopy = (Goods) good.clone();
                            goodCopy.setAmount(1);
                            task.setNeed(goodCopy);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                for (Goods good : goods) {
                    if (good.getDescription().contains(task.getReward().getDescription())) {
                        // deep copy good to task
                        try {
                            Goods goodCopy = (Goods) good.clone();
                            goodCopy.setAmount(1);
                            task.setReward(goodCopy);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }

                // copy weapon to task
                for (Weapon weapon : weapons) {
                    if (weapon.getDescription().contains(task.getNeed().getDescription())) {
                        // deep copy weapon to task
                        try {
                            Weapon weaponCopy = (Weapon) weapon.clone();
                            weaponCopy.setAmount(1);
                            task.setNeed(weaponCopy);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                for (Weapon weapon : weapons) {
                    if (weapon.getDescription().contains(task.getReward().getDescription())) {
                        // deep copy weapon to task
                        try {
                            Weapon weaponCopy = (Weapon) weapon.clone();
                            weaponCopy.setAmount(1);
                            task.setReward(weaponCopy);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }

                // copy armor to task
                for (Armor armor : armors) {
                    if (armor.getDescription().contains(task.getNeed().getDescription())) {
                        // deep copy armor to task
                        try {
                            Armor armorCopy = (Armor) armor.clone();
                            armorCopy.setAmount(1);
                            task.setNeed(armorCopy);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                for (Armor armor : armors) {
                    if (armor.getDescription().contains(task.getReward().getDescription())) {
                        // deep copy armor to task
                        try {
                            Armor armorCopy = (Armor) armor.clone();
                            armorCopy.setAmount(1);
                            task.setReward(armorCopy);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }
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

    private void loadCharactersForScenes() {
        // load characters for scenes
        for (Scene scene : scenes) {
            List<Character> characters = new ArrayList<>();
            for (Character character : scene.getCharacters()) {
                for (Monster monster : monsters) {
                    if (monster.getDescription().contains(character.getDescription())) {
                        // deep copy monster to character
                        try {
                            Monster monsterCopy = (Monster) monster.clone();
                            characters.add(monsterCopy);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }

                // maybe later i will add NPC to the scene TODO
            }
            scene.setCharacters(characters);
        }

        // load characters for town scenes TODO
    }

    private void loadProtagonists(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // ClassLoader classLoader = getClass().getClassLoader();
        // File file = new File(classLoader.getResource(jsonFilePath).getFile());
        // protagonists = objectMapper.readValue(file, new TypeReference<List<Protagonist>>() {});
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath)) {
            if (inputStream != null) {
                protagonists = objectMapper.readValue(inputStream, new TypeReference<List<Protagonist>>() {});
            } else {
                throw new IOException("File not found: " + jsonFilePath);
            }
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

    public void displayProtagonists() {
        System.out.println("Protagonists:");
        for (Protagonist protagonist : protagonists) {
            System.out.println(protagonist);
        }
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public List<Armor> getArmors() {
        return armors;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public List<Scene> getScenes() {
        return scenes;
    }

    public List<TownScene> getTownScenes() {
        return townScenes;
    }

    public List<Protagonist> getProtagonists() {
        return protagonists;
    }

    public static GameRepository getInstance() {
        if (instance == null) {
            instance = new GameRepository();
        }
        return instance;
    }
}
