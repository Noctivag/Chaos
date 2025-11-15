package com.noctivag.chaos.events;

import com.noctivag.chaos.ChaosMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Random;

public class MobSpawnChaosEvent implements ChaosEvent {
	private static final EntityType<?>[] SPAWN_TYPES = {
		EntityType.ZOMBIE,
		EntityType.SKELETON,
		EntityType.CREEPER,
		EntityType.SPIDER,
		EntityType.ENDERMAN,
		EntityType.WITCH,
		EntityType.SLIME,
		EntityType.PHANTOM,
		EntityType.COW,
		EntityType.PIG,
		EntityType.CHICKEN,
		EntityType.SHEEP
	};

	@Override
	public void trigger(MinecraftServer server, Random random) {
		List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
		if (players.isEmpty()) return;

		ServerPlayerEntity targetPlayer = players.get(random.nextInt(players.size()));
		ServerWorld world = targetPlayer.getServerWorld();

		EntityType<?> entityType = SPAWN_TYPES[random.nextInt(SPAWN_TYPES.length)];
		int mobCount = 1 + random.nextInt(ChaosMod.getConfig().maxMobsPerSpawn);

		for (int i = 0; i < mobCount; i++) {
			// Spawn near player
			double offsetX = (random.nextDouble() - 0.5) * 20;
			double offsetZ = (random.nextDouble() - 0.5) * 20;

			BlockPos spawnPos = targetPlayer.getBlockPos().add((int)offsetX, 0, (int)offsetZ);
			spawnPos = world.getTopPosition(net.minecraft.world.Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, spawnPos);

			try {
				MobEntity entity = (MobEntity) entityType.create(world);
				if (entity != null) {
					entity.refreshPositionAndAngles(
						spawnPos.getX() + 0.5,
						spawnPos.getY(),
						spawnPos.getZ() + 0.5,
						random.nextFloat() * 360,
						0
					);
					entity.initialize(world, world.getLocalDifficulty(spawnPos), SpawnReason.COMMAND, null);
					world.spawnEntity(entity);
				}
			} catch (Exception e) {
				// Ignore spawn failures
			}
		}
	}

	@Override
	public String getName() {
		return "Mob Spawn Chaos";
	}
}
