package net.timeworndevs.quantumadds.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.timeworndevs.quantumadds.registries.QuantumBlocks;
import net.timeworndevs.quantumadds.registries.QuantumItems;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(QuantumBlocks.TUNGSTEN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(QuantumBlocks.DEEPSLATE_TUNGSTEN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(QuantumBlocks.TUNGSTEN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(QuantumBlocks.PLUTONIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(QuantumBlocks.DEEPSLATE_PLUTONIUM_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        for (Item item : QuantumItems.allArmors) {
            itemModelGenerator.registerArmor((ArmorItem) item);
        }
    }
}
