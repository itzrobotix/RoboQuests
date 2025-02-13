package me.robotix.roboquests.playerdata;

import me.robotix.roboquests.quests.utils.QuestStage;
import me.robotix.roboquests.quests.utils.QuestState;

import java.util.*;

/**
 * Represents an instance of one player's data.
 */
public class PlayerData {

    private final UUID playerUUID;
    private final Map<String, QuestState> questStates;
    private final Map<String, QuestStage> activeQuestsProgress;

    public PlayerData(UUID playerUUID) {
        this.playerUUID = playerUUID;
        this.questStates = new HashMap<>();
        this.activeQuestsProgress = new HashMap<>();
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public Map<String, QuestState> getQuestStates() {
        return questStates;
    }

    public Map<String, QuestStage> getActiveQuestsProgress() {
        return activeQuestsProgress;
    }

//    public void completeQuest(Quest quest) {
//        completedQuests.add(quest.getID());
//        activeQuests.remove(quest.getID());
//    }
//
//    public void startQuest(Quest quest) {
//        if (!activeQuests.containsKey(quest.getID()) && !completedQuests.contains(quest.getID())) {
//            activeQuests.put(quest.getID(), new QuestProgress());
//        }
//    }
//
//    public void updateQuestProgress(Quest quest, int progress) {
//        if (activeQuests.containsKey(quest.getID())) {
//            activeQuests.get(quest.getID()).setProgress(progress);
//        }
//    }
}