package net.timeworndevs.quantumadds.item;

import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.timeworndevs.quantumadds.item.Armors.ArmorTestItems;
import net.timeworndevs.quantumadds.item.GeigerCounter.GeigerCounter;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.timeworndevs.quantumadds.Quantum;
import net.minecraft.util.Rarity;


public class ModItems {

    public static final Item RED_PILL = registerItem("red_pill", new Item(new FabricItemSettings().food(ModFood.SUSPICIOUS_PILL)));
    public static final Item BLUE_PILL = registerItem("blue_pill", new Item(new FabricItemSettings().food(ModFood.MEDICAL_PILL)));


    public static final Item GEIGER_COUNTER = registerItem("geiger_counter", new GeigerCounter(new FabricItemSettings().maxCount(1).maxDamage(64).rarity(Rarity.RARE)));


    public static final Item PLUTONIUM = registerItem("plutonium", new Item(new FabricItemSettings()));

    public static Item RAW_TUNGSTEN = registerItem("raw_tungsten", new Item(new FabricItemSettings()));

    public static Item TUNGSTEN_INGOT = registerItem("tungsten_ingot", new Item(new FabricItemSettings()));


    public static final Item ALPHA_SRC = registerItem("alphasrc", new Item(new FabricItemSettings()));

    public static final Item BETA_SRC = registerItem("betasrc", new Item(new FabricItemSettings()));

    public static final Item GAMMA_SRC = registerItem("gammasrc", new Item(new FabricItemSettings()));




    public static Item RUBBER = registerItem("rubber", new Item(new FabricItemSettings()));
    public static Item PVC = registerItem("pvc", new Item(new FabricItemSettings()));




    private static void radiationEntries(FabricItemGroupEntries entries) {

        entries.add(GEIGER_COUNTER);
        entries.add(RUBBER);
        entries.add(PVC);

        entries.add(PLUTONIUM);
        entries.add(RAW_TUNGSTEN);
        entries.add(TUNGSTEN_INGOT);

        for(Item i: ArmorTestItems.allArmors) {
            entries.add(i);
        }

        entries.add(RED_PILL);
        entries.add(BLUE_PILL);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Quantum.MOD_ID, name), item);
    }

    public static void registerItems() {
        ItemGroupEvents.modifyEntriesEvent(RegistryKey.of(Registries.ITEM_GROUP.getKey(), new Identifier(Quantum.MOD_ID, "radiation"))).register(ModItems::radiationEntries);
    }
}
