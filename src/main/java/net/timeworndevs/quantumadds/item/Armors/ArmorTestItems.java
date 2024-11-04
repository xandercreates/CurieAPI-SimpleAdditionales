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
    public static final Item HAZMAT_HELMET = new HazmatSuitItem(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new FabricItemSettings().rarity(Rarity.RARE), new String[]{"textures/armor/hazmatd_suit_feet.png", "textures/armor/hazmatd_suit_main.png"});
    public static final Item HAZMAT_CHESTPLATE = new HazmatSuitItem(ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE, new FabricItemSettings().rarity(Rarity.RARE), new String[]{"textures/armor/hazmatd_suit_feet.png", "textures/armor/hazmatd_suit_main.png"});
    public static final Item HAZMAT_LEGGINGS = new HazmatSuitItem(ArmorMaterials.LEATHER, ArmorItem.Type.LEGGINGS, new FabricItemSettings().rarity(Rarity.RARE), new String[]{"textures/armor/hazmatd_suit_feet.png", "textures/armor/hazmatd_suit_main.png"});
    public static final Item HAZMAT_BOOTS = new HazmatSuitItem(ArmorMaterials.LEATHER, ArmorItem.Type.BOOTS, new FabricItemSettings().rarity(Rarity.RARE), new String[]{"textures/armor/hazmatd_suit_feet.png", "textures/armor/hazmatd_suit_main.png"});

    public static final Item HAZMATC_HELMET = new HazmatSuitItem(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new FabricItemSettings().rarity(Rarity.RARE), new String[]{"textures/armor/hazmatc_suit_feet.png", "textures/armor/hazmatc_suit_main.png"});
    public static final Item HAZMATC_CHESTPLATE = new HazmatSuitItem(ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE, new FabricItemSettings().rarity(Rarity.RARE), new String[]{"textures/armor/hazmatc_suit_feet.png", "textures/armor/hazmatc_suit_main.png"});
    public static final Item HAZMATC_LEGGINGS = new HazmatSuitItem(ArmorMaterials.LEATHER, ArmorItem.Type.LEGGINGS, new FabricItemSettings().rarity(Rarity.RARE), new String[]{"textures/armor/hazmatc_suit_feet.png", "textures/armor/hazmatc_suit_main.png"});
    public static final Item HAZMATC_BOOTS = new HazmatSuitItem(ArmorMaterials.LEATHER, ArmorItem.Type.BOOTS, new FabricItemSettings().rarity(Rarity.RARE), new String[]{"textures/armor/hazmatc_suit_feet.png", "textures/armor/hazmatc_suit_main.png"});

    public static final Item HAZMATB_HELMET = new HazmatSuitItem(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new FabricItemSettings().rarity(Rarity.RARE), new String[]{"textures/armor/hazmatb_suit_feet.png", "textures/armor/hazmatb_suit_main.png"});
    public static final Item HAZMATB_CHESTPLATE = new HazmatSuitItem(ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE, new FabricItemSettings().rarity(Rarity.RARE), new String[]{"textures/armor/hazmatb_suit_feet.png", "textures/armor/hazmatb_suit_main.png"});
    public static final Item HAZMATB_LEGGINGS = new HazmatSuitItem(ArmorMaterials.LEATHER, ArmorItem.Type.LEGGINGS, new FabricItemSettings().rarity(Rarity.RARE), new String[]{"textures/armor/hazmatb_suit_feet.png", "textures/armor/hazmatb_suit_main.png"});
    public static final Item HAZMATB_BOOTS = new HazmatSuitItem(ArmorMaterials.LEATHER, ArmorItem.Type.BOOTS, new FabricItemSettings().rarity(Rarity.RARE), new String[]{"textures/armor/hazmatb_suit_feet.png", "textures/armor/hazmatb_suit_main.png"});

    public static final Item[] allArmors = {HAZMAT_BOOTS, HAZMAT_LEGGINGS, HAZMAT_CHESTPLATE, HAZMAT_HELMET, HAZMATC_BOOTS, HAZMATC_LEGGINGS, HAZMATC_CHESTPLATE, HAZMATC_HELMET,
            HAZMATB_BOOTS, HAZMATB_LEGGINGS, HAZMATB_CHESTPLATE, HAZMATB_HELMET};
    public static void registerItems() {
        registerItem("hazmatd_helmet", HAZMAT_HELMET);
        registerItem("hazmatd_chestplate", HAZMAT_CHESTPLATE);
        registerItem("hazmatd_leggings", HAZMAT_LEGGINGS);
        registerItem("hazmatd_boots", HAZMAT_BOOTS);

        registerItem("hazmatc_helmet", HAZMATC_HELMET);
        registerItem("hazmatc_chestplate", HAZMATC_CHESTPLATE);
        registerItem("hazmatc_leggings", HAZMATC_LEGGINGS);
        registerItem("hazmatc_boots", HAZMATC_BOOTS);

        registerItem("hazmatb_helmet", HAZMATB_HELMET);
        registerItem("hazmatb_chestplate", HAZMATB_CHESTPLATE);
        registerItem("hazmatb_leggings", HAZMATB_LEGGINGS);
        registerItem("hazmatb_boots", HAZMATB_BOOTS);

        /*registerItem("hazmata_helmet", HAZMAT_HELMET);
        registerItem("hazmata_chestplate", HAZMAT_CHESTPLATE);
        registerItem("hazmata_leggings", HAZMAT_LEGGINGS);
        registerItem("hazmata_boots", HAZMAT_BOOTS);*/
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Quantum.MOD_ID, name), item);
    }
}