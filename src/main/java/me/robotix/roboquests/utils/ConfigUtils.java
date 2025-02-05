package me.robotix.roboquests.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.robotix.roboquests.RoboQuests;

import java.io.*;
import java.nio.file.Paths;

public class ConfigUtils {

    private ConfigUtils() {}

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static final String CONFIGS_FOLDER_PATH = "config/roboquests";

    //Saves provided data to specified file.
    public static <T> boolean saveToFile(T data, File file) {
        createDefaultFolder();
        file = ensureJsonFile(file);

        try {
            if (file.createNewFile()) {
                RoboQuests.LOGGER.info("File created " + file.getName());
            }

            try (Writer writer = new FileWriter(file)) {
                GSON.toJson(data, writer);
                return true;
            }
        } catch (IOException e) {
            RoboQuests.LOGGER.error("");
        }
        return false;
    }

    //Loads requested data from specified file.
    public static <T> T loadFromFile(File file, Class<T> clazz) {
        createDefaultFolder();
        file = ensureJsonFile(file);

        if (!file.exists()) {
            RoboQuests.LOGGER.error("File " + file.getName() + " not found!");
            return null;
        }

        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, clazz);
        } catch (IOException e) {
            RoboQuests.LOGGER.error("Error loading data from file " + file.getName());
            return null;
        }
    }

    //Deletes specified file.
    public static boolean deleteFile(File file) {
        file = ensureJsonFile(file);
        return file.exists() && file.delete();
    }

    //Ensures files are all JSON files.
    private static File ensureJsonFile(File file) {
        if (!file.getName().endsWith(".json")) {
            return new File(file.getParent(), file.getName() + ".json");
        }
        return file;
    }

    //Checks default mod folder exist and creates if missing.
    public static void createDefaultFolder() {
        File file = getAbsolutePath(CONFIGS_FOLDER_PATH);

        if (file.mkdirs()) {
            RoboQuests.LOGGER.info("Created directory " + file.getName());
        }
    }

    //Returns file from the absolute path of specified file.
    public static File getAbsolutePath(String directoryPath) {
        return new File(Paths.get(new File("").getAbsolutePath()) + directoryPath);
    }

    public static File getConfigsFolder() {
        return getAbsolutePath(CONFIGS_FOLDER_PATH);
    }
}
