package me.robotix.roboquests.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;

public class QuestCommand implements Command<ServerCommandSource> {

    public static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher,
                                        LiteralArgumentBuilder<ServerCommandSource> base) {

    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        return 0;
    }
}
