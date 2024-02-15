package dev.upcraft.glassential.blocks;

import dev.upcraft.glassential.Glassential;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

@SuppressWarnings("deprecation")
public class GlassentialGlassBlock extends TransparentBlock {

    private final BlockProperties[] properties;
    private final boolean dark;
    private final boolean ethereal;
    private final boolean redstone;
    private final boolean reverseEthereal;
    private final TagKey<Block> cullingTag;

    public GlassentialGlassBlock(String cullId, BlockProperties... properties) {
        this(settings -> settings, cullId, properties);
    }

    public GlassentialGlassBlock(UnaryOperator<BlockBehaviour.Properties> settingsApplier, String cullId, BlockProperties... properties) {
        super(settingsApplier.apply(Properties.ofFullCopy(Blocks.GLASS).forceSolidOn()));
        this.properties = properties;
        List<BlockProperties> props = Arrays.asList(properties);
        this.dark = props.contains(BlockProperties.TINTED);
        this.ethereal = props.contains(BlockProperties.ETHEREAL);
        this.redstone = props.contains(BlockProperties.REDSTONE);
        this.reverseEthereal = !this.ethereal && props.contains(BlockProperties.REVERSE_ETHEREAL);
        cullingTag = TagKey.create(Registries.BLOCK, new ResourceLocation(Glassential.MODID, "no_cull/" + cullId));
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType type) {
        return (this.reverseEthereal && type != PathComputationType.WATER) || super.isPathfindable(state, world, pos, type);
    }

    @Deprecated
    @Override
    public int getLightBlock(BlockState state, BlockGetter view, BlockPos pos) {
        return this.dark ? view.getMaxLightLevel() : super.getLightBlock(state, view, pos);
    }

    @Deprecated
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext context) {
        if (this.ethereal || this.reverseEthereal) {
            //need that absent check to trick some cached values, else ethereal glass pushes entities out
            return context != CollisionContext.empty() && context.isDescending() == this.reverseEthereal ? state.getShape(view, pos, context) : Shapes.empty();
        }
        return super.getCollisionShape(state, view, pos, context);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, BlockGetter view, List<Component> tooltip, TooltipFlag options) {
        for (BlockProperties property : properties) {
            tooltip.add(Component.translatable(property.getTranslationKey()).withStyle(property.getFormatting()));
        }
    }

    @Deprecated
    @Override
    public boolean isSignalSource(BlockState state) {
        return this.redstone || super.isSignalSource(state);
    }

    @Deprecated
    @Override
    public int getSignal(BlockState state, BlockGetter view, BlockPos pos, Direction direction) {
        return this.redstone ? 15 : super.getSignal(state, view, pos, direction);
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.is(cullingTag) || super.skipRendering(state, stateFrom, direction);
    }
}
