package controller;

import model.Magic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GameRepository {
    private List<Magic> magics;

    public GameRepository() {
        // Initialize lists
        magics = new ArrayList<>();
    }

    // Methods to access the lists
    public List<Magic> getMagics() {
        return magics;
    }

    // Methods to load magical items from JSON, copy chatGPT
    public void loadMagics(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(jsonFilePath).getFile());
        magics = objectMapper.readValue(file, new TypeReference<List<Magic>>() {});
    }

    // Methods to load items from JSON
    public void loadAllResource() throws IOException {
        System.out.println("Loading all resources...");
        loadMagics("magics.json");
        System.out.println("Magics loaded!");
        displayMagics();

        System.out.println("All resources loaded!");
    }

    public void displayMagics() {
        System.out.println("Magics:");
        for (Magic magic : magics) {
            System.out.println(magic);
        }
    }
}
