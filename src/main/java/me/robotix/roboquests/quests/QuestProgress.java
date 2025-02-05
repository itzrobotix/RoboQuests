package me.robotix.roboquests.quests;

public class QuestProgress {

    private int progress;
    private String status;

    public QuestProgress() {
        this.progress = 0;
        this.status = "in_progress";
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getStatus() {
        return status;
    }

    public void complete() {
        this.status = "complete";
    }

}
