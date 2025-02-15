package me.robotix.roboquests.quests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents an instance of one quest.
 */
public class Quest {

    private final String questID;
    private String questDisplayName;
    private String questDescription;
    private final List<QuestStage> questStages = new ArrayList<>();
    private final List<String> questRewards = new ArrayList<>();
    private boolean isQuestRepeatable;

    private final transient Map<String, QuestStage> questStagesRegistry = new HashMap<>();

    public Quest(String questID,
                 String questDisplayName,
                 String questDescription,
                 boolean isQuestRepeatable) {
        this.questID = questID;
        this.questDisplayName = questDisplayName;
        this.questDescription = questDescription;
        this.isQuestRepeatable = isQuestRepeatable;
    }

    public String getID() {
        return questID;
    }

    public String getTitle() {
        return questDisplayName;
    }

    public void setTitle(String displayName) {
        questDisplayName = displayName;
    }

    public String getDescription() {
        return questDescription;
    }

    public void setDescription(String description) {
        questDescription = description;
    }

    public List<QuestStage> getStages() {
        return questStages;
    }

    /**
     * Adds a QuestStage to the Quest.
     *
     * @param questStage The QuestStage to add.
     */
    public void addStage(QuestStage questStage) {
        questStages.add(questStage);
        questStagesRegistry.put(questStage.getStageID(), questStage);
    }

    /**
     * Removes a QuestStage from the Quest.
     *
     * @param stageID The QuestStage ID to remove.
     */
    public void removeStage(String stageID) {
        QuestStage questStage = questStagesRegistry.remove(stageID);

        if (questStage != null) {
            questStages.remove(questStage);
        }
    }

    /**
     * Rebuilds quest stages map registry and all nested map registries.
     */
    public void rebuildQuestStagesRegistry() {
        questStagesRegistry.clear();

        for (QuestStage questStage : questStages) {
            questStagesRegistry.put(questStage.getStageID(), questStage);
            questStage.rebuildQuestTaskSetRegistry();
        }
    }

    public QuestStage getQuestStage(String stageID) {
        return questStagesRegistry.get(stageID);
    }

    public void addReward(String reward) {
        questRewards.add(reward);
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