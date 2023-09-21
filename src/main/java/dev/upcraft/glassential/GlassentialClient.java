package dev.upcraft.glassential;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class GlassentialClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), Glassential.ETHEREAL_GLASS, Glassential.REVERSE_ETHEREAL_GLASS, Glassential.GHOSTLY_GLASS, Glassential.LIGHT_GLASS, Glassential.REDSTONE_GLASS);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(), Glassential.TINTED_ETHEREAL_GLASS, Glassential.TINTED_REVERSE_ETHEREAL_GLASS);
    }
}
