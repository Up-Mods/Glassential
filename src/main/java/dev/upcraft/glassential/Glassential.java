package dev.upcraft.glassential;

import dev.upcraft.glassential.blocks.BlockProperties;
import dev.upcraft.glassential.blocks.GlassentialGlassBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

public class Glassential implements ModInitializer {

    public static final String MODID = "glassential";

    public static Block TINTED_ETHEREAL_GLASS;
    public static Block TINTED_REVERSE_ETHEREAL_GLASS;
    public static Block ETHEREAL_GLASS;
    public static Block REVERSE_ETHEREAL_GLASS;
    public static Block GHOSTLY_GLASS;
    public static Block LIGHT_GLASS;
    public static Block REDSTONE_GLASS;

    @SuppressWarnings("unused")
    public static final ResourceKey<CreativeModeTab> GLASSENTIAL_ITEM_GROUP = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(MODID, "items"));

    public static final TagKey<Block> TINTED_GLASS_NO_CULL = TagKey.create(Registries.BLOCK, new ResourceLocation(MODID, "no_cull/tinted"));

    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, GLASSENTIAL_ITEM_GROUP, FabricItemGroup.builder()
                .title(Component.translatable(Util.makeDescriptionId("itemGroup", new ResourceLocation(MODID, "items"))))
                .icon(() -> new ItemStack(LIGHT_GLASS))
                .displayItems((displayContext, entries) -> BuiltInRegistries.ITEM.holders()
                        .filter(itemReference -> itemReference.key().location().getNamespace().equals(MODID))
                        .sorted(Comparator.comparing(itemReference -> itemReference.key().location().getPath()))
                        .map(Holder.Reference::value)
                        .forEachOrdered(entries::accept))
                .build()
        );

        TINTED_ETHEREAL_GLASS = registerBlock("tinted_ethereal_glass", new GlassentialGlassBlock(BlockBehaviour.Properties::noCollission, "tinted", BlockProperties.TINTED, BlockProperties.ETHEREAL));
        TINTED_REVERSE_ETHEREAL_GLASS = registerBlock("tinted_reverse_ethereal_glass", new GlassentialGlassBlock(BlockBehaviour.Properties::noCollission, "tinted", BlockProperties.TINTED, BlockProperties.REVERSE_ETHEREAL));
        ETHEREAL_GLASS = registerBlock("ethereal_glass", new GlassentialGlassBlock(BlockBehaviour.Properties::noCollission, "ethereal", BlockProperties.ETHEREAL));
        REVERSE_ETHEREAL_GLASS = registerBlock("reverse_ethereal_glass", new GlassentialGlassBlock(BlockBehaviour.Properties::noCollission, "ethereal", BlockProperties.REVERSE_ETHEREAL));
        GHOSTLY_GLASS = registerBlock("ghostly_glass", new GlassentialGlassBlock(BlockBehaviour.Properties::noCollission, "ghostly", BlockProperties.GHOSTLY));
        LIGHT_GLASS = registerBlock("light_glass", new GlassentialGlassBlock(settings -> settings.lightLevel(state -> 15), "light", BlockProperties.LUMINOUS));
        REDSTONE_GLASS = registerBlock("redstone_glass", new GlassentialGlassBlock("redstone", BlockProperties.REDSTONE), CreativeModeTabs.REDSTONE_BLOCKS);
    }

    private static Block registerBlock(String name, Block block, @Nullable ResourceKey<CreativeModeTab> itemGroup) {
        ResourceLocation blockName = new ResourceLocation(MODID, name);
        block = Registry.register(BuiltInRegistries.BLOCK, blockName, block);
        Item item = Registry.register(BuiltInRegistries.ITEM, blockName, new BlockItem(block, new Item.Properties()));

        if (itemGroup != null) {
            ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.accept(item));
        }

        return block;
    }

    private static Block registerBlock(String name, Block block) {
        return registerBlock(name, block, CreativeModeTabs.BUILDING_BLOCKS);
    }
}
