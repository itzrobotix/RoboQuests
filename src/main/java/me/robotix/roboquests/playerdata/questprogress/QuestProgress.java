package me.robotix.roboquests.playerdata.questprogress;

import me.robotix.roboquests.quests.Quest;
import me.robotix.roboquests.quests.QuestStage;
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
