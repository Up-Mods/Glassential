package dev.upcraft.glassential.data.provider;

import dev.upcraft.glassential.Glassential;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class GlassentialBlockLootTableProvider extends FabricBlockLootTableProvider {

    public GlassentialBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropWhenSilkTouch(Glassential.GHOSTLY_GLASS);
        dropWhenSilkTouch(Glassential.ETHEREAL_GLASS);
        dropWhenSilkTouch(Glassential.REVERSE_ETHEREAL_GLASS);
        dropWhenSilkTouch(Glassential.LIGHT_GLASS);
        dropWhenSilkTouch(Glassential.REDSTONE_GLASS);
        dropWhenSilkTouch(Glassential.TINTED_ETHEREAL_GLASS);
        dropWhenSilkTouch(Glassential.TINTED_REVERSE_ETHEREAL_GLASS);
    }
}
