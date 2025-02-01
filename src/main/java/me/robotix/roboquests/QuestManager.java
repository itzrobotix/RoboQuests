package me.robotix.roboquests;

import net.minecraft.entity.player.PlayerEntity;

import java.util.*;

public final class QuestManager {

    private QuestManager() {}

    private static final Map<String, Map<UUID, QuestState>> playerQuestRegistry = new HashMap<>();
    private static final Map<String, Quest> quests = new HashMap<>();

    public static void initQuests
            (String questTitle, String questID, String questDescription,
             String questRequirements, String questStages, String questRewards, QuestState questState) {

        Quest quest = new Quest
                (questTitle, questID, questDescription, questRequirements, questStages, questRewards, questState);

        //Add quests

        //Add quest logic
    }

    //Adds a quest with a specified quest state to a players quest registry.
    public static boolean addQuestToPlayer(String questID, PlayerEntity player, QuestState questState) {
        UUID playerUUID = player.getUuid();
        Map<UUID, QuestState> playerQuestStates = playerQuestRegistry.getOrDefault(questID, null);

        if (playerQuestStates != null) {
            if (playerQuestStates.containsKey(playerUUID)) {
                return false; //Player already has quest.
            }

            playerQuestStates.put(playerUUID, questState);
            return true;
        }

        return false;
    }

    //Remove quest from player.
    public static boolean removeQuestForPlayer(String questID, PlayerEntity player) {
        UUID playerUUID = player.getUuid();

        if (!playerQuestRegistry.containsKey(questID) || !playerQuestRegistry.get(questID).containsKey(playerUUID)) {
            return false;
        }

        playerQuestRegistry.get(questID).remove(playerUUID);
        return true;
    }

    //Returns the quest state for specified quest and player.
    public static QuestState getQuestStateForPlayer(String questID, PlayerEntity player) {
        UUID playerUUID = player.getUuid();

        return playerQuestRegistry.getOrDefault
                (questID, Collections.emptyMap()).getOrDefault(playerUUID, null);
    }

    //Sets the quest state for specified quest and player.
    public static boolean setQuestStateForPlayer(String questID, PlayerEntity player, QuestState newQuestState) {
        UUID playerUUID = player.getUuid();

        if (!playerQuestRegistry.containsKey(questID) || !playerQuestRegistry.get(questID).containsKey(playerUUID)) {
            return false; //Quest or player not found.
        }

        playerQuestRegistry.get(questID).put(playerUUID, newQuestState);
        return true; //Quest state updated for player.
    }

    public static Set<UUID> getPlayersAssignedToQuest(String questID) {
        return playerQuestRegistry.getOrDefault(questID, Collections.emptyMap()).keySet();
    }

    public static Map<String, Map<UUID, QuestState>> getPlayerQuestRegistry() {
        return playerQuestRegistry;
    }

    //Returns false if quest already exists or true if successful.
    public static boolean addQuest(Quest quest) {
        if (quests.containsKey(quest.getQuestID())) {
            return false; //Quest already exists under this ID.
        }

        quests.put(quest.getQuestID(), quest);
        return true;
    }

    //Returns true if quest is removed and false if quest doesn't exist.
    public static boolean deleteQuestByID(String questID) {
        return quests.remove(questID) != null;
    }

    //Returns null if quest not found.
    public static Quest getQuestByID(String questID) {
        return quests.getOrDefault(questID, null);
    }

}
