package dev.upcraft.glassential.mixin;

import dev.upcraft.glassential.Glassential;
import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.TintedGlassBlock;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TintedGlassBlock.class)
public abstract class TintedGlassBlockMixin extends AbstractGlassBlock {

    private TintedGlassBlockMixin(Settings settings) {
        super(settings);
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.isIn(Glassential.TINTED_GLASS_NO_CULL) || super.isSideInvisible(state, stateFrom, direction);
    }
}
