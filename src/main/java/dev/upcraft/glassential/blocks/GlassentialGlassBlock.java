package dev.upcraft.glassential.blocks;

import dev.upcraft.glassential.Glassential;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class GlassentialGlassBlock extends AbstractGlassBlock {

    private final BlockProperties[] properties;
    private final boolean dark;
    private final boolean ethereal;
    private final boolean redstone;
    private final boolean reverseEthereal;
    private final TagKey<Block> cullingTag;

    public GlassentialGlassBlock(String cullId, BlockProperties... properties) {
        this(settings -> settings, cullId, properties);
    }

    public GlassentialGlassBlock(UnaryOperator<AbstractBlock.Settings> settingsApplier, String cullId, BlockProperties... properties) {
        super(settingsApplier.apply(FabricBlockSettings.copy(Blocks.GLASS).solid()));
        this.properties = properties;
        List<BlockProperties> props = Arrays.asList(properties);
        this.dark = props.contains(BlockProperties.TINTED);
        this.ethereal = props.contains(BlockProperties.ETHEREAL);
        this.redstone = props.contains(BlockProperties.REDSTONE);
        this.reverseEthereal = !this.ethereal && props.contains(BlockProperties.REVERSE_ETHEREAL);
        cullingTag = TagKey.of(RegistryKeys.BLOCK, new Identifier(Glassential.MODID, "no_cull/" + cullId));
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return (this.reverseEthereal && type != NavigationType.WATER) || super.canPathfindThrough(state, world, pos, type);
    }

    @Deprecated
    @Override
    public int getOpacity(BlockState state, BlockView view, BlockPos pos) {
        return this.dark ? view.getMaxLightLevel() : super.getOpacity(state, view, pos);
    }

    @Deprecated
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        if (this.ethereal || this.reverseEthereal) {
            //need that absent check to trick some cached values, else ethereal glass pushes entities out
            return context != ShapeContext.absent() && context.isDescending() == this.reverseEthereal ? state.getOutlineShape(view, pos, context) : VoxelShapes.empty();
        }
        return super.getCollisionShape(state, view, pos, context);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, BlockView view, List<Text> tooltip, TooltipContext options) {
        for (BlockProperties property : properties) {
            tooltip.add(Text.translatable(property.getTranslationKey()).formatted(property.getFormatting()));
        }
    }

    @Deprecated
    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return this.redstone || super.emitsRedstonePower(state);
    }

    @Deprecated
    @Override
    public int getWeakRedstonePower(BlockState state, BlockView view, BlockPos pos, Direction direction) {
        return this.redstone ? 15 : super.getWeakRedstonePower(state, view, pos, direction);
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.isIn(cullingTag) || super.isSideInvisible(state, stateFrom, direction);
    }
}
