package me.robotix.roboquests.quests;

public class Quest {

    private final String questTitle;
    private final String questID;
    private final String questDescription;
    private final String questRequirements;
    private final String questStages;
    private final String questRewards;

    private QuestState questState;


    public Quest(String questTitle, String questID, String questDescription,
                 String questRequirements, String questStages, String questRewards, QuestState questState) {

        this.questTitle = questTitle;
        this.questID = questID;
        this.questDescription = questDescription;
        this.questRequirements = questRequirements;
        this.questStages = questStages;
        this.questRewards = questRewards;
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

    public String getStages() {
        return questStages;
    }

    public String getRewards() {
        return questRewards;
    }

    public QuestState getState() {
        return questState;
    }

    public void setState(QuestState questState) {
        this.questState = questState;
    }

}