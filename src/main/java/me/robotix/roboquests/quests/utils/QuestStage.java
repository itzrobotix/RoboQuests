package me.robotix.roboquests.quests.utils;

import me.robotix.roboquests.quests.task.QuestTasks;

import java.util.List;

//Represents one stage of a quest, can have multiple instances per quest and store as many tasks as required per stage.
public class QuestStage {

    private QuestTasks taskSet;
    private List<QuestRequirement> stageRequirements;

    public QuestStage(QuestTasks taskSet, List<QuestRequirement> stageRequirements) {
        this.taskSet = taskSet;
        this.stageRequirements = stageRequirements;
    }

    public QuestTasks getTaskSet() {
        return taskSet;
    }

    public void setTaskSet(QuestTasks taskSet) {
        this.taskSet = taskSet;
    }

    public List<QuestRequirement> getStageRequirements() {
        return stageRequirements;
    }

    public void setStageRequirements(List<QuestRequirement> stageRequirements) {
        this.stageRequirements = stageRequirements;
    }
}
