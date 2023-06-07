package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ShadowItems implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("shadowitems");

    // Mod ID
    public static final String MOD_ID = "shadowitems";

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Hello Fabric world! Shadow Items Has Loaded!");

        loadItemRegistry();
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

    private void loadItemRegistry() {
        try {
            InputStream inputStream = FabricLoader.getInstance().getModContainer(MOD_ID).get().getRootPath().resolve("resources/itemRegistry.txt").toUri().toURL().openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    String itemGroup = parts[0];
                    String itemId = parts[1];
                    String nbtBase64 = parts[2];

                    Item item = Registry.ITEM.get(new Identifier(itemId));
                    if (item != null) {
                        ItemStack stack = new ItemStack(item);
                        if (!nbtBase64.isEmpty()) {
                            stack.setTag(item.fromTag(Base64.getDecoder().decode(nbtBase64)));
                        }

                        ItemGroup group = getItemGroupByName(itemGroup);
                        if (group != null) {
                            group.appendItems((stacks) -> stacks.add(stack));
                        }
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ItemGroup getItemGroupByName(String name) {
        switch (name) {
            case "special":
                return SPECIAL_GROUP;
            case "grief":
                return GRIEF_GROUP;
            case "exploits":
                return EXPLOITS_GROUP;
            default:
                return null;
        }
    }
}
