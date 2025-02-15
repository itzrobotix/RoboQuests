package me.robotix.roboquests.quests;

import me.robotix.roboquests.quests.utils.QuestRequirement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains a list of QuestTask to used for defining a QuestStage.
 */
public class QuestTaskSet {

    private final String taskSetID;
    private final List<QuestTask> tasks = new ArrayList<>();
    private final List<QuestRequirement> taskSetRequirements = new ArrayList<>();

    private final transient Map<String, QuestTask> taskRegistry = new HashMap<>();

    public QuestTaskSet(String taskSetID) {
        this.taskSetID = taskSetID;
    }

    public String getTaskSetID() {
        return taskSetID;
    }

    /**
     * Adds a Task to this TaskSet.
     *
     * @param questTask The Task to add.
     */
    public void addTask(QuestTask questTask) {
        tasks.add(questTask);
        taskRegistry.put(questTask.taskID(), questTask);
    }

    /**
     * Removes a Task from this TaskSet.
     *
     * @param taskID The Task ID to remove.
     */
    public void removeTask(String taskID) {
        QuestTask questTask = taskRegistry.remove(taskID);

        if (questTask != null) {
            tasks.remove(questTask);
        }
    }

    public QuestTask getTask(String taskID) {
        return taskRegistry.get(taskID);
    }

    public List<QuestTask> getTasks() {
        return tasks;
    }

    public void rebuildQuestTaskRegistry() {
        taskRegistry.clear();

        for (QuestTask questTask : tasks) {
            taskRegistry.put(questTask.taskID(), questTask);
        }
    }

    public void addTaskSetRequirement(QuestRequirement questRequirement) {
        taskSetRequirements.add(questRequirement);
    }

    public List<QuestRequirement> getTaskSetRequirements() {
        return taskSetRequirements;
    }

}