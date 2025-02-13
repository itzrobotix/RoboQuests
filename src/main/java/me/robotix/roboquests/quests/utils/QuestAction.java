package me.robotix.roboquests.quests.utils;

/**
 * Contains a list of available actions that can triggered upon completion of a QuestTask, QuestTaskSet or QuestStage.
 */
public enum QuestAction {
    CHANGE_EXP_LEVEL,
    COMPLETE_QUEST,
    GIVE_EXP,
    GIVE_ITEM,
    TAKE_ITEM,
    HEAL,
    RUN_COMMAND,
    SEND_MESSAGE,
    START_TIMER,
    STOP_TIMER,
    TELEPORT_TO_POS
}
