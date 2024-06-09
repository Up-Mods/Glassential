package dev.upcraft.glassential.data.provider;

import dev.upcraft.glassential.Glassential;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class GlassentialRecipesProvider extends FabricRecipeProvider {

    public GlassentialRecipesProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Glassential.ETHEREAL_GLASS, 4)
                .group("glassential_ethereal_glass")
                .define('#', Blocks.GLASS)
                .define('X', Items.ENDER_EYE)
                .pattern(" # ")
                .pattern("#X#")
                .pattern(" # ")
                .unlockedBy("has_ender_eye", has(Items.ENDER_EYE))
                .unlockedBy("has_ender_pearl", has(Items.ENDER_PEARL))
                .save(exporter, Glassential.id("ethereal_glass"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Glassential.ETHEREAL_GLASS)
                .group("glassential_ethereal_glass")
                .requires(Glassential.REVERSE_ETHEREAL_GLASS)
                .unlockedBy("has_reverse_ethereal_glass", has(Glassential.REVERSE_ETHEREAL_GLASS))
                .save(exporter, Glassential.id("ethereal_glass_from_reverse"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Glassential.REVERSE_ETHEREAL_GLASS)
                .group("glassential_reverse_ethereal_glass")
                .requires(Glassential.ETHEREAL_GLASS)
                .unlockedBy("has_ethereal_glass", has(Glassential.ETHEREAL_GLASS))
                .save(exporter, Glassential.id("reverse_ethereal_glass"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Glassential.GHOSTLY_GLASS, 8)
                .group("glassential_ghostly_glass")
                .define('#', Blocks.GLASS)
                .define('X', Items.ENDER_PEARL)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .unlockedBy("has_ender_pearl", has(Items.ENDER_PEARL))
                .save(exporter, Glassential.id("ghostly_glass"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Glassential.LIGHT_GLASS, 8)
                .group("glassential_light_glass")
                .define('#', Blocks.GLASS)
                .define('X', Items.GLOWSTONE_DUST)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .unlockedBy("has_glowstone_dust", has(Items.GLOWSTONE_DUST))
                .save(exporter, Glassential.id("light_glass"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Glassential.REDSTONE_GLASS, 8)
                .group("glassential_redstone_glass")
                .define('#', Blocks.GLASS)
                .define('X', Blocks.REDSTONE_BLOCK)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .unlockedBy("has_redstone", has(Items.REDSTONE))
                .unlockedBy("has_redstone_block", has(Items.REDSTONE_BLOCK))
                .save(exporter, Glassential.id("redstone_glass"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Glassential.TINTED_ETHEREAL_GLASS, 8)
                .group("glassential_tinted_ethereal_glass")
                .define('#', Blocks.TINTED_GLASS)
                .define('X', Items.ENDER_EYE)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .unlockedBy("has_tinted_glass", has(Blocks.TINTED_GLASS))
                .save(exporter, Glassential.id("tinted_ethereal_glass"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Glassential.TINTED_ETHEREAL_GLASS, 2)
                .group("glassential_tinted_ethereal_glass")
                .define('#', Items.AMETHYST_SHARD)
                .define('X', Glassential.ETHEREAL_GLASS)
                .pattern(" # ")
                .pattern("#X#")
                .pattern(" # ")
                .unlockedBy("has_amethyst_shard", has(Items.AMETHYST_SHARD))
                .save(exporter, Glassential.id("tinted_ethereal_glass_from_ethereal"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Glassential.TINTED_ETHEREAL_GLASS)
                .group("glassential_tinted_ethereal_glass")
                .requires(Glassential.TINTED_REVERSE_ETHEREAL_GLASS)
                .unlockedBy("has_tinted_reverse_ethereal_glass", has(Glassential.TINTED_REVERSE_ETHEREAL_GLASS))
                .save(exporter, Glassential.id("tinted_ethereal_glass_from_reverse"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Glassential.TINTED_REVERSE_ETHEREAL_GLASS, 2)
                .group("glassential_tinted_reverse_ethereal_glass")
                .define('#', Items.AMETHYST_SHARD)
                .define('X', Glassential.REVERSE_ETHEREAL_GLASS)
                .pattern(" # ")
                .pattern("#X#")
                .pattern(" # ")
                .unlockedBy("has_amethyst_shard", has(Items.AMETHYST_SHARD))
                .save(exporter, Glassential.id("tinted_reverse_ethereal_glass"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Glassential.TINTED_REVERSE_ETHEREAL_GLASS)
                .group("glassential_tinted_reverse_ethereal_glass")
                .requires(Glassential.TINTED_ETHEREAL_GLASS)
                .unlockedBy("has_tinted_ethereal_glass", has(Glassential.TINTED_ETHEREAL_GLASS))
                .save(exporter, Glassential.id("tinted_reverse_ethereal_glass_from_ethereal"));
    }
}
