package me.robotix.roboquests.quests;

public class QuestProgress {

    private int questProgressStages;

    public QuestProgress() {
        this.questProgressStages = 0;
    }

    public int getProgress() {
        return questProgressStages;
    }

    public void setProgress(int progress) {
        this.questProgressStages = progress;
    }

}
