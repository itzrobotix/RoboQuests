package me.robotix.roboquests.playerdata.questprogress;

import com.google.gson.annotations.Expose;
import me.robotix.roboquests.quests.QuestStage;
import me.robotix.roboquests.quests.QuestTaskSet;

import java.util.HashMap;
import java.util.Map;

public class QuestStageProgress {

    private final String stageID;
    private final Map<String, QuestTaskSetProgress> taskSetProgress = new HashMap<>();

    public QuestStageProgress(QuestStage questStage) {
        this.stageID = questStage.getStageID();

        for (QuestTaskSet taskSet : questStage.getTaskSets()) {
            taskSetProgress.put(taskSet.getTaskSetID(), new QuestTaskSetProgress(taskSet));
        }
    }

    public boolean isCompleted() {
        return taskSetProgress.values().stream()
                .allMatch(QuestTaskSetProgress::isCompleted);
    }

    public String getStageID() {
        return stageID;
    }

    public QuestTaskSetProgress getTaskSetProgress(String taskSetID) {
        return taskSetProgress.get(taskSetID);
    }
}
