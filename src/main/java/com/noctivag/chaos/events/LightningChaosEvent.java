package com.noctivag.chaos.events;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.List;
import java.util.Random;

public class LightningChaosEvent implements ChaosEvent {
	@Override
	public void trigger(MinecraftServer server, Random random) {
		List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
		if (players.isEmpty()) return;

		ServerPlayerEntity targetPlayer = players.get(random.nextInt(players.size()));
		ServerWorld world = targetPlayer.getServerWorld();

		// Strike lightning near player (not directly on them)
		double offsetX = (random.nextDouble() - 0.5) * 15;
		double offsetZ = (random.nextDouble() - 0.5) * 15;

		BlockPos pos = targetPlayer.getBlockPos().add(offsetX, 0, offsetZ);
		pos = world.getTopPosition(net.minecraft.world.Heightmap.Type.MOTION_BLOCKING, pos);

		LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
		if (lightning != null) {
			lightning.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(pos));
			world.spawnEntity(lightning);
		}
	}

	@Override
	public String getName() {
		return "Lightning Chaos";
	}
}
