package com.noctivag.chaos;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChaosMod implements DedicatedServerModInitializer {
	public static final String MOD_ID = "chaos";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final ChaosConfig config = new ChaosConfig();
	private static final ChaosEventManager eventManager = new ChaosEventManager();

	@Override
	public void onInitializeServer() {
		LOGGER.info("Initializing Chaos Mod - Server Side Only");

		// Load configuration
		config.load();

		// Register events
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			eventManager.tick(server);
		});

		LOGGER.info("Chaos Mod initialized successfully!");
	}

	public static ChaosConfig getConfig() {
		return config;
	}
}
