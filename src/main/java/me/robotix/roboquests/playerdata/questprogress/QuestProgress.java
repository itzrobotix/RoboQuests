package me.robotix.roboquests.playerdata.questprogress;

import me.robotix.roboquests.quests.Quest;
import me.robotix.roboquests.quests.QuestStage;
import me.robotix.roboquests.quests.utils.QuestState;

import java.util.HashMap;
import java.util.Map;

public class QuestProgress {

    private final transient Quest quest;

    private final String questID;
    private final Map<String, QuestStageProgress> questStageProgress = new HashMap<>();

    private QuestState questState;

    public QuestProgress(Quest quest, QuestState initialState) {
        this.quest = quest;
        this.questID = quest.getID();
        this.questState = initialState;

        for (QuestStage stage : quest.getStages()) {
            questStageProgress.put(stage.stageID(), new QuestStageProgress(stage));
        }
    }

    public boolean isQuestCompleted() {
        return questStageProgress.values().stream()
                .allMatch(QuestStageProgress::isStageCompleted);
    }

    public Quest getQuest() {
        return quest;
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
