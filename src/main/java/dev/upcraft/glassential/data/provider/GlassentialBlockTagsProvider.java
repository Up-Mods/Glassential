package dev.upcraft.glassential.data.provider;

import dev.upcraft.glassential.Glassential;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class GlassentialBlockTagsProvider extends FabricTagProvider.BlockTagProvider {

    private static final TagKey<Block> NO_CULL_ETHEREAL = TagKey.create(Registries.BLOCK, Glassential.id("no_cull/ethereal"));
    private static final TagKey<Block> NO_CULL_GHOSTLY = TagKey.create(Registries.BLOCK, Glassential.id("no_cull/ghostly"));
    private static final TagKey<Block> NO_CULL_LIGHT = TagKey.create(Registries.BLOCK, Glassential.id("no_cull/light"));
    private static final TagKey<Block> NO_CULL_REDSTONE = TagKey.create(Registries.BLOCK, Glassential.id("no_cull/redstone"));
    private static final TagKey<Block> NO_CULL_TINTED = TagKey.create(Registries.BLOCK, Glassential.id("no_cull/tinted"));

    public GlassentialBlockTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        getOrCreateTagBuilder(NO_CULL_ETHEREAL).add(Glassential.ETHEREAL_GLASS, Glassential.REVERSE_ETHEREAL_GLASS);
        getOrCreateTagBuilder(NO_CULL_GHOSTLY).add(Glassential.GHOSTLY_GLASS);
        getOrCreateTagBuilder(NO_CULL_LIGHT).add(Glassential.LIGHT_GLASS);
        getOrCreateTagBuilder(NO_CULL_REDSTONE).add(Glassential.REDSTONE_GLASS);
        getOrCreateTagBuilder(NO_CULL_TINTED).add(Blocks.TINTED_GLASS, Glassential.TINTED_ETHEREAL_GLASS, Glassential.TINTED_REVERSE_ETHEREAL_GLASS);

    }
}
