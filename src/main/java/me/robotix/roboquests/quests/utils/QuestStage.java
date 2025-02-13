package me.robotix.roboquests.quests.utils;

import me.robotix.roboquests.quests.task.QuestTaskSet;

import java.util.List;

/**
 * Represents one stage of a quest, can have multiple instances per quest and store as many tasks as required per stage.
 */
public class QuestStage {

    private final String stageID;
    private QuestTaskSet taskSet;
    private List<QuestRequirement> stageRequirements;

    public QuestStage(String stageID, QuestTaskSet taskSet, List<QuestRequirement> stageRequirements) {
        this.stageID = stageID;
        this.taskSet = taskSet;
        this.stageRequirements = stageRequirements;
    }

    public String getStageID() {
        return stageID;
    }

    public QuestTaskSet getTaskSet() {
        return taskSet;
    }

    public void setTaskSet(QuestTaskSet taskSet) {
        this.taskSet = taskSet;
    }

    public List<QuestRequirement> getStageRequirements() {
        return stageRequirements;
    }

    public void setStageRequirements(List<QuestRequirement> stageRequirements) {
        this.stageRequirements = stageRequirements;
    }
}
