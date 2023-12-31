package dev.upcraft.glassential;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class GlassentialClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), Glassential.ETHEREAL_GLASS, Glassential.REVERSE_ETHEREAL_GLASS, Glassential.GHOSTLY_GLASS, Glassential.LIGHT_GLASS, Glassential.REDSTONE_GLASS);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.translucent(), Glassential.TINTED_ETHEREAL_GLASS, Glassential.TINTED_REVERSE_ETHEREAL_GLASS);
    }
}
