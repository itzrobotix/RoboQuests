package me.robotix.roboquests.playerdata.questprogress;

import com.google.gson.annotations.Expose;
import me.robotix.roboquests.quests.QuestTask;

public class QuestTaskProgress {

    private final QuestTask questTask;

    @Expose private final String taskID;
    @Expose private int progress;

    public QuestTaskProgress(QuestTask questTask) {
        this.taskID = questTask.taskID();
        this.questTask = questTask;
        this.progress = 0;
    }

    public String getTaskID() {
        return taskID;
    }

    public void increaseProgress(int amount) {
        this.progress = progress + amount;
    }

    public boolean isTaskCompleted() {
        return progress >= questTask.timesToComplete();
    }
}
