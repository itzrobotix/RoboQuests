package me.robotix.roboquests.quests;

import me.robotix.roboquests.RoboQuests;
import me.robotix.roboquests.quests.utils.QuestRequirement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents one stage of a quest, can have multiple instances per quest and store as many tasks as required per stage.
 */
public class QuestStage {

    private final String stageID;
    private int stageOrder;
    private final List<QuestTaskSet> taskSet = new ArrayList<>();
    private final List<QuestRequirement> stageRequirements = new ArrayList<>();

    private final transient Map<String, QuestTaskSet> taskSetRegistry = new HashMap<>();

    public QuestStage(String stageID, int stageOrder) {
        this.stageID = stageID;
        this.stageOrder = stageOrder;
    }

    public String getStageID() {
        return stageID;
    }

    public int getStageOrder() {
        return stageOrder;
    }

    public void setStageOrder(int stageOrder) {
        this.stageOrder = stageOrder;
    }

    /**
     * Adds a TaskSet to this stage.
     *
     * @param questTaskSet The TaskSet to add.
     */
    public void addTaskSet(QuestTaskSet questTaskSet) {
        taskSet.add(questTaskSet);
        taskSetRegistry.put(questTaskSet.getTaskSetID(), questTaskSet);
    }

    /**
     * Removes a TaskSet from this stage.
     *
     * @param taskSetID The TaskSet ID to remove.
     */
    public void removeTaskSet(String taskSetID) {
        QuestTaskSet questTaskSet = taskSetRegistry.remove(taskSetID);

        if (questTaskSet != null) {
            taskSet.remove(questTaskSet);
        }
    }

    public void rebuildQuestTaskSetRegistry() {
        taskSetRegistry.clear();

        for (QuestTaskSet questTaskSet : taskSet) {
            taskSetRegistry.put(questTaskSet.getTaskSetID(), questTaskSet);
            questTaskSet.rebuildQuestTaskRegistry();
        }
    }

    public QuestTaskSet getTaskSet(String taskSetID) {
        return taskSetRegistry.get(taskSetID);
    }

    public List<QuestTaskSet> getTaskSets() {
        return taskSet;
    }

    public void addStageRequirement(QuestRequirement questRequirement) {
        stageRequirements.add(questRequirement);
    }

    public List<QuestRequirement> getStageRequirements() {
        return stageRequirements;
    }

}
