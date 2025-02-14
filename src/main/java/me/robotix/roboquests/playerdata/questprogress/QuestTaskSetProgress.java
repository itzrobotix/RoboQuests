package me.robotix.roboquests.playerdata.questprogress;

import com.google.gson.annotations.Expose;
import me.robotix.roboquests.quests.QuestTask;
import me.robotix.roboquests.quests.QuestTaskSet;

import java.util.HashMap;
import java.util.Map;

public class QuestTaskSetProgress {

    private final String taskSetID;
    private int tasksToComplete;
    private final Map<String, QuestTaskProgress> taskProgress = new HashMap<>();

    public QuestTaskSetProgress(QuestTaskSet questTaskSet) {
        this.taskSetID = questTaskSet.taskSetID();

        for (QuestTask task : questTaskSet.tasks()) {
            taskProgress.put(task.taskID(), new QuestTaskProgress(task));
        }
    }

    public boolean isTaskSetCompleted() {
        return taskProgress.values().stream()
                .allMatch(QuestTaskProgress::isTaskCompleted);
    }

    public String getTaskSetID() {
        return taskSetID;
    }

    public QuestTaskProgress getTaskProgress(String taskID) {
        return taskProgress.get(taskID);
    }

}
