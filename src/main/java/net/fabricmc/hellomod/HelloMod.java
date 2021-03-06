package net.fabricmc.hellomod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.hellomod.blocks.MessageOnUseBlock;
import net.fabricmc.hellomod.items.SoundOnUseItem;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloMod implements ModInitializer {
	private static final String HELLOMOD_MODID = "hellomod";

    public static final Logger LOGGER = LoggerFactory.getLogger(HelloMod.HELLOMOD_MODID);

	// creating blocks
	// TODO : damageable, take damage on use (state => textures to display)
	public static final Block TEST_BLOCK = new MessageOnUseBlock(FabricBlockSettings.of(Material.WOOL).strength(4.0f, 20.0f).requiresTool());

	// Creating items
	// TODO : damageable, take durability damage on use
	public static final SoundOnUseItem TEST_ITEM = new SoundOnUseItem(new FabricItemSettings());

	// Creating item groups
	public static final ItemGroup GENERAL_GROUP = FabricItemGroupBuilder.create(new Identifier(HelloMod.HELLOMOD_MODID, "general"))
			.icon(() -> new ItemStack(HelloMod.TEST_ITEM))
			.appendItems(stacks -> {
				stacks.add(new ItemStack(HelloMod.TEST_ITEM));
				stacks.add(new ItemStack(HelloMod.TEST_BLOCK));
			})
			.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registry.BLOCK, new Identifier(HelloMod.HELLOMOD_MODID, "test_block"), HelloMod.TEST_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(HelloMod.HELLOMOD_MODID, "test_block"), new BlockItem(HelloMod.TEST_BLOCK, new FabricItemSettings()));

		Registry.register(Registry.ITEM, new Identifier(HelloMod.HELLOMOD_MODID, "test_item"), HelloMod.TEST_ITEM);

		LOGGER.info("Hello Fabric world!");
	}
}
