package me.robotix.roboquests.playerdata;

import static me.robotix.roboquests.utils.ConfigUtils.*;

import me.robotix.roboquests.playerdata.questprogress.QuestProgress;
import me.robotix.roboquests.quests.Quest;
import me.robotix.roboquests.quests.QuestStage;
import me.robotix.roboquests.quests.utils.QuestState;
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

    /**
     * Loads all player data JSON files.
     */
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

    /**
     * Creates a new player data file.
     *
     * @param player The player specified for creating their data.
     * @return True if file created successfully, false if error with file creation.
     */
    public static boolean createPlayerData(PlayerEntity player) {
        UUID playerUUID = player.getUuid();
        File file = getPlayerFile(playerUUID);
        PlayerData playerData = new PlayerData(playerUUID);

        return saveToFile(file, playerData);
    }

    /**
     * Saves player data to file.
     *
     * @param player The player specified for saving their data.
     * @return True if file saved successfully, false if error with saving.
     */
    public static boolean savePlayerData(PlayerEntity player) {
        UUID playerUUID = player.getUuid();
        PlayerData playerData = getPlayerData(playerUUID);
        File file = getPlayerFile(playerData.getPlayerUUID());

        return saveToFile(file, playerData);
    }

    /**
     * Deletes a player data file.
     *
     * @param player The player specified for deleting data.
     * @return True if file deleted successfully, false if there was an error.
     */
    public static boolean deletePlayerData(PlayerEntity player) {
        UUID playerUUID = player.getUuid();
        File file = getPlayerFile(playerUUID);
        boolean deleted = deleteFile(file);

        if (deleted) {
            PLAYERS_DATA.remove(playerUUID);
        }

        return deleted;
    }

    /**
     * @param player The player's quest progress to find.
     * @param quest The quest to search for its progress.
     * @return The QuestStage instance.
     */
    public static QuestProgress getPlayerQuestProgress(PlayerEntity player, Quest quest) {
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
