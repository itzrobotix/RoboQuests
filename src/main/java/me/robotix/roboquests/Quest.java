package me.robotix.roboquests;

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

    public String getQuestTitle() {
        return questTitle;
    }

    public String getQuestID() {
        return questID;
    }

    public String getQuestDescription() {
        return questDescription;
    }

    public String getQuestRequirements() {
        return questRequirements;
    }

    public String getQuestStages() {
        return questStages;
    }

    public String getQuestRewards() {
        return questRewards;
    }

    public QuestState getQuestState() {
        return questState;
    }

    public void setQuestState(QuestState questState) {
        this.questState = questState;
    }

}