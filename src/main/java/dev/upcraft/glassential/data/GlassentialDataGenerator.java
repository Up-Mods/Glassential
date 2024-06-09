package dev.upcraft.glassential.data;

import dev.upcraft.glassential.data.provider.GlassentialBlockLootTableProvider;
import dev.upcraft.glassential.data.provider.GlassentialModelProvider;
import dev.upcraft.glassential.data.provider.GlassentialRecipesProvider;
import dev.upcraft.glassential.data.provider.GlassentialBlockTagsProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class GlassentialDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        var pack = generator.createPack();

        pack.addProvider(GlassentialBlockTagsProvider::new);
        pack.addProvider(GlassentialBlockLootTableProvider::new);
        pack.addProvider(GlassentialRecipesProvider::new);

        pack.addProvider(GlassentialModelProvider::new);

    }
}
