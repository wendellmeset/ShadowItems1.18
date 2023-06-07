package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.util.Identifier;

public class ShadowItems implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("shadowitems");

	//Mod ID
    	public static final String MOD_ID = "shadowitems";
	
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world! Shadow Items Has Loaded!");
	}
	
    	public static final ItemGroup EXPLOITS_GROUP = FabricItemGroupBuilder.create(
            	new Identifier(MOD_ID, "exploits"))
            	.icon(() -> new ItemStack(Blocks.ARMOR_STAND))
            	.build();

    	public static final ItemGroup GRIEF_GROUP = FabricItemGroupBuilder.create(
            	new Identifier(MOD_ID, "grief"))
            	.icon(() -> new ItemStack(Blocks.TNT))
            	.build();

    	public static final ItemGroup SPECIAL_GROUP = FabricItemGroupBuilder.create(
            	new Identifier(MOD_ID, "special"))
            	.icon(() -> new ItemStack(Blocks.STRUCTURE_VOID))
            	.build();	
}
