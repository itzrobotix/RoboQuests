package me.robotix.roboquests.playerdata;

import me.robotix.roboquests.playerdata.questprogress.QuestProgress;
import me.robotix.roboquests.quests.utils.QuestState;

import java.util.*;

/**
 * Represents an instance of one player's data.
 */
public class PlayerData {

    private final UUID playerUUID;
    private final Map<String, QuestState> questStates;
    private final Map<String, QuestProgress> activeQuestsProgress;

    public PlayerData(UUID playerUUID) {
        this.playerUUID = playerUUID;
        this.questStates = new HashMap<>();
        this.activeQuestsProgress = new HashMap<>();
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    /**
     * @param questID The Quest ID to match to the Quest.
     * @return Returns the QuestState value from the questStates map if found or defaults to QuestState.LOCKED.
     */
    public QuestState getQuestState(String questID) {
        return questStates.getOrDefault(questID, QuestState.LOCKED);
    }

    /**
     * Updates the QuestState for a Quest.
     *
     * @param questID The Quest ID to match to the Quest.
     * @param newState The new QuestState to pass to the Quest.
     */
    public void updateQuestState(String questID, QuestState newState) {
        questStates.put(questID, newState);

        if (newState == QuestState.ACTIVE || newState == QuestState.COMPLETED) {
            activeQuestsProgress.computeIfAbsent
                            (questID, qID -> new QuestProgress(qID, newState)).setQuestState(newState);
        } else {
            activeQuestsProgress.remove(questID);
        }
    }

    public Map<String, QuestState> getQuestStates() {
        return questStates;
    }

    public Map<String, QuestProgress> getActiveQuestsProgress() {
        return activeQuestsProgress;
    }

}