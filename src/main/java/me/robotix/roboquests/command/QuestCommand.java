package me.robotix.roboquests.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import me.robotix.roboquests.playerdata.questprogress.QuestProgress;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Map;

import static me.robotix.roboquests.playerdata.PlayerDataManager.*;
import static me.robotix.roboquests.quests.QuestManager.*;

public class QuestCommand {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher,
                                                    registryAccess,
                                                    environment) -> {
            dispatcher.register(CommandManager.literal("quest")
                    .then(CommandManager.literal("list")
                        .executes(context -> listQuests(context.getSource())))
                    .then(CommandManager.literal("active")
                        .executes(context -> listActiveQuests(context.getSource())))
                    .then(CommandManager.literal("progress")
                        .then(CommandManager.argument("questID", StringArgumentType.string())
                            .executes(context ->
                                getQuestProgress(context.getSource(), StringArgumentType.getString(context, "questID")))))

            );
        });
    }

    private static int listQuests(ServerCommandSource source) {
        String questIDsList = String.join(", ", getAllQuestIDs());
        source.sendFeedback(() -> Text.literal("All Quests: " + questIDsList), false);
        return Command.SINGLE_SUCCESS;
    }

    private static int listActiveQuests(ServerCommandSource source) {
        if (source.getPlayer() != null) {
            Map<String, QuestProgress> questProgressData = getPlayerData(source.getPlayer().getUuid()).getActiveQuestsProgress();
            String questProgressIDsList = String.join(",", questProgressData.keySet());
            source.sendFeedback(() -> Text.literal("Active Quests: " + questProgressIDsList), false);
        }
        return Command.SINGLE_SUCCESS;
    }

    private static int getQuestProgress(ServerCommandSource source, String questID) {
        if (source.getPlayer() != null && getQuestByID(questID) != null) {
            QuestProgress questProgress = getPlayerQuestProgress(source.getPlayer(), getQuestByID(questID));
            double percentComplete = questProgress.getCompletionPercentage();
            String remainingTasks = questProgress.getAllRemainingTasks();
            source.sendFeedback(() -> Text.literal("Quest is " + percentComplete + "% complete! The remaining tasks are:\n" + remainingTasks), false);
        }
        return Command.SINGLE_SUCCESS;
    }

}
