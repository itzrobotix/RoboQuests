package me.robotix.roboquests.playerdata;

import me.robotix.roboquests.quests.Quest;
import me.robotix.roboquests.quests.QuestProgress;

import java.util.*;

public class PlayerData {

    private final UUID playerUUID;
    private List<String> completedQuests;
    private Map<String, QuestProgress> activeQuests;

    public PlayerData(UUID playerUUID) {
        this.playerUUID = playerUUID;
        this.completedQuests = new ArrayList<>();
        this.activeQuests = new HashMap<>();
    }

    public List<String> getCompletedQuests() {
        return completedQuests;
    }

    public Map<String, QuestProgress> getActiveQuests() {
        return activeQuests;
    }

    public void completeQuest(Quest quest) {
        completedQuests.add(quest.getID());
        activeQuests.remove(quest.getID());
    }

    public void startQuest(Quest quest) {
        if (!activeQuests.containsKey(quest.getID()) && !completedQuests.contains(quest.getID())) {
            activeQuests.put(quest.getID(), new QuestProgress());
        }
    }

    public void updateQuestProgress(Quest quest, int progress) {
        if (activeQuests.containsKey(quest.getID())) {
            activeQuests.get(quest.getID()).setProgress(progress);
        }
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

}