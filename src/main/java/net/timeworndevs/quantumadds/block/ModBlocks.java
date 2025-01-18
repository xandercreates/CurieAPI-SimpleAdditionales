package net.timeworndevs.quantumadds.block;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.timeworndevs.quantumadds.Quantum;

public class ModBlocks {

    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Quantum.MOD_ID, name), block);
    }

    public static final Block TUNGSTEN_ORE = registerBlock("tungsten_ore", new Block(FabricBlockSettings.create().strength(4.5f)));
    public static final Block DEEPSLATE_TUNGSTEN_ORE = registerBlock("deepslate_tungsten_ore", new Block(FabricBlockSettings.create().strength(5.5f)));
    public static final Block TUNGSTEN_BLOCK = registerBlock("tungsten_block", new Block(FabricBlockSettings.create().strength(7.0f)));
    public static final Block HEAVY_TUNGSTEN_BLOCK = registerBlock("heavy_tungsten_block", new HeavyTungstenBlock(FabricBlockSettings.create().strength(7.0f)));

    public static final Block PLUTONIUM_ORE = registerBlock("plutonium_ore", new Block(FabricBlockSettings.create().strength(4.0f)));
    public static final Block DEEPSLATE_PLUTONIUM_ORE = registerBlock("deepslate_plutonium_ore", new Block(FabricBlockSettings.create().strength(5.0f)));
    //public static final Block PLUTONIUM_CORE = registerBlock("plutonium_core", new PlutoniumBlock(FabricBlockSettings.create().strength(4.0f))); GONNA UPDATE THIS SOME DAY IN THE FUTUREEEEEEEEEEEEEEEEEEEEEE

    public static final Block METEORITE_ROCK = registerBlock("meteorite_rock", new Block(FabricBlockSettings.create().strength(4.0f)));

    public static final Block NUCLEAR_WASTE = registerBlock("nuclear_waste", new Block(FabricBlockSettings.create().strength(4.0f)));

    public static final Block REACTOR_CORE = registerBlock("reactor_core", new ReactorCore(FabricBlockSettings.create().strength(8f).nonOpaque().luminance(4)));

    public static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Quantum.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
    }

    private static void creativeEntries(FabricItemGroupEntries entries) {

        entries.add(METEORITE_ROCK);
        entries.add(NUCLEAR_WASTE);
        entries.add(REACTOR_CORE);
        entries.add(TUNGSTEN_BLOCK);
        entries.add(TUNGSTEN_ORE);
        entries.add(DEEPSLATE_TUNGSTEN_ORE);
        entries.add(TUNGSTEN_BLOCK);
        entries.add(HEAVY_TUNGSTEN_BLOCK);

    }
    public static void registerBlockItems() {
        ItemGroupEvents.modifyEntriesEvent(RegistryKey.of(Registries.ITEM_GROUP.getKey(), new Identifier(Quantum.MOD_ID, "building_blocks"))).register(ModBlocks::creativeEntries);
    }
    public static void registerBlocks() {}

}
