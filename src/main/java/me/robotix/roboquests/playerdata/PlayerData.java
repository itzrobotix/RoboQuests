package me.robotix.roboquests.playerdata;

import me.robotix.roboquests.playerdata.questprogress.QuestProgress;
import me.robotix.roboquests.quests.utils.QuestState;

import java.util.*;

/**
 * Represents an instance of one player's data.
 */
public class PlayerData {

    private final transient UUID playerUUID;
    private final Map<String, QuestState> questStates;
    private final Map<String, QuestProgress> activeQuestsProgress;

    public PlayerData(UUID playerUUID) {
        this.playerUUID = playerUUID;
        this.questStates = new HashMap<>();
        this.activeQuestsProgress = new HashMap<>();
    }

    public QuestState getQuestState(String questID) {
        return activeQuestsProgress.get(questID).getQuestState();
    }

    public void updateQuestState(String questID, QuestState newState) {
        if (activeQuestsProgress.containsKey(questID)) {
            activeQuestsProgress.get(questID).setQuestState(newState);
        }
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public Map<String, QuestState> getQuestStates() {
        return questStates;
    }

    public Map<String, QuestProgress> getActiveQuestsProgress() {
        return activeQuestsProgress;
    }

}