package me.robotix.roboquests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigUtils {

    private ConfigUtils() {}

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final List<File> MOD_CONFIG_FOLDERS = new ArrayList<>();
    private static final File CONFIGS_FOLDER = new File("config/roboquests");
    private static final File QUESTS_FOLDER = new File(CONFIGS_FOLDER + "/quests/");

    public static Map<String, Quest> loadQuests() {
        createConfigFolders();
        Map<String, Quest> quests = new HashMap<>();
        File[] questFiles = QUESTS_FOLDER.listFiles((dir, name) -> name.endsWith(".json"));

        if (questFiles == null) return quests;

        for (File file : questFiles) {
            try (FileReader reader = new FileReader(file)) {
                JsonElement jsonElement = JsonParser.parseReader(reader);
                Quest quest = GSON.fromJson(jsonElement, Quest.class);
                quests.put(quest.getID(), quest);
                RoboQuests.LOGGER.info("Quest loaded: " + quest.getTitle());
            } catch (Exception e) {
                RoboQuests.LOGGER.error("Error loading quest from file: " + file.getName());
            }
        }

        return quests;
    }

    public static boolean createQuest(Quest quest, boolean overwrite) {
        createConfigFolders();
        File file = new File(QUESTS_FOLDER, quest.getID() + ".json");

        if (file.exists() && !overwrite) {
            RoboQuests.LOGGER.error("Quest already exists " + quest.getTitle());
            return false;
        }

        try {
            if (!file.createNewFile()) {
                RoboQuests.LOGGER.error("Quest already exists: " + quest.getTitle());
                return false;
            }

            try (Writer writer = new FileWriter(file)) {
                GSON.toJson(quest, writer);
                RoboQuests.LOGGER.info("Quest created: " + quest.getTitle());
                return true;
            }
        } catch (Exception e) {
            RoboQuests.LOGGER.error("Error creating quest: " + quest.getTitle());
            return false;
        }
    }

    public static void saveQuest(Quest quest) {
        createConfigFolders();
        File file = new File(QUESTS_FOLDER, quest.getID() + ".json");

        try (Writer writer = new FileWriter(file)) {
            GSON.toJson(quest, writer);
            RoboQuests.LOGGER.info("Quest saved: " + quest.getTitle());
        } catch (Exception e) {
            RoboQuests.LOGGER.error("Error saving quest: " + quest.getTitle());
        }
    }

    public static void deleteQuest(String questID) {
        createConfigFolders();
        Quest quest = QuestManager.getQuestByID(questID);
        File file = new File(QUESTS_FOLDER, questID + ".json");

        if (quest != null && file.exists() && file.delete()) {
            RoboQuests.LOGGER.error("Quest deleted: " + quest.getTitle());
        } else {
            RoboQuests.LOGGER.error("Error deleting quest: " + questID);
        }
    }

    //Checks mod config folders exist and creates them if missing.
    public static void createConfigFolders() {
        MOD_CONFIG_FOLDERS.add(CONFIGS_FOLDER);
        MOD_CONFIG_FOLDERS.add(QUESTS_FOLDER);

        for (File file : MOD_CONFIG_FOLDERS) {
            if (!file.exists()) {
                if (file.mkdirs()) {
                    RoboQuests.LOGGER.info("Created directory " + file.getPath());
                } else {
                    RoboQuests.LOGGER.error(" Failed to create directory " + file.getPath());
                }
            }
        }
    }

    public static File getAbsolutePath(String directoryPath) {
        return new File(Paths.get(new File("").getAbsolutePath()) + directoryPath);
    }

}
