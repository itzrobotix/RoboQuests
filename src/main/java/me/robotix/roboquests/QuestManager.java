package me.robotix.roboquests;

import java.util.ArrayList;
import java.util.List;

public final class QuestManager {

    private QuestManager() {}

    private static final List<Quest> questList = new ArrayList<>();

    public static void createQuest
            (String questTitle, String questID, String questDescription,
             String questRequirements, String questStages, String questRewards) {

        Quest quest = new Quest(questTitle, questID, questDescription, questRequirements, questStages, questRewards);
        questList.add(quest);

        //Add quest logic
    }

    public static List<Quest> getQuestList() {
        return questList;
    }

    public static boolean deleteQuestByID(String questID) {
        return questList.removeIf(quest -> quest.getQuestID().equals(questID));
    }
}
