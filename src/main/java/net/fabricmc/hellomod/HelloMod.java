package net.fabricmc.hellomod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("hellomod");

	// Creating items
	// TODO : damageable, take durability damage on une
	public static final SoundOnUseItem TEST_ITEM = new SoundOnUseItem(new FabricItemSettings());

	// Creating item groups
	public static final ItemGroup GENERAL_GROUP = FabricItemGroupBuilder.create(new Identifier("hellomod", "general"))
			.icon(() -> new ItemStack(HelloMod.TEST_ITEM))
			.appendItems(stacks -> {
				stacks.add(new ItemStack(HelloMod.TEST_ITEM));
			})
			.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registry.ITEM, new Identifier("hellomod", "test_item"), HelloMod.TEST_ITEM);

		LOGGER.info("Hello Fabric world!");
	}
}
