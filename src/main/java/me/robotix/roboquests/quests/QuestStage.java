package me.robotix.roboquests.quests;

import me.robotix.roboquests.quests.utils.QuestRequirement;

import java.util.List;

/**
 * Represents one stage of a quest, can have multiple instances per quest and store as many tasks as required per stage.
 */
public record QuestStage(String stageID, int stageOrder, List<QuestTaskSet> taskSet,
                         List<QuestRequirement> stageRequirements) {}
