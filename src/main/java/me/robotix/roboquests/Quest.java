package me.robotix.roboquests;

public class Quest {

    private final String questTitle;
    private final String questID;
    private final String questDescription;
    private final String questRequirements;
    private final String questStages;
    private final String questRewards;


    public Quest(String questTitle, String questID, String questDescription,
                 String questRequirements, String questStages, String questRewards) {

        this.questTitle = questTitle;
        this.questID = questID;
        this.questDescription = questDescription;
        this.questRequirements = questRequirements;
        this.questStages = questStages;
        this.questRewards = questRewards;
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

}