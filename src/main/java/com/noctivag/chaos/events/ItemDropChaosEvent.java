package com.noctivag.chaos.events;

import com.noctivag.chaos.ChaosMod;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Random;

public class ItemDropChaosEvent implements ChaosEvent {
	private static final Item[] ITEMS = {
		Items.DIAMOND,
		Items.IRON_INGOT,
		Items.GOLD_INGOT,
		Items.EMERALD,
		Items.COAL,
		Items.DIRT,
		Items.COBBLESTONE,
		Items.STICK,
		Items.BREAD,
		Items.APPLE,
		Items.GOLDEN_APPLE,
		Items.ENCHANTED_GOLDEN_APPLE,
		Items.TNT,
		Items.OBSIDIAN,
		Items.ENDER_PEARL,
		Items.SLIME_BALL,
		Items.REDSTONE,
		Items.GLOWSTONE_DUST,
		Items.GUNPOWDER,
		Items.ARROW
	};

	@Override
	public void trigger(MinecraftServer server, Random random) {
		List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
		if (players.isEmpty()) return;

		ServerPlayerEntity targetPlayer = players.get(random.nextInt(players.size()));
		ServerWorld world = targetPlayer.getServerWorld();

		Item item = ITEMS[random.nextInt(ITEMS.length)];
		int count = 1 + random.nextInt(ChaosMod.getConfig().maxItemsPerDrop);

		// Drop items near player
		BlockPos pos = targetPlayer.getBlockPos();
		ItemStack stack = new ItemStack(item, count);
		ItemEntity itemEntity = new ItemEntity(
			world,
			pos.getX() + 0.5,
			pos.getY() + 1,
			pos.getZ() + 0.5,
			stack
		);
		itemEntity.setVelocity(
			(random.nextDouble() - 0.5) * 0.3,
			0.3,
			(random.nextDouble() - 0.5) * 0.3
		);
		world.spawnEntity(itemEntity);
	}

	@Override
	public String getName() {
		return "Item Drop Chaos";
	}
}
