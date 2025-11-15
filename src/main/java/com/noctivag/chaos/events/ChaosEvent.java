package com.noctivag.chaos.events;

import net.minecraft.server.MinecraftServer;
import java.util.Random;

public interface ChaosEvent {
	/**
	 * Trigger this chaos event
	 * @param server The minecraft server instance
	 * @param random Random instance for randomization
	 */
	void trigger(MinecraftServer server, Random random);

	/**
	 * Get the name of this event
	 * @return Event name
	 */
	String getName();
}
