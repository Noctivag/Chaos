package com.noctivag.chaos.events;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import java.util.Random;

public class WeatherChaosEvent implements ChaosEvent {
	@Override
	public void trigger(MinecraftServer server, Random random) {
		for (ServerWorld world : server.getWorlds()) {
			if (world.getRegistryKey() == World.OVERWORLD) {
				int weatherType = random.nextInt(3);

				switch (weatherType) {
					case 0: // Clear weather
						world.setWeather(6000, 0, false, false);
						break;
					case 1: // Rain
						world.setWeather(0, 6000, true, false);
						break;
					case 2: // Thunder
						world.setWeather(0, 6000, true, true);
						break;
				}
			}
		}
	}

	@Override
	public String getName() {
		return "Weather Chaos";
	}
}
