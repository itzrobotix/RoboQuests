package me.robotix.roboquests.quests;

import java.util.List;

/**
 * Contains a list of QuestTask to used for defining a QuestStage.
 */
public record QuestTaskSet(String taskSetID, List<QuestTask> tasks) {}