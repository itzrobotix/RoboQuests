package me.robotix.roboquests.quests;

import me.robotix.roboquests.quests.utils.QuestRequirement;
import me.robotix.roboquests.quests.utils.QuestTaskType;

import java.util.List;

/**
 * Contains an instance of a singular task in a set of tasks for a quest.
 */
public record QuestTask(String taskID, QuestTaskType taskType, String target, int timesToComplete,
                        List<QuestRequirement> taskRequirements) {}