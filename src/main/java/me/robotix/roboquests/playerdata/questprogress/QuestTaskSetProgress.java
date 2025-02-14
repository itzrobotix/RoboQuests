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
        this.taskSetID = questTaskSet.getTaskSetID();

        for (QuestTask task : questTaskSet.getTasks()) {
            taskProgress.put(task.taskID(), new QuestTaskProgress(task));
        }
    }

    public boolean isCompleted() {
        return taskProgress.values().stream()
                .allMatch(QuestTaskProgress::isCompleted);
    }

    public String getTaskSetID() {
        return taskSetID;
    }

    public QuestTaskProgress getTaskProgress(String taskID) {
        return taskProgress.get(taskID);
    }

}
