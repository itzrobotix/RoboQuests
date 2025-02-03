package me.robotix.roboquests;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoboQuests implements ModInitializer {

	public static final String MOD_ID = "roboquests";
	public static final String MOD_NAME = "RoboQuests";
	public static final String MOD_VERSION = "1.0.0";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static MinecraftServer SERVER;

	@Override
	public void onInitialize() {
		LOGGER.info(MOD_NAME + " has loaded successfully!");

		CommandRegistrationCallback.EVENT.register
				((commandDispatcher,
				  commandRegistryAccess,
				  registrationEnvironment) -> {
			if (registrationEnvironment.dedicated) {
				commandDispatcher.register(CommandManager.literal("quest")
						.executes(context -> {
							context.getSource().sendFeedback(() -> Text.literal
											("This command is gonna do funky stuff soon!"),
									false);
							return 1;
						}));
			}
		});
	}
}