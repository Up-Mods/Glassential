package dev.upcraft.glassential.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
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

    public GlassentialGlassBlock(BlockProperties... properties) {
        this(settings -> settings, properties);
    }

    public GlassentialGlassBlock(UnaryOperator<AbstractBlock.Settings> settingsApplier, BlockProperties... properties) {
        super(settingsApplier.apply(FabricBlockSettings.copy(Blocks.GLASS).solid()));
        this.properties = properties;
        List<BlockProperties> props = Arrays.asList(properties);
        this.dark = props.contains(BlockProperties.TINTED);
        this.ethereal = props.contains(BlockProperties.ETHEREAL);
        this.redstone = props.contains(BlockProperties.REDSTONE);
        this.reverseEthereal = !this.ethereal && props.contains(BlockProperties.REVERSE_ETHEREAL);
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

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return this.redstone || super.emitsRedstonePower(state);
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView view, BlockPos pos, Direction direction) {
        return this.redstone ? 15 : super.getWeakRedstonePower(state, view, pos, direction);
    }
}
