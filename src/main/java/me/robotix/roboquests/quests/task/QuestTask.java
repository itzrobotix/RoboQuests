package me.robotix.roboquests.quests.task;

import me.robotix.roboquests.quests.utils.QuestRequirement;

import java.util.List;

//Contains an instance of a singular task in a set of tasks for a quest.
public class QuestTask {

    private final String taskID;
    private final QuestTaskType taskType;
    private final String target;
    private final int timesToComplete;

    private final List<QuestRequirement> taskRequirements;

    public QuestTask(String taskID, QuestTaskType taskType, String target, int timesToComplete, List<QuestRequirement> taskRequirements) {
        this.taskID = taskID;
        this.taskType = taskType;
        this.target = target;
        this.timesToComplete = timesToComplete;
        this.taskRequirements = taskRequirements;
    }

    public String getTaskID() {
        return taskID;
    }

    public QuestTaskType getTaskType() {
        return taskType;
    }

    public String getTarget() {
        return target;
    }

    public int getTimesToComplete() {
        return timesToComplete;
    }

    public List<QuestRequirement> getTaskRequirements() {
        return taskRequirements;
    }
}