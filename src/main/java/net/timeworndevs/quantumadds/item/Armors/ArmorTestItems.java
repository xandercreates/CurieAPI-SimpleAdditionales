package net.timeworndevs.quantumadds.item.Armors;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.timeworndevs.quantumadds.Quantum;
import net.timeworndevs.quantumadds.item.HazmatSuitItem;

public class ArmorTestItems {
    public static final Item HAZMAT_HELMET = new HazmatSuitItem(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new FabricItemSettings().rarity(Rarity.RARE));
    public static final Item HAZMAT_CHESTPLATE = new HazmatSuitItem(ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE, new FabricItemSettings().rarity(Rarity.RARE));
    public static final Item HAZMAT_LEGGINGS = new HazmatSuitItem(ArmorMaterials.LEATHER, ArmorItem.Type.LEGGINGS, new FabricItemSettings().rarity(Rarity.RARE));
    public static final Item HAZMAT_BOOTS = new HazmatSuitItem(ArmorMaterials.LEATHER, ArmorItem.Type.BOOTS, new FabricItemSettings().rarity(Rarity.RARE));

    public static final Item[] allArmors = {HAZMAT_BOOTS, HAZMAT_LEGGINGS, HAZMAT_CHESTPLATE, HAZMAT_HELMET};
    public static void registerItems() {
        registerItem("hazmatd_helmet", HAZMAT_HELMET);
        registerItem("hazmatd_chestplate", HAZMAT_CHESTPLATE);
        registerItem("hazmatd_leggings", HAZMAT_LEGGINGS);
        registerItem("hazmatd_boots", HAZMAT_BOOTS);

        /*registerItem("hazmatc_helmet", HAZMAT_HELMET);
        registerItem("hazmatc_chestplate", HAZMAT_CHESTPLATE);
        registerItem("hazmatc_leggings", HAZMAT_LEGGINGS);
        registerItem("hazmatc_boots", HAZMAT_BOOTS);

        registerItem("hazmatb_helmet", HAZMAT_HELMET);
        registerItem("hazmatb_chestplate", HAZMAT_CHESTPLATE);
        registerItem("hazmatb_leggings", HAZMAT_LEGGINGS);
        registerItem("hazmatb_boots", HAZMAT_BOOTS);

        registerItem("hazmata_helmet", HAZMAT_HELMET);
        registerItem("hazmata_chestplate", HAZMAT_CHESTPLATE);
        registerItem("hazmata_leggings", HAZMAT_LEGGINGS);
        registerItem("hazmata_boots", HAZMAT_BOOTS);*/
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Quantum.MOD_ID, name), item);
    }
}