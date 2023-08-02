package dev.upcraft.glassential;

import dev.upcraft.glassential.blocks.BlockProperties;
import dev.upcraft.glassential.blocks.GlassentialGlassBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
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
    public static final ItemGroup GLASSENTIAL_ITEM_GROUP = FabricItemGroup.builder(new Identifier(MODID, "items")).icon(() -> new ItemStack(LIGHT_GLASS))
            .entries((displayContext, entries) -> Registries.ITEM.streamEntries()
                    .filter(itemReference -> itemReference.registryKey().getValue().getNamespace().equals(MODID))
                    .sorted(Comparator.comparing(itemReference -> itemReference.registryKey().getValue().getPath()))
                    .map(RegistryEntry.Reference::value)
                    .forEachOrdered(entries::add))
            .build();

    @Override
    public void onInitialize() {
        TINTED_ETHEREAL_GLASS = registerBlock("tinted_ethereal_glass", new GlassentialGlassBlock(AbstractBlock.Settings::noCollision, BlockProperties.TINTED, BlockProperties.ETHEREAL));
        TINTED_REVERSE_ETHEREAL_GLASS = registerBlock("tinted_reverse_ethereal_glass", new GlassentialGlassBlock(AbstractBlock.Settings::noCollision, BlockProperties.TINTED, BlockProperties.REVERSE_ETHEREAL));
        ETHEREAL_GLASS = registerBlock("ethereal_glass", new GlassentialGlassBlock(AbstractBlock.Settings::noCollision, BlockProperties.ETHEREAL));
        REVERSE_ETHEREAL_GLASS = registerBlock("reverse_ethereal_glass", new GlassentialGlassBlock(AbstractBlock.Settings::noCollision, BlockProperties.REVERSE_ETHEREAL));
        GHOSTLY_GLASS = registerBlock("ghostly_glass", new GlassentialGlassBlock(AbstractBlock.Settings::noCollision, BlockProperties.GHOSTLY));
        LIGHT_GLASS = registerBlock("light_glass", new GlassentialGlassBlock(settings -> settings.luminance(state -> 15), BlockProperties.LUMINOUS));
        REDSTONE_GLASS = registerBlock("redstone_glass", new GlassentialGlassBlock(BlockProperties.REDSTONE), ItemGroups.REDSTONE);
    }

    private static Block registerBlock(String name, Block block, @Nullable ItemGroup itemGroup) {
        Identifier blockName = new Identifier(MODID, name);
        block = Registry.register(Registries.BLOCK, blockName, block);
        Item item = Registry.register(Registries.ITEM, blockName, new BlockItem(block, new Item.Settings()));

        if (itemGroup != null) {
            ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.add(item));
        }

        return block;
    }

    private static Block registerBlock(String name, Block block) {
        return registerBlock(name, block, ItemGroups.BUILDING_BLOCKS);
    }
}
