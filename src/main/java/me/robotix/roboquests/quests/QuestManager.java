package me.robotix.roboquests.quests;

import static me.robotix.roboquests.playerdata.PlayerDataManager.*;
import static me.robotix.roboquests.utils.ConfigUtils.*;

import me.robotix.roboquests.playerdata.PlayerData;
import me.robotix.roboquests.quests.utils.QuestState;
import net.minecraft.entity.player.PlayerEntity;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public final class QuestManager {

    private QuestManager() {}

    private static final String QUESTS_FOLDER_PATH = getConfigsFolder() + "/quests";

    private static final Map<String, Quest> QUESTS = new HashMap<>();

    /**
     * Loads all quest JSON files.
     */
    public static void loadQuests() {
        QUESTS.clear();

        File[] files = getQuestsFolder().listFiles((dir, name) -> name.endsWith(".json"));

        if (files != null) {
            for (File file : files) {
                Quest quest = loadFromFile(file, Quest.class);

                if (quest != null) {
                    QUESTS.put(quest.getID(), quest);
                }
            }
        }
    }

    /**
     * Returns the quest state for the specified quest and player.
     *
     * @param player The specified player.
     * @param quest The specified quest.
     * @return The QuestState.
     * @throws NullPointerException if no QuestState is found.
     */
    public QuestState getQuestStateForPlayer(PlayerEntity player, Quest quest) {
        PlayerData playerData = getPlayerData(player.getUuid());

        return playerData.getQuestStates().entrySet().stream()
                .filter(entry -> entry.getKey().equals(quest.getID()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    /**
     * Returns all quests for a player in the specified state.
     *
     * @param player The specified player.
     * @param questState The specified QuestState.
     * @return A list containing all modIDs found matching QuestState.
     */
    public List<String> getQuestsInStateForPlayer(PlayerEntity player, QuestState questState) {
        PlayerData playerData = getPlayerData(player.getUuid());

        return playerData.getQuestStates().entrySet().stream()
                .filter(entry -> entry.getValue() == questState)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    //    public static Map<String, Quest> loadQuests() {
//        createConfigFolders();
//        Map<String, Quest> quests = new HashMap<>();
//        File[] questFiles = QUESTS_FOLDER.listFiles((dir, name) -> name.endsWith(".json"));
//
//        if (questFiles == null) return quests;
//
//        for (File file : questFiles) {
//            try (FileReader reader = new FileReader(file)) {
//                JsonElement jsonElement = JsonParser.parseReader(reader);
//                Quest quest = GSON.fromJson(jsonElement, Quest.class);
//                quests.put(quest.getID(), quest);
//                RoboQuests.LOGGER.info("Quest loaded: " + quest.getTitle());
//            } catch (Exception e) {
//                RoboQuests.LOGGER.error("Error loading quest from file: " + file.getName());
//            }
//        }
//
//        return quests;
//    }
//
//    public static boolean createQuest(Quest quest, boolean overwrite) {
//        createConfigFolders();
//        File file = new File(QUESTS_FOLDER, quest.getID() + ".json");
//
//        if (file.exists() && !overwrite) {
//            RoboQuests.LOGGER.error("Quest already exists " + quest.getTitle());
//            return false;
//        }
//
//        try {
//            if (!file.createNewFile()) {
//                RoboQuests.LOGGER.error("Quest already exists: " + quest.getTitle());
//                return false;
//            }
//
//            try (Writer writer = new FileWriter(file)) {
//                GSON.toJson(quest, writer);
//                RoboQuests.LOGGER.info("Quest created: " + quest.getTitle());
//                return true;
//            }
//        } catch (Exception e) {
//            RoboQuests.LOGGER.error("Error creating quest: " + quest.getTitle());
//            return false;
//        }
//    }
//
//    public static void saveQuest(Quest quest) {
//        createConfigFolders();
//        File file = new File(QUESTS_FOLDER, quest.getID() + ".json");
//
//        try (Writer writer = new FileWriter(file)) {
//            GSON.toJson(quest, writer);
//            RoboQuests.LOGGER.info("Quest saved: " + quest.getTitle());
//        } catch (Exception e) {
//            RoboQuests.LOGGER.error("Error saving quest: " + quest.getTitle());
//        }
//    }
//
//    public static void deleteQuest(String questID) {
//        createConfigFolders();
//        Quest quest = QuestManager.getQuestByID(questID);
//        File file = new File(QUESTS_FOLDER, questID + ".json");
//
//        if (quest != null && file.exists() && file.delete()) {
//            RoboQuests.LOGGER.error("Quest deleted: " + quest.getTitle());
//        } else {
//            RoboQuests.LOGGER.error("Error deleting quest: " + questID);
//        }
//    }

//    //Adds a quest with a specified quest state to a players quest registry.
//    public static boolean addQuestToPlayer(String questID, PlayerEntity player, QuestState questState) {
//        UUID playerUUID = player.getUuid();
//        Map<UUID, QuestState> playerQuestStates = PLAYER_QUEST_STATE_REGISTRY.getOrDefault(questID, null);
//
//        if (playerQuestStates != null) {
//            if (playerQuestStates.containsKey(playerUUID)) {
//                return false; //Player already has quest.
//            }
//
//            playerQuestStates.put(playerUUID, questState);
//            return true;
//        }
//
//        return false;
//    }
//
//    //Remove quest from player.
//    public static boolean removeQuestForPlayer(String questID, PlayerEntity player) {
//        UUID playerUUID = player.getUuid();
//
//        if (!PLAYER_QUEST_STATE_REGISTRY.containsKey(questID) || !PLAYER_QUEST_STATE_REGISTRY.get(questID).containsKey(playerUUID)) {
//            return false;
//        }
//
//        PLAYER_QUEST_STATE_REGISTRY.get(questID).remove(playerUUID);
//        return true;
//    }
//
//    //Returns the quest state for specified quest and player.
//    public static QuestState getQuestStateForPlayer(String questID, PlayerEntity player) {
//        UUID playerUUID = player.getUuid();
//
//        return PLAYER_QUEST_STATE_REGISTRY.getOrDefault
//                (questID, Collections.emptyMap()).getOrDefault(playerUUID, null);
//    }
//
//    //Sets the quest state for specified quest and player.
//    public static boolean setQuestStateForPlayer(String questID, PlayerEntity player, QuestState newQuestState) {
//        UUID playerUUID = player.getUuid();
//
//        if (!PLAYER_QUEST_STATE_REGISTRY.containsKey(questID) || !PLAYER_QUEST_STATE_REGISTRY.get(questID).containsKey(playerUUID)) {
//            return false; //Quest or player not found.
//        }
//
//        PLAYER_QUEST_STATE_REGISTRY.get(questID).put(playerUUID, newQuestState);
//        return true; //Quest state updated for player.
//    }
//
//    public static Set<UUID> getPlayersAssignedToQuest(String questID) {
//        return PLAYER_QUEST_STATE_REGISTRY.getOrDefault(questID, Collections.emptyMap()).keySet();
//    }
//
//    public static Map<String, Map<UUID, QuestState>> getPlayerQuestRegistry() {
//        return PLAYER_QUEST_STATE_REGISTRY;
//    }

    /**
     * Takes a Quest object and adds it to collection of quests.
     *
     * @param quest The Quest to create.
     * @return True if quest is created, false if quest already exists.
     */
    public static boolean createQuest(Quest quest) {
        if (QUESTS.containsKey(quest.getID())) {
            return false;
        }

        QUESTS.put(quest.getID(), quest);
        return true;
    }

    /**
     * Deletes a Quest file and removes it from collection of quests.
     *
     * @param questID The Quest ID to match.
     * @return True if quest is deleted, false if quest doesn't exist.
     */
    public static boolean deleteQuestByID(String questID) {
        //Add logic to delete quest file.
        return QUESTS.remove(questID) != null;
    }

    /**
     * @param questID The Quest ID to match.
     * @return The Quest object if exists or null.
     */
    public static Quest getQuestByID(String questID) {
        return QUESTS.getOrDefault(questID, null);
    }

    public static File getQuestsFolder() {
        return getAbsolutePath(QUESTS_FOLDER_PATH);
    }
}
