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

    // Methods to load magical items from JSON
    public void loadMagics(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("magics.json").getFile());
        magics = objectMapper.readValue(file, new TypeReference<List<Magic>>() {});
    }

    // Methods to load items from JSON
    public void loadAllResource() throws IOException {
        loadMagics("../resource/magics.json");
    }

    public static void main(String[] args) throws IOException {
        GameRepository gameRepository = new GameRepository();
        gameRepository.loadAllResource();

        System.out.println(gameRepository.getMagics());
    }
}
