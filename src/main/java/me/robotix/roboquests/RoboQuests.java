package me.robotix.roboquests;

import me.robotix.roboquests.command.QuestCommand;
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
		LOGGER.info(MOD_NAME + " " + MOD_VERSION + " has loaded successfully!");

		QuestCommand.register();
	}
}