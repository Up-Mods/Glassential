package dev.upcraft.glassential.data.provider;

import dev.upcraft.glassential.Glassential;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;

public class GlassentialModelProvider extends FabricModelProvider {

    public GlassentialModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(Glassential.GHOSTLY_GLASS);
        blockStateModelGenerator.createTrivialCube(Glassential.ETHEREAL_GLASS);
        blockStateModelGenerator.createTrivialCube(Glassential.REVERSE_ETHEREAL_GLASS);
        blockStateModelGenerator.createTrivialCube(Glassential.LIGHT_GLASS);
        blockStateModelGenerator.createTrivialCube(Glassential.REDSTONE_GLASS);
        blockStateModelGenerator.createTrivialCube(Glassential.TINTED_ETHEREAL_GLASS);
        blockStateModelGenerator.createTrivialCube(Glassential.TINTED_REVERSE_ETHEREAL_GLASS);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

    }
}
