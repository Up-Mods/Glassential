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
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
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
    public static final RegistryKey<ItemGroup> GLASSENTIAL_ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MODID, "items"));

    public static final TagKey<Block> TINTED_GLASS_NO_CULL = TagKey.of(RegistryKeys.BLOCK, new Identifier(MODID, "no_cull/tinted"));

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM_GROUP, GLASSENTIAL_ITEM_GROUP, FabricItemGroup.builder()
                .displayName(Text.translatable(Util.createTranslationKey("itemGroup", new Identifier(MODID, "items"))))
                .icon(() -> new ItemStack(LIGHT_GLASS))
                .entries((displayContext, entries) -> Registries.ITEM.streamEntries()
                        .filter(itemReference -> itemReference.registryKey().getValue().getNamespace().equals(MODID))
                        .sorted(Comparator.comparing(itemReference -> itemReference.registryKey().getValue().getPath()))
                        .map(RegistryEntry.Reference::value)
                        .forEachOrdered(entries::add))
                .build()
        );

        TINTED_ETHEREAL_GLASS = registerBlock("tinted_ethereal_glass", new GlassentialGlassBlock(AbstractBlock.Settings::noCollision, "tinted", BlockProperties.TINTED, BlockProperties.ETHEREAL));
        TINTED_REVERSE_ETHEREAL_GLASS = registerBlock("tinted_reverse_ethereal_glass", new GlassentialGlassBlock(AbstractBlock.Settings::noCollision, "tinted", BlockProperties.TINTED, BlockProperties.REVERSE_ETHEREAL));
        ETHEREAL_GLASS = registerBlock("ethereal_glass", new GlassentialGlassBlock(AbstractBlock.Settings::noCollision, "ethereal", BlockProperties.ETHEREAL));
        REVERSE_ETHEREAL_GLASS = registerBlock("reverse_ethereal_glass", new GlassentialGlassBlock(AbstractBlock.Settings::noCollision, "ethereal", BlockProperties.REVERSE_ETHEREAL));
        GHOSTLY_GLASS = registerBlock("ghostly_glass", new GlassentialGlassBlock(AbstractBlock.Settings::noCollision, "ghostly", BlockProperties.GHOSTLY));
        LIGHT_GLASS = registerBlock("light_glass", new GlassentialGlassBlock(settings -> settings.luminance(state -> 15), "light", BlockProperties.LUMINOUS));
        REDSTONE_GLASS = registerBlock("redstone_glass", new GlassentialGlassBlock("redstone", BlockProperties.REDSTONE), ItemGroups.REDSTONE);
    }

    private static Block registerBlock(String name, Block block, @Nullable RegistryKey<ItemGroup> itemGroup) {
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
