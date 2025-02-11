package me.robotix.roboquests.quests.utils;

import java.util.List;

public class Quest {

    private final String questDisplayName;
    private final String questID;
    private final String questDescription;
    private final List<QuestStage> questStages;
    private final List<String> questRewards;

    private boolean isQuestRepeatable;


    public Quest(String questDisplayName,
             String questID,
             String questDescription,
             List<QuestStage> questStages,
             List<String> questRewards) {
        this.questDisplayName = questDisplayName;
        this.questID = questID;
        this.questDescription = questDescription;
        this.questStages = questStages;
        this.questRewards = questRewards;
    }

    public String getTitle() {
        return questDisplayName;
    }

    public String getID() {
        return questID;
    }

    public String getDescription() {
        return questDescription;
    }

    public List<QuestStage> getStages() {
        return questStages;
    }

    public List<String> getRewards() {
        return questRewards;
    }

    public boolean isQuestRepeatable() {
        return isQuestRepeatable;
    }

    public void setIsQuestRepeatable(boolean repeatable) {
        isQuestRepeatable = repeatable;
    }
}