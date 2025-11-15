package com.noctivag.chaos;

public class ChaosConfig {
	// Time between chaos events in ticks (20 ticks = 1 second)
	public int eventInterval = 600; // 30 seconds default
	public int eventVariance = 400; // Random variance

	// Event weights
	public boolean enableWeatherEvents = true;
	public boolean enableMobEvents = true;
	public boolean enablePlayerEffects = true;
	public boolean enableItemEvents = true;
	public boolean enableExplosions = true;
	public boolean enableLightning = true;

	// Event intensities
	public int maxMobsPerSpawn = 5;
	public int explosionRadius = 3;
	public int maxItemsPerDrop = 16;

	public void load() {
		// TODO: Load from config file if needed
		ChaosMod.LOGGER.info("Chaos configuration loaded");
	}
}
