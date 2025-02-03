package me.robotix.roboquests;

import net.minecraft.entity.player.PlayerEntity;

import java.util.*;

public final class QuestManager {

    private QuestManager() {}

    private static final Map<String, Map<UUID, QuestState>> PLAYER_QUEST_REGISTRY = new HashMap<>();
    private static final Map<String, Quest> QUESTS = new HashMap<>();

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
        Map<UUID, QuestState> playerQuestStates = PLAYER_QUEST_REGISTRY.getOrDefault(questID, null);

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

        if (!PLAYER_QUEST_REGISTRY.containsKey(questID) || !PLAYER_QUEST_REGISTRY.get(questID).containsKey(playerUUID)) {
            return false;
        }

        PLAYER_QUEST_REGISTRY.get(questID).remove(playerUUID);
        return true;
    }

    //Returns the quest state for specified quest and player.
    public static QuestState getQuestStateForPlayer(String questID, PlayerEntity player) {
        UUID playerUUID = player.getUuid();

        return PLAYER_QUEST_REGISTRY.getOrDefault
                (questID, Collections.emptyMap()).getOrDefault(playerUUID, null);
    }

    //Sets the quest state for specified quest and player.
    public static boolean setQuestStateForPlayer(String questID, PlayerEntity player, QuestState newQuestState) {
        UUID playerUUID = player.getUuid();

        if (!PLAYER_QUEST_REGISTRY.containsKey(questID) || !PLAYER_QUEST_REGISTRY.get(questID).containsKey(playerUUID)) {
            return false; //Quest or player not found.
        }

        PLAYER_QUEST_REGISTRY.get(questID).put(playerUUID, newQuestState);
        return true; //Quest state updated for player.
    }

    public static Set<UUID> getPlayersAssignedToQuest(String questID) {
        return PLAYER_QUEST_REGISTRY.getOrDefault(questID, Collections.emptyMap()).keySet();
    }

    public static Map<String, Map<UUID, QuestState>> getPlayerQuestRegistry() {
        return PLAYER_QUEST_REGISTRY;
    }

    //Returns false if quest already exists or true if successful.
    public static boolean createQuest(Quest quest) {
        if (QUESTS.containsKey(quest.getID())) {
            return false; //Quest already exists under this ID.
        }

        QUESTS.put(quest.getID(), quest);
        return true;
    }

    //Returns true if quest is removed and false if quest doesn't exist.
    public static boolean deleteQuestByID(String questID) {
        return QUESTS.remove(questID) != null;
    }

    //Returns null if quest not found.
    public static Quest getQuestByID(String questID) {
        return QUESTS.getOrDefault(questID, null);
    }

}
