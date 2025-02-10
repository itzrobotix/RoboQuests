package me.robotix.roboquests.quests;

import me.robotix.roboquests.quests.task.QuestTasks;

import java.util.List;

public class Quest {

    private final String questDisplayName;
    private final String questID;
    private final String questDescription;
    private final List<QuestTasks> questTasks;
    private final String questRewards;

    private boolean isQuestRepeatable;


    public Quest(String questDisplayName,
             String questID,
             String questDescription,
             List<QuestTasks> questTasks,
             String questRewards) {
        this.questDisplayName = questDisplayName;
        this.questID = questID;
        this.questDescription = questDescription;
        this.questTasks = questTasks;
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

    public List<QuestTasks> getStages() {
        return questTasks;
    }

    public String getRewards() {
        return questRewards;
    }

    public boolean isQuestRepeatable() {
        return isQuestRepeatable;
    }

    public void setIsQuestRepeatable(boolean repeatable) {
        isQuestRepeatable = repeatable;
    }
}