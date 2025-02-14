package me.robotix.roboquests.quests;

import me.robotix.roboquests.quests.utils.QuestState;

import java.util.List;
import java.util.Map;

/**
 * Represents an instance of one quest.
 */
public class Quest {

    private final transient String questID;
    private final String questDisplayName;
    private final String questDescription;
    private final List<QuestStage> questStages;
    private final List<String> questRewards;

    private boolean isQuestRepeatable;


    public Quest(String questID,
                 String questDisplayName,
                 String questDescription,
                 List<QuestStage> questStages,
                 List<String> questRewards,
                 boolean isQuestRepeatable) {
        this.questID = questID;
        this.questDisplayName = questDisplayName;
        this.questDescription = questDescription;
        this.questStages = questStages;
        this.questRewards = questRewards;
        this.isQuestRepeatable = isQuestRepeatable;
    }

    public String getID() {
        return questID;
    }

    public String getTitle() {
        return questDisplayName;
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