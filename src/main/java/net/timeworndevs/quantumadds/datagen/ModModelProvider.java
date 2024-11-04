package net.timeworndevs.quantumadds.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.ArmorItem;
import net.timeworndevs.quantumadds.item.Armors.ArmorTestItems;
import net.timeworndevs.quantumadds.item.ModItems;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.registerArmor(((ArmorItem) ArmorTestItems.HAZMAT_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ArmorTestItems.HAZMAT_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ArmorTestItems.HAZMAT_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ArmorTestItems.HAZMAT_BOOTS));


        itemModelGenerator.registerArmor(((ArmorItem) ArmorTestItems.HAZMATC_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ArmorTestItems.HAZMATC_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ArmorTestItems.HAZMATC_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ArmorTestItems.HAZMATC_BOOTS));

        itemModelGenerator.registerArmor(((ArmorItem) ArmorTestItems.HAZMATB_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ArmorTestItems.HAZMATB_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ArmorTestItems.HAZMATB_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ArmorTestItems.HAZMATB_BOOTS));

        /*itemModelGenerator.registerArmor(((ArmorItem) ArmorTestItems.HAZMATA_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ArmorTestItems.HAZMATA_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ArmorTestItems.HAZMATA_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ArmorTestItems.HAZMATA_BOOTS));*/
    }
}
