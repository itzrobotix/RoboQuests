package me.robotix.roboquests.playerdata.questprogress;

import me.robotix.roboquests.quests.Quest;
import me.robotix.roboquests.quests.QuestStage;
import me.robotix.roboquests.quests.QuestTask;
import me.robotix.roboquests.quests.utils.QuestState;

import java.util.HashMap;
import java.util.Map;

public class QuestProgress {

    private transient Quest quest;

    private final String questID;
    private final Map<String, QuestStageProgress> questStageProgress = new HashMap<>();

    private QuestState questState;

    public QuestProgress(String questID, QuestState initialState) {
        this.questID = questID;
        this.questState = initialState;
    }

    public boolean isCompleted() {
        return questStageProgress.values().stream()
                .allMatch(QuestStageProgress::isCompleted);
    }

    public double getCompletionPercentage() {
        int totalTasks = 0;
        int completedTasks = 0;

        for (QuestStageProgress stageProgress : questStageProgress.values()) {
            for (QuestTaskSetProgress taskSetProgress : stageProgress.getTaskSetProgress().values()) {
                for (QuestTaskProgress taskProgress : taskSetProgress.getTaskProgress().values()) {
                    totalTasks++;

                    if (taskProgress.isCompleted()) {
                        completedTasks++;
                    }
                }
            }
        }
        return totalTasks > 0 ? (completedTasks / (double) totalTasks) *100 : 0.0;
    }

    public String getAllRemainingTasks() {
        StringBuilder tasks = new StringBuilder();

        for (QuestStageProgress stageProgress : questStageProgress.values()) {
            for (QuestTaskSetProgress taskSetProgress : stageProgress.getTaskSetProgress().values()) {
                for (QuestTaskProgress taskProgress : taskSetProgress.getTaskProgress().values()) {
                    QuestTask task = taskProgress.getQuestTask();

                    if (!taskProgress.isCompleted()) {
                        tasks.append("- ")
                                .append(task.taskType()) // e.g., "Kill", "Collect"
                                .append(" ")
                                .append(task.target()) // e.g., "Zombie", "Iron Ore"
                                .append(" (")
                                .append(taskSetProgress.getTaskProgress(task.taskID())) // Progress made
                                .append("/")
                                .append(task.timesToComplete()) // Goal
                                .append(")\n");
                    }
                }
            }
        }

        return tasks.isEmpty() ? "All tasks completed!" : tasks.toString();
    }

    public Quest getQuest() {
        return quest;
    }

    public void linkQuest(Quest quest) {
        this.quest = quest;
    }

    public String getQuestID() {
        return questID;
    }

    public QuestState getQuestState() { return questState; }

    public void setQuestState(QuestState questState) {
        this.questState = questState;
    }

    public Map<String, QuestStageProgress> getQuestStageProgress() {
        return questStageProgress;
    }
}
