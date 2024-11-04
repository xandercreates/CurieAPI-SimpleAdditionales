package net.timeworndevs.quantumadds.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.timeworndevs.quantumadds.item.Armors.ArmorTestItems;
import net.timeworndevs.quantumadds.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {




        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ArmorTestItems.HAZMAT_HELMET, ArmorTestItems.HAZMAT_CHESTPLATE,
                        ArmorTestItems.HAZMAT_LEGGINGS, ArmorTestItems.HAZMAT_BOOTS);


        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ArmorTestItems.HAZMATC_HELMET, ArmorTestItems.HAZMATC_CHESTPLATE,
                        ArmorTestItems.HAZMATC_LEGGINGS, ArmorTestItems.HAZMATC_BOOTS);


        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ArmorTestItems.HAZMATB_HELMET, ArmorTestItems.HAZMATB_CHESTPLATE,
                        ArmorTestItems.HAZMATB_LEGGINGS, ArmorTestItems.HAZMATB_BOOTS);


        /*getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ArmorTestItems.HAZMATA_HELMET, ArmorTestItems.HAZMATA_CHESTPLATE,
                        ArmorTestItems.HAZMATA_LEGGINGS, ArmorTestItems.HAZMATA_BOOTS);*/

    }
}
