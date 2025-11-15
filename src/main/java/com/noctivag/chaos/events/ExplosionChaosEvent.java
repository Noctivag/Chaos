package com.noctivag.chaos.events;

import com.noctivag.chaos.ChaosMod;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.List;
import java.util.Random;

public class ExplosionChaosEvent implements ChaosEvent {
	@Override
	public void trigger(MinecraftServer server, Random random) {
		List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
		if (players.isEmpty()) return;

		ServerPlayerEntity targetPlayer = players.get(random.nextInt(players.size()));
		ServerWorld world = targetPlayer.getServerWorld();

		// Create explosion near player (but not on them)
		double offsetX = (random.nextDouble() - 0.5) * 10;
		double offsetY = random.nextDouble() * 3;
		double offsetZ = (random.nextDouble() - 0.5) * 10;

		BlockPos pos = targetPlayer.getBlockPos().add(offsetX, offsetY, offsetZ);

		float power = 1.0f + random.nextFloat() * ChaosMod.getConfig().explosionRadius;

		world.createExplosion(
			null,
			pos.getX(),
			pos.getY(),
			pos.getZ(),
			power,
			false,
			World.ExplosionSourceType.TNT
		);
	}

	@Override
	public String getName() {
		return "Explosion Chaos";
	}
}
