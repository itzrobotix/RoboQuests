package me.robotix.roboquests.playerdata;

import static me.robotix.roboquests.utils.ConfigUtils.*;

import me.robotix.roboquests.quests.Quest;
import me.robotix.roboquests.quests.tasks.QuestTasks;
import me.robotix.roboquests.quests.QuestState;
import net.minecraft.entity.player.PlayerEntity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {

    private PlayerDataManager() {}

    private static final String PLAYER_DATA_FOLDER_PATH = getConfigsFolder() + "/playerdata";

    private static final Map<UUID, PlayerData> PLAYERS_DATA = new HashMap<>();
    private static final Map<UUID, Map<String, QuestState>> PLAYER_QUEST_STATE_REGISTRY = new HashMap<>();

    //Loads all player data JSON files.
    public static void loadPlayerData() {
        PLAYERS_DATA.clear();

        File[] files = getPlayerDataFolder().listFiles((dir, name) -> name.endsWith(".json"));

        if (files != null) {
            for (File file : files) {
                UUID playerUUID = UUID.fromString(file.getName().replace(".json", ""));
                PlayerData playerData = loadFromFile(file, PlayerData.class);

                if (playerData != null) {
                    PLAYERS_DATA.put(playerUUID, playerData);

                    for (Map.Entry<String, QuestState> entry : playerData.getQuestStates().entrySet()) {
                        String questID = entry.getKey();
                        QuestState questState = entry.getValue();

                        PLAYER_QUEST_STATE_REGISTRY
                                .computeIfAbsent(playerUUID, k -> new HashMap<>())
                                .put(questID, questState);
                    }
                }
            }
        }
    }

    //Create a new player data file.
    public static boolean createPlayerData(PlayerEntity player) {
        UUID playerUUID = player.getUuid();
        File file = getPlayerFile(playerUUID);

        if (file.exists()) {
            return false;
        }

        PlayerData playerData = new PlayerData(playerUUID);
        saveToFile(playerData, file);
        return true;
    }

    //Saves an instance of player data to config file.
    public static boolean savePlayerData(PlayerEntity player) {
        UUID playerUUID = player.getUuid();
        PlayerData playerData = getPlayerData(playerUUID);
        File file = getPlayerFile(playerData.getPlayerUUID());

        return saveToFile(playerData, file);
    }

    //Deletes a player's data file.
    public static boolean deletePlayerData(PlayerEntity player) {
        UUID playerUUID = player.getUuid();
        File file = getPlayerFile(playerUUID);
        boolean deleted = deleteFile(file);

        if (deleted) {
            PLAYERS_DATA.remove(playerUUID);
        }

        return deleted;
    }

    //Returns a specified quest's progress for a player.
    public static QuestTasks getPlayerQuestProgress(PlayerEntity player, Quest quest) {
        UUID playerUUID = player.getUuid();
        PlayerData playerData = getPlayerData(playerUUID);
        return playerData.getActiveQuestsProgress().get(quest.getID());
    }

    public static File getPlayerFile(UUID playerUUID) {
        return new File(getPlayerDataFolder().getPath() + playerUUID + ".json");
    }

    public static File getPlayerDataFolder() {
        return getAbsolutePath(PLAYER_DATA_FOLDER_PATH);
    }

    public static PlayerData getPlayerData(UUID playerUUID) {
        return PLAYERS_DATA.get(playerUUID);
    }

    public static Map<UUID, PlayerData> getPlayersData() {
        return PLAYERS_DATA;
    }
}
