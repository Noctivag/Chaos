package com.noctivag.chaos;

import com.noctivag.chaos.events.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChaosEventManager {
	private final Random random = new Random();
	private final List<ChaosEvent> events = new ArrayList<>();
	private int tickCounter = 0;
	private int nextEventTick;

	public ChaosEventManager() {
		registerEvents();
		scheduleNextEvent();
	}

	private void registerEvents() {
		ChaosConfig config = ChaosMod.getConfig();

		if (config.enableWeatherEvents) {
			events.add(new WeatherChaosEvent());
		}
		if (config.enableMobEvents) {
			events.add(new MobSpawnChaosEvent());
		}
		if (config.enablePlayerEffects) {
			events.add(new PlayerEffectChaosEvent());
		}
		if (config.enableItemEvents) {
			events.add(new ItemDropChaosEvent());
		}
		if (config.enableExplosions) {
			events.add(new ExplosionChaosEvent());
		}
		if (config.enableLightning) {
			events.add(new LightningChaosEvent());
		}

		ChaosMod.LOGGER.info("Registered {} chaos events", events.size());
	}

	private void scheduleNextEvent() {
		ChaosConfig config = ChaosMod.getConfig();
		nextEventTick = tickCounter + config.eventInterval +
			(config.eventVariance > 0 ? random.nextInt(config.eventVariance) : 0);
	}

	public void tick(MinecraftServer server) {
		tickCounter++;

		if (tickCounter >= nextEventTick && !events.isEmpty()) {
			// Only trigger events if there are players online
			List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
			if (!players.isEmpty()) {
				triggerRandomEvent(server);
			}
			scheduleNextEvent();
		}
	}

	private void triggerRandomEvent(MinecraftServer server) {
		if (events.isEmpty()) return;

		ChaosEvent event = events.get(random.nextInt(events.size()));
		try {
			event.trigger(server, random);
			ChaosMod.LOGGER.info("Triggered chaos event: {}", event.getName());
		} catch (Exception e) {
			ChaosMod.LOGGER.error("Error triggering chaos event: {}", event.getName(), e);
		}
	}
}
