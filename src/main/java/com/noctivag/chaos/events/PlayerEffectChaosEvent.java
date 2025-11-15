package com.noctivag.chaos.events;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.List;
import java.util.Random;

public class PlayerEffectChaosEvent implements ChaosEvent {
	private static final RegistryEntry<StatusEffect>[] EFFECTS = new RegistryEntry[]{
		StatusEffects.SPEED,
		StatusEffects.SLOWNESS,
		StatusEffects.HASTE,
		StatusEffects.MINING_FATIGUE,
		StatusEffects.STRENGTH,
		StatusEffects.JUMP_BOOST,
		StatusEffects.NAUSEA,
		StatusEffects.REGENERATION,
		StatusEffects.RESISTANCE,
		StatusEffects.FIRE_RESISTANCE,
		StatusEffects.WATER_BREATHING,
		StatusEffects.INVISIBILITY,
		StatusEffects.BLINDNESS,
		StatusEffects.NIGHT_VISION,
		StatusEffects.WEAKNESS,
		StatusEffects.POISON,
		StatusEffects.WITHER,
		StatusEffects.HEALTH_BOOST,
		StatusEffects.ABSORPTION,
		StatusEffects.SATURATION,
		StatusEffects.GLOWING,
		StatusEffects.LEVITATION,
		StatusEffects.LUCK,
		StatusEffects.UNLUCK,
		StatusEffects.SLOW_FALLING
	};

	@Override
	public void trigger(MinecraftServer server, Random random) {
		List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
		if (players.isEmpty()) return;

		// Apply effect to one or all players
		boolean applyToAll = random.nextBoolean();
		RegistryEntry<StatusEffect> effect = EFFECTS[random.nextInt(EFFECTS.length)];
		int duration = 200 + random.nextInt(400); // 10-30 seconds
		int amplifier = random.nextInt(3); // Level 1-3

		if (applyToAll) {
			for (ServerPlayerEntity player : players) {
				player.addStatusEffect(new StatusEffectInstance(effect, duration, amplifier));
			}
		} else {
			ServerPlayerEntity targetPlayer = players.get(random.nextInt(players.size()));
			targetPlayer.addStatusEffect(new StatusEffectInstance(effect, duration, amplifier));
		}
	}

	@Override
	public String getName() {
		return "Player Effect Chaos";
	}
}
