package dev.upcraft.glassential.mixin;

import dev.upcraft.glassential.Glassential;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.TintedGlassBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TintedGlassBlock.class)
public abstract class TintedGlassBlockMixin extends AbstractGlassBlock {

    private TintedGlassBlockMixin(Properties settings) {
        super(settings);
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.is(Glassential.TINTED_GLASS_NO_CULL) || super.skipRendering(state, stateFrom, direction);
    }
}
