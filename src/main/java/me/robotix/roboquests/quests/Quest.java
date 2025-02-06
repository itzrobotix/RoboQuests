package me.robotix.roboquests.quests;

public class Quest {

    private final String questTitle;
    private final String questID;
    private final String questDescription;
    private final String questRequirements;
    private final String questRewards;

    private QuestProgress questProgress;
    private QuestState questState;


    public Quest(String questTitle, String questID, String questDescription,
                 String questRequirements, String questStages, String questRewards,
                 QuestProgress questProgress, QuestState questState) {

        this.questTitle = questTitle;
        this.questID = questID;
        this.questDescription = questDescription;
        this.questRequirements = questRequirements;
        this.questRewards = questRewards;
        this.questProgress = questProgress;
        this.questState = questState;
    }

    public String getTitle() {
        return questTitle;
    }

    public String getID() {
        return questID;
    }

    public String getDescription() {
        return questDescription;
    }

    public String getRequirements() {
        return questRequirements;
    }

    public String getRewards() {
        return questRewards;
    }

    public QuestProgress getStages() {
        return questProgress;
    }

    public QuestState getState() {
        return questState;
    }

    public void setState(QuestState questState) {
        this.questState = questState;
    }

}